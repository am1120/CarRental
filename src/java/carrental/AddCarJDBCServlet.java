package carrental;

import carrental.helper.CarRentalLogger;
import carrental.helper.DatabaseOperations;
import carrental.helper.XMLHelper;

import java.io.IOException;

import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCarJDBCServlet
        extends HttpServlet {

    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config)
            throws ServletException {
        super.init(config);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DatabaseOperations myDbOps = new DatabaseOperations();
        response.setContentType(CONTENT_TYPE);
        XMLHelper myXMLHelper = new XMLHelper();
        CarRentalLogger carLogger = new CarRentalLogger();
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>AddCarJDBCServlet</title></head><body>");
        out.println("</body></html>");
        // get parameters from the form
        String name = request.getParameter("name");
        String miles = request.getParameter("miles");
        String dbtype = request.getParameter("dbtype");

        myDbOps.insertQuery(name, miles);
        response.sendRedirect("listcarsjdbc"); // redirect to list page
    }
}
