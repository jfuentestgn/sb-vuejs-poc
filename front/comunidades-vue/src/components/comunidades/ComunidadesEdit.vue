<template>
    <div class="submit-form">
        <div v-if="!submitted">
            <div class="form-group">
                <label for="form-field-name">Nombre</label>
                <input type="text" class="form-control" id="form-field-name" required v-model="comunidad.nombre" name="nombre" />
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
import ComunidadesService from '../../services/ComunidadesService';

export default {
    name: "comunidades-edit",

    data() {
        return {
            id: null,
            comunidad: {},
            submitted: false,
            error: false
        };
    },

    methods: {
        saveComunidad() {
            ComunidadesService.update(this.id, this.comunidad).then(response => {
                console.log(response);
                this.$router.push({ name: 'comunidadesList' });
            })
        }
    },

    created() {
        this.id = this.$route.params.id;
        ComunidadesService.get(this.id).then(response => {
            this.comunidad = response.data;
        });
    }
}

</script>

<style scoped>

</style>