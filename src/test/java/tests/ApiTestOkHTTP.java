package tests;

import org.testng.annotations.Test;

import api.PetService;
import client.ApiClientOkHTTP;
import okhttp3.OkHttpClient;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import utils.ConfigUtils;
import utils.EndpointUtils;

public class ApiTestOkHTTP {
    @Test
    public void testGet() {
        ApiClientOkHTTP client = new ApiClientOkHTTP(new OkHttpClient());
        PetService petService = new PetService(client);

        try{
            String response = petService.getSoldPets();

            assertTrue(response.contains("\"id\":23688827"));
            assertTrue(response.contains("\"id\":23688827"));
            assertTrue(response.contains("\"name\":\"doggie\""));
            assertTrue(response.contains("\"status\":\"sold\""));

        }
        catch (Exception e){
            System.out.println("Exception: " + e);
        }
    }

    @Test
    public void testInvalidGet() {
        ApiClientOkHTTP client = new ApiClientOkHTTP(new OkHttpClient());

        try{
            String response = client.get(EndpointUtils.getEndpoint("pet.invalidFindByStatus"));

            assertFalse(response.contains("\"id\":23688827"));
            assertFalse(response.contains("\"id\":23688827"));
            assertFalse(response.contains("\"name\":\"doggie\""));
            assertFalse(response.contains("\"status\":\"sold\""));

        }
        catch (Exception e){
            System.out.println("Exception: " + e);
        }
    }
    @Test
    public void testPost() {
        ApiClientOkHTTP client = new ApiClientOkHTTP(new OkHttpClient());
        PetService petService = new PetService(client);

        try{
            String response = petService.postSoldPets(ConfigUtils.readJsonFile("src/main/resources/mappings/post.json"));

            assertTrue(response.contains("\"id\":9222968140497181000"));
            assertTrue(response.contains("\"name\":\"doggie\""));
            assertTrue(response.contains("\"status\":\"available\""));

        }
        catch (Exception e){
            System.out.println("Exception: " + e);
        }
    }
    @Test
    public void testPut() {
        ApiClientOkHTTP client = new ApiClientOkHTTP(new OkHttpClient());
        PetService petService = new PetService(client);

        try{
            String response = petService.putSoldPets(ConfigUtils.readJsonFile("src/main/resources/mappings/put.json"));

            assertTrue(response.contains("\"id\":9222968140497181000"));
            assertTrue(response.contains("\"name\":\"doggie\""));
            assertTrue(response.contains("\"status\":\"available\""));

        }
        catch (Exception e){
            System.out.println("Exception: " + e);
        }
    }
}
