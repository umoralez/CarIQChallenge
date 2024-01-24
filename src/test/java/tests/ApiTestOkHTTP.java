package tests;

import java.io.IOException;

import org.testng.annotations.Test;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ApiTestOkHTTP {
    @Test
    public void testFindByStatusSoldWithOkHttp() throws IOException {
        String baseUrl = "http://localhost:8080";
        String endpoint = "/pet/findByStatus?status=sold";
        String fullUrl = baseUrl + endpoint;

        OkHttpClient client = new OkHttpClient();

        try{
            Request request = new Request.Builder()
                    .url(fullUrl)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            System.out.println("Response Body: " + response.body().string());

            assertEquals(200, response.code());

            assertTrue(response.header("Content-Type").contains("application/json"));

            String responseBody = response.body().string();
            assertTrue(responseBody.contains("\"id\":23688827"));
            assertTrue(responseBody.contains("\"name\":\"doggie\""));
            assertTrue(responseBody.contains("\"status\":\"sold\""));
        }
        catch (Exception e){
            System.out.println("Exception: " + e);
        }
    }
}
