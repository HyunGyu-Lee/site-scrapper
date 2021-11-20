
module.exports = {
  transpileDependencies: [
    'vuetify'
  ],
	devServer: {
		proxy: {
			'/api': {
				target: `http://${process.env.VUE_APP_BACKEND_HOST}:${process.env.VUE_APP_BACKEND_PORT}`,
				changeOrigin: true,
			}
		}
	},
	outputDir: 'target/dist',
	assetsDir: 'static'
};
