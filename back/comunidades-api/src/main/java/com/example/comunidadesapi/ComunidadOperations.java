package com.example.comunidadesapi;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ComunidadOperations {

    @GetMapping("")
    public List<Comunidad> listAll();

    @GetMapping("/{id}")
    public Comunidad getById(@PathVariable Long id);

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Comunidad create(@RequestBody CreateComunidadRequest createComunidadRequest);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comunidad update(@PathVariable Long id, @RequestBody CreateComunidadRequest createComunidadRequest);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id);
}
