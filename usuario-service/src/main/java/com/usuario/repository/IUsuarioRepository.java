package com.usuario.repository;

import com.usuario.model.entity.Usuario;
import com.usuario.pojo.RespuestaLoginPojo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends CrudRepository<Usuario, Integer> {

    Usuario findByEmail(String email);

    @Query("SELECT u.idUsuario, u.email, u.password, r.nombre AS rol_nombre " +
            "FROM Usuario u " +
            "LEFT JOIN Rol r " +
            "ON r.idRol = u.roles " +
            "WHERE u.idUsuario = ?1")
    List<Usuario> findByIdUsuario(String id);
}
