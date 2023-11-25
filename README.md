# Simplificando a Exposição de Dados com Spring Data REST

O desenvolvimento de aplicativos modernos muitas vezes envolve a criação de APIs RESTful para expor dados de maneira eficiente. O Spring Data REST é uma poderosa extensão do ecossistema Spring que simplifica consideravelmente a tarefa de criar serviços REST para operações CRUD (Create, Read, Update, Delete) em cima de repositórios Spring Data.

### O que é o Spring Data REST?
O Spring Data REST é uma extensão do Spring Data que elimina a necessidade de criar manualmente controladores REST para expor recursos de domínio. Com apenas algumas anotações e configurações mínimas, é possível expor repositórios Spring Data como serviços REST completos.

### Principais Características:
1. Exposição Automática de Repositórios:
   O Spring Data REST mapeia automaticamente os métodos de repositório para operações HTTP, permitindo que os clientes realizem operações CRUD simplesmente fazendo chamadas HTTP a URLs específicas.

2. Formato JSON HAL (Hypertext Application Language):
   O Spring Data REST utiliza o formato JSON HAL para representar recursos. Esse formato fornece links de hipertexto que facilitam a navegação entre recursos relacionados.

3. Habilitação com Anotações Simples:
   A funcionalidade do Spring Data REST pode ser habilitada com uma única anotação @RepositoryRestResource no repositório. Isso proporciona uma exposição REST completa sem a necessidade de escrever código adicional.

4. Customização Flexível:
   Apesar da automação, o Spring Data REST permite a customização de endpoints, consultas personalizadas, e até mesmo a aplicação de lógica de negócios específica por meio de anotações e configurações.

### Como Começar:
1. Adicione as Dependências ao Projeto:
   Inclua as dependências necessárias do Spring Data JPA e do Spring Data REST no seu projeto.
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-rest</artifactId>
</dependency>
```
2. Anote o Repositório com @RepositoryRestResource:
```
@RepositoryRestResource(path = "usuarios")
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}
```
3. Execute o Aplicativo:
   Inicie a aplicação Spring Boot. Agora, você pode explorar os seguintes endpoints gerados automaticamente pelo Spring Data REST:
- Recuperar Todos os Usuários: `GET http://localhost:8080/usuarios`
- Recuperar Usuário por ID: `GET http://localhost:8080/usuarios/{id}`
- Salvar Novo Usuário: `POST http://localhost:8080/usuarios`
    - Corpo da Requisição:
```
{
  "nome": "Nome do Usuário",
  "email": "usuario@email.com"
}
```
- Atualizar Usuário Existente: `PUT http://localhost:8080/usuarios/{id}`
    - Corpo da Requisição:
```
{
  "nome": "Novo Nome do Usuário",
  "email": "novo_email@email.com"
}
```
- Remover Usuário: `DELETE http://localhost:8080/usuarios/{id}`
4. Gerando Endpoints Customizados:
   O Spring Data REST oferece flexibilidade para customizar endpoints, permitindo que você atenda requisitos específicos da sua aplicação.
   Para criar endpoints customizados, você pode usar as anotações `@RestResource` e `@Query`. Vejamos um exemplo:
```
@RepositoryRestResource(path = "usuarios")
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
       
       @RestResource(path = "porNome")
       List<Usuario> findByNome(@Param("nome") String nome);
   
       @RestResource(path = "contarPorCodigo")
       Long countByCodigo(@Param("codigo") Long codigo);
   
       @Query("SELECT u FROM Usuario AS u WHERE u.email LIKE %:dominio%")
       @RestResource(path = "porDominioEmail")
       List<Usuario> findByDominioEmail(@Param("dominio") String dominio);
   }
```
Endpoints gerados:
- Metodo 1: Um endpoint customizado para recuperar usuários por nome.
    - GET: `http://localhost:8080/usuarios/search/porNome?nome=NomeDoUsuario`
- Metodo 2: Um endpoint customizado para contar usuários por tipo.
    - GET: `http://localhost:8080/usuarios/search/contarPorCodigo?codigo=codigoDoUsuario`
- Metodo 3: Um endpoint customizado usando JPQL para recuperar usuários cujos emails contenham um domínio específico.
    - GET: `http://localhost:8080/usuarios/search/porDominioEmail?dominio=dominio.com`

Estes são só alguns exemplos de endpoints gerados pelo Spring Data Rest. </br>
Se quiser, você pode fazer o clone do [projeto](https://github.com/jeffersontavaresdm/spring-data-rest.git) que fiz em Kotlin para testar mais.

#

### Conclusão:
O Spring Data REST é uma ferramenta valiosa para simplificar o desenvolvimento de APIs RESTful em aplicações baseadas em Spring. Ao eliminar a necessidade de criar controladores manualmente, ele permite que os desenvolvedores foquem mais na lógica de negócios e menos na configuração de endpoints. Essa abordagem orientada a convenções facilita a criação rápida e eficiente de serviços RESTful, acelerando o ciclo de desenvolvimento.

#