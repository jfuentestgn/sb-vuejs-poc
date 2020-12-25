package com.example.comunidades.comunidad;

import com.example.comunidadesapi.Comunidad;
import com.example.comunidadesapi.ComunidadNotFound;
import com.example.comunidadesapi.ComunidadOperations;
import com.example.comunidadesapi.CreateComunidadRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = { "http://localhost:8081" })
@RequestMapping("/comunidades")
public class ComunidadController implements ComunidadOperations {

    private final ComunidadRepository comunidadRepository;

    public ComunidadController(ComunidadRepository comunidadRepository) {
        this.comunidadRepository = comunidadRepository;
    }

    @Override
    public Comunidad getById(Long id) {
        return buildFromEntity(this.comunidadRepository.findById(id).orElseThrow(ComunidadNotFound::new));
    }

    @Override
    public List<Comunidad> listAll() {
        Iterable<ComunidadEntity> all = this.comunidadRepository.findAll();
        List<Comunidad> result = new ArrayList<>();
        all.forEach(entity -> {
            result.add(buildFromEntity(entity));
        });
        return result;
    }



    @Override
    public Comunidad create(CreateComunidadRequest createComunidadRequest) {
        ComunidadEntity entity = new ComunidadEntity();
        entity.setNombre(createComunidadRequest.getNombre());
        ComunidadEntity created = this.comunidadRepository.save(entity);
        return buildFromEntity(created);
    }

    @Override
    public Comunidad update(Long id, CreateComunidadRequest createComunidadRequest) {
        ComunidadEntity entity = this.comunidadRepository.findById(id).get();
        entity.setNombre(createComunidadRequest.getNombre());
        ComunidadEntity updated = this.comunidadRepository.save(entity);
        return buildFromEntity(updated);
    }

    @Override
    public void delete(Long id) {
        this.comunidadRepository.deleteById(id);
    }


    private Comunidad buildFromEntity(ComunidadEntity entity) {
        Comunidad c = new Comunidad();
        c.setId(entity.getId());
        c.setNombre(entity.getNombre());
        return c;
    }
}
