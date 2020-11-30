package agv.Motor;

import agv.Component;
import agv.Pins;
import agv.utils.Multithread;
import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.SoftPwm;

public class Motor extends Component {
    public Motor() {
        super("Motoren");
    }

    @Override
    public void onDisable() {
        System.out.println("Motor disabled");
    }

    private final GpioController gpio = GpioFactory.getInstance();
    private final GpioPinDigitalOutput vooruit_rechts = gpio.provisionDigitalOutputPin(Pins.VOORUIT_RECHTS);
    private final GpioPinDigitalOutput vooruit_links = gpio.provisionDigitalOutputPin(Pins.VOORUIT_LINKS);
    private final GpioPinDigitalOutput achteruit_rechts = gpio.provisionDigitalOutputPin(Pins.ACHTERUIT_RECHTS);
    private final GpioPinDigitalOutput achteruit_links = gpio.provisionDigitalOutputPin(Pins.ACHTERUIT_LINKS);

    @Override
    public void onEnable() {
        Multithread.execute(this::start);
    }

    public void start() {
        if (!this.isEnabled())
            return;
        System.out.println("entered!");
        try {
            //this.setSpeed(50);
            for (int i = 0; i < 5; ++i) {
                forwards();
                System.out.println("should run...");
                Thread.sleep(250);
                backwards();
                Thread.sleep(250);

            }
            disableMotors();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }





    private void disableMotors() {
        this.vooruit_links.low();
        this.vooruit_rechts.low();
        this.achteruit_links.low();
        this.achteruit_rechts.low();
    }

    private void forwards() {
        this.disableMotors();
        this.vooruit_rechts.high();
        this.vooruit_links.high();
    }

    private void backwards() {
        this.disableMotors();
        this.achteruit_rechts.high();
        this.achteruit_links.high();
    }

    public void steer(int richting){
        if(richting == 3)
            forwards();
        else if(richting >= 4)
            steerRight();
        else if(richting <= 2)
            steerLeft();
    }
    private void steerRight(){
        this.disableMotors();
        this.vooruit_links.high();
    }
    private void steerLeft(){
        this.disableMotors();
        this.vooruit_rechts.high();
    }

    private void setSpeed(int procent) throws InterruptedException {

        SoftPwm.softPwmCreate(this.achteruit_links.getPin().getAddress(), 0, 100);
        SoftPwm.softPwmCreate(this.achteruit_rechts.getPin().getAddress(), 0, 100);
        SoftPwm.softPwmCreate(this.vooruit_links.getPin().getAddress(), 0, 100);
        SoftPwm.softPwmCreate(this.vooruit_rechts.getPin().getAddress(), 0, 100);

        SoftPwm.softPwmWrite(this.achteruit_links.getPin().getAddress(), procent);
        SoftPwm.softPwmWrite(this.achteruit_rechts.getPin().getAddress(), procent);
        SoftPwm.softPwmWrite(this.vooruit_links.getPin().getAddress(), procent);
        SoftPwm.softPwmWrite(this.vooruit_rechts.getPin().getAddress(), procent);
    }
}
