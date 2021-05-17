import { createApp } from 'vue'
import ElementPlus from 'element-plus';
import 'dayjs/locale/zh-cn'
import locale from 'element-plus/lib/locale/lang/zh-cn'
import 'element-plus/lib/theme-chalk/index.css';
import App from './App.vue'
import { router } from './router'

const app = createApp(App)
app.use(ElementPlus, {locale})
app.use(router)
app.mount('#app')