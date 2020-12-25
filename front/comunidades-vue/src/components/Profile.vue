<template>
    <div>
        <h3>User Profile</h3>
        <dl class="row">

            <dt class="col-sm-2">User name:</dt>
            <dd class="col-sm-10">{{ currentUser.username }}</dd>

            <dt class="col-sm-2">User email:</dt>
            <dd class="col-sm-10">{{ currentUser.email }}</dd>

            <dt class="col-sm-2">User roles:</dt>
            <dd class="col-sm-10">
                <ul>
                    <li v-for="r in currentUser.roles" v-bind:key="r">{{ r }}</li>
                </ul>
            </dd>

        </dl>
    </div>
</template>

<script>
export default {
    name: "UserProfileContainer",

    computed: {
        currentUser() {
            return this.$store.state.user;
        }
    }
}
</script>