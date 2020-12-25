import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        sidebarShow: 'responsive',
        sidebarMinimize: false,
        authenticated: false,
        accessToken: localStorage.getItem('accessToken') || null,
        user: null,
        sidebarActiveLink: ''
    },


    mutations: {
        userLoggedIn (state, userData) {
            console.log('userLoggedIn: ' + userData);
            var user = userData;
            var token = userData.accessToken;
            var tokenType = userData.tokenType;
            var authHeader = tokenType + ' ' + token;
            

            localStorage.setItem('authHeader', authHeader);
            state.user = user;
            state.authHeader = authHeader;
        },

        userLoggedOut (state) {
            state.user = null;
            state.authHeader = null;
        }
        
    }
})