package agv.utils;

import com.pi4j.io.gpio.RaspiPin;
//public class UltrasonicSensor
//{
 //  public static void meet() {
 //      Thread thread = new Thread(() ->
 //      {
 //          PiJavaUltrasonic sonic = new PiJavaUltrasonic(
 //                  21,//ECO PIN (physical 33)
 //                  30,//TRIG PIN (pysical 35)
 //                  1000,//REJECTION_START ; long (nano seconds)
 //                  23529411 //REJECTION_TIME ; long (nano seconds)
 //          );
 //          System.out.println("Start");
 //          while (true)
 //          {
 //              try
 //              {
 //                  System.out.println("distance " + sonic.getDistance() + "mm");
 //                  Thread.sleep(1000); //1s
 //              } catch (Exception e)
 //              {
 //                  e.printStackTrace();
 //              }
 //          }
 //      });
 //      thread.start();
 //  }
//}