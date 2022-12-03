package com.usuario.model.dao;

import com.usuario.model.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUsuarioDao extends CrudRepository<Usuario, Integer> {

    @Query(value = "SELECT email " +
            "FROM usuario u " +
            "WHERE u.email = ?1", nativeQuery = true)
    String buscarEmail(String email);

    @Query(value = "SELECT id, nombre, apellido, email, rol " +
            "FROM usuario u " +
            "WHERE p.id = ?1", nativeQuery = true)
    List<Usuario> buscarIdUsuario(int id);

}