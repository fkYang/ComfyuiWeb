import { useState, useEffect } from 'react';
import { searchFlows } from './api/flows';
import styles from './App.module.css';
import FlowDrawer from './components/FlowDrawer';


export default function App() {
  const [searchTerm, setSearchTerm] = useState('');
  const [flows, setFlows] = useState([]);
  const [loading, setLoading] = useState(false);

// 在App组件顶部
const [selectedFlow, setSelectedFlow] = useState(null);
const [drawerVisible, setDrawerVisible] = useState(false);
// 处理运行按钮点击
const handleRun = (flow) => {
    setSelectedFlow(flow);
    setDrawerVisible(true);
  };


  // 手动触发搜索
  const handleSearch = async () => {
    // if (!searchTerm.trim()) return;
    
    setLoading(true);
    try {
      const results = await searchFlows(searchTerm);
      setFlows(results);
    } catch (error) {
      console.error('搜索失败:', error);
      setFlows([]);
    } finally {
      setLoading(false);
    }
  };

  // 支持回车触发
  const handleKeyPress = (e) => {
    if (e.key === 'Enter') {
      handleSearch();
    }
  };

// 新增初始化查询
useEffect(() => {
    handleSearch(); // 直接复用搜索函数
  }, []); 


  // 修改后的渲染部分
return (
    <div className={styles.container}>
      <h1>Flow 搜索</h1>
      
      {/* 搜索框部分保持不变 */}
      <div className={styles.searchSection}>
        <div className={styles.searchGroup}>
          <input
            type="text"
            placeholder="输入流程名称搜索"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            onKeyPress={handleKeyPress}
            className={styles.searchInput}
          />
          <button 
            onClick={handleSearch}
            disabled={loading}
            className={styles.searchButton}
          >
            {loading ? '搜索中...' : '搜索'}
          </button>
        </div>
      </div>  

      {/* 表格展示区域 */}
      <div className={styles.tableContainer}>
        <table className={styles.flowTable}>
          <thead>
            <tr>
              <th className={styles.actionColumn}>操作</th>
              <th className={styles.nameColumn}>流程名称</th>
              <th className={styles.descColumn}>流程描述</th>
            </tr>
          </thead>
          <tbody>
            {flows.map(flow => (
              <tr key={flow.id} className={styles.tableRow}>
                
                <td className={styles.actionCell}>
                    <button 
                        onClick={() => handleRun(flow)}
                        className={styles.runButton}
                    >
                    运行
                    </button>
                </td>
                
                <td className={styles.nameCell}>{flow.flow_name}</td>
                <td className={styles.descCell}>
                  {flow.flow_desc || <span className={styles.noDesc}>暂无描述</span>}
                </td>
                
              </tr>
            ))}
          </tbody>
        </table>
        
        {/* 空状态提示 */}
        {!loading && flows.length === 0 && searchTerm && (
          <div className={styles.empty}>未找到匹配的流程</div>
        )}
      </div>

<FlowDrawer
      visible={drawerVisible}
      data={selectedFlow}
      onClose={() => setDrawerVisible(false)}
    />

      {drawerVisible && (
  <div 
    className={styles.mask}
    onClick={() => setDrawerVisible(false)}
  />
)}


    </div>
  );
}

//       {/* ce bian */}
//       <div className={`${styles.sidePanel} ${showSidePanel ? styles.active : ''}`}>
//         <div className={styles.panelContent}>
//           <button className={styles.closeButton} onClick={closePanel}>×</button>
//           {selectedFlow?.input_nodes?.length > 0 ? (
//             <div className={styles.nodeList}>
//               <h3>输入节点配置 - {selectedFlow.flow_name}</h3>
//               <table className={styles.nodeTable}>
//                 <thead>
//                   <tr>
//                     <th>参数描述</th>
//                     <th>类型</th>
//                     <th>替换标识</th>
//                   </tr>
//                 </thead>
//                 <tbody>
//                   {selectedFlow.input_nodes.map((node, index) => (
//                     <tr key={index}>
//                       <td>{node.desc}</td>
//                       <td>{node.type === 1 ? '图片' : '文本'}</td>
//                       <td><code>{node.replaced_str}</code></td>
//                     </tr>
//                   ))}
//                 </tbody>
//               </table>
//             </div>
//           ) : (
//             <div className={styles.emptyNodes}>无输入节点配置</div>
//           )}
//         </div>
//       </div>
      
//       {/* 遮罩层 */}
//       {showSidePanel && <div className={`${styles.overlay} ${styles.active}`} onClick={closePanel} />}

// //   return (
// //     <div className={styles.container}>
// //       <h1>Flow 搜索</h1>
// //       <div className={styles.searchSection}>
// //         <div className={styles.searchGroup}>
// //           <input
// //             type="text"
// //             placeholder="输入流程名称搜索"
// //             value={searchTerm}
// //             onChange={(e) => setSearchTerm(e.target.value)}
// //             onKeyPress={handleKeyPress}
// //             className={styles.searchInput}
// //           />
// //           <button 
// //             onClick={handleSearch}
// //             disabled={loading}
// //             className={styles.searchButton}
// //           >
// //             {loading ? '搜索中...' : '搜索'}
// //           </button>
// //         </div>
// //       </div>
      
// //       <div className={styles.flowList}>
// //         {flows.map(flow => (
// //           <div key={flow.id} className={styles.flowCard}>
// //             <h3>{flow.flow_name}</h3>
// //             {flow.flow_desc && <p>{flow.flow_desc}</p>}
// //           </div>
// //         ))}
// //         {!loading && flows.length === 0 && searchTerm && (
// //           <div className={styles.empty}>未找到相关流程</div>
// //         )}
// //       </div>
// //     </div>
// //   );
