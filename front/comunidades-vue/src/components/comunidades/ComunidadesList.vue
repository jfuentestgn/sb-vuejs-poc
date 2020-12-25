<template>
    <div>
        <h3>Comunidades</h3>
        <div>
            <table class="table">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Nombre</th>
                </tr>
            </thead>
            <tbody>
            <tr v-for="comunidad in comunidades" v-bind:key="comunidad.id">
                <td>{{comunidad.id}}</td>
                <td>{{comunidad.nombre}}</td>
                <td class="text-right">
                    <router-link :to="'/comunidades/' + comunidad.id + '/edit'">Edit</router-link>
                    <button class="btn btn-link" @click="deleteComunidad(comunidad.id)">Delete</button>
                </td>
            </tr>
            </tbody>
            </table>
        </div>
    </div>
</template>

<script>

import ComunidadesService from '@/services/ComunidadesService';

export default {
    name: 'ComunidadesList',

    data() {
        return {
            comunidades: []
        };
    },

    methods: {
        refreshComunidades() {
            ComunidadesService.getAll()
                .then(response => {
                    this.comunidades = response.data;
                });
        },

        deleteComunidad(id) {
            console.log('Delete: ' + id);
            ComunidadesService.delete(id).then(response => {
                console.log(response);
                this.refreshComunidades();
            });
        }
    },

    created() {
        this.refreshComunidades();
    }
};
</script>

<style>

</style>