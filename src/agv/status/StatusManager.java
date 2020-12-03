package agv.status;

import agv.Component;
import agv.pins.Outputs;
import agv.utils.Database;
import agv.utils.Multithread;

public class StatusManager extends Component {
    public StatusManager() {
        super("Status Manager");
    }

    static boolean werktNaarBehoren = false;
    static boolean taakVoltooid = false;
    static boolean geenDashboardConnectie = false;
    static boolean geenStationConnectie = false;
    static boolean routeKwijt = false;
    static boolean afslagOnbekend = false;



    public static void setStatus(Status.StatusType type, boolean value) {
        if (type == Status.StatusType.WERKT_NAAR_BEHOREN) werktNaarBehoren = value;
        if (type == Status.StatusType.TAAK_VOLTOOID) taakVoltooid = value;
        if (type == Status.StatusType.GEEN_DASHBOARD_CONNECTIE) geenDashboardConnectie = value;
        if (type == Status.StatusType.GEEN_STATION_CONNECTIE) geenStationConnectie = value;
        if (type == Status.StatusType.ROUTE_KWIJT) routeKwijt = value;
        if (type == Status.StatusType.AFSLAG_ONBEKEND) afslagOnbekend = value;


    }

    public void onEnable() {
        Multithread.execute(this::start);
    }

    private void start() {
        while (this.isEnabled()) {
            this.debug(Outputs.TRACKING_SENSOR_1.getState() + " - " + Outputs.TRACKING_SENSOR_2.getState() + " - " + Outputs.TRACKING_SENSOR_3.getState() + " - " + Outputs.TRACKING_SENSOR_4.getState() + " - " + Outputs.TRACKING_SENSOR_5.getState());
            update();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignored) {
            }
        }
    }

    private void update() {
        //lampjes en shit



        //database
        Database db = new Database();
        db.connect();
        db.query(String.format("INSERT INTO `status`(`werktNaarBehoren`, `taakVoltooid`, `geenDashboardConnectie`, 'geenStationConnectie','routeKwijt','afslagOnbekend') VALUES (\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\")", werktNaarBehoren,taakVoltooid,geenDashboardConnectie, geenStationConnectie, routeKwijt, afslagOnbekend ));
        db.disconnect();

    }


}
