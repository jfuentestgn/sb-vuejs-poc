package com.example.comunidades.security.models;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public RoleEntity() {
    }

    public RoleEntity(ERole name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RoleEntity{");
        sb.append("id=").append(id);
        sb.append(", name=").append(name);
        sb.append('}');
        return sb.toString();
    }
}
