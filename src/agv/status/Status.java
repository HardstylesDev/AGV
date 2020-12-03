package agv.status;

@SuppressWarnings("SpellCheckingInspection")
public class Status {
    public enum StatusType {
        WERKT_NAAR_BEHOREN,
        TAAK_VOLTOOID,
        GEEN_DASHBOARD_CONNECTIE,
        GEEN_STATION_CONNECTIE,
        ROUTE_KWIJT,
        AFSLAG_ONBEKEND,
    }
}