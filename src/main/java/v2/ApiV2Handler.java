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
        String url = "https://osu.ppy.sh/api/v2/oauth/authorize";
        String testUrl = "https://osu.ppy.sh/api/v2/oauth/tokens/current";

        String clientId = "&client_id=5419";
        String redirectUri = "&redirect_uri=google.com";
        String responseType = "&response_type=code";
        String scope = "&scope=bot";

        String fullUrl = url + clientId + redirectUri + responseType + scope;
        System.out.println(fullUrl);

        HttpURLConnection con = (HttpURLConnection) new URL(fullUrl).openConnection();
        con.setRequestMethod("GET");

        con.addRequestProperty("Accept", "application/json");
        con.addRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);


        @SuppressWarnings("SpellCheckingInspection")
        String jsonInputString = """
                {
                grant_type: client_credentials,
                client_id: 5419,
                client_secret: 0EUXizImCoZU426332SjBmCglzsfJHpsqdxLuRJW,
                scope: public
                    }""";
//
//        try(OutputStream os = con.getOutputStream()) {
//            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
//            os.write(input, 0, input.length);
//        }

        con.getOutputStream().flush();



        con.connect();
        String body = getFromInputStream(con.getInputStream());

        System.out.println(body);
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
