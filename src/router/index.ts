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

        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes: constantRouter
})

export default router