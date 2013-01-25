package carrental.simulate;


import carrental.helper.CarRentalLogger;
import carrental.helper.XMLHelper;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SimulateLoadServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        //        DatabaseOperations myDbOps = new DatabaseOperations();
        CarRentalLogger carLogger = new CarRentalLogger();
        XMLHelper myXMLHelper = new XMLHelper();
        File myFile = new File("simulateLoad-settings.xml");
        String operations = request.getParameter("operations");
        String interval = request.getParameter("interval");
        String length = request.getParameter("length");
        String running = request.getParameter("running");

        if (running != null) {
            myXMLHelper.saveOperationsSettings("yes", operations, interval, length);
            carLogger.Logger("Simulating " + operations + " operations over a " + interval + " interval for " + length + ".  Check SimulatedData.txt for statements executed.");
            carLogger.LoadLogger(" ---------------------------- STARTING ----------------------------", "action");
            
            response.sendRedirect("SimulateLoadDisplay.jsp");
        } else {
            myXMLHelper.saveOperationsSettings("no", operations, interval, length);
            response.addHeader("Refresh", "0");
            carLogger.Logger("Stopping simulating load ..");
            carLogger.LoadLogger(" ---------------------------- STOPPING ----------------------------", "action");
            response.sendRedirect("home.jsp");
        }


        // TODO: check for null insert/update/delete values to possibly set them as true here


        //        response.sendRedirect("simulateloadwork");

    }


}
