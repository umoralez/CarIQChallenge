package api;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import client.ApiClient;
import client.ApiClientOkHTTP;
import client.ApiClientRestAssured;
import utils.EndpointUtils;

public class PetService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PetService.class);
    private final ApiClient apiClient;

    public PetService(ApiClientOkHTTP apiClient) {
        this.apiClient = apiClient;
    }

    public PetService(ApiClientRestAssured apiClient) {
        this.apiClient = apiClient;
    }

    public String getSoldPets() throws IOException {
        LOGGER.info("Get sold pets");
        return apiClient.get(EndpointUtils.getEndpoint("pet.findByStatus"));
    }

    public String postSoldPets(String body) throws IOException {
        LOGGER.info("Post sold pets");
        return apiClient.post(EndpointUtils.getEndpoint("pet.post"), body);
    }

    public String putSoldPets(String body) throws IOException {
        LOGGER.info("Put sold pets");
        return apiClient.put(EndpointUtils.getEndpoint("pet.put"), body);
    }
}
