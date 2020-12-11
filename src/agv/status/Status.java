package agv.status;

@SuppressWarnings("SpellCheckingInspection")
public class Status {
    public enum StatusType {
        WERKT_NAAR_BEHOREN("Werkt naar behoren", 0, 1, 0, false),
        TAAK_VOLTOOID("Taak is voltooid", 0, 1, 0, true),
        GEEN_DASHBOARD_CONNECTIE("Geen dashboard connectie", 0, 0, 1, false),
        GEEN_STATION_CONNECTIE("Geen station connectie", 0, 0, 1, true),
        ROUTE_KWIJT("Route is kwijt", 1, 0, 0, false),
        AFSLAG_ONBEKEND("Afslag word niet herkend.", 1, 0, 0, true),
        ;

        private String name;
        private int red;
        private int green;
        private int blue;
        private boolean blink;

        StatusType(String name, int red, int green, int blue, boolean blink) {
            this.name = name;
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.blink = blink;
        }

        public String getName() {
            return this.name;
        }

        public int getRed() {
            return this.red;
        }

        public int getGreen() {
            return this.green;
        }

        public int getBlue() {
            return this.blue;
        }

        public boolean getBlink() {
            return this.blink;
        }
    }



}