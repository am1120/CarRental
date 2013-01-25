package carrental.testers;

import carrental.helper.CarRentalLogger;
import carrental.helper.XMLHelper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;
import java.util.Date;

public class TimerTest {

    static Timer timer = new Timer();
    private static SecondsCounter counter;
    private static int counters = 0;
    private static int number;
    static java.util.Date date = new java.util.Date();
    private boolean running;
    private XMLHelper myXMLHelper = new XMLHelper();
    private CarRentalLogger carLogger = new CarRentalLogger();

    public TimerTest(SecondsCounter c, double speed, int num) { //int seconds, double speed) {
        counter = c;
        number = num;
        timer = new java.util.Timer();
        long interval = Math.round(1000 / speed);
        System.out.println("Interval is: " + interval);
        System.out.println("Number is: " + number);

        timer.schedule(new RepeatTask5o_10i_1l(), 2000);


    }

//    void resetSpeed(double speed) {
//        timer.cancel();
//        timer = new java.util.Timer();
//        timer.scheduleAtFixedRate(new RepeatTask(), 0, Math.round(1000 / speed));
//    }
    void stopTask() {
        timer.cancel();
        System.out.println("finished!");
        System.exit(0);
    }

    class RepeatTask5o_10i_1l extends TimerTask {

        private final int operations = 5;

        public void run() {
            System.out.println(new Timestamp(date.getTime()) + ": counter: " + counters++);
//            System.exit(0);
        }
    }

    public static void main(String[] args) {
//        try {
        boolean running = true;
        String input = "";
        InputStreamReader converter = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(converter);
//            TimerTest myTimerTest = new TimerTest(counter, 1, 2);
        TimerTest myTimerTest = new TimerTest(counter, 1, 4);
        if (counters >= 5) {
            myTimerTest.stopTask();
        }
        System.out.println("Done");

    }
}
