package com.steps;

import com.client.activities.ActivitiesClient;
import com.model.activities.ActivityResponse;
// Importe as anotações em inglês agora
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class ActivitiesSteps {

    private final ActivitiesClient activitiesClient = new ActivitiesClient();
    private Response response;
    private List<ActivityResponse> activitiesList;
    private ActivityResponse requestBody;
    private ActivityResponse responseBody;

    @Given("que eu solicito a listagem de todas as atividades")
    public void queEuSolicitoAListagemDeTodasAsAtividades() {
        response = activitiesClient.getActivities();
        response.then().log().all();
    }

    @Then("a API deve retornar o status code {int}")
    public void aApiDeveRetornarOStatusCode(int statusCode) {
        Assertions.assertEquals(statusCode, response.getStatusCode(), "Status code divergente!");
    }

    @And("o contrato da resposta deve ser validado com sucesso")
    public void oContratoDaRespostaDeveSerValidadoComSucesso() {
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/activities-schema.json"));
    }

    @And("a lista de atividades não deve estar vazia")
    public void aListaDeAtividadesNaoDeveEstarVazia() {
        activitiesList = response.jsonPath().getList("", ActivityResponse.class);
        Assertions.assertFalse(activitiesList.isEmpty(), "A lista retornou vazia!");
    }

    @And("a primeira atividade deve conter os dados esperados")
    public void aPrimeiraAtividadeDeveConterOsDadosEsperados() {
        ActivityResponse firstActivity = activitiesList.get(0);
        Assertions.assertEquals(1, firstActivity.getId());
        Assertions.assertEquals("Activity 1", firstActivity.getTitle());
        Assertions.assertFalse(firstActivity.isCompleted());
    }

    @Given("que eu envio os dados de uma nova atividade:")
    public void queEuEnvioOsDadosDeUmaNovaAtividade(List<ActivityResponse> activities) {
        // O Cucumber converte a linha da tabela diretamente no objeto Java
        requestBody = activities.get(0);

        // Disparando o POST com o corpo mapeado da DataTable
        response = activitiesClient.postActivity(requestBody);
        response.then().log().all();
    }

    @And("a resposta deve conter os mesmos dados enviados")
    public void aRespostaDeveConterOsMesmosDadosEnviados() {
        // Desserializa a resposta em um objeto Java
        responseBody = response.as(ActivityResponse.class);

        // Compara se o que a API devolveu é igual ao que enviamos
        Assertions.assertEquals(requestBody.getId(), responseBody.getId(), "ID incorreto!");
        Assertions.assertEquals(requestBody.getTitle(), responseBody.getTitle(), "Title incorreto!");
        Assertions.assertEquals(requestBody.getDueDate(), responseBody.getDueDate(), "DueDate incorreta!");
        Assertions.assertEquals(requestBody.isCompleted(), responseBody.isCompleted(), "Status de completado incorreto!");
    }
}
