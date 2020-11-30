package agv;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class Pins {
    // pin 1 = links-vooruit
    // pin 2 = rechts-vooruit

    // pin 0 = links-achteruit
    // pin 3 = rechts-achteruit
    public static Pin VOORUIT_RECHTS = RaspiPin.GPIO_02;
    public static Pin VOORUIT_LINKS = RaspiPin.GPIO_01;

    public static Pin ACHTERUIT_LINKS = RaspiPin.GPIO_00;
    public static Pin ACHTERUIT_RECHTS = RaspiPin.GPIO_03;


}
