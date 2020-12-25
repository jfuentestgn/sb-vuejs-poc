<template>
<div class="row">


  <!-- Register -->
  <div class="col-md-12">
      <div class="card card-container">
      <img
        id="profile-img"
        src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
        class="profile-img-card"
      />
      <form name="form" @submit.prevent="signUp">
        <div v-if="!successful">
          <div class="form-group">
            <label for="register-username">Username</label>
            <input
              v-model="register.username"
              type="text"
              class="form-control"
              name="register_username"
            />
          </div>
          <div class="form-group">
            <label for="register-email">Email</label>
            <input
              v-model="register.email"
              type="email"
              class="form-control"
              name="register_email"
            />
          </div>
          <div class="form-group">
            <label for="register-password">Password</label>
            <input
              v-model="register.password"
              type="password"
              class="form-control"
              name="register_password"
            />
          </div>
          <div class="form-group">
            <button class="btn btn-primary btn-block">Sign Up</button>
          </div>
        </div>
      </form>

      <div
        v-if="message"
        class="alert"
        :class="successful ? 'alert-success' : 'alert-danger'"
      >{{message}}</div>
    </div>
  </div>
</div>
</template>

<script>

import AuthService from '@/services/auth-service';

export default {
    name: "Register",

    data() {
        return {
            message: null,
            successful: false,

            register: {
                email: '',
                username: '',
                password: ''
            }
        };
    },

    methods: {
        signUp() {
            AuthService.register(
              this.register
              ).then(response => {
                console.log(response);
                this.successful = true;
                this.message = response.message;
            }).catch(error => {
                console.log(JSON.stringify(error));
                this.successful = false;
                if (error.response) {
                  this.message = error.response.data.message;
                } else {
                  this.message = error.message;
                }
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