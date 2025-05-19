
import styles from './FlowDrawer.module.css';
import { useState, useEffect, useCallback, useRef } from 'react';
import { uploadFile, runFlow, getResult } from '../../api/flows';

import axios from 'axios';


const typeMapping = {
  1: '图片',
  0: '文本'
};


export default function FlowDrawer({ visible, data, onClose }) {
  const [drawerWidth, setDrawerWidth] = useState(400);
  const [isDragging, setIsDragging] = useState(false);

  // 新增输入值状态管理
  const [inputs, setInputs] = useState({});


  // 新增轮询相关状态
  const pollingRef = useRef(null);

  const [pollingInterval, setPollingInterval] = useState(null);
  const [runStatus, setRunStatus] = useState(null);
  const [resultUrls, setResultUrls] = useState([]);

  // 新增运行相关状态
  const [isRunning, setIsRunning] = useState(false);
  const [runResult, setRunResult] = useState(null);
  // 验证所有输入是否完成
  const validateInputs = useCallback(() => {
    return data?.input_nodes?.every(node => {
      const input = inputs[node.replaced_str];

      // 图片类型需要验证image_id存在
      if (node.type === 1) {
        return input?.status === 'done' && input.image_id;
      }

      // 文本类型需要验证非空
      return input?.trim().length > 0;
    });
  }, [data, inputs]);


  // 处理文本输入变化
  const handleInputChange = (key, value) => {
    setInputs(prev => ({
      ...prev,
      [key]: value
    }));
  };


  const handleMouseDown = (e) => {
    setIsDragging(true);
    e.preventDefault();
  };

  const handleMouseMove = useCallback((e) => {
    if (!isDragging) return;

    const newWidth = window.innerWidth - e.clientX;
    setDrawerWidth(Math.min(Math.max(newWidth, 300), 800)); // 限制宽度范围
  }, [isDragging]);

  const handleMouseUp = useCallback(() => {
    setIsDragging(false);
  }, []);

  useEffect(() => {
    document.addEventListener('mousemove', handleMouseMove);
    document.addEventListener('mouseup', handleMouseUp);
    return () => {
      document.removeEventListener('mousemove', handleMouseMove);
      document.removeEventListener('mouseup', handleMouseUp);
    };
  }, [handleMouseMove, handleMouseUp]);




  // 修改后的文件处理逻辑
  const handleFileUpload = async (key, file) => {
    if (!file) return;

    try {
      // 设置上传中状态
      setInputs(prev => ({
        ...prev,
        [key]: { status: 'uploading' }
      }));

      // 执行上传
      const result = await uploadFile(file);
      // console.log(`[handleFileUpload] uploadFile result : ${result.data.online_url}`);
      // console.log('uploadFile result:', result);

      // 更新状态为上传成功
      setInputs(prev => ({
        ...prev,
        [key]: {
          status: 'done',
          image_id: result.data.image_id,
          online_url: result.data.online_url,
          originalName: file.name
        }
      }));
    } catch (error) {
      // 更新为错误状态
      setInputs(prev => ({
        ...prev,
        [key]: {
          status: 'error',
          message: '上传失败，请重试'
        }
      }));
    }
  };

  // 轮询检查任务状态
  const startPolling = useCallback(async (recordId) => {
    console.log('[开始轮询] recordId:', recordId);
    if (pollingRef.current) clearInterval(pollingRef.current);

    pollingRef.current = setInterval(async () => {
      console.log('[轮询触发] 检查任务状态');
      try {
        const response = await getResult(recordId);
        console.log('[轮询响应]', response.data);

        switch (response.data.status) {
          case 1: // 完成
            clearInterval(pollingRef.current);
            setRunStatus('done');
            setResultUrls(response.data.urls);
            setIsRunning(false);
            break;
          case -1: // 失败
            clearInterval(pollingRef.current);
            setRunStatus('error');
            setIsRunning(false);
            break;
          default: // 进行中
            setRunStatus('processing');
        }
      } catch (error) {
        console.error('[轮询错误]', error);
        clearInterval(pollingRef.current);
        setRunStatus('error');
      }
    }, 5000);

    // 添加卸载清理
    return () => {
      if (pollingRef.current) clearInterval(pollingRef.current);
      // setIsRunning(false); // 轮询结束后重置状态
    };
  }, []);

  // 在useEffect中添加清理
  useEffect(() => {
    return () => {
      if (pollingRef.current) {
        console.log('[组件卸载] 清理定时器');
        clearInterval(pollingRef.current);
      }
    };
  }, []);


  // 提交运行请求
  const handleRun = async () => {
    if (!validateInputs() || isRunning) return;

    // 运行一直持续到回调函数完成
    setIsRunning(true);
    setRunStatus(null);
    setResultUrls([]);


    try {
      const params = {
        flow_id: data.id,
        input_map: Object.fromEntries(
          data.input_nodes.map(node => [
            node.replaced_str,
            node.type === 1
              ? inputs[node.replaced_str].image_id
              : inputs[node.replaced_str]
          ]))
      };

      const response = await runFlow(params.flow_id, params.input_map)
      // await apiClient.post('/comfyui/run', params);
      setRunResult(response.data);
      console.log('runFlow response:', response);
      console.log('runFlow response: status', response.status);

      if (response.status === 0) {
        // startPolling(response.data); // 开始轮询
        console.log('runFlow response: data', response.data);
        const cleanup = startPolling(response.data); // 假设返回结构为 { data: { recordId: "xxx" } }
        return cleanup; // 返回清理函数
      }

    } catch (error) {
      console.error('运行失败:', error);
      setRunResult({
        status: -1,
        message: error.response?.data?.message || '请求失败'
      });
    } finally {
      // setIsRunning(false);
    }
  };

  return (
    <div className={`${styles.drawer} ${visible ? styles.visible : ''}`}
      style={{ width: drawerWidth }}
    >
      <div className={styles.header}>
        <h3>{data?.flow_name}</h3>

        <div className={styles.footer}>
          <button
            className={styles.runButton}
            onClick={handleRun}
            disabled={!validateInputs() || isRunning}
          >
            {isRunning ? (
              <span className={styles.loading}>
                <span className={styles.spinner} /> 运行中...
              </span>
            ) : '立即运行'}
          </button>

          {runResult && (
            <div className={runResult.status === 0 ? styles.success : styles.error}>
              {runResult.message}
            </div>
          )}
        </div>

        <button onClick={onClose} className={styles.closeBtn}>×</button>
      </div>

    
      <div className={styles.description}>
        {/* <div className={styles.descLabel}>流程描述：</div> */}
        <div className={styles.descContent}>{data?.flow_desc}</div>
      </div>


      <div className={styles.scrollContainer}>
        <div className={styles.content}>
          <table className={styles.configTable}>
            <thead>
              <tr>
                <th className={styles.descCol}>参数描述</th>
                <th className={styles.typeCol}>输入类型</th>
                <th className={styles.valueCol}>输入参数</th>
              </tr>
            </thead>
            <tbody>
              {data?.input_nodes?.map((node, index) => (
                <tr key={index} className={styles.tableRow}>
                  <td className={styles.descCell}>{node.desc}</td>
                  <td className={styles.typeCell}>
                    <span className={node.type ? styles.imageType : styles.textType}>
                      {typeMapping[node.type] || '未知类型'}
                    </span>
                  </td>

                  <td className={styles.inputCell}>
                    {node.type === 1 ? (
                      // 图片上传区域
                      <div className={styles.uploadArea}>
                        <label className={styles.uploadButton}>
                          <input
                            type="file"
                            accept="image/*"
                            onChange={(e) => handleFileUpload(node.replaced_str, e.target.files?.[0])}
                            disabled={inputs[node.replaced_str]?.status === 'uploading'}
                          />
                          {getUploadButtonText(inputs[node.replaced_str])}
                        </label>
                        {renderUploadStatus(inputs[node.replaced_str])}
                      </div>
                    ) : (
                      // 文本输入框
                      <input
                        type="text"
                        className={styles.textInput}
                        value={inputs[node.replaced_str] || ''}
                        onChange={(e) => handleInputChange(node.replaced_str, e.target.value)}
                        placeholder={`请输入 ${node.desc}`}
                      />
                    )}
                  </td>


                </tr>
              ))}
            </tbody>
          </table>

          {/* // 在render函数中添加状态显示 */}
          <div className={styles.statusContainer}>
            {runStatus === 'processing' && (
              <div className={styles.processing}>
                <span className={styles.spinner} />
                任务执行中，预计需要一些时间...
              </div>
            )}

            {runStatus === 'done' && (
              <div className={styles.resultSection}>
                <h4>生成结果：</h4>
                <div className={styles.resultGrid}>
                  {resultUrls.map((url, index) => (
                    <div key={index} className={styles.resultItem}>
                      <img src={url} alt={`结果${index + 1}`} />
                      <a href={url} download className={styles.downloadBtn}>
                        下载
                      </a>
                    </div>
                  ))}
                </div>
              </div>
            )}

            {runStatus === 'error' && (
              <div className={styles.error}>
                ❌ 任务执行失败，请稍后重试
              </div>
            )}



          </div>


        </div>
      </div>

      {/* 支持可拖动 */}
      <div className={styles.dragHandle} onMouseDown={handleMouseDown} />

    </div>
  );
}


