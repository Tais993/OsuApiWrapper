package v1.entities.replay;

import com.google.gson.JsonObject;
import v1.entities.OsuEntityV1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ReplayV1 implements OsuEntityV1 {

    public ReplayV1(JsonObject json) throws FileNotFoundException {
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
