package agv;

import agv.utils.Multithread;
import jssc.SerialPort;
import jssc.SerialPortException;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Kleursensor extends Component {
    public Kleursensor() {
        super("Kleursensor");
    }

    @Override
    public void onEnable() {
        Multithread.execute(this::start);
    }

    int delay = 1000;

    public void start() {
        if (!this.isEnabled())
            return;
        GeefKleur();
        while (this.isEnabled()) {
            try {

                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
        }
    }
//    pipotje dans
    private void init(){}
    public String waarde;

    public String GeefKleur() {
        SerialPort serialPort = new SerialPort("/dev/ttyUSB0");
        try {
            System.out.println("Port opened: " + serialPort.openPort());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Kleursensor.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("Params setted: " + serialPort.setParams(9600, 8, 1, 0));
            while (true) {
                Thread.sleep(1000);
                waarde = serialPort.readString();
                if (waarde != null) {
                    System.out.println(waarde.replaceAll("\t", ""));

                }
            }
        } catch (SerialPortException ex) {
            System.out.println(ex);
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return waarde;
    }
}
