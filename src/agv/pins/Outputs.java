package agv.pins;

import com.pi4j.io.gpio.*;

public class Outputs {

    /**
     * Maak alle in- en outputs aan. Op deze manier voorkom je errors als je de sensoren in andere klasses wilt gebruiken
     */
    public static final GpioController gpio = GpioFactory.getInstance();
    public static final GpioPinDigitalOutput MOTOR_VOORUIT_RECHTS = gpio.provisionDigitalOutputPin(Pins.VOORUIT_RECHTS);
    public static final GpioPinDigitalOutput MOTOR_VOORUIT_LINKS = gpio.provisionDigitalOutputPin(Pins.VOORUIT_LINKS);
    public static final GpioPinDigitalOutput MOTOR_ACHTERUIT_RECHTS = gpio.provisionDigitalOutputPin(Pins.ACHTERUIT_RECHTS);
    public static final GpioPinDigitalOutput MOTOR_ACHTERUIT_LINKS = gpio.provisionDigitalOutputPin(Pins.ACHTERUIT_LINKS);


    public static final GpioPinDigitalOutput STOPLICHT_GROEN = gpio.provisionDigitalOutputPin(Pins.STOPLICHT_GROEN);
    // //public static final GpioPinDigitalOutput STOPLICHT_ROOD = gpio.provisionDigitalOutputPin(Pins.STOPLICHT_ROOD);
    // public static final GpioPinDigitalOutput STOPLICHT_GEEL = gpio.provisionDigitalOutputPin(Pins.STOPLICHT_GEEL);

    public static final GpioPinDigitalInput TRACKING_SENSOR_1 = gpio.provisionDigitalInputPin(Pins.LIJN_SENSOR_1);
    public static final GpioPinDigitalInput TRACKING_SENSOR_2 = gpio.provisionDigitalInputPin(Pins.LIJN_SENSOR_2);
    public static final GpioPinDigitalInput TRACKING_SENSOR_3 = gpio.provisionDigitalInputPin(Pins.LIJN_SENSOR_3);
    public static final GpioPinDigitalInput TRACKING_SENSOR_4 = gpio.provisionDigitalInputPin(Pins.LIJN_SENSOR_4);
    public static final GpioPinDigitalInput TRACKING_SENSOR_5 = gpio.provisionDigitalInputPin(Pins.LIJN_SENSOR_5);

    public static final GpioPinDigitalOutput SERVO = gpio.provisionDigitalOutputPin(Pins.SERVO);


}
