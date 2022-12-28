# Desafio-Shadow-5
Desafios-Shadow: Micros serviço usuário: (Arquitetura de camadas)

# Requisitos:

Microsserviço usuário: (Arquitetura de camadas)

> Cadastro de novos usuários

> Cadastro de senha encriptada, BCrypt

> Validação de CPF (11111111111), https://dicasdeprogramacao.com.br/algoritmo-para-validar-cpf/

> CPF deve ser único no banco de dados

> Campos para cadastro: Código (id auto incremento), nome, CPF, email, senha, tipo usuário (Cliente, Fornecedor ou Admin)

> Algoritmo para Validar CPF: https://dicasdeprogramacao.com.br/algoritmo-para-validar-cpf/

# Idealização:

Iremos construir um sistema de estoque composto por três microsserviços.

Primeiramente deve-se construir a aplicação de usuários que será responsável por armazenar, cadastrar e gerenciar tipos de usuários.

A aplicação de produtos deverá ser capaz de cadastrar, excluir, editar e consultar.

O serviço de autenticação será responsável por prover tokens de acesso que possua dados relacionados as ROLES dos usuários (Admin, Cliente, Fornecedor) seguindo o padrão JWT.

# Requisitos Técnicos:

> Java com Spring Boot

> Banco de dados MySQL


# Erros a serem tratados:

> PUT | Ao atualizar a senha não fica criptografada






