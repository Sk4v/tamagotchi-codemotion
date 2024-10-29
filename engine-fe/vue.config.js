const { defineConfig } = require('@vue/cli-service');

module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: process.env.VUE_APP_PORT || 3000, 
    proxy: {
      '/domain': {
        target: process.env.VUE_APP_PROXY_TARGET || 'http://localhost:8080', 
        changeOrigin: true,
      },
    },
  },
});
