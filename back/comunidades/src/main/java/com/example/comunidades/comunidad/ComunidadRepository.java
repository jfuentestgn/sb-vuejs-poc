package com.example.comunidades.comunidad;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComunidadRepository extends CrudRepository<ComunidadEntity,Long> {
}
