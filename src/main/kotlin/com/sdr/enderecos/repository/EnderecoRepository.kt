package com.sdr.enderecos.repository

import com.sdr.enderecos.entity.Endereco
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.rest.core.annotation.RestResource

@RepositoryRestResource(path = "enderecos")
interface EnderecoRepository : JpaRepository<Endereco, Long> {

  @RestResource(path = "porUsuarioId")
  fun findAllByUsuarioId(@Param("id") usuarioId: Long): List<Endereco>

  @RestResource(path = "contarPorUsuarioCodigo")
  fun countByUsuarioCodigo(@Param("codigo") usuarioCodigo: Long): Long

  @RestResource(path = "porUsuarioEBairro")
  fun findByUsuarioIdAndBairro(@Param("usuarioId") usuarioId: Long, @Param("bairro") bairro: String): List<Endereco>

  @RestResource(path = "/{usuarioId}/enderecos")
  fun findEnderecosByUsuarioId(@Param("usuarioId") usuarioId: Long): List<Endereco>
}