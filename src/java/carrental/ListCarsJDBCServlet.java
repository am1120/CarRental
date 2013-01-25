package carrental;

import carrental.helper.CarRentalLogger;
import carrental.helper.DatabaseOperations;
import carrental.helper.XMLHelper;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListCarsJDBCServlet
        extends HttpServlet {

    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config)
            throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DatabaseOperations myDbOps = new DatabaseOperations();
        XMLHelper myXMLHelper = new XMLHelper();
        CarRentalLogger carLogger = new CarRentalLogger();
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        ResultSet rs;
        rs = myDbOps.selectFromTable();
        out.println("<html>");
        out.println("<head><title>TestJDBCServlet</title></head>");
        out.println("<link type=\"text/css\" rel=\"stylesheet\" href=\"css/jdeveloper.css\"/>");
        out.println("<body bgcolor='white'>");
        out.println("<h1>List JDBC Cars</h1>");
        
//        TODO: how many cars in fleet?
//        try adding num of cars to XML file
//        out.println("<p>You currently have <b>" +  + "</b> Cars in your Fleet:</p><br/>");

        try {
            if (rs != null) {
                out.println("<table class=\"borders\" cellspacing=\"2\" cellpadding=\"3\" border=\"1\">");
                out.println("<tr>");
                out.println("    <th>Car Id</th>");
                out.println("    <th>Car Name</th>");
                out.println("    <th>Car Miles</th>");
                out.println("</tr>");
                while (rs.next()) {
                    out.println("<tr>");
                    out.println("    <td>" + rs.getString("P_Id") + "</td>");
                    out.println("    <td>" + rs.getString("Name") + "</td>");
                    out.println("    <td>" + rs.getString("Miles") + "</td>");
                    out.println("</tr>");
                }
                out.println("</p>");
                out.println("</table>");
            } else {
                out.println("<code>Result Set is null!  Verify database connectivity first.");
            }

        } catch (SQLException e) {
            carLogger.Logger("SQLException: " + e, e);

        }
        out.println("<h3><a href=\"home.jsp\"> Home </a></h3>");
        out.println("<h3><a href=\"addCarJDBC.jsp\"> Add a new Rental Car </a></h3>");
        out.println("<h3><a href=\"configDB.jsp\"> Configure Database Connectivity </a></h3>");
        out.println("</body></html>");
        out.close();
    }
}
