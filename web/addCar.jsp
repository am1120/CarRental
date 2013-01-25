<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"></meta>
    <title>addCar</title>
    <link type="text/css" rel="stylesheet" href="css/jdeveloper.css"/>
    <script language="JavaScript" type="text/javascript">
      function validateForm()
      {
        var carName = document.addCar.name.value;
        var carMiles = document.addCar.miles.value;
        var numericExpression = /^[0-9]+$/;
        if (carName == null || carName == "")
        {
          alert("Car name must be entered.");
          return false;
        }
        else if (!carMiles.match(numericExpression))
        {
          if (carMiles == 0 || carMiles == "")
          {
            alert("Car miles cannot be 0!");
          }
          else
          {
            alert("Car miles cannot contain letters!");
          }
          return false;
        }
        else
        {
          return true;
        }
      }
    </script>
  </head>
  <body>
    <h1>Add&nbsp;to&nbsp;your Fleet of Rental Cars</h1>

    <form name="addCar" action="addcar" method="post" onsubmit="return validateForm()">
      <table class="borders" cellspacing="2" cellpadding="3" border="1">
        <tr>
          <td>Car Name</td>
          <td>
            <input type="text" name="name"/>
          </td>
        </tr>

        <tr>
          <td>Car Miles</td>
          <td>
            <input type="text" name="miles"/>
          </td>
        </tr>
      </table>
      <p>
        <input type="submit" name="submit" value="submit"/>
      </p>
    </form>

    <h3>
      <a href="home.jsp">Home</a>
    </h3>

    <h3>
      <a href="addBulkCars.jsp">Add Bulk Cars</a>
    </h3>

    <h3>
      <a href="configDb.jsp">Configure Database Connectivity</a>
    </h3>
  </body>
</html>