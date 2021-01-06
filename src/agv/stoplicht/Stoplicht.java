package agv.stoplicht;

import agv.Component;
import agv.status.Status;
import agv.status.StatusManager;
import agv.utils.Multithread;

import java.io.IOException;

@SuppressWarnings("SpellCheckingInspection")

public class Stoplicht extends Component {
    /**
     * Deze class bepaalt wat het LEDje weergeeft. Wij hebben ons stoplicht vervangen met een RGB ledje.
     * Ook hebben wij daarom de kleur geel vervangen met blauw
     */
    public Stoplicht() {
        super("Stoplicht");
    }
    // dit zijn de specifieke pins.
    int GREEN = 15;
    int RED = 11;
    int BLUE = 10;

    @Override
    public void onEnable() {
        Multithread.execute(this::start);
    }

    /**
     * Start het stoplicht. Deze methode loopt op een nieuwe thread.
     */
    public void start() {
        if (!this.isEnabled())
            return;
        if (new StatusManager().getActiveStatus() != null)
            enable(new StatusManager().getActiveStatus(), new StatusManager().getActiveStatus().getBlink());
        while (this.isEnabled()) {
            try {
                if (new StatusManager().getActiveStatus() != null)
                    enable(new StatusManager().getActiveStatus(), new StatusManager().getActiveStatus().getBlink());
                Thread.sleep(500);
            } catch (InterruptedException ignored) {
            }
        }
    }

    /**
     * Zet alle LED's uit
     */
    private void disableAll() {
        enableLED(15, false);
        enableLED(11, false);
        enableLED(10, false);
    }

    /**
     *
     * @param i, Welke pin moet aan?
     * @param enable, moet hij aan of uit
     */
    private void enableLED(int i, boolean enable) {
        Runtime runTime = Runtime.getRuntime();
        try {
            runTime.exec("gpio write " + i + " " + (enable ? 1 : 0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param red 0 of 1, moet rood aan?
     * @param green 0 of 1, moet groen aan?
     * @param blue 0 of 1, moet blauw aan?
     * @return Welk lampje moet aan.
     */
    private int getLamp(int red, int green, int blue) {
        if (red == 1 && green == 0 && blue == 0)
            return RED;
        else if (red == 0 && green == 1 && blue == 0)
            return GREEN;
        else if (red == 0 && green == 0 && blue == 1)
            return BLUE;
        return -1;
    }

    /**
     *
     * @param statusType, De type status die je aan wil geven
     * @param blink, Geef aan of het lampje moet knipperen.
     */
    private void enable(Status.StatusType statusType, boolean blink) {
        System.out.println("enable(Status.StatusType " + statusType.getName());
        int LED = getLamp(statusType.getRed(), statusType.getGreen(), statusType.getBlue());
        disableAll();
        if (blink) {
            enableLED(LED, true);
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            enableLED(LED, false);
        } else
            enableLED(LED, true);
    }
}
