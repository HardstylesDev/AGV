package agv.status;

import agv.Component;
import agv.pins.Outputs;
import agv.utils.Database;
import agv.utils.Multithread;

public class StatusManager extends Component {

    /**
     * Deze class houd alle statussen bij en zet deze in de database.
     * Wanneer er een nieuwe status is gedetecteerd zal dit opgepikt worden.
     *
     */

    public StatusManager() {
        super("Status Manager");
    }

    static boolean werktNaarBehoren = false;
    static boolean taakVoltooid = false;
    static boolean geenDashboardConnectie = false;
    static boolean geenStationConnectie = false;
    static boolean routeKwijt = false;
    static boolean afslagOnbekend = false;

    /**
     * Deze methode zet een van de statustypes op true of op false.
     * @param type Geef aan welke status hij moet krijgen en welke waarde hij dan krijgt
     * @param value moet de aangegeven type status aan of uit?
     */
    public static void setStatus(Status.StatusType type, boolean value) {
        if (type == Status.StatusType.WERKT_NAAR_BEHOREN) werktNaarBehoren = value;
        if (type == Status.StatusType.TAAK_VOLTOOID) taakVoltooid = value;
        if (type == Status.StatusType.GEEN_DASHBOARD_CONNECTIE) geenDashboardConnectie = value;
        if (type == Status.StatusType.GEEN_STATION_CONNECTIE) geenStationConnectie = value;
        if (type == Status.StatusType.ROUTE_KWIJT) routeKwijt = value;
        if (type == Status.StatusType.AFSLAG_ONBEKEND) afslagOnbekend = value;


    }

    /**
     * Check welke status er op dit moment actief is.
     * @return Je krijgt de actieve status terug.
     */
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
            check();
            update();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignored) {
            }
        }
    }

    private void check() {
        if (!taakVoltooid && !geenDashboardConnectie && !afslagOnbekend && !routeKwijt && !geenStationConnectie)
            setStatus(Status.StatusType.WERKT_NAAR_BEHOREN, false);
        else
            setStatus(Status.StatusType.WERKT_NAAR_BEHOREN, true);
    }

    /**
     * Stop de momentele waarde van de status in de database
     */
    private void update() {
        Database db = new Database();
        db.connect();
        db.query("DELETE FROM `status`");
        System.out.println(String.format("INSERT INTO `status`(`werktNaarBehoren`, `taakVoltooid`, `geenDashboardConnectie`, `geenStationConnectie`,`routeKwijt`,`afslagOnbekend`) VALUES (\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\")", werktNaarBehoren, taakVoltooid, geenDashboardConnectie, geenStationConnectie, routeKwijt, afslagOnbekend));
        db.query(String.format("INSERT INTO `status`(`werktNaarBehoren`, `taakVoltooid`, `geenDashboardConnectie`, `geenStationConnectie`,`routeKwijt`,`afslagOnbekend`) VALUES (\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\")", werktNaarBehoren, taakVoltooid, geenDashboardConnectie, geenStationConnectie, routeKwijt, afslagOnbekend));
        db.disconnect();

    }


}
