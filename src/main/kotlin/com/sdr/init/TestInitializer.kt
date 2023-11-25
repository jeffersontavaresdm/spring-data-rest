package com.sdr.init

import com.sdr.enderecos.entity.Endereco
import com.sdr.enderecos.repository.EnderecoRepository
import com.sdr.usuarios.entity.Usuario
import com.sdr.usuarios.repository.UsuarioRepository
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class TestInitializer(
  val usuarioRepository: UsuarioRepository,
  val enderecoRepository: EnderecoRepository
) {

  @PostConstruct
  fun init() {
    val usuario1 = Usuario(nome = "Jesus", email = "jesus@gmail.com", senha = "123", codigo = 123)
    val usuario2 = Usuario(nome = "Maria", email = "maria@hotmail.com", senha = "456", codigo = 456)
    val usuario3 = Usuario(nome = "José", email = "jose@yahoo.com", senha = "789", codigo = 789)

    usuarioRepository.saveAll(listOf(usuario1, usuario2, usuario3))

    val endereco1 = Endereco(logradouro = "Rua A", bairro = "Bairro A", cep = 12345, usuario = usuario1)
    val endereco2 = Endereco(logradouro = "Rua B", bairro = "Bairro B", cep = 67890, usuario = usuario2)
    val endereco3 = Endereco(logradouro = "Rua C", bairro = "Bairro C", cep = 54321, usuario = usuario1)

    enderecoRepository.saveAll(listOf(endereco1, endereco2, endereco3))
  }
}