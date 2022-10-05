import {createRouter, createWebHistory, RouteRecordRaw} from "vue-router";
import Layout from '../layout/index.vue'

export const constantRouter: Readonly<RouteRecordRaw[]> = [
    {
        path: '/redirect',
        component: Layout,
        children: [
            {
                path: '/redirect/:path(.*)',
                component: () => import('../views/redirect.vue')
            }
        ]
    },
    {
        path: '/login',
        component: () => import('../views/login.vue')
    },
    {
        path: '/register',
        component: () => import('../views/register.vue')
    },
    {
        path: '',
        component: Layout,
        redirect: '/index',
        children: [
            {
                path: '/index',
                component: () => import('../views/index.vue'),
                name: 'Index'
            },
            {
                path: '/myMusic',
                component: () => import('../views/myMusic.vue'),
                name: 'MyMusic'
            },
            {
                path: '/search',
                component: () => import('../views/search.vue'),
                name: 'Search'
            },
            {
                path: '/record',
                component: () => import('../views/record.vue'),
                name: 'Record'
            },
            {
                path: '/upload',
                component: () => import('../views/upload.vue'),
                name: 'Upload'
            },
            {
                path: '/settings',
                component: () => import('../views/settings.vue'),
                name: 'Settings'
            }
        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes: constantRouter
})

export default router