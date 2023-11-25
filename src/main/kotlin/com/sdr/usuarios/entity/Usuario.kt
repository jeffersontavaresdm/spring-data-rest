package com.sdr.usuarios.entity

import com.sdr.enderecos.entity.Endereco
import javax.persistence.*

@Entity(name = "usuario")
data class Usuario(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null,

  val nome: String,
  val email: String? = null,
  val senha: String,
  val codigo: Long? = null,

  @OneToMany(
    mappedBy = "usuario",
    cascade = [CascadeType.ALL],
    orphanRemoval = true
  )
  val enderecos: List<Endereco> = mutableListOf()
) {

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Usuario

    return id == other.id
  }

  override fun hashCode(): Int {
    return id?.hashCode() ?: 0
  }

  override fun toString(): String {
    return "Usuario: id=$id, nome='$nome', email=$email, senha='$senha', codigo=$codigo"
  }
}