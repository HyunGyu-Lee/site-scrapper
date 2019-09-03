module.exports = {
	devServer: {
		proxy: {
			'/api': {
				target: 'http://localhost:8000',
				changeOrigin: true,
			}
		}
	},
	outputDir: 'target/dist',
	assetsDir: 'static'
};
