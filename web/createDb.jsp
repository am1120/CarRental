
  <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page import="carrental.helper.XMLHelper,java.sql.Connection,java.sql.DriverManager,
java.sql.ResultSet,java.sql.SQLException,java.sql.Statement,carrental.helper.CarRentalLogger, carrental.helper.DatabaseOperations"%>
<%! XMLHelper myXMLHelper = new XMLHelper(); %>
<%! DatabaseOperations dbQueries = new DatabaseOperations(); %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>createDb</title>
    <link type="text/css" rel="stylesheet" href="css/jdeveloper.css"/>
    <link type="text/css" rel="stylesheet" href="css/modaldbox.css"/>
    <script language="JavaScript" src="modaldbox.js"></script>
    <script language="JavaScript">
      function DatabaseInstructions()
      {
        document.write("<b><u>SQL SERVER:<\/b><\/u><br/>");
        document.write("<br/>");
        document.write("Enable TCP/IP connections using SQLServer Configuration Manager and ensure the SQLServer Browser service is running.<br/>");
        document.write("<br/>");
        document.write("Run the following statements from Management Studio:<br/>");
        document.write("<br/>");
        document.write("CREATE DATABASE CarRental;<br/>");
        document.write("<br/>");
        document.write("CREATE TABLE CarRental.dbo.Cars(P_Id int PRIMARY KEY IDENTITY,Name varchar(255) NOT NULL,Miles int NOT NULL);<br/>");
        document.write("<br/>");
        document.write("INSERT INTO CarRental.dbo.Cars VALUES ('test', 12345);<br/>");
        document.write("<br/>");
        document.write("<br/>");
        document.write("<b><u>ORACLE:<\/b><\/u><br/>");
        document.write("<br/>");
        document.write("Log into SQLPlus and execute the following queries:<br/>");
        document.write("<br/>");
        document.write("CREATE TABLE Cars(P_Id INT PRIMARY KEY,Name VARCHAR(255) NOT NULL,Miles INT NOT NULL);<br/>");
        document.write("<br/>");
        document.write("create sequence carid_seq start with 1 increment by 1 nomaxvalue;<br/>");
        document.write("<br/>");
        document.write("INSERT INTO Cars (P_Id, Name, Miles) VALUES (carid_seq.nextval, 'test', 12345);<br/>");
        document.write("<br/>");
        document.write("commit;<br/>");
      }
    </script>
  </head>
  <%
    String conUrl = myXMLHelper.getConnectionStringFromXMLNoLogging();
    //String[] params = myXMLHelper.getConnectionParamsFromXML();
    String dbType;
    Connection connection;
    Statement statement;
    ResultSet rs;
    String query;
    boolean valid = false;
    String message = dbQueries.getJDBCConnectionStatus();

    if (conUrl.contains("sqlserver")) {
        dbType = "SQLServer";
    }
    else if(conUrl.contains("oracle")){
        dbType = "Oracle";
    }else {
        dbType = "MySQL";
    }

  %>
  <h1>Configure your CarRental database for first time use</h1>
  <code>
    <%=message%>
  </code>
  <br/>
  <br/>
  <h3>
    <% if (dbType == "SQLServer" && message.contains("SUCCESS")) { %>
    Step 4. Create the 'CarRental' database, then click below.
    <% valid = true; %>
    <% } else if (dbType == "Oracle" && message.contains("SUCCESS")) { %>
    Step 4. Create the CarRental table, sequence, and first "car" row by clicking the button below.
    <% valid = true; %>
    <% } else if (dbType == "MySQL" && message.contains("SUCCESS")) { %>
    Step 4. Create the CarRental table, sequence, and first "car" row by clicking the button below.
    <% valid = true; %>
    <% } else if ((dbType == "SQLServer" && !message.contains("SUCCESS")) || dbType == "Oracle" && !message.contains("SUCCESS") || dbType == "MySQL" && !message.contains("SUCCESS")) {%>
    Unable to establish JDBC connection.  Go back and try again.
    <!-- set SQL Server tcp/ip requirements ? -->
    <% valid = false; %>
    <% } %>
  </h3>
  <form name="createdatabaseobjects" action="createdatabaseobjects" method="post">
    <% if (valid) { %>
    <input type="submit" name="btnSubmit" value="Create Database Objects"/>
    <% } else { %>
    <p>Unable to establish JDBC connection.  Go back and try again.</p>
    <% } %>
  </form>
  <h4>
    Want to manually create the objects? View
    <u>
      <span class="dialink" onclick="sm('box',750,575)">these instructions</span></u>
    to run the scripts manually.
  </h4>
  <h3>
    <a href="home.jsp">Home</a>
  </h3>
  <div id="box" class="dialog">
    <div style="text-align:center">
      <span id="txt">
        <script type="text/javascript">
          this.DatabaseInstructions();
        </script></span><br></br>

      <button onclick="hm('box');okSelected()">OK</button>
    </div>
  </div>
</html>