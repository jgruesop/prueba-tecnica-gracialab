package com.usuario.repository;

import com.usuario.model.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUsuarioRepository extends CrudRepository<Usuario, Integer> {

    Usuario findByEmail(String email);

    @Query(value = "SELECT u.id_usuario, u.nombre, u.apellido, u.email, u.password, u.roles_id_rol\n" +
            "FROM usuario u\n" +
            "LEFT JOIN rol r\n" +
            "ON r.id_rol = u.roles_id_rol\n" +
            "WHERE u.id_usuario = :id\n", nativeQuery = true)
    List<Usuario> findByIdUsuario(@Param("id") String id);
}
