<template>
    <div class="submit-form">
        <div v-if="!submitted">
            <div class="form-group">
                <label for="form-field-name">Nombre</label>
                <input type="text" class="form-control" id="form-field-name" required v-model="nombre" name="nombre" />
            </div>

            <button @click="saveComunidad" class="btn btn-success">Guardar</button>
        </div>
        <div v-else>
            <h4>You submitted successfully!</h4>
        </div>
        <div v-if="error">
            <h4>ERROR!</h4>
        </div>
    </div>
</template>

<script>

import ComunidadesService from '@/services/ComunidadesService';

export default {
    name: "comunidades-create",

    data() {
        return {
            nombre: "",
            submitted: false,
            error: false
        };
    },

    methods: {
        saveComunidad() {
            var data = {
                nombre: this.nombre
            };

            ComunidadesService.create(data).then(response => {
                console.log(response.data);
                this.submitted = true;
            }, err => {
                console.log(err);
                this.error = true;
            });
        }
    }
}
</script>

<style scoped>

</style>