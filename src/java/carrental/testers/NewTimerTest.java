package carrental.testers;

import java.util.Timer;
import java.util.TimerTask;

public class NewTimerTest {

    Timer timer;

    public int ConvertToSeconds(int _number, char _period) {
        int number = _number;
        char period = _period;
        int temp = 0;
        int milisecondsTotal = 0;
        if (period == 's') {
            milisecondsTotal = number * 1000;
        }
        if (period == 'h') {
            milisecondsTotal = 123456789;
        }

        //60s, 1h, 1w

        System.out.println("milisecondsTotal: " + milisecondsTotal);
        return milisecondsTotal;
    }

    public  NewTimerTest(String _time) {
        String time = _time;
        System.out.println("Time var is: " + time);
        if (time != "indefinitely") {
            int seconds = 0;
            //example input 60s
            int length = Integer.parseInt(time.substring(0, (time.length() - 1)));
            char interval = time.substring(time.length() - 1).charAt(0);
            System.out.println("length: " + length + " interval: " + interval);
            int totalTimeInMS = ConvertToSeconds(length, interval);


            timer = new Timer();
            System.out.println("Scheduling task with the following:");
            timer.scheduleAtFixedRate(new RepeatTask(), 1000, totalTimeInMS);
//            timer.schedule(new RepeatTask(), totalTimeInMS);
        } else {
            System.out.println("Indefinitely.");
        }
    }

    class RepeatTask extends TimerTask {
        public void run() {
            System.out.println("ASDF");
        }
    }

    public static void main(String[] args) {
        new NewTimerTest("5s");
//        NewTimerTest timer = new NewTimerTest();
        
        System.out.println("Task scheduled.");
    }
//    
//    Answer 
//    Use the scheduleAtFixedRate() method of the java.util.Timer class:
//
//    int initialDelay = 30000; // start after 30 seconds
//    int period = 5000;        // repeat every 5 seconds
//    Timer timer = new Timer();
//    TimerTask task = new TimerTask() {
//      public void run() {
//        // job code here
//      }
//    };
//    timer.scheduleAtFixedRate(task, initialDelay, period);
}
