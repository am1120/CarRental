<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"></meta>
    <title>configDB</title>
    <link type="text/css" rel="stylesheet" href="css/jdeveloper.css"/>
    <link type="text/css" rel="stylesheet" href="css/modaldbox.css"/>
    <script language="JavaScript" src="modaldbox.js"></script>
    <script language="JavaScript">
      function enableTextField() {
        var form = document.forms[0];
        form.sid.disabled = false;
        form.port.value = "3306";
        form.host.value = "localhost";
        form.username.value = "SYSTEM";
        form.password.value = "admin";
        form.sid.value = "orainst";
      }

      function disableTextField() {
        var form = document.forms[0];
        form.sid.disabled = true;
        form.port.value = "1433"
        form.host.value = "localhost";
        form.username.value = "sa";
        form.password.value = "admin";
        form.sid.value = "N/A";
      }

      function DatabaseInstructions() {
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
  <body>
    <h1>Configure your CarRental database for first time use.</h1>
     
    <form name="savejdbc" action="savejdbc" method="post">
    
      <h3>Step 1. Choose your database flavor:</h3>
      <input type="radio" name="dbtype" checked="checked" value="sqlserver" onclick="javascript:disableTextField()"/>
      <input type="text" name="txtSQLServer" value="SQLServer" readonly="readonly"/>
      <p/>
      <input type="radio" name="dbtype" value="oracle" onclick="javascript:enableTextField()"/>
      <input type="text" name="txtOracle" value="Oracle" readonly="readonly"/>
      <p/>
      <input type="radio" name="dbtype" value="mysql" onclick="javascript:enableTextField()"/>
      <input type="text" name="txtMySQL" value="MySQL" readonly="readonly"/>
      
      <h3>Step 2. .Set JDBC connection parameters:</h3>
      <table class="borders" cellspacing="2" cellpadding="3" border="1">
        <tr>
          <td>Hostname</td>
          <td>
            <input type="text" name="host" value="localhost"/>
          </td>
        </tr>
         
        <tr>
          <td>Port</td>
          <td>
            <input type="text" name="port" value="1433"/>
          </td>
        </tr>
         
        <tr>
          <td>Username</td>
          <td>
            <input type="text" name="username" value="sa"/>
          </td>
        </tr>
         
        <tr>
          <td>Password</td>
          <td>
            <input type="text" name="password" value="admin"/>
          </td>
        </tr>
         
        <tr>
          <td>Oracle SID</td>
          <td>
            <input type="text" name="sid" value="N/A" disabled="true"/>
          </td>
        </tr>
      </table>
      <p/>
      <h3>Step 3. Attempt to establish the JDBC connection by clicking submit.</h3>
      <p>
        <input type="submit" name="submit" value="submit"/>
      </p>
      <h4>Note that when you click submit, these settings are saved in jdbc-settings.xml which can also be modified manually.</h4>
      <input type="hidden" name="config" value="config"/>
    </form>
     
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
  </body>
</html>