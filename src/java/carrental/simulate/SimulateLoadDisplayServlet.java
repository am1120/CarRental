package carrental.simulate;


import carrental.helper.CarRentalLogger;
import carrental.helper.XMLHelper;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SimulateLoadDisplayServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    private static CarRentalLogger carLogger = new CarRentalLogger();
//    private OperationScheduler opsScheduler = new OperationScheduler();
    private ScheduledExecutorService scheduler;
    //    private int refreshCount = -1;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public enum Letters {
        s,
        m,
        h,
        d,
        y;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //        response.setContentType(CONTENT_TYPE);
        doPost(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //        String conUrl = "asdf";
        //        String[] params = myXMLHelper.getConnectionParamsFromXML();
        //        Connection connection;
        //        Statement statement;
        //        ResultSet rs;
        //        String query;
        //String[] myOps = myXMLHelper.getOperationParamsFromXML();
        Timer timer = new Timer(true);
        response.setContentType(CONTENT_TYPE);

//        OperationScheduler myOpsScheduler = new OperationScheduler();
        XMLHelper myXMLHelper = new XMLHelper();
        String[] myOps = myXMLHelper.getOperationSettingParamsFromXML();
        PrintWriter out = response.getWriter();

        String running = myOps[0];
        String operations = myOps[1];
        String interval = myOps[2];
        String intervalLetter = myOps[3];
        String length = myOps[4];
        String lengthLetter = myOps[5];
        int seconds = 0;
        Letters iLetter = Letters.valueOf(intervalLetter.toLowerCase());
        Letters lLetter = Letters.valueOf(lengthLetter.toLowerCase());
        switch (iLetter) {
        case s:
            seconds = Integer.parseInt(interval.substring(0, (interval.length() - 1)));
            break;
        case m:
            seconds = (Integer.parseInt(interval.substring(0, (interval.length() - 1))) * 60);
            break;
        case h:
            seconds = (Integer.parseInt(interval.substring(0, (interval.length() - 1))) * 60 * 60);
            break;
        case y:
            seconds = 0;
            break;
        }
        String intervalSeconds = "asdf";
        String refreshSeconds = "fdsa";
        if (seconds != 0) {
            intervalSeconds = Integer.toString(seconds);
            refreshSeconds = intervalSeconds;
        } else {
            refreshSeconds = Integer.toString(30);
        }
        switch (lLetter) {
        case h:
            seconds = (Integer.parseInt(length.substring(0, (length.length() - 1))) * 60 * 60);
            break;
        case d:
            seconds = (Integer.parseInt(length.substring(0, (length.length() - 1))) * 60 * 60 * 24);
            break;
        case y:
            seconds = 0;
            break;
        }

        response.addHeader("Refresh", refreshSeconds);
        
//        intervalNumber = interval.substring(0, (interval.length()-1));
        
        //        refreshCount++;
        out.println("<html>");
        out.println("<head><title>Simulate Load Servlet</title></head>");
        out.println("<link type=\"text/css\" rel=\"stylesheet\" href=\"css/jdeveloper.css\"/>");
        out.println("<body bgcolor='white'>");
        out.println("<h1>Simulate Load Servlet</h1>");

        
        out.println("<code>Simulating " + operations + " operations over a " + interval + " interval for " + length + ".</code><br/><br/>");
        //        carLogger.Logger("Refresh count: " + refreshCount);
        //        out.println("<code>Page refresh count: " + refreshCount + "</code><br/><br/>");
        out.println("<code>Refreshing page every " + interval + ".</code><br/><br/>");

        out.println("<h3>Recent load simulated:</h3>");
        //        out.println("<td>Some data</td>");
        out.println("<table class =\"borders\" cellspacing=\"2\" cellpadding=\"3\" border=\"1\">");
        out.println("<tr>");
        out.println("    <th>SQL statements:</th>");
        out.println("    <th>Java methods:</th>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("    <td><textarea name=\"txtArea1\" cols=\"75\" rows=\"20\" style=\"background-color:InfoBackground;resize:none;\" readonly=\"readonly\">");

        //        myOpsScheduler.DoWork();
//        carLogger.LoadLogger("my message", "action" );

                
        // beg sql statement data
        out.println("some data");
        // end sql statement data

        out.println("</textarea></td>");
        out.println("    <td><textarea name=\"txtArea2\" cols=\"75\" rows=\"20\" style=\"background-color:InfoBackground;resize:none;\" readonly=\"readonly\">");

        // beg java method data
        out.println("some data");
        // end java method data

        out.println("</textarea></td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<h4>All simulated data is saved to the log \"SimulatedData.txt\".</h4><br/>");
        out.println("<form name=\"simulateload\" action=\"simulateload\" method=\"post\">");
        out.println("<input type=\"submit\" name=\"submit\" value=\"Stop Simulating Load!\"/><br/>");
        out.println("</form>");
        out.println("</body></html>");

//        myOpsScheduler.run();
//        timer.scheduleAtFixedRate(new OperationScheduler(), 1000, (Integer.parseInt(intervalSeconds) * 1000));
//        scheduler = Executors.newSingleThreadScheduledExecutor();
//        scheduler.scheduleAtFixedRate(new OperationScheduler(), 500, (Integer.parseInt(intervalSeconds)), TimeUnit.SECONDS);

        
    }
}
