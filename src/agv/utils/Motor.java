package agv.utils;

import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.Gpio;

public class Motor extends Component {

    public Motor() {
        super("Motoren");
    }

    @Override
    public void onDisable() {
        System.out.println("Motor disabled");
    }
    // private 17, 18, 27, 22

    static GpioController gpio = GpioFactory.getInstance();

    //  static GpioPinDigitalOutput pinDONE = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "a", PinState.LOW);
    //static GpioPinDigitalOutput pinD = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "a", PinState.LOW);
    //  static GpioPinDigitalOutput pinOK = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "a", PinState.LOW);
    // static GpioPinDigitalOutput pinF = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "a", PinState.LOW);

    //  // static GpioPinDigitalOutput pinJ = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "PinJ", PinState.LOW);
    //  static GpioPinDigitalOutput pinK = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_12, "PinK", PinState.LOW);
    //  static GpioPinDigitalOutput pinL = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_13, "PinL", PinState.LOW);
    //  static GpioPinDigitalOutput pinL1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_14, "PinL", PinState.LOW);
    //  static GpioPinDigitalOutput pinL2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_15, "PinL", PinState.LOW);
    //  static GpioPinDigitalOutput pinL3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_16, "PinL", PinState.LOW);
    //  static GpioPinDigitalOutput pinL4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_17, "PinL", PinState.LOW);
    //  //static GpioPinDigitalOutput pinL5 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_18, "PinL", PinState.LOW);
    //  static GpioPinDigitalOutput pinL6 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_19, "PinL", PinState.LOW);
    //  static GpioPinDigitalOutput pinL7 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_20, "PinL", PinState.LOW);
    //  static GpioPinDigitalOutput pinL8 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_21, "PinL", PinState.LOW);
    //  static GpioPinDigitalOutput pinL9 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_22, "PinL", PinState.LOW);
    //  static GpioPinDigitalOutput pinL10 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_23, "PinL", PinState.LOW);
    //  static GpioPinDigitalOutput pinL11 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_24, "PinL", PinState.LOW);
    //  static GpioPinDigitalOutput pinL12 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "PinL", PinState.LOW);
    //  static GpioPinDigitalOutput pinL13 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_26, "PinL", PinState.LOW);
    //  static GpioPinDigitalOutput pinL14 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27, "PinL", PinState.LOW);
    //  static GpioPinDigitalOutput pinL15 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28, "PinL", PinState.LOW);
    //  static GpioPinDigitalOutput pinL16 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29, "PinL", PinState.LOW);
    //  static GpioPinDigitalOutput pinL17 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_30, "PinL", PinState.LOW);


    //  static GpioPinDigitalOutput pinM = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_10, "PinM", PinState.LOW);
    // static GpioPinDigitalOutput pinN = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_11, "PinN", PinState.LOW);

    //private static Pin MOTOR_PIN_1 = RaspiPin.GPIO_17;
    //private static Pin MOTOR_PIN_2 = RaspiPin.GPIO_18;
    //private static Pin MOTOR_PIN_3 = RaspiPin.GPIO_27;
    //private static Pin MOTOR_PIN_4 = RaspiPin.GPIO_22;


    public void runMotor() throws InterruptedException {
      // if(!this.isEnabled())
      //     return;

        //GpioPinDigitalOutput pinn = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "");
        //pinn.high();

        //Thread.sleep(3000);
        //pinn.low();
        //  pinD.high();
        //  pinF.high();


        //pinOK.low();
        //pinDONE.low();

        //Thread.sleep(1000);
        ////pinC.low();
        //pinD.low();
        //pinF.low();


        for (Pin pin : RaspiPin.allPins()) {
            try {
                GpioPinDigitalOutput pinn = gpio.provisionDigitalOutputPin(pin, "");
                System.out.println("Pin: " + pinn.getName());
                pinn.high();
                Thread.sleep(500);
                pinn.low();
                Thread.sleep(250);
                // Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error with: " + pin.getName());
            }
        }


    }

    private static void setSpeed(int speed) throws InterruptedException {
        System.out.println("Speed is set to " + speed + "%");
        // softPwmWrite(int pin, int value)
        // This updates the PWM value on the given pin. The value is checked to
        // be in-range and pins that haven't previously been initialized via
        // softPwmCreate will be silently ignored.
        // SoftPwm.softPwmWrite(22, speed);
        // wait 3 seconds
        Thread.sleep(3000);
    }


    //public static void vooruit(){
    //    pinD.high();
    //    pinF.low();
    //    pinOK.high();
    //    pinDONE.low();
    //}
}
