import http from "./http-common";


class ComunidadesService {
    
    getAll() {
        return http.get("/comunidades");
    }

    get(id) {
        return http.get(`/comunidades/${id}`);
    }

    create(data) {
        return http.post("/comunidades", data);
    }

    update(id, data) {
        return http.put(`/comunidades/${id}`, data);
    }

    delete(id) {
        return http.delete(`/comunidades/${id}`);
    }
}

export default new ComunidadesService();