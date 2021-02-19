package v1;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import v1.Futures.FutureImpl;
import v1.Futures.SetFutureImpl;
import v1.entities.beatmap.Beatmap;
import v1.entities.beatmap.BeatmapRequestBuilder;
import v1.entities.bestperformance.BestPerformance;
import v1.entities.bestperformance.BestPerformanceRequestBuilder;
import v1.entities.multiplayer.Match;
import v1.entities.multiplayer.MatchRequestBuilder;
import v1.entities.scores.Score;
import v1.entities.scores.ScoresRequestBuilder;
import v1.entities.user.User;
import v1.entities.user.UserRequestBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class ApiV1Handler {
    private final String key;
    public final static String startUrl = "https://osu.ppy.sh/api/";
    private final ArrayBlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(100);
    private final ExecutorService executorService = new ThreadPoolExecutor(1, 10, 100L, TimeUnit.SECONDS, blockingQueue);

    public ApiV1Handler(String key) {
        this.key = key;
    }

    public FutureImpl<Beatmap> retrieveBeatmap(BeatmapRequestBuilder beatmapRequestBuilder) {
        Callable<Beatmap> callable = () -> {
            beatmapRequestBuilder.setKey(key);
            System.out.println(beatmapRequestBuilder.getUrl());

            return new Beatmap(getJsonArray(beatmapRequestBuilder.getUrl()).get(0).getAsJsonObject());
        };

        return new FutureImpl<>(callable, executorService);
    }

    public SetFutureImpl<Beatmap, Set<Beatmap>> retrieveBeatmaps(BeatmapRequestBuilder beatmapRequestBuilder) {
        Callable<Set<Beatmap>> callable = () -> {
            Set<Beatmap> beatmaps = new HashSet<>();

            beatmapRequestBuilder.setKey(key);
            JsonArray json = getJsonArray(beatmapRequestBuilder.getUrl());

            json.forEach(jsonElement -> beatmaps.add(new Beatmap(jsonElement.getAsJsonObject())));

            return beatmaps;
        };

        return new SetFutureImpl<>(callable, executorService);
    }

    public FutureImpl<User> retrieveUser(UserRequestBuilder userRequestBuilder) {
        Callable<User> callable = ()  -> {

            userRequestBuilder.setKey(key);
            System.out.println(userRequestBuilder.getUrl());

            return new User(getJsonArray(userRequestBuilder.getUrl()).get(0).getAsJsonObject());
        };

        return new FutureImpl<>(callable, executorService);
    }

    public SetFutureImpl<Score, Set<Score>> retrieveScores(ScoresRequestBuilder scoresRequestBuilder) {
        Callable<Set<Score>> callable = () -> {
            Set<Score> scores = new HashSet<>();

            scoresRequestBuilder.setKey(key);

            JsonArray json = getJsonArray(scoresRequestBuilder.getUrl());

            json.forEach(jsonElement -> scores.add(new Score(jsonElement.getAsJsonObject())));

            return scores;
        };

        return new SetFutureImpl<>(callable, executorService);
    }

    public SetFutureImpl<BestPerformance, Set<BestPerformance>> retrieveBestPerformance(BestPerformanceRequestBuilder bestPerformanceRequestBuilder) {
        Callable<Set<BestPerformance>> callable = () -> {
            Set<BestPerformance> bestPerformances = new HashSet<>();

            bestPerformanceRequestBuilder.setKey(key);

            JsonArray json = getJsonArray(bestPerformanceRequestBuilder.getUrl());

            System.out.println(json);

            json.forEach(jsonElement -> bestPerformances.add(new BestPerformance(jsonElement.getAsJsonObject())));

            return bestPerformances;
        };

        return new SetFutureImpl<>(callable, executorService);
    }

    public FutureImpl<Match> retrieveMatchInfo(MatchRequestBuilder matchRequestBuilder) {
        Callable<Match> callable = ()  -> {

            matchRequestBuilder.setKey(key);
            System.out.println(matchRequestBuilder.getUrl());

            return new Match(getJsonObject(matchRequestBuilder.getUrl()));
        };

        return new FutureImpl<>(callable, executorService);
    }

//    public FutureImpl<Replay> retrieveReplay(ReplayRequestBuilder replayRequestBuilder) {
//        Callable<Replay> callable = ()  -> {
//
//            replayRequestBuilder.setKey(key);
//            System.out.println(replayRequestBuilder.getUrl());
//
//            return new Replay(getJsonObject(replayRequestBuilder.getUrl()));
//        };
//
//        return new FutureImpl<>(callable, executorService);
//    }

    protected static JsonObject getJsonObject(String urlString) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .GET()
                .build();

        String jsonString = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        return new Gson().fromJson(jsonString, JsonObject.class);
    }

    protected static JsonArray getJsonArray(String urlString) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .GET()
                .build();

        String jsonString = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        return new Gson().fromJson(jsonString, JsonArray.class);
    }

    protected static JsonElement getJsonElement(String urlString) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .GET()
                .build();

        String jsonString = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        return new Gson().fromJson(jsonString, JsonElement.class);
    }
}
