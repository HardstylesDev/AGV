package agv.pins;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class Pins {

    public static Pin VOORUIT_RECHTS = RaspiPin.GPIO_02;
    public static Pin VOORUIT_LINKS = RaspiPin.GPIO_01;

    public static Pin ACHTERUIT_LINKS = RaspiPin.GPIO_00;
    public static Pin ACHTERUIT_RECHTS = RaspiPin.GPIO_03;


    public static Pin LIJN_SENSOR_1 = RaspiPin.GPIO_21;
    public static Pin LIJN_SENSOR_2 = RaspiPin.GPIO_27;
    public static Pin LIJN_SENSOR_3 = RaspiPin.GPIO_06;
    public static Pin LIJN_SENSOR_4 = RaspiPin.GPIO_05;
    public static Pin LIJN_SENSOR_5 = RaspiPin.GPIO_04;


    public static Pin STOPLICHT_GROEN = RaspiPin.GPIO_15; // 14
    public static Pin STOPLICHT_GEEL = RaspiPin.GPIO_10; // 8
    public static Pin STOPLICHT_ROOD = RaspiPin.GPIO_11; // 7


    public static Pin SERVO = RaspiPin.GPIO_26; // 7


}
