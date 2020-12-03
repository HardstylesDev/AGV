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

    private long lastDetection = -1;
    private int lastDirection = -1;

    public void onEnable() {
        Multithread.execute(this::start);
    }

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
    boolean left = false;
    boolean right = false;
    boolean center = false;
    public void update() {
        if (Outputs.TRACKING_SENSOR_1.isLow() && Outputs.TRACKING_SENSOR_2.isLow() && Outputs.TRACKING_SENSOR_3.isLow() && Outputs.TRACKING_SENSOR_4.isLow() && Outputs.TRACKING_SENSOR_5.isLow())
        {
            lastDetection = System.currentTimeMillis();
            new Motor().steer(3);
        }
        else if (Outputs.TRACKING_SENSOR_1.isLow()) {
            lastDetection = System.currentTimeMillis();
            lastDirection = 5;
            new Motor().steer(5);
        } else if (Outputs.TRACKING_SENSOR_2.isLow()) {
            lastDetection = System.currentTimeMillis();
            lastDirection = 4;
            new Motor().steer(4);
        } else if (Outputs.TRACKING_SENSOR_3.isLow()) {
            lastDetection = System.currentTimeMillis();
            lastDirection = 3;
            new Motor().steer(3);
        } else if (Outputs.TRACKING_SENSOR_4.isLow()) {
            lastDirection = 2;
            lastDetection = System.currentTimeMillis();
            new Motor().steer(2);
        } else if (Outputs.TRACKING_SENSOR_5.isLow()) {
            lastDetection = System.currentTimeMillis();
            lastDirection = 1;
            new Motor().steer(1);
        } else if (Outputs.TRACKING_SENSOR_1.isHigh() && Outputs.TRACKING_SENSOR_2.isHigh() && Outputs.TRACKING_SENSOR_3.isHigh() && Outputs.TRACKING_SENSOR_4.isHigh() && Outputs.TRACKING_SENSOR_5.isHigh())
            if (System.currentTimeMillis() - lastDetection > 3000){
                new Motor().disableMotors();
                StatusManager.setStatus(Status.StatusType.ROUTE_KWIJT, false);
            }
            else
                new Motor().steer(lastDirection);
    }
    private void send(boolean left, boolean center, boolean right){

    }


}
