<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link type="text/css" rel="stylesheet" href="css/jdeveloper.css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/custom.css" rel="stylesheet" type="text/css"/>

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <title>CarRental - Remove a car</title>
        <script language="JavaScript" src="modaldbox.js"></script>
        <script language="JavaScript" type="text/javascript">
            function validateForm() {
                var carId = document.removeCar.carid.value;
                var numericExpression = /^[0-9]+$/; 
               if (!carId.match(numericExpression)) {
                    if (carId == 0 || carId == "") {
                        alert("Car Id cannot be 0!");
                    }
                    else {
                        alert("Car id cannot contain letters!");
                    }
                    return false;
                }
                else {
                    return true;
                }
            }
        </script>
    </head>
    <body>

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">CarRental</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li ><a href="home.jsp">Home </a></li>
                    <li ><a href="listcarsjdbc">List Cars</a></li>
                    <li><a href="addCarJDBC.jsp">Add a car</a></li>
                   <li class="active"><a href="removeCar.jsp">Remove a car<span class="sr-only">(current)</span></a></li>
                </ul>

            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
    
    <div class="container-fluid">
        <div class="row">
            <div class="main col-md-6 center-block">
                <h1>Remove a car from Rental List</h1>
                <form name="removeCar" action="removecar" method="post"
                      onsubmit="return validateForm()">
                  
                    <div class="form-group">
                        <label for="CarId">Car Id</label>
                        <input type="text" name="carid" class="form-control" id="carId" placeholder="Car Id">
                    </div>
                    <button type="submit" name="submit" value="submit" class="btn btn-default">Remove</button>                   
                </form>
            </div>
        </div>
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>