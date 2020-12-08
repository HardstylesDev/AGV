package agv;

import agv.status.Status;
import agv.status.StatusManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;

public class Station {
    public static boolean isStationAvailable(String kleur) {
        JsonObject part = null;
        try {
            JsonArray j = Requests.getArray("https://examen1704.gcmsi.nl/werkstations/");
            if (j.toString().contains("\"error\":"))
                return false;
            System.out.println(j.size());
            for (JsonElement element : j) {
                StatusManager.setStatus(Status.StatusType.GEEN_STATION_CONNECTIE, false);

                part = element.getAsJsonObject();
                String k = part.get("kleur").getAsString();
                boolean s = !part.get("bezet").getAsBoolean();
                if (k.equalsIgnoreCase(kleur)){
                    StatusManager.setStatus(Status.StatusType.AFSLAG_ONBEKEND, false);

                    return s;
                }
                StatusManager.setStatus(Status.StatusType.AFSLAG_ONBEKEND, true);


            }
        } catch (IOException e) {
            StatusManager.setStatus(Status.StatusType.GEEN_STATION_CONNECTIE, true);
            e.printStackTrace();
        }
        return false;
    }

}
