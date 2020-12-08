package agv.stoplicht;

import agv.Component;
import agv.utils.Multithread;

import java.io.IOException;

@SuppressWarnings("SpellCheckingInspection")

public class Stoplicht extends Component {
    public Stoplicht() {
        super("Stoplicht");
    }


    @Override
    public void onEnable() {
        Multithread.execute(this::start);
    }

    public void start() {
        if (!this.isEnabled())
            return;
        enable(11, true);
        while (this.isEnabled()) {
            try {
                update();
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
        }
    }
    private void update() {

    }

    private void disableAll(){
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

    boolean test = true;
    int lamp = 0;
    private void enable(int i, boolean blink){
        disableAll();
        if(blink){
            while(blink && i == lamp){
                enableLED(i, test);
                if (test) {
                    test = !test;
                    return;
                }
                test = !test;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        enableLED(i, true);
    }
}
