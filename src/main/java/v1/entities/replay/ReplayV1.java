package v1.entities.replay;

import com.google.gson.JsonObject;
import v1.entities.OsuEntityV1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;

public class ReplayV1 implements OsuEntityV1 {

    public ReplayV1(JsonObject json) throws IOException {
//        System.out.println(json);
        String content = json.get("content").getAsString();
        String encoding = json.get("encoding").getAsString();

        byte[] decodedBytes = Base64.getDecoder().decode(content);

        Files.write(Paths.get("C:\\Users\\Tijs\\Documents\\OsuApiWrapper\\test.zip"), decodedBytes, StandardOpenOption.APPEND);
    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public String getIdString() {
        return null;
    }
}
