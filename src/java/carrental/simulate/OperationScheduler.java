package carrental.simulate;

import carrental.helper.CarRentalLogger;
import carrental.helper.XMLHelper;

import java.util.Timer;
import java.util.TimerTask;

public class OperationScheduler { // extends TimerTask {

    Timer timer;
    private static SecondsCounter counter;
    private static CarRentalLogger carLogger = new CarRentalLogger();
    private static XMLHelper myXMLHelper = new XMLHelper();
    private static SimulateWork mySimMethods = new SimulateWork();
    private String conUrl = myXMLHelper.getConnectionStringFromXMLNoLogging();
    private String[] params = myXMLHelper.getConnectionParamsFromXML();

    public OperationScheduler(SecondsCounter c, double speed) { //int seconds, double speed) {
        counter = c;
        timer = new java.util.Timer();
        long interval = Math.round(1000 / speed);
        timer.scheduleAtFixedRate(new RepeatTask(), 0, interval);
        System.out.println("Interval is: " + interval);
//        timer = new Timer();
//        timer.schedule(new RepeatTask(), seconds * 1000);
//        System.out.println("Task scheduled.");
    }

    void resetSpeed(double speed) {
        timer.cancel();
        timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new RepeatTask(), 0, Math.round(1000 / speed));
    }

    class RepeatTask extends TimerTask {

        public void run() {
            System.out.println("ASDF");
//            System.exit(0);
        }
    }
}