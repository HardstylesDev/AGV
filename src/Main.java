import agv.Camera;
import agv.Component;
import agv.Motor.TrackingSensor;
import agv.Motor.Motor;
import agv.pins.Outputs;
import agv.Motor.Ultrasoon;
import agv.status.OrderManager;
import agv.stoplicht.Stoplicht;
import agv.test.Servo;

import java.lang.reflect.Constructor;

public class Main {

    public static void main(String[] args) throws Exception {
        //Camera.main(new String[]{});
        System.out.println(Outputs.TRACKING_SENSOR_3);
        Class<? extends Component>[] COMPONENTS = new Class[]{
                Motor.class,
                TrackingSensor.class,
                Ultrasoon.class,
                Stoplicht.class,
                Servo.class,
                OrderManager.class//,
              //  Camera.class
        };

        for (Class<? extends Component> component : COMPONENTS) {
            Class<? extends Component> clazz = component;
            Constructor<? extends Component> constructor = clazz.getConstructor();
            Component componentInstance = constructor.newInstance();
            if (!(componentInstance instanceof Motor)) {
                componentInstance.setEnabled(true);
                componentInstance.setDebugOutput(false);
            }
            if (componentInstance instanceof OrderManager)
                componentInstance.setDebugOutput(true);
        }
    }
}


//  private static void setSpeed(int speed) throws InterruptedException {
//      System.out.println("Speed is set to " + speed + "%");
//      // softPwmWrite(int pin, int value)
//      // This updates the PWM value on the given pin. The value is checked to
//      // be in-range and pins that haven't previously been initialized via
//      // softPwmCreate will be silently ignored.
//      SoftPwm.softPwmWrite(PIN_NUMBER, speed);
//      // wait 3 seconds
//      Thread.sleep(3000);
//  }