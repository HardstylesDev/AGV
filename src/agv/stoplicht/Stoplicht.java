package agv.stoplicht;

import agv.Component;
import agv.Pins;
import agv.utils.Multithread;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
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
        while(this.isEnabled()){
            try {
                update();
                Thread.sleep(500);
            } catch (InterruptedException ignored) {
            }
        }
    }




    private void reset(){

    }
    private void update() {

    }
}
