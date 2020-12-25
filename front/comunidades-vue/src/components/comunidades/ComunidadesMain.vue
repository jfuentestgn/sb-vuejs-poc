<template>
    <div id="wrapper">
        <div id="sidebar_comunidades" class="float-left">
            <h4>Acciones</h4>
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <router-link to="/comunidades" class="nav-link">Listar</router-link>
                    </li>
                    <li class="nav-item">
                         <router-link to="/comunidades/create" class="nav-link">Nueva...</router-link>
                    </li>
                </ul>
        </div>
        <div id="main_comunidades" class="">
            <router-view></router-view>
        </div>
    </div>

</template>

<script>
export default {
    
}
</script>

<style scoped>
#sidebar_comunidades {
    width: 190px;
}

#main_comunidades {
    margin-left: 192px;
}
</style>