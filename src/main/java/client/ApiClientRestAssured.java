package client;

import java.io.IOException;
import java.net.http.HttpClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import utils.ConfigUtils;


public class ApiClientRestAssured implements ApiClient{
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiClientRestAssured.class);
    public ApiClientRestAssured() {
        RestAssured.config = RestAssuredConfig.newConfig()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", ConfigUtils.getApiTimeout())
                        .setParam("http.socket.timeout", ConfigUtils.getApiTimeout()));
    }

    @Override
    public String get(String endpoint) {
        String baseUrl = ConfigUtils.getBaseUrl();
        LOGGER.info("GET {}", baseUrl + endpoint);
        Response response = RestAssured.get(baseUrl + endpoint);
        return response.getBody().asString();
    }

    @Override
    public String post(String endpoint, String requestBody) {
        String baseUrl = ConfigUtils.getBaseUrl();
        Response response = RestAssured.given()
                .body(requestBody)
                .post(baseUrl + endpoint);

        LOGGER.info("POST {}", baseUrl + endpoint);
        return response.getBody().asString();
    }

    @Override
    public String put(String endpoint, String requestBody) {
        String baseUrl = ConfigUtils.getBaseUrl();
        Response response = RestAssured.given()
                .body(requestBody)
                .put(baseUrl + endpoint);

        LOGGER.info("PUT {}", baseUrl + endpoint);
        return response.getBody().asString();

    }
}
