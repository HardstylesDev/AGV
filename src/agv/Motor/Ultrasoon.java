package agv.Motor;

import agv.Component;
import agv.test.PiJavaUltrasonic;
import agv.utils.Multithread;

//servo pin = 12
public class Ultrasoon extends Component {
    public Ultrasoon() {
        super("Servo");
    }

    @Override
    public void onEnable() {
        Multithread.execute(this::start);
    }

    public void start() {
      // if (!this.isEnabled())
      //     return;

        PiJavaUltrasonic sonic = new PiJavaUltrasonic(
                8,//ECO PIN (physical 33)
                29,//TRIG PIN (pysical 35)
                1000,//REJECTION_START ; long (nano seconds)
                23529411 //REJECTION_TIME ; long (nano seconds)
        );

        while (this.isEnabled()) {
            try {

                this.debug("distance " + sonic.getDistance() + "mm");
                setCurrentDistance(sonic.getDistance());
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static double distance = -1;

    public void setCurrentDistance(double i){
        distance = i;
    }
    public static double getDistance(){
        return distance;
    }
}

