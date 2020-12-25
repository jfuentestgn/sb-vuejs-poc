package com.example.comunidadesapi;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "comunidad not found")
public class ComunidadNotFound extends RuntimeException {
}
