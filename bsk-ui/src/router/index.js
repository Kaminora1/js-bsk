import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    children: [
      {
        path: '/addemp',
        name: 'addemp',
        component: () => import('../views/emp/AddEmpView.vue')
      },
      {
        path: '/EmpList',
        name: 'EmpList',
        component: () => import('../views/emp/EmpList.vue')
      },
      {
        path: '/addcate',
        name: 'addcate',
        component: () => import('../views/cate/AddcateView.vue')
      },
      {
        path: '/CateList',
        name: 'CateList',
        component: () => import('../views/cate/CateList.vue')
      },
      {
        path: '/empCenter',
        name: 'empCenter',
        component: () => import('../views/emp/EmpCenterView.vue')
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
     component: () => import('../views/LoginView.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
