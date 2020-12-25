<template>
    <div id="main">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample08" aria-controls="navbarsExample08" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse justify-content-md-center" id="navbarsExample08">
        <ul class="navbar-nav">
          <li class="nav-item active">
            <router-link to="/home" class="nav-link">GesCom <span class="sr-only">(current)</span></router-link>
          </li>
          <li class="nav-item">
            <router-link to="/login" class="nav-link">Login</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/register" class="nav-link">Register</router-link>
          </li>
        </ul>
      </div>
    </nav>
    <main role="main">
        <div class="container-fluid mt-3">
            <router-view></router-view>
        </div>
    </main>
    </div>
</template>

<script>

export default {
    name: "PublicContainer",

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