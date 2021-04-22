import osu.Osu;
import osu.OsuSettingsBuilder;
import v1.entities.beatmap.BeatmapRequestBuilderV1;
import v1.entities.bestperformance.BestPerformanceRequestBuilderV1;
import v1.entities.multiplayer.MatchRequestBuilderV1;
import v1.entities.multiplayer.MatchV1;
import v1.entities.replay.ReplayRequestBuilderV1;
import v1.entities.scores.ScoresRequestBuilderV1;
import v1.entities.user.UserRequestBuilderV1;

public class Test {
    public static void main(String[] args) {
        Osu osu = new Osu(
                new OsuSettingsBuilder()
                        .setKeyV1("40c7ffe907acbcdfa992dd129031ad8886662880")
                        .setKeyV2("0EUXizImCoZU426332SjBmCglzsfJHpsqdxLuRJW")
                        .build()
        );

        retrieveBeatmap(osu);
    }

    private static void retrieveBeatmap(Osu osu) {
        BeatmapRequestBuilderV1 beatmapV1RequestBuilder = new BeatmapRequestBuilderV1()
                .setBeatmapId(2747701L);

        osu.getV1().retrieveBeatmap(beatmapV1RequestBuilder)
                .runAsync(beatmapV1 -> {
                    System.out.println(beatmapV1.getBeatmapCoverImageUrl());
                    System.out.println(beatmapV1.getBeatmapCoverThumbnailUrl() );
                });
    }

    private static void retrieveUser(Osu osu) {
        UserRequestBuilderV1 userV1RequestBuilder = new UserRequestBuilderV1()
                .setUsername("DirkieDurky")
                .setEventDays(31);

        osu.getV1().retrieveUser(userV1RequestBuilder)
                .runAsync(user -> {
                    user.getEvents().forEach(System.out::println);
                    System.out.println(user.getUserProfileImageUrl());
                    System.out.println(user.getCountry().getCountryName());
                    System.out.println(user.getPpRank());
                });
    }

    private static void retrieveScores(Osu osu) {
        ScoresRequestBuilderV1 scoresV1RequestBuilder = new ScoresRequestBuilderV1()
                .setBeatmapId(298880L);

        osu.getV1().retrieveScores(scoresV1RequestBuilder)
                .forEachAsync(System.out::println);
    }

    private static void retrieveBestPerformance(Osu osu) {
        BestPerformanceRequestBuilderV1 bestPerformanceV1RequestBuilder = new BestPerformanceRequestBuilderV1()
                .setUsername("Tais993")
                .setLimit(2);

        osu.getV1().retrieveBestPerformance(bestPerformanceV1RequestBuilder)
                .forEachAsync(System.out::println);
    }

    private static void retrieveMatch(Osu osu) {
        MatchRequestBuilderV1 matchV1RequestBuilder = new MatchRequestBuilderV1()
                .setMatchId(1251251251L);

        osu.getV1().retrieveMatchInfo(matchV1RequestBuilder)
                .mapToList(MatchV1::toGames)
                .forEachAsync(System.out::println);
    }

    private static void retrieveReplay(Osu osu) {
        ReplayRequestBuilderV1 replayV1RequestBuilder = new ReplayRequestBuilderV1()
                .setBeatmapId(1180753L)
                .setUsername("gatama344");

        osu.getV1().retrieveReplay(replayV1RequestBuilder)
                .runAsync(replay -> System.out.println(replay.getId()));
    }

    public static void test(Osu osu) {
         ScoresRequestBuilderV1 scoresV1RequestBuilder = new ScoresRequestBuilderV1()
                .setBeatmapId(1762728L)
                .setUsername("DirkieDurky");

        osu.getV1().retrieveScores(scoresV1RequestBuilder)
                .forEachAsync(System.out::println);
    }
}