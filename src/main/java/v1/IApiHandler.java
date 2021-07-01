package v1;

import futures.FutureImpl;
import futures.FutureListImpl;
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

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;

public interface IApiHandler {
    ArrayBlockingQueue<Runnable> getBlockingQueue();
    ExecutorService getExecutorService();

    FutureImpl<IBeatmap> retrieveBeatmap(BeatmapIRequestBuilder requestBuilder);
    FutureListImpl<BeatmapV1, List<BeatmapV1>> retrieveBeatmaps(BeatmapIRequestBuilder requestBuilder);
    FutureImpl<UserV1> retrieveUser(UserIRequestBuilder requestBuilder);
    FutureListImpl<UserV1, List<UserV1>> retrieveUsers(List<UserIRequestBuilder> requestBuilder);
    FutureListImpl<ScoreV1, List<ScoreV1>> retrieveScores(ScoresIRequestBuilder requestBuilder);
    FutureListImpl<BestPerformanceV1, List<BestPerformanceV1>> retrieveBestPerformance(BestPerformanceIRequestBuilder requestBuilder);
    FutureImpl<MatchV1> retrieveMatchInfo(MatchIRequestBuilder requestBuilder);
    FutureImpl<ReplayV1> retrieveReplay(ReplayIRequestBuilder requestBuilder);
}
