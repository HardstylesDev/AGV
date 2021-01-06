package agv.utils;

public class Multithread {
    /**
     * Met deze class maken we een nieuwe thread aan. Dit doen we om te voorkomen dat het hele programma stopt
     * wanneer we een delay willen gebruiken ('sleep')
     * @param r De runnable, Deze methode specificeerd wat er precies op een nieuwe thread moet lopen.
     */
    public static void execute(Runnable r) {
        new Thread(new Runnable() {
            public void run() {
                r.run();
            }
        }).start();
    }
}
