package v1;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import futures.FutureImpl;
import futures.FutureListImpl;
import osu.OsuSettings;
import v1.entities.beatmap.BeatmapRequestBuilderV1;
import v1.entities.beatmap.BeatmapV1;
import v1.entities.bestperformance.BestPerformanceRequestBuilderV1;
import v1.entities.bestperformance.BestPerformanceV1;
import v1.entities.multiplayer.MatchRequestBuilderV1;
import v1.entities.multiplayer.MatchV1;
import v1.entities.replay.ReplayRequestBuilderV1;
import v1.entities.replay.ReplayV1;
import v1.entities.scores.ScoreV1;
import v1.entities.scores.ScoresRequestBuilderV1;
import v1.entities.user.UserRequestBuilderV1;
import v1.entities.user.UserV1;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

public class ApiV1Handler {
    private final String key;
    public final static String startUrl = "https://osu.ppy.sh/api/";
    private final ArrayBlockingQueue<Runnable> blockingQueue;
    private final ExecutorService executorService;

    public ApiV1Handler(OsuSettings osuS) {
        this.key = osuS.getKeyV1();

        if (osuS.useSeparateQueues()) {
            this.blockingQueue = osuS.getV1Queue();
            this.executorService = osuS.getV1Executor();
        } else {
            this.blockingQueue = osuS.getBlockingQueue();
            this.executorService = osuS.getExecutorService();
        }
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public FutureImpl<BeatmapV1> retrieveBeatmap(BeatmapRequestBuilderV1 beatmapV1RequestBuilder) {
        Callable<BeatmapV1> callable = () -> {
            beatmapV1RequestBuilder.setKey(key);
            System.out.println(beatmapV1RequestBuilder.getUrl());

            return new BeatmapV1(getJsonArray(beatmapV1RequestBuilder.getUrl()).get(0).getAsJsonObject());
        };

        return new FutureImpl<>(callable, this);
    }

    public FutureListImpl<BeatmapV1, List<BeatmapV1>> retrieveBeatmaps(BeatmapRequestBuilderV1 beatmapV1RequestBuilder) {
        Callable<List<BeatmapV1>> callable = () -> {
            List<BeatmapV1> beatmapV1s = new ArrayList<>();

            beatmapV1RequestBuilder.setKey(key);
            JsonArray json = getJsonArray(beatmapV1RequestBuilder.getUrl());

            json.forEach(jsonElement -> beatmapV1s.add(new BeatmapV1(jsonElement.getAsJsonObject())));

            return beatmapV1s;
        };

        return new FutureListImpl<>(callable, this);
    }


    public FutureImpl<UserV1> retrieveUser(UserRequestBuilderV1 userV1RequestBuilder) {
        Callable<UserV1> callable = ()  -> {

            userV1RequestBuilder.setKey(key);
            System.out.println(userV1RequestBuilder.getUrl());

            return new UserV1(getJsonArray(userV1RequestBuilder.getUrl()).get(0).getAsJsonObject());
        };

        return new FutureImpl<>(callable, this);
    }

    public FutureListImpl<UserV1, List<UserV1>> retrieveUsers(List<UserRequestBuilderV1> userRequestBuildersV1) {
        Callable<List<UserV1>> callable = ()  -> {

            userRequestBuildersV1.forEach(userRequestBuilderV1 -> userRequestBuilderV1.setKey(key));

            List<UserV1> users = new ArrayList<>();

            for (UserRequestBuilderV1 userRequestBuilderV1 : userRequestBuildersV1) {
                users.add(new UserV1(getJsonArray(userRequestBuilderV1.getUrl()).get(0).getAsJsonObject()));
            }

            return users;
        };

        return new FutureListImpl<>(callable, this);
    }


    public FutureListImpl<ScoreV1, List<ScoreV1>> retrieveScores(ScoresRequestBuilderV1 scoresV1RequestBuilder) {
        Callable<List<ScoreV1>> callable = () -> {
            List<ScoreV1> scoreV1s = new ArrayList<>();

            scoresV1RequestBuilder.setKey(key);

            JsonArray json = getJsonArray(scoresV1RequestBuilder.getUrl());

            json.forEach(jsonElement -> scoreV1s.add(new ScoreV1(jsonElement.getAsJsonObject())));

            return scoreV1s;
        };

        return new FutureListImpl<>(callable, this);
    }


    public FutureListImpl<BestPerformanceV1, List<BestPerformanceV1>> retrieveBestPerformance(BestPerformanceRequestBuilderV1 bestPerformanceV1RequestBuilder) {
        Callable<List<BestPerformanceV1>> callable = () -> {
            List<BestPerformanceV1> bestPerformanceV1s = new ArrayList<>();

            bestPerformanceV1RequestBuilder.setKey(key);

            JsonArray json = getJsonArray(bestPerformanceV1RequestBuilder.getUrl());

            System.out.println(json);

            json.forEach(jsonElement -> bestPerformanceV1s.add(new BestPerformanceV1(jsonElement.getAsJsonObject())));

            return bestPerformanceV1s;
        };

        return new FutureListImpl<>(callable, this);
    }


    public FutureImpl<MatchV1> retrieveMatchInfo(MatchRequestBuilderV1 matchV1RequestBuilder) {
        Callable<MatchV1> callable = ()  -> {

            matchV1RequestBuilder.setKey(key);
            System.out.println(matchV1RequestBuilder.getUrl());

            return new MatchV1(getJsonObject(matchV1RequestBuilder.getUrl()));
        };

        return new FutureImpl<>(callable, this);
    }


    public FutureImpl<ReplayV1> retrieveReplay(ReplayRequestBuilderV1 replayV1RequestBuilder) {
        Callable<ReplayV1> callable = ()  -> {

            replayV1RequestBuilder.setKey(key);

            return new ReplayV1(getJsonObject(replayV1RequestBuilder.getUrl()));
        };

        return new FutureImpl<>(callable, this);
    }


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
