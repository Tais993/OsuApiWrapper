package v1.handler;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiV1Handler {
    private final String token;
    protected final static String startUrl = "https://osu.ppy.sh/api/";

    public ApiV1Handler(String token) {
        this.token = token;
    }

    public void getBeatmap(Long beatmapId) {

    }

    protected static void getJson(String urlString) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .GET()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }
}
