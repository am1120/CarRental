<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"></meta>
    <title>testDb</title>
    <link type="text/css" rel="stylesheet" href="css/jdeveloper.css"/>
    <script language="JavaScript">
      function enableTextField()
      {
        var form = document.forms[0];
        form.sid.disabled = false;
        form.port.value = "1521";
        form.host.value = "localhost";
        form.username.value = "SYSTEM";
        form.password.value = "admin";
        form.sid.value = "orainst";
      }

      function disableTextField()
      {
        var form = document.forms[0];
        form.sid.disabled = true;
        form.port.value = "1433"
        form.host.value = "localhost";
        form.username.value = "sa";
        form.password.value = "admin";
        form.sid.value = "N/A";
      }
      
      function disableTextFieldMySQL()
      {
        var form = document.forms[0];
        form.sid.disabled = true;
        form.port.value = "3306"
        form.host.value = "localhost";
        form.username.value = "carrental";
        form.password.value = "123456";
        form.sid.value = "N/A";
      }
    </script>
    <link type="text/css" rel="stylesheet" href="css/jdeveloper.css"/>
  </head>
  <body>
    <h1>Test Database Connectivity</h1>

    <form name="savejdbc" action="savejdbc" method="post">
      <p>
        <input type="radio" name="dbtype" checked="checked" value="sqlserver" onclick="javascript:disableTextField()"/><input type="text"
                                                                                                                              name="txtSQLServer"
                                                                                                                              value="SQLServer"
                                                                                                                              readonly="readonly"/>
      </p>
      <p>
        <input type="radio" name="dbtype" value="oracle" onclick="javascript:enableTextField()"/><input type="text" name="txtOracle" value="Oracle"
                                                                                                        readonly="readonly"/>
      </p>
      
      <p>
        <input type="radio" name="dbtype" value="mysql" onclick="javascript:enableTextFieldMySQL()"/><input type="text" name="txtOracle" value="MySQL"
                                                                                                        readonly="readonly"/>
      </p>
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
      <p>
        <input type="checkbox" name="save" value="save" checked="true"/>Save these settings to jdbc-settings.xml for future use?
      </p>
      <p>
        <input type="submit" name="submit" value="submit"/>
      </p>
      <input type="hidden" name="test" value="test"/>
    </form>

    <h3>
      <a href="home.jsp">Home</a>
    </h3>
  </body>
</html>