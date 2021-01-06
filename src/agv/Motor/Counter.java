package agv.Motor;

public class Counter {
    private int ticks;
    private long lastTick = 0;

    public void add() {
        if (lastTick - System.currentTimeMillis() > 500) {
            ticks++;
            lastTick = System.currentTimeMillis();
            if (ticks >= 8)
                ticks = 0;
        }
    }

    public int getTicks() {
        return ticks;
    }

    public void reset() {
        lastTick = 0;
        ticks = 0;
    }

    public void set(int newTicks) {
        ticks = newTicks;
    }
}
