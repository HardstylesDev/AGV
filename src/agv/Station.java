package agv;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;

public class Station {
    public static boolean isStationAvailable(String kleur) {
        JsonObject part = null;
        try {
            JsonArray j = Requests.getArray("https://examen1704.gcmsi.nl/werkstations/");
            if(j.toString().contains("\"error\":"))
                return false;
            System.out.println(j.size());
            for (JsonElement element : j) {

                part = element.getAsJsonObject();
                String k = part.get("kleur").getAsString();
                boolean s = !part.get("bezet").getAsBoolean();
                if (k.equalsIgnoreCase(kleur))
                    return s;
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
