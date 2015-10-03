@@@ AVALIAÇÂO @@@


@ Activitys:

1. Listagem dos itens.

2. Cadastro de itens:

Na Activity de formulário, foram feitos os campos para inserir nome, emails, telefones, rede sociais, cep, rua, cidade, bairro, etc..


O usuário ao digitar um email, telefone ou uma  rede social, o tem a opção do botão de adicionar, estes valores ficavam armazenados em uma respectiva lista, caso fosse um email, iria para lista de email, caso telefone, iria para a de telefone, caso fosse rede social, iria para a lista de rede social. Estas listas que no caso, eram atributos do CONTATO.

O usuário ao inserir um cep, tinha a opção de clicar no botão de procurar, ao clicar neste botão, fazia a chamada para o webservice, passando o cep, e retornava para os campos do formulário os atributos do cep informado.

O usuário ao clicar em salvar(CONTATO). O sistema salva o contato no banco de dados(pegando os atributos da classe contato e os atributos da classe address(webservice), após adicionar o contato no banco de dados, era retornado o ID do contato, com este ID, o sistema adicionava no banco, os objetos contidos nas listas(Emails, telefones e redes sociais), com o respectivo ID.

@ Tabelas do BD

Contato: 

Contem ID, nome, e os atributos que eram pegos no web service, como cidade, bairro, etc.

Email: 

Contem ID, UserName, ID do contato.

Telefone: 

Contem o  ID, numero e o ID do contato.

Rede social: 

Contem o ID, link e o ID do contato.


@ Observações

1. CRUD funcionando perfeitamente.
2. WEBSERVICE funcionando perfeitamente.
3. ADD de contato, com mais de um email, telefone ou rede social, está funcionando perfeitamente.
4. Edição dos contatos, dava erro ao retornar todos valores(Estava arrumando, mas não deu tempo de testar se deu certo).
5. Filtro de contatos, consegui fazer os métodos(pode reparar que contém os metodos feitos), mas não deu tempo de implementar eles na activity, porém é simples fazer isso, é os mesmos principios que usei no LOGIN de outras aplicações, utilizei o mesmo no meu tcc e funcionou.


*/ Arquivo editado após as 15 horas, para melhor explicação, pois não deu tempo de escrever tudo detalhado na sala de aula, porém o commit do projeto, pode-se reparar que é no horario estabelecido (até as 15hs - 03/10). /*


