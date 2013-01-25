<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"></meta>
        <title>Add Bulk Cars</title>
        <link type="text/css" rel="stylesheet" href="css/jdeveloper.css"/>
        <script language="JavaScript" src="modaldbox.js"></script>
        <link type="text/css" rel="stylesheet" href="css/modaldbox.css"/>
        <script language="JavaScript" type="text/javascript">
            function validateForm()
            {
                var carName = document.addBulkCarsJDBC.name.value;
                var numCars = document.addBulkCarsJDBC.numcars.value;
                var numericExpression = /^[0-9]+$/;
                if (carName == null || carName == "")
                {
                    alert("Car name must be entered.");
                    return false;
                }
                else if (!numCars.match(numericExpression))
                {
                    if (numCars == 0 || numCars == "")
                    {
                        alert("Number of cars cannot be 0!");
                    }
                    else
                    {
                        alert("Number of cars cannot contain letters!");
                    }
                    return false;
                }
                else if (numCars > 100)
                {
                    alert("Must add between 1-100 cars.");
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
        <h1>Add several cars to your fleet</h1>

        <form name="addBulkCars" action="addbulkcars" method="post" onsubmit="return validateForm()">
            <input type="radio" name="type" value="addtojdbc" checked="checked"/>
            Add to Database
            <br/>
            <input type="radio" name="type" value="addtoservletcontext"/>
            Add to Servlet Context
            <p>
                Car Names (Will be appended with &quot;_#&quot;):
                <input type="text" name="name"/>
            </p>
            <p>
                Number of cars to add:
                <input type="text" name="numcars"/>
            </p>
            <h4>Note that car miles will be randomized between 1-200000.</h4>
            <p>
                <input type="submit" name="submit" value="submit"/>
            </p>
        </form>

        <h3>
            <a href="home.jsp">Home</a>
        </h3>

        <h3>
            <a href="addCarJDBC.jsp">Add a single car</a>
        </h3>
    </body>
</html>