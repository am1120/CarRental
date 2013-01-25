package carrental.testers;


import carrental.helper.CarRentalLogger;
import carrental.helper.DatabaseOperations;
import carrental.helper.XMLHelper;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TestJDBCServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doPost(HttpServletRequest inRequest, HttpServletResponse outResponse) throws ServletException, IOException {
        doGet(inRequest, outResponse);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        XMLHelper myXMLHelper = new XMLHelper();
        CarRentalLogger carLogger = new CarRentalLogger();
        ServletContext globalData = this.getServletContext();
        DatabaseOperations myDbOps = new DatabaseOperations();
        String conUrl = "asdf";
        String[] params = myXMLHelper.getConnectionParamsFromXML();
        String dbType = (String)globalData.getAttribute("dbType");
        String host = (String)globalData.getAttribute("host");
        String user = (String)globalData.getAttribute("user");
        String pass = (String)globalData.getAttribute("pass");
        String port = (String)globalData.getAttribute("port");
        String sid = (String)globalData.getAttribute("sid");
        String save = (String)globalData.getAttribute("save");
        Connection connection;
        Statement statement;
        ResultSet rs;
        String query;
        PrintWriter out = response.getWriter();
        response.setContentType(CONTENT_TYPE);
        out.println("<html>");
        out.println("<head><title>TestJDBCServlet</title></head>");
        out.println("<link type=\"text/css\" rel=\"stylesheet\" href=\"css/jdeveloper.css\"/>");
        out.println("<body bgcolor='white'>");
        out.println("<h1>Test JDBC Servlet</h1>");

        try {
            /* SAVING  - USING XML */
            if (save != "no") {
                carLogger.Logger("Save was checked - getting url from XML");
                conUrl = myXMLHelper.getConnectionStringFromXML();
                if (conUrl.toLowerCase().contains("sqlserver")) {
                    carLogger.Logger("SQLServer detected ..");
                    query = "SELECT * FROM CarRental.dbo.Cars";
                    out.println("<code>Loading com.microsoft.sqlserver.jdbc.SQLServerDriver driver ..</code><br/><br/>");
                    carLogger.Logger("Loading com.microsoft.sqlserver.jdbc.SQLServerDriver driver ..");
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    out.println("<code>Driver loaded.</code><br/><br/>");
                    carLogger.Logger("Driver loaded.");
                    out.println("<code>Using connection string: " + conUrl + "</code><br/><br/>");
                    carLogger.Logger("Using connection string: " + conUrl);
                    connection = DriverManager.getConnection(conUrl);

                } else {
                    carLogger.Logger("Oracle detected ..");
                    query = "SELECT * FROM Cars";
                    out.println("<code>Loading oracle.jdbc.driver.OracleDriver driver ..</code><br/><br/>");
                    carLogger.Logger("Loading oracle.jdbc.driver.OracleDriver driver ..");
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    //Class.forName("oracle.jdbc.driver.DMSFactory");
                    out.println("<code>Driver loaded.</code><br/><br/>");
                    carLogger.Logger("Driver loaded.");
                    out.println("<code>Using connection string: " + conUrl + " with User: " + params[3] + " and password: " + params[4] + "</code><br/><br/>");
                    carLogger.Logger("Using connection string: " + conUrl + " with User: " + params[3] + " and password: " + params[4]);
                    connection = DriverManager.getConnection(conUrl, params[3], params[4]);

                    //jdbc:oracle:thin:@myhost:1521:orcl
                }
            }
            /* SAVE WAS NOT CHECKED - USING CONTEXT */
            else {
                carLogger.Logger("Save was NOT checked .. get params from context.");
                carLogger.Logger("Checking null status': " + dbType + host + user + pass + port + sid + save);
                //                                            null   localhost sa admin 1433 null no

                if (dbType.toLowerCase().equals("sqlserver")) {
                    conUrl = myDbOps.buildConnectionURL(dbType, host, user, pass, port, sid);
                    query = "SELECT * FROM CarRental.dbo.Cars";
                    out.println("<code>Loading com.microsoft.sqlserver.jdbc.SQLServerDriver driver ..</code><br/><br/>");
                    carLogger.Logger("Loading com.microsoft.sqlserver.jdbc.SQLServerDriver driver ..");
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    out.println("<code>Driver loaded.</code><br/><br/>");
                    carLogger.Logger("Driver loaded.");
                    out.println("<code>Using connection string: " + conUrl + "</code><br/><br/>");
                    carLogger.Logger("Using connection string: " + conUrl);
                    connection = DriverManager.getConnection(conUrl);
                } else {
                    conUrl = myDbOps.buildConnectionURL(dbType, host, user, pass, port, sid);
                    query = "SELECT * FROM Cars";
                    out.println("<code>Loading oracle.jdbc.driver.OracleDriver driver ..</code><br/><br/>");
                    carLogger.Logger("Loading oracle.jdbc.driver.OracleDriver driver ..");
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    //Class.forName("oracle.jdbc.driver.DMSFactory");
                    out.println("<code>Driver loaded.</code><br/><br/>");
                    carLogger.Logger("Driver loaded.");
                    out.println("<code>Using connection string: " + conUrl + " with User: " + params[3] + " and password: " + params[4] + "</code><br/><br/>");
                    carLogger.Logger("Using connection string: " + conUrl + " with User: " + params[3] + " and password: " + params[4]);

                    //without SID - similar to sql server
                    //connection = DriverManager.getConnection(conUrl, params[3], params[4]);

                    //includes SID
                    connection = DriverManager.getConnection(conUrl);
                    /*
           * Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/orcl", "scott", "tiger");
           * // @//machineName:port/SID,   userid,  password
           */

                    //jdbc:oracle:thin:@myhost:1521:orcl

                }
                if (connection.isValid(2)) {
                    out.println("<code><b>SUCCESS!</b> A JDBC connection has been established.</code><br/><br/>");
                } else {
                    //this will never be reached because a SQL exception will always be thrown
                    out.println("<code>Could not establish JDBC connection.  Check the application server .out log for specific details.</code><br/>");
                }
            }

            statement = connection.createStatement();
            out.println("<code>Executing query: " + query + "</code><br/><br/>");
            out.println("<table class =\"borders\" cellspacing=\"2\" cellpadding=\"3\" border=\"1\">");
            out.println("<tr>");
            out.println("    <th>Car Id</th>");
            out.println("    <th>Car Name</th>");
            out.println("    <th>Car Miles</th>");
            out.println("</tr>");
            rs = statement.executeQuery(query);
            while (rs.next()) {
                out.println("<tr>");
                out.println("    <td>" + rs.getString("P_Id") + "</td>");
                out.println("    <td>" + rs.getString("Name") + "</td>");
                out.println("    <td>" + rs.getString("Miles") + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");


        } catch (SQLException e) {
            out.println("<code>SQLException: " + e + "</code><br/><br/>");
            carLogger.Logger("SQLException: " + e, e);
        } catch (ClassNotFoundException e) {
            out.println("<code>Driver Error: " + e + "</code><br/><br/>");
            carLogger.Logger("Driver Error: " + e, e);
        } catch (Exception e) {
            out.println("<code>Exception: " + e + "</code><br/><br/>");
            carLogger.Logger("Exception: " + e, e);
        }

        out.println("<h3><a href=\"home.jsp\"> Home </a></h3>");
        out.println("<h3><a href=\"addCarJDBC.jsp\"> Add a new Rental Car </a></h3>");
        out.println("<h3><a href=\"configDb.jsp\"> Configure Database Connectivity </a></h3>");
        out.println("</body></html>");
        out.close();
    }
}
