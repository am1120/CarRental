package carrental.testers;

import java.util.Observable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class SecondsCounter extends Observable {

    private AtomicLong seconds = new AtomicLong();  // the count
    private AtomicBoolean running = new AtomicBoolean();
    public TimerTest ticker;

    
    public SecondsCounter(double speed, long secs, boolean run) { // constructor
        seconds.set(secs);
        running.set(run);
        ticker = new TimerTest(this, speed, 0);
    }

    public void set(long s) {
        seconds.set(s);
        setChanged();
        notifyObservers();
    }

    public void tick() {
        seconds.getAndIncrement();
        setChanged();
        notifyObservers();
    }

    public long get() {
        return seconds.get();
    }

    public void setRunning(boolean r) {
        running.set(r);
    }

    public boolean getRunning() {
        return running.get();
    }

//    public void setSpeed(double spd) {
//        ticker.resetSpeed(spd);
//    }
}