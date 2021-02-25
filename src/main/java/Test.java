import osu.Osu;
import osu.OsuSettingsBuilder;
import v1.entities.beatmap.BeatmapRequestBuilder;
import v1.entities.bestperformance.BestPerformanceRequestBuilder;
import v1.entities.global.Mod;
import v1.entities.multiplayer.MatchRequestBuilder;
import v1.entities.replay.ReplayRequestBuilder;
import v1.entities.scores.ScoresRequestBuilder;
import v1.entities.user.UserRequestBuilder;

public class Test {
    public static void main(String[] args) {
        Osu osu = new Osu(
                new OsuSettingsBuilder()
                        .setKeyV1("40c7ffe907acbcdfa992dd129031ad8886662880")
                        .setKeyV2("0EUXizImCoZU426332SjBmCglzsfJHpsqdxLuRJW")
                        .build()
        );

        retrieveReplay(osu);
    }

    private static void retrieveBeatmap(Osu osu) {
        BeatmapRequestBuilder beatmapRequestBuilder = new BeatmapRequestBuilder()
                .setBeatmapId(298880L)
                .addMods(Mod.DOUBLETIME, Mod.HARDROCK);

        osu.getV1().retrieveBeatmap(beatmapRequestBuilder)
                .runAsync(System.out::println);

        beatmapRequestBuilder = new BeatmapRequestBuilder()
                .setBeatmapId(298880L);

        osu.getV1().retrieveBeatmap(beatmapRequestBuilder)
                .runAsync(System.out::println);
    }

    private static void retrieveUser(Osu osu) {
        UserRequestBuilder userRequestBuilder = new UserRequestBuilder()
                .setUsername("DirkieDurky")
                .setEventDays(31);

        osu.getV1().retrieveUser(userRequestBuilder)
                .runAsync(user -> {
                    user.getEvents().forEach(System.out::println);
                    System.out.println(user.getUserProfileImageUrl());
                    System.out.println(user.getCountry().getCountryName());
                    System.out.println(user.getPpRank());
                });
    }

    private static void retrieveScores(Osu osu) {
        ScoresRequestBuilder scoresRequestBuilder = new ScoresRequestBuilder()
                .setBeatmapId(298880L);

        osu.getV1().retrieveScores(scoresRequestBuilder)
                .forEachAsync(System.out::println);
    }

    private static void retrieveBestPerformance(Osu osu) {
        BestPerformanceRequestBuilder bestPerformanceRequestBuilder = new BestPerformanceRequestBuilder()
                .setUsername("Tais993")
                .setLimit(2);

        osu.getV1().retrieveBestPerformance(bestPerformanceRequestBuilder)
                .forEachAsync(System.out::println);
    }

    private static void retrieveMatch(Osu osu) {
        MatchRequestBuilder matchRequestBuilder = new MatchRequestBuilder()
                .setMatchId(1251251251L);

        osu.getV1().retrieveMatchInfo(matchRequestBuilder)
                .runAsync(System.out::println);
    }

    private static void retrieveReplay(Osu osu) {
        ReplayRequestBuilder replayRequestBuilder = new ReplayRequestBuilder()
                .setBeatmapId(1180753L)
                .setUsername("gatama344");

        osu.getV1().retrieveReplay(replayRequestBuilder)
                .runAsync(replay -> System.out.println(replay.getId()));
    }

    public static void test(Osu osu) {
         ScoresRequestBuilder scoresRequestBuilder = new ScoresRequestBuilder()
                .setBeatmapId(1762728L)
                .setUsername("DirkieDurky");

        osu.getV1().retrieveScores(scoresRequestBuilder)
                .forEachAsync(System.out::println);
    }
}