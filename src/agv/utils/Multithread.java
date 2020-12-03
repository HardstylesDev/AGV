package agv.utils;

public class Multithread {
    public static void execute(Runnable r) {
        new Thread(new Runnable() {
            public void run() {
                r.run();
            }
        }).start();
    }
}
