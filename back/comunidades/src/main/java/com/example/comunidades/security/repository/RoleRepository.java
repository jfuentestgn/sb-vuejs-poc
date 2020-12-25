package com.example.comunidades.security.repository;

import com.example.comunidades.security.models.ERole;
import com.example.comunidades.security.models.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {

    Optional<RoleEntity> findByName(ERole name);
}
