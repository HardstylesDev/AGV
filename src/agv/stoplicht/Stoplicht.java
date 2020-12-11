package agv.stoplicht;

import agv.Component;
import agv.status.Status;
import agv.status.StatusManager;
import agv.utils.Multithread;

import java.io.IOException;

@SuppressWarnings("SpellCheckingInspection")

public class Stoplicht extends Component {
    public Stoplicht() {
        super("Stoplicht");
    }

    int GREEN = 15;
    int RED = 11;
    int BLUE = 10;

    @Override
    public void onEnable() {
        Multithread.execute(this::start);
    }

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

    private void update() {

    }

    private void disableAll() {
        enableLED(15, false);
        enableLED(11, false);
        enableLED(10, false);
    }

    private void enableLED(int i, boolean enable) {
        Runtime runTime = Runtime.getRuntime();
        try {
            runTime.exec("gpio write " + i + " " + (enable ? 1 : 0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getLamp(int red, int green, int blue) {
        if (red == 1 && green == 0 && blue == 0)
            return RED;
        else if (red == 0 && green == 1 && blue == 0)
            return GREEN;
        else if (red == 0 && green == 0 && blue == 1)
            return BLUE;
        return -1;
    }

    boolean test = true;
    int lamp = 0;

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
