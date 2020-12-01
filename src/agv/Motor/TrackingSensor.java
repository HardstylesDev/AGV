package agv.Motor;

import agv.Component;
import agv.Outputs.Outputs;
import agv.Pins;
import agv.utils.Multithread;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;

@SuppressWarnings("SpellCheckingInspection")
public class TrackingSensor extends Component {
    public TrackingSensor() {
        super("Lijn Detectie");
    }
    // 1: 23 -> 4
    // 2: 24 -> 5
    // 3: 25 -> 6
    // 4: 4 -> 7
    // 5: 5 -> 21

    @Override
    public void onEnable() { Multithread.execute(this::start); }

    private void start() {
        while (this.isEnabled()) {
            this.debug(Outputs.TRACKING_SENSOR_1.getState() + " - " + Outputs.TRACKING_SENSOR_2.getState() + " - " + Outputs.TRACKING_SENSOR_3.getState() + " - " + Outputs.TRACKING_SENSOR_4.getState() + " - " + Outputs.TRACKING_SENSOR_5.getState());
            update();
            try {
                Thread.sleep(50);
            } catch (InterruptedException ignored) {
            }

        }

    }


    final GpioController gpio = GpioFactory.getInstance();







    public void update(){

        if(Outputs.TRACKING_SENSOR_1.isLow())
            new Motor().steer(5);
        else if(Outputs.TRACKING_SENSOR_2.isLow())
            new Motor().steer(4);
        else if(Outputs.TRACKING_SENSOR_3.isLow())
            new Motor().steer(3);
        else if(Outputs.TRACKING_SENSOR_4.isLow())
            new Motor().steer(2);
        else  if(Outputs.TRACKING_SENSOR_5.isLow())
            new Motor().steer(1);

        else  if(Outputs.TRACKING_SENSOR_1.isHigh() && Outputs.TRACKING_SENSOR_2.isHigh() && Outputs.TRACKING_SENSOR_3.isHigh() && Outputs.TRACKING_SENSOR_4.isHigh() && Outputs.TRACKING_SENSOR_5.isHigh())
            new Motor().steer(3);
        }

    

}
