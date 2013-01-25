package carrental.helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;


public class CarRentalLogger {

    //    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    //    Calendar cal = Calendar.getInstance();
    //    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
    //    //public String appender = ("[" + sdf.format(cal.getTime() + "]" + "[CarRental]:"));


    public CarRentalLogger() {
        super();

    }

    public void Logger(String _message) {
        String message = _message;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String appender = ("[" + dateFormat.format(date) + "]" + "[CarRental]: ");
        System.out.println(appender + message);
    }

    public void Logger(String _message, Exception _e) {
        Exception e = _e;
        String message = _message;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String appender = ("[" + dateFormat.format(date) + "]" + "[CarRental]: ");
        System.out.println(appender + message);
        e.printStackTrace();
    }

    public void LoadLogger(String _message, String _type) {
        

        try {
            String text = "fdsa";
//            FileWriter write = new FileWriter(new File("C:\\Files\\CarRental\\CarRental\\SimulatedData.txt"), true);
            FileWriter write = new FileWriter(new File("SimulatedData.txt"), true);
            BufferedWriter writer = new BufferedWriter(write);

            String message = _message;
            String type = _type;
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String loadAppender = ("[" + dateFormat.format(date) + "]");
            if (type.toLowerCase() == "sqlstatement") {
                System.out.println(loadAppender + "[SQLStatement]: " + message);
                text = (loadAppender + "[SQLStatement]: " + message);
                //                output.write(text + "\r\n");
                //                logger.info(text);
                writer.write(text);
                writer.newLine();
                writer.close();
            } else if (type.toLowerCase() == "method") {
                System.out.println(loadAppender + "[Method]: " + message);
                text = (loadAppender + "[Method]: " + message);
                //                output.write(text + "\r\n");
                //                logger.info(text);
                writer.write(text);
                writer.newLine();
                writer.close();
            } else {
                System.out.println(loadAppender + "[Action]: " + message);
                text = (loadAppender + "[Action]: " + message);
                //                output.write(text + "\r\n");
                writer.write(text);
                writer.newLine();
                writer.close();
                //                logger.info(text);
            }
            //            print_line.close();
            writer.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

    public void CreateLoadLogger() {
        CarRentalLogger carLogger = new CarRentalLogger();
//        File myFile = new File("C:\\Files\\Support\\SimulatedData.txt");
        File myFile = new File("SimulatedData.txt");
        try {
            myFile.createNewFile();
            carLogger.Logger("Created file SimulatedData.txt in app server context root.");
        } catch (IOException e) {
            carLogger.Logger("Exception creating SimulatedData.txt log.");
            carLogger.Logger("IOException: " + e, e);
        }
    }

//    public static void main(String[] args) {
//        CarRentalLogger myLogger = new CarRentalLogger();
//        myLogger.LoadLogger("my message", "sqlstatement");
//        myLogger.LoadLogger("my second message", "action");
//        myLogger.LoadLogger("my third message", "method");
//
//    }
}
