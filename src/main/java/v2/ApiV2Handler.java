package v2;

import osu.OsuSettings;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;

public class ApiV2Handler {
    private final String key;
    public final static String startUrl = "https://osu.ppy.sh/api/2/";
    private final ArrayBlockingQueue<Runnable> blockingQueue;
    private final ExecutorService executorService;

    public ApiV2Handler(OsuSettings osuS) {
        this.key = osuS.getKeyV2();

        if (osuS.useSeparateQueues()) {
            this.blockingQueue = osuS.getV2Queue();
            this.executorService = osuS.getV2Executor();
        } else {
            this.blockingQueue = osuS.getBlockingQueue();
            this.executorService = osuS.getExecutorService();
        }
    }

    public static void main(String[] args) throws IOException {
        String url = "https://osu.ppy.sh/oauth/token";

        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("POST");

        con.addRequestProperty("Accept", "application/json");
        con.addRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        String jsonInputString = """
                {
                grant_type: client_credentials,
                client_id: 5419,
                client_secret: 0EUXizImCoZU426332SjBmCglzsfJHpsqdxLuRJW,
                scope: public
                    }""";

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        con.getOutputStream().flush();

        con.connect();
        String body = getFromInputStream(con.getInputStream());

        System.out.println(body);
        



//        String url = "https://osu.ppy.sh/api/v2/beatmaps/lookup?id=1241370";
//
//        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
//        con.setRequestMethod("GET");
//        con.setRequestProperty("Authorization", "Bearer 0EUXizImCoZU426332SjBmCglzsfJHpsqdxLuRJW");
//
//        String body = getFromInputStream(con.getInputStream());
//
//        System.out.println(body);

//        HttpRequest httpRequest = HttpRequest.newBuilder()
//                .setHeader("Authorization", "Bearer 0EUXizImCoZU426332SjBmCglzsfJHpsqdxLuRJW")
//                .uri(new URI("https://osu.ppy.sh/api/v2/beatmaps/lookup?id=1241370"))
//                .build();
//
//        System.out.println(httpRequest.headers());
//
//        System.out.println(HttpClient.newHttpClient()
//                .send(httpRequest, HttpResponse.BodyHandlers.ofString())
//                .body());
//
//        HashMap<String, String> categories = new LinkedHashMap<>(){
//
//        };
    }

    private static String getFromInputStream(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
