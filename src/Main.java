import agv.utils.Motor;
import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;

import javax.sound.midi.Soundbank;

public class Main {

   //public static void main(String[] args) {
   //    System.out.println("jokers");

   //    GpioController gpio = GpioFactory.getInstance();

   //    GpioPinDigitalOutput pinA = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27);
   //    int test = 0;
   //    while (true) {
   //        test++;

   //        pinA.high();
   //       // if(test > 10000000){
   //       //     System.out.println("break");
   //       //     break;
   //        }
   //    }


    private static final int PIN_NUMBER = 27;
    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.getProperty("system.os"));
        System.out.println("Started");
        // initialize wiringPi library, this is needed for PWM
    //Gpio.wiringPiSetup();
    //// softPwmCreate(int pin, int value, int range)
    //// the range is set like (min=0 ; max=100)
    //SoftPwm.softPwmCreate(PIN_NUMBER, 0, 100);
    //setSpeed(25);
    //setSpeed(50);
    //setSpeed(100);
    //setSpeed(0);
    //System.out.println("Finished");
        new Motor().runMotor();
     //   mains();
    }
    private static void setSpeed(int speed) throws InterruptedException {
        System.out.println("Speed is set to " + speed + "%");
        // softPwmWrite(int pin, int value)
        // This updates the PWM value on the given pin. The value is checked to
        // be in-range and pins that haven't previously been initialized via
        // softPwmCreate will be silently ignored.
        SoftPwm.softPwmWrite(PIN_NUMBER, speed);
        // wait 3 seconds
        Thread.sleep(3000);
    }

    //GPIO Pins
    private static GpioPinDigitalOutput sensorTriggerPin ;
    private static GpioPinDigitalInput sensorEchoPin ;


    final static GpioController gpio = GpioFactory.getInstance();

    public static void mains()
    {
        sensorTriggerPin =  gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03); // Trigger pin as OUTPUT
        sensorEchoPin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02,PinPullResistance.PULL_DOWN); // Echo pin as INPUT

        while(true){
            try {
                Thread.sleep(2000);
                sensorTriggerPin.high(); // Make trigger pin HIGH
                Thread.sleep((long) 0.01);// Delay for 10 microseconds
                sensorTriggerPin.low(); //Make trigger pin LOW

                while(sensorEchoPin.isLow()){ //Wait until the ECHO pin gets HIGH

                }
                long startTime= System.nanoTime(); // Store the surrent time to calculate ECHO pin HIGH time.
                while(sensorEchoPin.isHigh()){ //Wait until the ECHO pin gets LOW

                }
                long endTime= System.nanoTime(); // Store the echo pin HIGH end time to calculate ECHO pin HIGH time.

                System.out.println("Distance :"+((((endTime-startTime)/1e3)/2) / 29.1) +" cm"); //Printing out the distance in cm
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


