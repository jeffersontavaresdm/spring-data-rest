package com.sdr.enderecos.entity

import com.sdr.usuarios.entity.Usuario
import javax.persistence.*

@Entity(name = "endereco")
data class Endereco(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,

  val logradouro: String,
  val bairro: String? = null,
  val cep: Long? = null,

  @ManyToOne
  @JoinColumn(name = "usuario_id")
  val usuario: Usuario
) {

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Endereco

    return id == other.id
  }

  override fun hashCode(): Int {
    return id?.hashCode() ?: 0
  }

  override fun toString(): String {
    return "Endereco: id=$id, logradouro='$logradouro', bairro=$bairro, cep=$cep"
  }
}