<template>
<div class="row">
  <!-- Login -->
  <div class="col-md-12">
    <div class="card card-container">
      <img
        id="profile-img"
        src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
        class="profile-img-card"
      />
      <form name="form" @submit.prevent="signIn">
        <div class="form-group">
          <label for="username">Username</label>
          <input
            v-model="login.username"
            type="text"
            class="form-control"
            name="username"
          />
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input
            v-model="login.password"
            type="password"
            class="form-control"
            name="password"
          />
        </div>
        <div class="form-group">
          <button class="btn btn-primary btn-block" :disabled="loading">
            <span v-show="loading" class="spinner-border spinner-border-sm"></span>
            <span>Login</span>
          </button>
        </div>
        <div class="form-group">
          <div v-if="message" class="alert alert-danger" role="alert">{{message}}</div>
        </div>
      </form>
    </div>
  </div>


</div>
</template>

<script>

import AuthService from '@/services/auth-service';

export default {
    name: "Login",

    data() {
        return {
            login: {
                username: '',
                password: ''
            },
            loading: false,

            message: null,

        };
    },

    methods: {
        signIn() {
            this.loading = true;
            console.log('signIn. username = ' + this.login.username);
            AuthService.login({
                username: this.login.username,
                password: this.login.password
            }).then(response => {
                console.log(response);
                this.$store.commit('userLoggedIn', response);
                console.log("User in state = " + this.$store.state.user);
                this.loading = false;
                this.$router.push('/dashboard');
            }).catch(error => {
              this.loading = false;
                console.log(error);
                this.message = error.message;
            });
        }
    }
};
</script>

<style scoped>
label {
  display: block;
  margin-top: 10px;
}
.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}
.card {
  background-color: #f7f7f7;
  padding: 20px 25px 30px;
  margin: 0 auto 25px;
  margin-top: 50px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}
.profile-img-card {
  width: 96px;
  height: 96px;
  margin: 0 auto 10px;
  display: block;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
}
</style>