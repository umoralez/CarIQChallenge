package client;

import java.io.IOException;

public interface ApiClient {
    String get(String endpoint) throws IOException;

    String post(String endpoint, String body) throws IOException;

    String put(String endpoint, String body) throws IOException;
}
