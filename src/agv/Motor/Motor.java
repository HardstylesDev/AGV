package agv.Motor;

import agv.Component;
import agv.pins.Outputs;
import agv.status.Status;
import agv.status.StatusManager;
import agv.utils.Multithread;
import com.pi4j.wiringpi.SoftPwm;

public class Motor extends Component {
    public Motor() {
        super("Motoren");
    }

    int speed = 25;
    int turnspeed = 35;

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
        SoftPwm.softPwmCreate(Outputs.MOTOR_ACHTERUIT_LINKS.getPin().getAddress(), 0, 100); // zet motor snelheid op 0
        SoftPwm.softPwmCreate(Outputs.MOTOR_VOORUIT_LINKS.getPin().getAddress(), 0, 100);
        SoftPwm.softPwmCreate(Outputs.MOTOR_VOORUIT_RECHTS.getPin().getAddress(), 0, 100);
        SoftPwm.softPwmCreate(Outputs.MOTOR_ACHTERUIT_RECHTS.getPin().getAddress(), 0, 100);

        Outputs.MOTOR_VOORUIT_RECHTS.high(); // zet motoren aan
        Outputs.MOTOR_VOORUIT_LINKS.high();
        Outputs.MOTOR_ACHTERUIT_LINKS.high();
        Outputs.MOTOR_ACHTERUIT_RECHTS.high();
    }


    public void disableMotors() { // Zet alle motoren uit
        SoftPwm.softPwmWrite(Outputs.MOTOR_ACHTERUIT_LINKS.getPin().getAddress(), 0);
        SoftPwm.softPwmWrite(Outputs.MOTOR_VOORUIT_LINKS.getPin().getAddress(), 0);
        SoftPwm.softPwmWrite(Outputs.MOTOR_VOORUIT_RECHTS.getPin().getAddress(), 0);
        SoftPwm.softPwmWrite(Outputs.MOTOR_ACHTERUIT_RECHTS.getPin().getAddress(), 0);
    }

    private void forwards() { // Auto rijd vooruit
        this.disableMotors();
        SoftPwm.softPwmWrite(Outputs.MOTOR_VOORUIT_LINKS.getPin().getAddress(), speed);
        SoftPwm.softPwmWrite(Outputs.MOTOR_VOORUIT_RECHTS.getPin().getAddress(), speed);


    }

    private void backwards() {
        this.disableMotors();

        SoftPwm.softPwmCreate(Outputs.MOTOR_ACHTERUIT_LINKS.getPin().getAddress(), speed, 100);
        SoftPwm.softPwmCreate(Outputs.MOTOR_ACHTERUIT_RECHTS.getPin().getAddress(), speed, 100);

    }

    public void steer(int richting) { // Sturen op basis van richting
        StatusManager.setStatus(Status.StatusType.ROUTE_KWIJT, false); // geef aan dat er een lijn gedetecteerd is
        if (richting == 3) {
            forwards();
        } else if (richting >= 4) {
            steerRight();
        } else if (richting <= 2) {
            steerLeft();
        }
    }

    boolean slaatAf;

    public boolean isAanHetAfslaan() {
        return slaatAf;
    }

    public void afslaan() {
        try {
            slaatAf = true;
            this.disableMotors();
            this.steerRight();
            Thread.sleep(1000);
            this.disableMotors();
            this.slaatAf = false;
        } catch (InterruptedException ignored) {
        }
    }

    private void steerRight() {
        this.disableMotors();
        SoftPwm.softPwmWrite(Outputs.MOTOR_VOORUIT_LINKS.getPin().getAddress(), turnspeed);
        SoftPwm.softPwmWrite(Outputs.MOTOR_ACHTERUIT_RECHTS.getPin().getAddress(), turnspeed);
    }

    private void steerLeft() {
        this.disableMotors();
        SoftPwm.softPwmWrite(Outputs.MOTOR_VOORUIT_RECHTS.getPin().getAddress(), turnspeed);
        SoftPwm.softPwmWrite(Outputs.MOTOR_ACHTERUIT_LINKS.getPin().getAddress(), turnspeed);


    }
    private void rotate180(){
        disableMotors();

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
