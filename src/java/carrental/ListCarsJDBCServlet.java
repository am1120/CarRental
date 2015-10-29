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
        out.println("<head><title>CarRental - List Cars for Rental</title></head>");
        out.println("<link type=\"text/css\" rel=\"stylesheet\" href=\"css/jdeveloper.css\"/>");
        out.println("<link type=\"text/css\" rel=\"stylesheet\" href=\"css/bootstrap.min.css\"/>");
        out.println("<link type=\"text/css\" rel=\"stylesheet\" href=\"css/custom.css\"/>");
        out.println("<body>");
        out.println(ListCarsVar.navHtml);

//        TODO: how many cars in fleet?
//        try adding num of cars to XML file
//        out.println("<p>You currently have <b>" +  + "</b> Cars in your Fleet:</p><br/>");
        try {
            if (rs != null) {
                out.println("<div class=\"container-fluid\">\n"
                        + "        <div class=\"row\">\n"
                        + "            <div class=\"main col-md-6 center-block\">");
                out.println("<h1>List of cars available for rent</h1>");
                out.println("<table class=\"table table-hover\">");
                out.println("<tr>");
                out.println("    <th>Car Id</th>");
                out.println("    <th>Car Manufacturer</th>");
                out.println("    <th>Car Model</th>");
                out.println("    <th>Car Miles</th>");
                out.println("</tr>");
                while (rs.next()) {
                    out.println("<tr>");
                    out.println("    <td>" + rs.getString("P_Id") + "</td>");
                    out.println("    <td>" + rs.getString("Name") + "</td>");
                    out.println("    <td>" + rs.getString("Model") + "</td>");
                    out.println("    <td>" + rs.getString("Miles") + "</td>");
                    out.println("</tr>");
                }
                out.println("</p>");
                out.println("</table>");
                out.println("</div>\n"
                        + "        </div>\n"
                        + "    </div>");
            } else {
                out.println("<code>Result Set is null!  Verify database connectivity first.");
            }

        } catch (SQLException e) {
            carLogger.Logger("SQLException: " + e, e);

        }
      
        out.println(ListCarsVar.scriptHtml);
        out.println("</body></html>");
        out.close();
    }
}

class ListCarsVar {

    public static String navHtml = " <nav class=\"navbar navbar-default\">"
            + "<div class=\"container-fluid\">"
            + "<div class=\"navbar-header\">\n"
            + "                <button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\" aria-expanded=\"false\">\n"
            + "                    <span class=\"sr-only\">Toggle navigation</span>\n"
            + "                    <span class=\"icon-bar\"></span>\n"
            + "                    <span class=\"icon-bar\"></span>\n"
            + "                    <span class=\"icon-bar\"></span>\n"
            + "                </button>\n"
            + "                <a class=\"navbar-brand\" href=\"#\">CarRental</a>\n"
            + "            </div>"
            + "  <div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">\n"
            + "                <ul class=\"nav navbar-nav\">\n"
            + "                    <li ><a href=\"home.jsp\">Home </a></li>\n"
            + "                    <li class=\"active\"><a href=\"listcarsjdbc\">List Cars<span class=\"sr-only\">(current)</span></a></li>\n"
            + "                    <li ><a href=\"addCarJDBC.jsp\">Add a car</a></li>\n"
            +"  <li ><a href=\"removeCar.jsp\">Remove a car</a></li>"
            + "\n"
            + "                </ul>\n"
            + "\n"
            + "            </div><!-- /.navbar-collapse -->\n"
            + "        </div><!-- /.container-fluid -->\n"
            + "    </nav>";

    public static String scriptHtml = "<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->\n"
            + "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>\n"
            + "    <!-- Include all compiled plugins (below), or include individual files as needed -->\n"
            + "    <script src=\"js/bootstrap.min.js\"></script>";
}
