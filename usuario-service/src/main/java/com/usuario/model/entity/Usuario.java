package com.usuario.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn( name = "id_rol")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Rol roles;

    public static Usuario of(String nombre, String apellido, String email, String password, Rol roles) {
        return new Usuario(null, nombre, apellido, email, password, roles);
    }

    private Usuario(String nombre, String apellido, String email, String password, Rol roles) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}