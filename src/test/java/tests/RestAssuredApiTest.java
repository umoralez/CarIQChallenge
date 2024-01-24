package tests;

import java.io.IOException;

import org.testng.annotations.Test;

import api.PetService;
import client.ApiClientRestAssured;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import utils.ConfigUtils;
import utils.EndpointUtils;

public class RestAssuredApiTest {

    ApiClientRestAssured client = new ApiClientRestAssured();
    PetService petService = new PetService(client);
    @Test(description = "Get all sold pets")
    public void getTest() throws IOException {
        String response = petService.getSoldPets();

        assertTrue(response.contains("\"id\":23688827"));
        assertTrue(response.contains("\"name\":\"doggie\""));
        assertTrue(response.contains("\"status\":\"sold\""));
    }

    @Test(description = "Test invalid get all sold pets")
    public void getInvalidTest() throws IOException {
        String response = client.get(EndpointUtils.getEndpoint("pet.invalidFindByStatus"));

        assertFalse(response.contains("\"id\":23688827"));
        assertFalse(response.contains("\"name\":\"doggie\""));
        assertFalse(response.contains("\"status\":\"sold\""));
    }

    @Test(description = "Post a pet as sold")
    public void postTest() throws IOException {
        String response = petService.postSoldPets(ConfigUtils.readJsonFile("src/main/resources/mappings/post.json"));

        assertTrue(response.contains("\"id\":9222968140497181000"));
        assertTrue(response.contains("\"name\":\"doggie\""));
        assertTrue(response.contains("\"status\":\"available\""));
    }

    @Test(description = "Put a pet as sold")
    public void putTest() throws IOException {
        String response = petService.putSoldPets(ConfigUtils.readJsonFile("src/main/resources/mappings/put.json"));

        assertTrue(response.contains("\"id\":9222968140497181000"));
        assertTrue(response.contains("\"name\":\"doggie\""));
        assertTrue(response.contains("\"status\":\"available\""));
    }
}
