<template>
    <div id="main">
        <nav class="navbar navbar-expand navbar-dark bg-dark">
            <router-link to="/" class="navbar-brand">GesCom</router-link>
            <div class="navbar-nav mr-auto">
                <li class="nav-item">
                    <router-link to="/dashboard" class="nav-link">Dashboard</router-link>
                </li>
                <li class="nav-item">
                    <router-link to="/comunidades" class="nav-link">Comunidades</router-link>
                </li>
            </div>
            <div v-if="currentUser" class="navbar-nav ml-auto">
                <li class="nav-item">
                    <router-link to="/profile" class="nav-link">
                        {{ currentUser.username }} - {{ currentUser.email }}
                    </router-link>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href @click.prevent="logOut">
                        LogOut
                    </a>
                </li>
            </div>
        </nav>

        <div class="container-fluid mt-3">
            <router-view></router-view>
        </div>
    </div>
</template>

<script>

export default {
    name: "MainContainer",

    methods: {
        logOut() {
            this.$store.commit('userLoggedOut');
            this.$router.push('/');
        }
    },

    computed: {
        currentUser() {
            return this.$store.state.user;
        }
    }
};
</script>

<style scoped>
.userinfo {
    color: white;
}
</style>