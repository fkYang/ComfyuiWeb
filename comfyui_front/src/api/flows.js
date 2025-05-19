import axios from 'axios';

// 创建带基础路径的实例
export const apiClient = axios.create({
 baseURL: '/api' // 对应代理配置的标识
});

export const searchFlows = async (name) => {
  console.log('[DEBUG] 完整请求URL:', 
    apiClient.defaults.baseURL + '/comfyui/flow_search?name=' + encodeURIComponent(name)
  );

  try {
    const response = await apiClient.get('/comfyui/flow_search', {
      params: name ? { name } : {} // 空参数时不带name字段
    });
    // const response = await apiClient.get('/comfyui/flow_search', { // 注意这里的路径变化
    //   params: { name }
    // });
    return response.data.data || [];
  } catch (error) {
    console.error('Search failed:', error);
    return [];
  }
};

  // 新增文件上传函数
  export const uploadFile = async (file) => {
    const formData = new FormData();
    formData.append('file', file);
  
    try {
      const response = await apiClient.post('/comfyui/upload', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      });
      
      console.log('post:', response);

      if (response.data.status === 0) {
        return response.data;
      }
      throw new Error(response.data.message || '上传失败');
    } catch (error) {
      console.error('文件上传失败:', error);
      throw error;
    }
  };

  export const runFlow = async (flowId, inputMap) => {
    try {
      const response = await apiClient.post('/comfyui/run', {
        flow_id: flowId,
        input_map: inputMap
      });
      console.log('runFlow post:', response);
      return response.data;
    } catch (error) {
      console.error('运行失败:', error);
      throw error;
    }
  };
  export const getResult = async(recordId) => {
    try {
      const response = await apiClient.get(`/comfyui/run_result?recordId=${recordId}`);
      console.log('getResult get:', response);

      return response.data;
    } catch (error) {
      console.error('运行失败:', error);
      throw error;
    }
  }