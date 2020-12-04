package agv.Motor;

import agv.Component;
import agv.pins.Outputs;
import agv.utils.Multithread;
import com.pi4j.wiringpi.SoftPwm;

public class Motor extends Component {
    public Motor() {
        super("Motoren");
    }

    int speed = 25;
    int turnspeed = 60;

    @Override
    public void onDisable() {
        this.debug("Motor disabled");
    }


    @Override
    public void onEnable() {
        Multithread.execute(this::start);
    }

    public void start() {

        if (!this.isEnabled())
            return;
        SoftPwm.softPwmCreate(Outputs.MOTOR_ACHTERUIT_LINKS.getPin().getAddress(), 0, 100);
        SoftPwm.softPwmCreate(Outputs.MOTOR_VOORUIT_LINKS.getPin().getAddress(), 0,100);
        SoftPwm.softPwmCreate(Outputs.MOTOR_VOORUIT_RECHTS.getPin().getAddress(), 0,100);
        SoftPwm.softPwmCreate(Outputs.MOTOR_ACHTERUIT_RECHTS.getPin().getAddress(), 0,100);

        Outputs.MOTOR_VOORUIT_RECHTS.high();
        Outputs.MOTOR_VOORUIT_LINKS.high();
        Outputs.MOTOR_ACHTERUIT_LINKS.high();
        Outputs.MOTOR_ACHTERUIT_RECHTS.high();

        debug("start() called");
        try {
            // setSpeed(85);
            // forwards();
            // Thread.sleep(3000);
            // new TrackingSensor().setEnabled(false);
            // this.disableMotors();
        } catch (Exception e) {
        }

    }

    private int lastSeen = -1;

    private void returnToSafety() {

    }

    public void disableMotors() {
        debug("disableMotors() called");

        SoftPwm.softPwmWrite(Outputs.MOTOR_ACHTERUIT_LINKS.getPin().getAddress(), 0);
        SoftPwm.softPwmWrite(Outputs.MOTOR_VOORUIT_LINKS.getPin().getAddress(), 0);
        SoftPwm.softPwmWrite(Outputs.MOTOR_VOORUIT_RECHTS.getPin().getAddress(), 0);
        SoftPwm.softPwmWrite(Outputs.MOTOR_ACHTERUIT_RECHTS.getPin().getAddress(), 0);

        //  Outputs.MOTOR_ACHTERUIT_LINKS.low();
        //  Outputs.MOTOR_ACHTERUIT_RECHTS.low();
        //  Outputs.MOTOR_VOORUIT_LINKS.low();
        //  Outputs.MOTOR_VOORUIT_RECHTS.low();

    }

    private void forwards() {

        debug("forwards() called");
        //  if(!this.isEnabled())
        //      return;
        this.disableMotors();
        // Outputs.MOTOR_VOORUIT_RECHTS.high();
        // Outputs.MOTOR_VOORUIT_LINKS.high();


        SoftPwm.softPwmWrite(Outputs.MOTOR_VOORUIT_LINKS.getPin().getAddress(), speed);
        SoftPwm.softPwmWrite(Outputs.MOTOR_VOORUIT_RECHTS.getPin().getAddress(), speed);


    }

    private void backwards() {
        // if(!this.isEnabled())
        //     return;
        this.disableMotors();
        //Outputs.MOTOR_ACHTERUIT_RECHTS.high();
        //Outputs.MOTOR_ACHTERUIT_LINKS.high();

        SoftPwm.softPwmCreate(Outputs.MOTOR_ACHTERUIT_LINKS.getPin().getAddress(), speed, 100);
        SoftPwm.softPwmCreate(Outputs.MOTOR_ACHTERUIT_RECHTS.getPin().getAddress(), speed, 100);

    }

    public void steer(int richting) {
        if(Ultrasoon.getDistance() >= 0 && Ultrasoon.getDistance() <= 100){
            debug("Disable motors, object detectie.");
            disableMotors();
            return;
        }
        //this.lastSeen = richting;
        debug("steer() called with value: " + richting);
        //if(!this.isEnabled())
        //    return;
        if (richting == 3) {
            forwards();
            debug("forward");
        } else if (richting >= 4) {
            steerRight();
            debug("right");
        } else if (richting <= 2) {
            steerLeft();
            debug("left");
        }
    }

    private void steerRight() {
        //  if(!this.isEnabled())
        //      return;
        this.disableMotors();


        SoftPwm.softPwmWrite(Outputs.MOTOR_VOORUIT_LINKS.getPin().getAddress(), turnspeed);


        //Outputs.MOTOR_VOORUIT_LINKS.high();
    }

    private void steerLeft() {
        //  if(!this.isEnabled())
        //      return;
        this.disableMotors();
        //  Outputs.MOTOR_VOORUIT_RECHTS.high();

        SoftPwm.softPwmWrite(Outputs.MOTOR_VOORUIT_RECHTS.getPin().getAddress(), turnspeed);


    }

    private void setSpeed(int procent) throws InterruptedException {


        SoftPwm.softPwmCreate(Outputs.MOTOR_ACHTERUIT_LINKS.getPin().getAddress(), procent, 100);
        SoftPwm.softPwmCreate(Outputs.MOTOR_VOORUIT_LINKS.getPin().getAddress(), procent, 100);
        SoftPwm.softPwmCreate(Outputs.MOTOR_VOORUIT_RECHTS.getPin().getAddress(), procent, 100);
        SoftPwm.softPwmCreate(Outputs.MOTOR_ACHTERUIT_RECHTS.getPin().getAddress(), procent, 100);

        //  SoftPwm.softPwmWrite(Outputs.MOTOR_ACHTERUIT_LINKS.getPin().getAddress(), procent);
        //  SoftPwm.softPwmWrite(Outputs.MOTOR_ACHTERUIT_RECHTS.getPin().getAddress(), procent);
        //  SoftPwm.softPwmWrite(Outputs.MOTOR_VOORUIT_LINKS.getPin().getAddress(), procent);
        //  SoftPwm.softPwmWrite(Outputs.MOTOR_VOORUIT_RECHTS.getPin().getAddress(), procent);
    }
}
