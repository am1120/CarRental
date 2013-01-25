package carrental;

import carrental.helper.CarRentalLogger;
import carrental.helper.XMLHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.*;
import javax.servlet.http.*;

public class ConfigureDatabaseServlet extends HttpServlet {
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
        String conUrl = myXMLHelper.getConnectionStringFromXML();
        String[] params = myXMLHelper.getConnectionParamsFromXML();
        Connection connection;
        Statement statement;
        ResultSet rs;
        String query;
        PrintWriter out = response.getWriter();
        response.setContentType(CONTENT_TYPE);

        try {
            //  ("jdbc:sqlserver://" + conString + ";databaseName=CarRental;user=sa;password=admin;");
            out.println("<html>");
            out.println("<head><title>ConfigureDatabaseServlet</title>");
            out.println("<link type=\"text/css\" rel=\"stylesheet\" href=\"css/jdeveloper.css\"/>");
            out.println("<link type=\"text/css\" rel=\"stylesheet\" href=\"css/modaldbox.css\"/>");
            out.println("    <script language=\"JavaScript\">\n" +
                    "      function DatabaseInstructions() {\n" +
                    "          document.write(\"<b><u>SQL SERVER:<\\/b><\\/u><br/>\");\n" +
                    "          document.write(\"<br/>\");\n" +
                    "          document.write(\"Enable TCP/IP connections using SQLServer Configuration Manager and ensure the SQLServer Browser service is running.<br/>\");\n" +
                    "          document.write(\"<br/>\");\n" +
                    "          document.write(\"Run the following statements from Management Studio:<br/>\");\n" +
                    "          document.write(\"<br/>\");\n" +
                    "          document.write(\"CREATE DATABASE CarRental;<br/>\");\n" +
                    "          document.write(\"<br/>\");\n" +
                    "          document.write(\"CREATE TABLE CarRental.dbo.Cars(P_Id int PRIMARY KEY IDENTITY,Name varchar(255) NOT NULL,Miles int NOT NULL);<br/>\");\n" +
                    "          document.write(\"<br/>\");\n" +
                    "          document.write(\"INSERT INTO CarRental.dbo.Cars VALUES ('test', 12345);<br/>\");\n" +
                    "          document.write(\"<br/>\");\n" +
                    "          document.write(\"<br/>\");\n" +
                    "          document.write(\"<b><u>ORACLE:<\\/b><\\/u><br/>\");\n" +
                    "          document.write(\"<br/>\");\n" +
                    "          document.write(\"Log into SQLPlus and execute the following queries:<br/>\");\n" +
                    "          document.write(\"<br/>\");\n" +
                    "          document.write(\"CREATE TABLE Cars(P_Id INT PRIMARY KEY,Name VARCHAR(255) NOT NULL,Miles INT NOT NULL);<br/>\");\n" +
                    "          document.write(\"<br/>\");\n" +
                    "          document.write(\"create sequence carid_seq start with 1 increment by 1 nomaxvalue;<br/>\");\n" +
                    "          document.write(\"<br/>\");\n" +
                    "          document.write(\"INSERT INTO Cars (P_Id, Name, Miles) VALUES (carid_seq.nextval, 'test', 12345);<br/>\");\n" +
                    "          document.write(\"<br/>\");\n" +
                    "          document.write(\"commit;<br/>\");\n" +
                    "\n" +
                    "      }\n" +
                    "    </script>");


            out.println("<script language=\"JavaScript\" src=\"modaldbox.js\"></script></head>");
            out.println("<body bgcolor='white'>");
            out.println("<h1>Configure your CarRental database for first time use.</h1>");
            if (conUrl.contains("sqlserver")) {
                query = "SELECT * FROM CarRental.dbo.Cars";
                //out.println("<code>Loading com.microsoft.sqlserver.jdbc.SQLServerDriver driver ..</code><br/><br/>");
                carLogger.Logger("Loading com.microsoft.sqlserver.jdbc.SQLServerDriver driver ..");
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                //out.println("<code>Driver loaded.</code><br/><br/>");
                carLogger.Logger("Driver loaded.");
                //out.println("<code>Using connection string: " + conUrl + "</code><br/><br/>");
                carLogger.Logger("Using connection string: " + conUrl);
                connection = DriverManager.getConnection(conUrl);

            } else {
                //out.println("<code>Loading oracle.jdbc.driver.OracleDriver driver ..</code><br/><br/>");
                carLogger.Logger("Loading oracle.jdbc.driver.OracleDriver driver ..");
                Class.forName("oracle.jdbc.driver.OracleDriver");
                //Class.forName("oracle.jdbc.driver.DMSFactory");
                //out.println("<code>Driver loaded.</code><br/><br/>");
                carLogger.Logger("Driver loaded.");
                //out.println("<code>Using connection string: " + conUrl + " with User: " + params[3] + " and password: " + params[4] + "</code><br/><br/>");
                carLogger.Logger("Using connection string: " + conUrl + " with User: " + params[3] + " and password: " + params[4]);
                connection = DriverManager.getConnection(conUrl, params[3], params[4]);
                //jdbc:oracle:thin:@myhost:1521:orcl

            }
            if (connection.isValid(2)) {
                out.println("<code><b>SUCCESS!</b> A JDBC connection has been established.</code><br/>");
                out.println("<h3>Step 2. Create database objects.</h3>");


                out.println("<h4>Want to manually create the objects? View <u><span class=\"dialink\" onclick=\"sm('box',750,575)\">these instructions</span></u> to run the scripts manually.</h4>");
            } else {
                //this will never be reached because a SQL exception will always be thrown
                out.println("<code>Could not establish JDBC connection.  Check the application server .out log for specific details.</code>");
            }

            //            statement = connection.createStatement();
            //            out.println("<code>Executing query: " + query + "</code><br/><br/>");
            //            out.println("<table class =\"borders\" cellspacing=\"2\" cellpadding=\"3\" border=\"1\">");
            //            out.println("<tr>");
            //            out.println("    <th>Car Id</th>");
            //            out.println("    <th>Car Name</th>");
            //            out.println("    <th>Car Miles</th>");
            //            out.println("</tr>");
            //            rs = statement.executeQuery(query);
            //            while (rs.next()) {
            //                out.println("<tr>");
            //                out.println("    <td>" + rs.getString("P_Id") + "</td>");
            //                out.println("    <td>" + rs.getString("Name") + "</td>");
            //                out.println("    <td>" + rs.getString("Miles") + "</td>");
            //                out.println("</tr>");
            //            }
            //            out.println("</table>");


        } catch (SQLException e) {
            out.println("<code>ERROR: Unable to establish JDBC connection.<br/><br/>");
            out.println("SQLException: " + e + "<br/><br/>");
            out.println("For SQLServer, ensure that the SQL Server Browser service is running.</code><br/><br/>");
            carLogger.Logger("SQLException: " + e, e);
        } catch (ClassNotFoundException e) {
            out.println("<code>ERROR: Unable to establish JDBC connection.<br/><br/>");
            out.println("Driver Error" + e + "</code><br/><br/>");
            carLogger.Logger("Driver Error" + e, e);
        } catch (Exception e) {
            out.println("<code>ERROR: Unable to establish JDBC connection.<br/><br/>");
            out.println("Exception: " + e + "</code><br/><br/>");
            carLogger.Logger("Exception: " + e, e);
        }
        out.println("<h3><a href=\"home.jsp\"> Home </a></h3>");
        out.println("<h3><a href=\"addCarJDBC.jsp\"> Add a new Rental Car </a></h3>");
        out.println("<h3><a href=\"configDb.jsp\"> Configure Database Connectivity </a></h3>");
        out.println("<div id=\"box\" class=\"dialog\">\n" +
                "      <div style=\"text-align:center\">\n" +
                "        <span id=\"txt\">\n" +
                "          <script type=\"text/javascript\">\n" +
                "            this.DatabaseInstructions();\n" +
                "          </script></span><br></br>\n" +
                "         \n" +
                "        <button onclick=\"hm('box');okSelected()\">OK</button>\n" +
                "      </div>\n" +
                "    </div>");
        out.println("</body></html>");
        out.close();
    }
}
