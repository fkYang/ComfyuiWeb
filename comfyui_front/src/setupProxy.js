const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
  const proxy = createProxyMiddleware({
    target: 'http://127.0.0.1:8080',
    changeOrigin: true,
    pathRewrite: {'^/api': ''},
    // 详细日志配置
    on: {
      proxyReq: (proxyReq, req, res) => {
        console.log(`[Proxy] 请求代理路径: ${req.originalUrl}`);
        console.log(`[Proxy] 目标路径: ${proxyReq.path}`);
        console.log(`[Proxy] 请求头: ${JSON.stringify(req.headers, null, 2)}`);
      },
      proxyRes: (proxyRes, req, res) => {
        console.log(`[Proxy] 响应状态码: ${proxyRes.statusCode}`);
        console.log(`[Proxy] 响应头: ${JSON.stringify(proxyRes.headers, null, 2)}`);
      },
      error: (err, req, res) => {
        console.error(`[Proxy] 代理错误: ${err.message}`);
      }
    }
  });

  console.log('[Proxy] 代理中间件已创建');
  app.use('/api', proxy);
};