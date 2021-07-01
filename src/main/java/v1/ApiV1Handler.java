package v1;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import futures.FutureImpl;
import futures.FutureListImpl;
import osu.OsuSettings;
import v1.entities.beatmap.Beatmap;
import v1.entities.beatmap.BeatmapIRequestBuilder;
import v1.entities.beatmap.IBeatmap;
import v1.entities.bestperformance.BestPerformanceIRequestBuilder;
import v1.entities.bestperformance.BestPerformanceV1;
import v1.entities.multiplayer.MatchIRequestBuilder;
import v1.entities.multiplayer.MatchV1;
import v1.entities.replay.ReplayIRequestBuilder;
import v1.entities.replay.ReplayV1;
import v1.entities.scores.ScoreV1;
import v1.entities.scores.ScoresIRequestBuilder;
import v1.entities.user.UserIRequestBuilder;
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

public class ApiV1Handler implements IApiHandler{
    private final String key;
    public final static String startUrl = "https://osu.ppy.sh/api/";
    private final ArrayBlockingQueue<Runnable> blockingQueue;
    private final ExecutorService executorService;

    public ApiV1Handler(final OsuSettings osuS) {
        key = osuS.getKeyV1();

        if (osuS.useSeparateQueues()) {
            blockingQueue = osuS.getV1Queue();
            executorService = osuS.getV1Executor();
        } else {
            blockingQueue = osuS.getBlockingQueue();
            executorService = osuS.getExecutorService();
        }
    }

    public ArrayBlockingQueue<Runnable> getBlockingQueue() {
        return blockingQueue;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    @Override
    public FutureImpl<IBeatmap> retrieveBeatmap(final BeatmapIRequestBuilder beatmapV1RequestBuilder) {
        final Callable<IBeatmap> callable = () -> {
            beatmapV1RequestBuilder.setKey(key);
            System.out.println(beatmapV1RequestBuilder.getUrl());

            return Beatmap.fromJson(getJsonArray(beatmapV1RequestBuilder.getUrl()).get(0).getAsJsonObject());
        };

        return new FutureImpl<IBeatmap>(callable, this);
    }

    @Override
    public FutureListImpl<BeatmapV1, List<BeatmapV1>> retrieveBeatmaps(final BeatmapIRequestBuilder beatmapV1RequestBuilder) {
        final Callable<List<BeatmapV1>> callable = () -> {
            final List<BeatmapV1> beatmapV1s = new ArrayList<>();

            beatmapV1RequestBuilder.setKey(key);
            final JsonArray json = getJsonArray(beatmapV1RequestBuilder.getUrl());

            json.forEach(jsonElement -> beatmapV1s.add(new BeatmapV1(jsonElement.getAsJsonObject())));

            return beatmapV1s;
        };

        return new FutureListImpl<>(callable, this);
    }

    @Override
    public FutureImpl<UserV1> retrieveUser(final UserIRequestBuilder userV1RequestBuilder) {
        final Callable<UserV1> callable = ()  -> {

            userV1RequestBuilder.setKey(key);
            System.out.println(userV1RequestBuilder.getUrl());

            return new UserV1(getJsonArray(userV1RequestBuilder.getUrl()).get(0).getAsJsonObject());
        };

        return new FutureImpl<>(callable, this);
    }

    @Override
    public FutureListImpl<UserV1, List<UserV1>> retrieveUsers(final List<UserIRequestBuilder> userRequestBuildersV1) {
        final Callable<List<UserV1>> callable = ()  -> {

            userRequestBuildersV1.forEach(userRequestBuilderV1 -> userRequestBuilderV1.setKey(key));

            final List<UserV1> users = new ArrayList<>();

            for (final UserIRequestBuilder userRequestBuilderV1 : userRequestBuildersV1) {
                users.add(new UserV1(getJsonArray(userRequestBuilderV1.getUrl()).get(0).getAsJsonObject()));
            }

            return users;
        };

        return new FutureListImpl<>(callable, this);
    }


    @Override
    public FutureListImpl<ScoreV1, List<ScoreV1>> retrieveScores(final ScoresIRequestBuilder scoresV1RequestBuilder) {
        final Callable<List<ScoreV1>> callable = () -> {
            final List<ScoreV1> scoreV1s = new ArrayList<>();

            scoresV1RequestBuilder.setKey(key);

            final JsonArray json = getJsonArray(scoresV1RequestBuilder.getUrl());

            json.forEach(jsonElement -> scoreV1s.add(new ScoreV1(jsonElement.getAsJsonObject())));

            return scoreV1s;
        };

        return new FutureListImpl<>(callable, this);
    }

    @Override
    public FutureListImpl<BestPerformanceV1, List<BestPerformanceV1>> retrieveBestPerformance(final BestPerformanceIRequestBuilder bestPerformanceV1RequestBuilder) {
        final Callable<List<BestPerformanceV1>> callable = () -> {
            final List<BestPerformanceV1> bestPerformanceV1s = new ArrayList<>();

            bestPerformanceV1RequestBuilder.setKey(key);

            final JsonArray json = getJsonArray(bestPerformanceV1RequestBuilder.getUrl());

            System.out.println(json);

            json.forEach(jsonElement -> bestPerformanceV1s.add(new BestPerformanceV1(jsonElement.getAsJsonObject())));

            return bestPerformanceV1s;
        };

        return new FutureListImpl<>(callable, this);
    }

    @Override
    public FutureImpl<MatchV1> retrieveMatchInfo(final MatchIRequestBuilder matchV1RequestBuilder) {
        final Callable<MatchV1> callable = ()  -> {

            matchV1RequestBuilder.setKey(key);
            System.out.println(matchV1RequestBuilder.getUrl());

            return new MatchV1(getJsonObject(matchV1RequestBuilder.getUrl()));
        };

        return new FutureImpl<>(callable, this);
    }

    @Override
    public FutureImpl<ReplayV1> retrieveReplay(final ReplayIRequestBuilder replayV1RequestBuilder) {
        final Callable<ReplayV1> callable = ()  -> {

            replayV1RequestBuilder.setKey(key);

            return new ReplayV1(getJsonObject(replayV1RequestBuilder.getUrl()));
        };

        return new FutureImpl<>(callable, this);
    }


    protected static JsonObject getJsonObject(final String urlString) throws IOException, InterruptedException {
        final HttpClient client = HttpClient.newHttpClient();
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .GET()
                .build();

        final String jsonString = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        return new Gson().fromJson(jsonString, JsonObject.class);
    }

    protected static JsonArray getJsonArray(final String urlString) throws IOException, InterruptedException {
        final HttpClient client = HttpClient.newHttpClient();
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .GET()
                .build();

        final String jsonString = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        return new Gson().fromJson(jsonString, JsonArray.class);
    }

    protected static JsonElement getJsonElement(final String urlString) throws IOException, InterruptedException {
        final HttpClient client = HttpClient.newHttpClient();
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .GET()
                .build();

        final String jsonString = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        return new Gson().fromJson(jsonString, JsonElement.class);
    }
}
