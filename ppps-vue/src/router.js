import { createRouter, createWebHistory } from 'vue-router'
import Main from './Main.vue'

import PaperSearch from './components/PaperSearch'


export const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            name: 'main',
            component: Main,
            children: [
                { path: 'paper/search', component: PaperSearch },
            ],
        }
    ],
})