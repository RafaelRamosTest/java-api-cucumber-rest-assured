@Activities
Feature: Listar Atividades
  Como um usuário da API FakeRest
  Quero buscar a listagem de atividades cadastradas
  Para validar a integridade e os dados retornados

  @Regressivo @Regressivo
  Scenario: Validar o cadastro de uma nova atividade com sucesso
    Given que eu envio os dados de uma nova atividade:
      | id | title                    | dueDate                  | completed |
      | 99 | Nova Atividade Automação | 2026-06-18T20:13:07.114Z | true      |
    Then a API deve retornar o status code 200
    And a resposta deve conter os mesmos dados enviados

  @Regressivo @Regressivo
  Scenario: Validar a listagem de todas as atividades com sucesso
    Given que eu solicito a listagem de todas as atividades
    Then a API deve retornar o status code 200
    And o contrato da resposta deve ser validado com sucesso
    And a lista de atividades não deve estar vazia
    And a primeira atividade deve conter os dados esperados