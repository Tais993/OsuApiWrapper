package Osu;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Osu {
    private final String token;

    public Osu(String token) {
        this.token = token;
    }
}
