import { createRouter, createWebHistory } from 'vue-router'
import Main from './Main.vue'

// Step1: Import your Components here.
// Project: import Something from './components/project/Something'
// Patent: import Something from './components/patent/Something'
// Paper: import Something from './components/paper/Something'
import PaperSearch from './components/paper/PaperSearch'


export const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            name: 'main',
            component: Main,
            children: [
                // Step2: Add your route here.
                // Format: {path: 'path/to/your/components', component: <Component Name>}
                { path: 'paper/search', component: PaperSearch },
            ],
        }
    ],
})