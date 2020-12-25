import http from "./http-common";


class AuthService {

    register(user) {
        return http.post('/auth/signup', {
            username: user.username,
            password: user.password,
            email: user.email
        }).then(response => {
            console.log(response.data);
            return response.data;
        });
    }


    login(user) {
        return http.post('/auth/signin', {
            username: user.username,
            password: user.password
        }).then(response => {
            console.log(response.data);
            return response.data;
        });
    }
}

export default new AuthService();