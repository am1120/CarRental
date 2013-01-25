package carrental.simulate;


import carrental.helper.CarRentalLogger;
import carrental.helper.XMLHelper;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SaveOperationsServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        XMLHelper myXMLHelper = new XMLHelper();
        CarRentalLogger carLogger = new CarRentalLogger();
        String insert = request.getParameter("chkBoxInsert");
        String update = request.getParameter("chkBoxUpdate");
        String delete = request.getParameter("chkBoxDelete");
        if (!(insert == null))
            insert = "true";
        else
            insert = "false"; // can never happen
        if (!(update == null))
            update = "true";
        else
            update = "false";
        if (!(delete == null))
            delete = "true";
        else
            delete = "false";
        carLogger.Logger("simulating inserts? " + insert);
        carLogger.Logger("simulating updates? " + update);
        carLogger.Logger("simulating deletes? " + delete);
        myXMLHelper.saveSimulateSettings(insert, update, delete);
        
        response.sendRedirect("simulateLoad.jsp");
    }
}
