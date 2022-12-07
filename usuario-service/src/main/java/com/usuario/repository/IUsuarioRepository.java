package com.usuario.repository;

import com.usuario.model.entity.Usuario;
import com.usuario.pojo.RespuestaUsuarioPojo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends CrudRepository<Usuario, Integer> {

    Usuario findByEmail(String email);

    @Query(value = "SELECT u.email " +
            "FROM usuario u " +
            "WHERE u.email = ?1", nativeQuery = true)
    String buscarEmail(String email);

    @Query(value = "SELECT u.id_usuario, u.nombre, u.apellido, u.email, u.password, r.nombre AS rol_nombre " +
            "FROM usuario u " +
            "LEFT JOIN rol r " +
            "ON r.id_rol = u.roles_id_rol " +
            "WHERE u.id_usuario = :id", nativeQuery = true)
    Usuario buscarIdUsuario(@Param("id") String id);
}
