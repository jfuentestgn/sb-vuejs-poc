<template>
    <div id="home">
        <div class="jumbotron">
          <div class="col-sm-8 mx-auto">
            <h1>GesCom 1.0</h1>
            <p>..............</p>
            <p>
              <router-link class="btn btn-primary" to="/dashboard" role="button">Go!</router-link>
            </p>
          </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "Home"
};
</script>

<style scoped>
#home {
    text-align: center;
    margin-top: 5em;
}
</style>