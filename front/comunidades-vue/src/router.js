import Vue from "vue";
import Router from "vue-router";

// Containers
const MainContainer = () => import('@/components/containers/MainContainer')
const PublicContainer = () => import('@/components/containers/PublicContainer')

// Views
const Home = () => import('@/components/Home')
const Dashboard = () => import('@/components/Dashboard')
const UserProfile = () => import ('@/components/Profile');

const Login = () => import('@/components/auth/Login')
const Register = () => import('@/components/auth/Register')

// Comunidades
const ComunidadesMain = () => import('@/components/comunidades/ComunidadesMain')
const ComunidadesList = () => import('@/components/comunidades/ComunidadesList')
const ComunidadesEdit = () => import('@/components/comunidades/ComunidadesEdit')
const ComunidadesCreate = () => import('@/components/comunidades/ComunidadesCreate')

import store from './store'

Vue.use(Router);

const vueRouter = new Router({

    mode: "history",
    linkActiveClass: 'active',
    scrollBehavior: () => ({ y: 0 }),
    routes: configRoutes()
});

vueRouter.beforeEach((to, from, next) => {
    // redirect to login page if not logged in and trying to access a restricted page
    console.log('beforeEach: (to) = ', to.path);
    const publicPages = ['/', '/login', '/register', '/home'];
    const authRequired = !publicPages.includes(to.path);
    const user = store.state.user;
    console.log('beforeEach. User = ' + user);

    if (authRequired && !user) {
        console.log('beforeEach. Redirect to login');
        return next('/login');
    }
    console.log('beforeEach. NEXT!!');
    next();
});

function configRoutes () {
    return [
        {
            path: '/',
            redirect: '/home',
            name: 'PublicContainer',
            component: PublicContainer,

            children: [
                {
                    path: '/home',
                    component: Home
                },
                {
                    path: '/login',
                    name: 'loginPage',
                    component: Login
                },
                {
                    path: '/register',
                    name: 'registerPage',
                    component: Register
                }
            ]

        },

        {
            path: '/main',
            redirect: '/dashboard',
            name: 'Main',
            component: MainContainer,

            children: [
                {
                    path: '/dashboard',
                    component: Dashboard
                },
                {
                    path: '/profile',
                    component: UserProfile
                },
                {
                    path: '/comunidades',
                    //name: 'Comunidades',
                    component: ComunidadesMain,
                    children: [
                        {
                            path: '',
                            name: 'comunidadesList',
                            component: ComunidadesList
                        },
                        {
                            path: 'create',
                            name: 'comunidadesCreate',
                            component: ComunidadesCreate
                        },
                        /*
                        {
                            path: "/comunidades/:id/show",
                            name: "comunidades-detail",
                            component: () => import("./components/comunidades/ComunidadesDetail.vue")
                        },
                        */
                        {
                            path: ':id/edit',
                            name: 'comunidadesEdit',
                            component: ComunidadesEdit
                        }
                    ]
                }
            ]
        },




    ]
}

export default vueRouter;