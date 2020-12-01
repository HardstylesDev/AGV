package agv.Motor;

import agv.Component;
import agv.Outputs.Outputs;
import agv.utils.Multithread;
import com.pi4j.wiringpi.SoftPwm;

import javax.sound.midi.Track;

public class Motor extends Component {
    public Motor() {
        super("Motoren");
    }

    @Override
    public void onDisable() {
        this.debug("Motor disabled");
    }


    @Override
    public void onEnable() {
        Multithread.execute(this::start);
    }

    public void start() {
        //try {
        //    setSpeed(10);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        if (!this.isEnabled())
            return;
        debug("start() called");
        //try {
        //    forwards();
        //    Thread.sleep(3000);
        //    new TrackingSensor().setEnabled(false);
        //    this.disableMotors();
        //} catch (Exception e) {
        //}
    }


    private void disableMotors() {
        debug("disableMotors() called");

        Outputs.MOTOR_ACHTERUIT_LINKS.low();
        Outputs.MOTOR_ACHTERUIT_RECHTS.low();
        Outputs.MOTOR_VOORUIT_LINKS.low();
        Outputs.MOTOR_VOORUIT_RECHTS.low();

    }

    private void forwards() {
        debug("forwards() called");
        //  if(!this.isEnabled())
        //      return;
        this.disableMotors();
        Outputs.MOTOR_VOORUIT_RECHTS.high();
        Outputs.MOTOR_VOORUIT_LINKS.high();
    }

    private void backwards() {
        // if(!this.isEnabled())
        //     return;
        this.disableMotors();
        Outputs.MOTOR_ACHTERUIT_RECHTS.high();
        Outputs.MOTOR_ACHTERUIT_LINKS.high();

    }

    public void steer(int richting) {
        System.out.println("steer() called with value: " + richting);
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
        Outputs.MOTOR_VOORUIT_LINKS.high();
    }

    private void steerLeft() {
        //  if(!this.isEnabled())
        //      return;
        this.disableMotors();
        Outputs.MOTOR_VOORUIT_RECHTS.high();

    }

    private void setSpeed(int procent) throws InterruptedException {

        SoftPwm.softPwmCreate(Outputs.MOTOR_ACHTERUIT_RECHTS.getPin().getAddress(), 0, 100);
        SoftPwm.softPwmCreate(Outputs.MOTOR_ACHTERUIT_LINKS.getPin().getAddress(), 0, 100);
        SoftPwm.softPwmCreate(Outputs.MOTOR_VOORUIT_LINKS.getPin().getAddress(), 0, 100);
        SoftPwm.softPwmCreate(Outputs.MOTOR_VOORUIT_RECHTS.getPin().getAddress(), 0, 100);

        SoftPwm.softPwmWrite(Outputs.MOTOR_ACHTERUIT_LINKS.getPin().getAddress(), procent);
        SoftPwm.softPwmWrite(Outputs.MOTOR_ACHTERUIT_RECHTS.getPin().getAddress(), procent);
        SoftPwm.softPwmWrite(Outputs.MOTOR_VOORUIT_LINKS.getPin().getAddress(), procent);
        SoftPwm.softPwmWrite(Outputs.MOTOR_VOORUIT_RECHTS.getPin().getAddress(), procent);
    }
}
