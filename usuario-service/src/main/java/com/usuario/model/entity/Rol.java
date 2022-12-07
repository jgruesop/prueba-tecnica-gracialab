package com.usuario.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity @Table(name = "rol") @Getter @Setter @NoArgsConstructor
public class Rol implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer idRol;
    @Column(length = 50)
    private String nombre;

    @OneToMany(mappedBy ="roles", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Usuario> usuario;

    public Rol(String nombre) {
        this.nombre = nombre;
    }
}

