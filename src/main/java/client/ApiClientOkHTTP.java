package client;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import utils.ConfigUtils;

public class ApiClientOkHTTP implements ApiClient{

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiClientOkHTTP.class);
    private final OkHttpClient httpClient;

    public ApiClientOkHTTP(OkHttpClient httpClient) {
        this.httpClient = httpClient.newBuilder()
                .connectTimeout(ConfigUtils.getApiTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(ConfigUtils.getApiTimeout(), TimeUnit.MILLISECONDS)
                .build();
    }

    @Override
    public String get(String endpoint) throws IOException {
        HttpUrl url = buildUrl(endpoint);

        LOGGER.info("GET {}", url);
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

    @Override
    public String post(String endpoint, String requestBody) throws IOException {
        String baseUrl = ConfigUtils.getBaseUrl();
        RequestBody requestBodyObject = RequestBody.create(MediaType.parse("application/json"), requestBody);

        Request request = new Request.Builder()
                .url(baseUrl + endpoint)
                .post(requestBodyObject)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

    @Override
    public String put(String endpoint, String requestBody) throws IOException {
        String baseUrl = ConfigUtils.getBaseUrl();
        RequestBody requestBodyObject = RequestBody.create(MediaType.parse("application/json"), requestBody);

        Request request = new Request.Builder()
                .url(baseUrl + endpoint)
                .put(requestBodyObject)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }


    private HttpUrl buildUrl(String endpoint) {
        String baseUrl = ConfigUtils.getBaseUrl();
        return HttpUrl.parse(baseUrl + endpoint);
    }
}