// 辅助函数：获取按钮文字
const getUploadButtonText = (input) => {
  if (!input) return '选择文件';
  if (input.status === 'uploading') return '上传中...';
  if (input.status === 'done') return '重新上传';
  return '选择文件';
};

// 辅助函数：渲染状态提示
const renderUploadStatus = (input) => {
  if (!input) return null;

  // console.log(`[renderUploadStatus] input: ${input.image_id}`);
  // console.log(`[renderUploadStatus] online_url: ${input.online_url}`);
  // console.log(`[renderUploadStatus] status: ${input.status}`);


  return (
    <div className={styles.statusContainer}>
      {input.status === 'done' && (
        <>
          <div className={styles.previewRow}>
            <span className={styles.successText}>已上传 </span>
            <a
              href={input.online_url}
              target="_blank"
              rel="noopener noreferrer"
              className={styles.link}
            >
              查看原图
            </a>
          </div>
          <img
            src={input.online_url}
            alt="预览"
            className={styles.previewImage}
            style={{
              maxWidth: '100%',
              maxHeight: '300px', // 设置最大高度
              objectFit: 'contain'
            }}
          />
        </>
      )}
      {input.status === 'error' && (
        <span className={styles.error}>{input.message}</span>
      )}
      {input.status === 'uploading' && (
        <span className={styles.uploading}>上传中...</span>
      )}
    </div>
  );
};