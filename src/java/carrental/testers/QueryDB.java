package carrental.testers;


import carrental.helper.CarRentalLogger;
import carrental.helper.XMLHelper;

import carrental.simulate.OperationScheduler;

import java.util.Timer;


public class QueryDB {
    public QueryDB() {
        super();
    }

    public enum Letters {
        s,
        m,
        h,
        d,
        y;
    }

    public void run() {
        //code

    }

    public static void main(String[] args) {
        try {
            XMLHelper myXMLHelper = new XMLHelper();
            CarRentalLogger carLogger = new CarRentalLogger();
            String[] myOps = myXMLHelper.getOperationSettingParamsFromXML();
            String running = myOps[0];
            String operations = myOps[1];
            String interval = myOps[2];
            String intervalLetter = myOps[3];
            String length = myOps[4];
            String lengthLetter = myOps[5];
            int iSeconds = 0;
            int lSeconds = 0;
//            OperationScheduler myOpsScheduler = new OperationScheduler();
            Timer timer = new Timer(true);
            String intervalNumber = "asdf";


            for (int i = 0; i < myOps.length; i++) {
                carLogger.Logger(myOps[i]);

            }


            carLogger.Logger("interval is: " + interval);
            carLogger.Logger("intervalLetter is: " + intervalLetter);
            carLogger.Logger("period is: " + length);
            carLogger.Logger("periodLetter is: " + lengthLetter);


            Letters iLetter = Letters.valueOf(intervalLetter.toLowerCase());
            Letters lLetter = Letters.valueOf(lengthLetter.toLowerCase());
            carLogger.Logger("-------------- begin switch -------------");
            switch (iLetter) {
            case s:
                carLogger.Logger("iLetter Case is s!");
                iSeconds = Integer.parseInt(interval.substring(0, (interval.length() - 1)));
                break;
            case m:
                carLogger.Logger("iLetter Case is m!");
                iSeconds = (Integer.parseInt(interval.substring(0, (interval.length() - 1))) * 60);
                break;
            case h:
                carLogger.Logger("iLetter Case is h!");
                iSeconds = (Integer.parseInt(interval.substring(0, (interval.length() - 1))) * 60 * 60);
                break;
            case y:
                carLogger.Logger("iLetter Case is indefinitely!");
                iSeconds = 0;
                break;
            }
            carLogger.Logger("Total interval seconds is: " + iSeconds);
            switch (lLetter) {
            case h:
                carLogger.Logger("lLetter Case is m!");
                lSeconds = (Integer.parseInt(length.substring(0, (length.length() - 1))) * 60 * 60);
                break;
            case d:
                carLogger.Logger("lLetter Case is h!");
                lSeconds = (Integer.parseInt(length.substring(0, (length.length() - 1))) * 60 * 60 * 24);
                break;
            case y:
                carLogger.Logger("lLetter Case is indefinitely!");
                lSeconds = 0;
                break;
            }

            carLogger.Logger("Total length  seconds is: " + lSeconds);
            // scheduled code
            
            //timer.scheduleAtFixedRate(new OperationScheduler(), 0, (iSeconds * 1000));
            //            timer.scheduleAtFixedRate(new StatisticsTask(), 0, oneQuartInMillis);


        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }


    }
}
