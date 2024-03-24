Aluna Alessandra Gonçalves

Pós Graduação Unipê
Disciplina de BackEnd

1 - Crie um crud para entidade Clube contendo as seguintes informações.
    Id: integer
    Nome : String
    Estado: String
    Email: String 
    Cnpj: String 
    DataCriacao : Date

2 - Validação de campos 
- Validar se o e-mail, CNPJ são válidos. 
- Não permitir a inclusão de campos em branco.
- Criar um custon validator que não permita incluir no nome do clube a lista de palavras ( Flamengo, Palmeiras, Fluminense, Botafogo)

3 - Criar uma integração com um serviço externo da escolha do time utilizando Feing. Pode ser criado um micro serviço novo em outra porta.
  - Adicionada a integração com a API de Football que retorna dados sobre times brasileiros de futebol.
  - Além da integração foram adicionadas 3 funcionalidades:
    - Listar todos os clubes brasileiros
    - Buscar um clube por nome
    - Cadastrar um clube na base conforme dados da Api de football

4 - Incluir dtos para todas as entidades anotadas com Entity, ou seja, nos controllers o que é respondido response para o usuário é sempre um 

5 - Criar uma chamada no repository de clube uma listagem que ordene pelo nome do clube de forma ascendente.
