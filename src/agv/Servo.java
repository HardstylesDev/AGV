package agv;

import agv.test.PiJavaUltrasonic;
import agv.utils.Multithread;

//servo pin = 12
public class Servo extends Component {
    public Servo() {
        super("Servo");
    }

    @Override
    public void onEnable() {
        Multithread.execute(this::start);
    }

    public void start() {
        if (!this.isEnabled())
            return;

        PiJavaUltrasonic sonic = new PiJavaUltrasonic(
                8,//ECO PIN (physical 33)
                29,//TRIG PIN (pysical 35)
                1000,//REJECTION_START ; long (nano seconds)
                23529411 //REJECTION_TIME ; long (nano seconds)
        );
        System.out.println("Start");
        while (true) {
            try {
                System.out.println("distance " + sonic.getDistance() + "mm");
                Thread.sleep(1000); //1s
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

