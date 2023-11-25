package com.sdr.usuarios.repository

import com.sdr.usuarios.entity.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.rest.core.annotation.RestResource

@RepositoryRestResource(path = "usuarios")
interface UsuarioRepository : JpaRepository<Usuario, Long> {

  @RestResource(path = "/porNome")
  fun findByNome(@Param("nome") nome: String?): List<Usuario?>

  @RestResource(path = "/contarPorCodigo")
  fun countByCodigo(@Param("codigo") codigo: Long?): Long

  @Query("SELECT u FROM usuario AS u WHERE u.email LIKE %:dominio%")
  @RestResource(path = "/porDominioEmail")
  fun findByDominioEmail(@Param("dominio") dominio: String?): List<Usuario?>
}