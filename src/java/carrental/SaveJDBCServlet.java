package carrental;


import carrental.helper.CarRentalLogger;
import carrental.helper.DatabaseOperations;
import carrental.helper.XMLHelper;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SaveJDBCServlet
  extends HttpServlet
{
  private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

  public void init(ServletConfig config)
    throws ServletException
  {
    super.init(config);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {

    ServletContext globalData = this.getServletContext();
    ServletContext context = this.getServletContext();
    XMLHelper myXMLHelper = new XMLHelper();
    DatabaseOperations dbQueries = new DatabaseOperations();
    response.setContentType(CONTENT_TYPE);
    CarRentalLogger carLogger = new CarRentalLogger();
    String test = request.getParameter("test");
    if (test == null)
    {
      test = "no";
    }
    String save = request.getParameter("save");
    if (save == null)
    {
      save = "no";
    }
    carLogger.Logger("Save is: " + save);
    String dbType = request.getParameter("dbtype"); // says oracle
    carLogger.Logger("dbtype is: " + dbType);
    String host = request.getParameter("host");
    String port = request.getParameter("port");
    String user = request.getParameter("username");
    String pass = request.getParameter("password");
    String sid = request.getParameter("sid");
    if (sid == null)
    {
      sid = "N/A";
      carLogger.Logger("Setting 'SID' to \"N/A\".");
    }
    else
    {
      carLogger.Logger("Setting sid to " + sid);
      context.setAttribute("sid", sid);
    }

    if (test != "no")
    {
      carLogger.Logger("Testing ..");
      if (save != "no")
      {
        carLogger.Logger("Saving ..");

        myXMLHelper.saveConnectionStringToXML(dbType, host, port, user, pass, sid);
      }
      else
      {
        carLogger.Logger("Not saving to XML, so setting the following context attributes: " + dbType + host + port + user + pass + sid);
        context.setAttribute("dbType", dbType);
        context.setAttribute("host", host);
        context.setAttribute("port", port);
        context.setAttribute("user", user);
        context.setAttribute("pass", pass);
      }
      carLogger.Logger("Redirecting to test JDBC connection settings.");
      context.setAttribute("save", save);
      //      carLogger.Logger("Save context attrib is: " + context.getAttribute("save")); reports "no"
      response.sendRedirect("testjdbc");
    }
    else if (test == "no")
    {
      myXMLHelper.saveConnectionStringToXML(dbType, host, port, user, pass, sid);
      carLogger.Logger("Redirecting to create Database objects.");
      response.sendRedirect("createDb.jsp");
    }
    else
    {
      carLogger.Logger("???");
      response.sendRedirect("testjdbc");

    }


  }
}
