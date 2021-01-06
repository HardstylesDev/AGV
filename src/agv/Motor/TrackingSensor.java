package agv.Motor;

import agv.Component;
import agv.pins.Outputs;
import agv.status.Status;
import agv.status.StatusManager;
import agv.utils.Multithread;

@SuppressWarnings("SpellCheckingInspection")
public class TrackingSensor extends Component {
    public TrackingSensor() {
        super("Lijn Detectie");
    }

    private final Counter counterBackwards = new Counter();
    private final Counter counterForwards = new Counter();
    private final Motor motor = new Motor();
    private long lastDetection = -1;
    private int lastDirection = -1;

    public void onEnable() {
        Multithread.execute(this::start);
    } // run start op nieuwe thread

    private void start() {
        while (this.isEnabled()) {
            this.debug(Outputs.TRACKING_SENSOR_1.getState() + " - " + Outputs.TRACKING_SENSOR_2.getState() + " - " + Outputs.TRACKING_SENSOR_3.getState() + " - " + Outputs.TRACKING_SENSOR_4.getState() + " - " + Outputs.TRACKING_SENSOR_5.getState());
            update();
            try {
                Thread.sleep(25);
            } catch (InterruptedException ignored) {
                //
            }
        }
    }


    public void update() { // word 40x per seconde aangeroepen
        System.out.println(counterForwards.getTicks());

        if (Outputs.TRACKING_SENSOR_1.isLow() && Outputs.TRACKING_SENSOR_2.isLow())
            counterForwards.add();
        if (Outputs.TRACKING_SENSOR_4.isLow() && Outputs.TRACKING_SENSOR_5.isLow())
            counterBackwards.add();
        if (Outputs.TRACKING_SENSOR_1.isLow() && Outputs.TRACKING_SENSOR_2.isLow() && Outputs.TRACKING_SENSOR_3.isLow() && Outputs.TRACKING_SENSOR_4.isLow() && Outputs.TRACKING_SENSOR_5.isLow()) {
            lastDetection = System.currentTimeMillis();
            motor.steer(3);
        } else if (Outputs.TRACKING_SENSOR_1.isLow()) {
            lastDetection = System.currentTimeMillis();
            lastDirection = 5;
            motor.steer(5);
        } else if (Outputs.TRACKING_SENSOR_2.isLow()) {
            lastDetection = System.currentTimeMillis();
            lastDirection = 4;
            motor.steer(4);
        } else if (Outputs.TRACKING_SENSOR_3.isLow()) {
            lastDetection = System.currentTimeMillis();
            lastDirection = 3;
            motor.steer(3);
        } else if (Outputs.TRACKING_SENSOR_4.isLow()) {
            lastDirection = 2;
            lastDetection = System.currentTimeMillis();
            motor.steer(2);
        } else if (Outputs.TRACKING_SENSOR_5.isLow()) {
            lastDetection = System.currentTimeMillis();
            lastDirection = 1;
            motor.steer(1);
        } else if (Outputs.TRACKING_SENSOR_1.isHigh() && Outputs.TRACKING_SENSOR_2.isHigh() && Outputs.TRACKING_SENSOR_3.isHigh() && Outputs.TRACKING_SENSOR_4.isHigh() && Outputs.TRACKING_SENSOR_5.isHigh())
            if (System.currentTimeMillis() - lastDetection > 1300) {
                motor.disableMotors();
            } else {
                motor.steer(lastDirection);
            }
    }
}

