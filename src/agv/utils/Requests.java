package agv.utils.web;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.SocketTimeoutException;


public class Requests {
    public static JsonArray getArray(String u) throws IOException {
        try {

            JsonParser jp = new JsonParser();
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(u)
                    .build();
            Response response = client.newCall(request).execute();
            JsonElement root = jp.parse(response.body().string());

            return root.getAsJsonArray();
        } catch (SocketTimeoutException | IllegalStateException e) {
            return new JsonParser().parse("[{\"error\":true}]").getAsJsonArray();
        }
    }
}