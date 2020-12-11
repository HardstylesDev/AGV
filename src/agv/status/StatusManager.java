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

    public boolean getStatus(Status.StatusType statusType) {
        if (statusType == Status.StatusType.WERKT_NAAR_BEHOREN) return werktNaarBehoren;
        if (statusType == Status.StatusType.TAAK_VOLTOOID) return taakVoltooid;
        if (statusType == Status.StatusType.GEEN_DASHBOARD_CONNECTIE) return geenDashboardConnectie;
        if (statusType == Status.StatusType.GEEN_STATION_CONNECTIE) return geenStationConnectie;
        if (statusType == Status.StatusType.ROUTE_KWIJT) return routeKwijt;
        if (statusType == Status.StatusType.AFSLAG_ONBEKEND) return afslagOnbekend;
        return false;
    }

    public Status.StatusType getActiveStatus() {
        if(!afslagOnbekend && !routeKwijt && !geenDashboardConnectie && !geenStationConnectie && !taakVoltooid) return Status.StatusType.WERKT_NAAR_BEHOREN;
        if (afslagOnbekend) return Status.StatusType.AFSLAG_ONBEKEND;
        else if (routeKwijt) return Status.StatusType.ROUTE_KWIJT;
        else if (geenStationConnectie) return Status.StatusType.GEEN_STATION_CONNECTIE;
        else if (geenDashboardConnectie) return Status.StatusType.GEEN_DASHBOARD_CONNECTIE;
        else if (taakVoltooid) return Status.StatusType.TAAK_VOLTOOID;
        else if (werktNaarBehoren) return Status.StatusType.WERKT_NAAR_BEHOREN;
        return null;
    }

    public void onEnable() {
        Multithread.execute(this::start);
    }

    private void start() {
        while (this.isEnabled()) {
            _();
            update();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignored) {
            }
        }
    }

    private void _() {
        if (!taakVoltooid && !geenDashboardConnectie && !afslagOnbekend && !routeKwijt && !geenStationConnectie)
            setStatus(Status.StatusType.WERKT_NAAR_BEHOREN, false);
        else
            setStatus(Status.StatusType.WERKT_NAAR_BEHOREN, true);
    }

    private void update() {
        //lampjes en shit
        //database
        Database db = new Database();
        db.connect();
        db.query("DELETE FROM `status`");
        db.query(String.format("INSERT INTO `status`(`werktNaarBehoren`, `taakVoltooid`, `geenDashboardConnectie`, `geenStationConnectie`,`routeKwijt`,`afslagOnbekend`) VALUES (\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\")", werktNaarBehoren, taakVoltooid, geenDashboardConnectie, geenStationConnectie, routeKwijt, afslagOnbekend));
        db.disconnect();

    }


}
