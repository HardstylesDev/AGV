package agv.Motor;

import agv.Component;
import agv.utils.Multithread;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.RaspiPin;

@SuppressWarnings("SpellCheckingInspection")
public class Detectie extends Component {
    public Detectie() {
        super("Lijn Detectie");
    }
    // 1: 23 -> 4
    // 2: 24 -> 5
    // 3: 25 -> 6
    // 4: 4 -> 7
    // 5: 5 -> 21

    @Override
    public void onEnable() { Multithread.execute(this::start); }

    private void start() {
        while (this.isEnabled()) {
            System.out.println(sensor1.getState() + " | " + sensoraaa.getState() + " | " + sensor2.getState() + " | " + sensor4.getState() + " | " + sensor3.getState());
            try {
                Thread.sleep(50);
            } catch (InterruptedException ignored) {
            }
        }
        update();
    }


    final GpioController gpio = GpioFactory.getInstance();

    // provision gpio pin #02 as an input pin with its internal pull down resistor enabled
    final GpioPinDigitalInput sensor1 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_21); // eerste sensor
    final GpioPinDigitalInput sensor3 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_04); // laatste sensor
    final GpioPinDigitalInput sensor4 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_05); // 1 positie voor laatste sensor
    final GpioPinDigitalInput sensor2 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_06); // 1 positie voor laatste sensor
    final GpioPinDigitalInput sensoraaa = gpio.provisionDigitalInputPin(RaspiPin.GPIO_24); // 2 positie voor laatste sensor

    // final GpioPinDigitalInput sensor5 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_06); // sensor 5

    public void update(){
        if(sensor1.isLow())
            new Motor().steer(1);
        if(sensor2.isLow())
            new Motor().steer(2);
        if(sensor3.isLow())
            new Motor().steer(3);
        if(sensor4.isLow())
            new Motor().steer(4);
        if(sensor5.isLow())
            new Motor().steer(5);
    }

    

}
