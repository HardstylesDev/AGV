package agv;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.Runnable;
import jssc.SerialPort;
import jssc.SerialPortException;
public class Kleursensor extends Thread
{
    public String kleur;
    public boolean bezet;

//    public HTTPRequest httpRequest = new HTTPRequest();

    public void Geefkleur()
    {
        SerialPort serialPort = new SerialPort("/dev/ttyUSB0");
        try
        {
            System.out.println("Port opened: " + serialPort.openPort());

            try
            {
                Thread.sleep(2000);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(Kleursensor.class.getName()).log(Level.SEVERE, null, ex);
            }


            System.out.println("Params setted: " + serialPort.setParams(9600, 8, 1, 0));




            while (true)
            {
                Thread.sleep(1000);
                kleur = serialPort.readString();
                if (kleur != null)
                {
//                    if(kleur.contains("rood")){
//                        kleur = "rood";}
//                    else if (kleur.contains("bruin")){
//                        kleur = "bruin";
//                    }
//                    else if (kleur.contains("geel"))
//                    {
//                        kleur = "geel";
//                    }
//                    else if (kleur.contains("grijs"))
//                    {
//                        kleur = "grijs";
//                    }
//                    else if (kleur.contains("blauw"))
//                    {
//                        kleur = "blauw";
//                    }
//                    else if (kleur.contains("wit"))
//                    {
//                        kleur = "wit";
//                    }
//                    else if (kleur.contains("oranje"))
//                    {
//                        kleur = "oranje";
//                    }
//                    else if (kleur.contains("groen"))
//                    {
//                        kleur = "groen";
//                    }

                    kleur = kleur.replaceAll("\t", "").replaceAll(" ","").replaceAll("\n","");
                    System.out.println(kleur);
                    //Main.kleurwaarde = kleur;
                    //System.out.println(kleur);

                    //this.bezet = Station.isStationAvailable(kleur);
                      //System.out.println("Kleur > "+ kleur + " -> " +  bezet);

                }

            }

        } catch (SerialPortException ex)
        {
            System.out.println(ex);

        } catch (Exception exception)
        {
            System.out.println(exception);
        }
    }

}