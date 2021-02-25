package v1.entities.replay;

import com.google.gson.JsonObject;
import lzma.streams.LzmaOutputStream;
import v1.entities.OsuEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Replay implements OsuEntity {

    public Replay(JsonObject json) throws FileNotFoundException {
//        System.out.println(json);
        String content = json.get("content").getAsString();
        String encoding = json.get("encoding").getAsString();

        byte[] decodedBytes = Base64.getDecoder().decode(content);

        FileOutputStream fileOutputStream = new FileOutputStream(new File("C:\\Users\\Tijs\\Documents\\OsuApiWrapper\\test.zip"));

        try {
            fileOutputStream.write(decodedBytes);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try (LzmaOutputStream outputStream = new LzmaOutputStream.Builder(
//                fileOutputStream)
//                .build()) {
//
//            outputStream.write(decodedBytes);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
