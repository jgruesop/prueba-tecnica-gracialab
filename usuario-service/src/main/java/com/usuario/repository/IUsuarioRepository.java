package com.usuario.repository;

import com.usuario.model.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends CrudRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);

    @Query(value = "SELECT u.email " +
            "FROM usuario u " +
            "WHERE u.email = ?1", nativeQuery = true)
    String buscarEmail(String email);

    @Query(value = "SELECT u.id, u.nombre, u.apellido, u.email, u.rol " +
            "FROM usuario u " +
            "WHERE u.id = ?1", nativeQuery = true)
    List<Usuario> buscarIdUsuario(int id);
}
