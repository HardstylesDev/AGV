package agv.test;

import agv.Component;
import agv.Outputs.Outputs;
import agv.utils.Multithread;
import com.pi4j.component.servo.ServoDriver;
import com.pi4j.component.servo.ServoProvider;
import com.pi4j.component.servo.impl.RPIServoBlasterProvider;

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
        {
            System.out.println("its hereeee");
            int pin = 12;
            try {
                System.out.println("true");
                debug("Try");
                Runtime runTime = Runtime.getRuntime();
                runTime.exec("gpio mode " + pin + " pwm");
                runTime.exec("gpio pwm-ms");
                runTime.exec("gpio pwmc 192");
                runTime.exec("gpio pwmr 2000");
                runTime.exec("gpio pwm " + pin + " 152"); // center
                debug("sleep");
                Thread.sleep(5000);
                runTime.exec("gpio pwm " + pin + " ME"); // turn right
                Thread.sleep(3000);
                runTime.exec("gpio pwm " + pin + " 200"); // turn left
                Thread.sleep(3000);
                int i = 100;
                boolean turningLeft = true;
                while (this.isEnabled()) {
                    runTime.exec("gpio pwm " + pin + " " + i);
                    Thread.sleep(10);
                    if (turningLeft) {
                        i++;
                    } else {
                        i--;
                    }
                    if (i > 200) {
                        turningLeft = false;
                    }
                    if (i < 100) {
                        turningLeft = true;
                    }
                }

            } catch (Exception e) {
                System.out.println("Exception occured: " + e.getMessage());
            }
        }
    }
}
        
