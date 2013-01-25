<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result Parameter Test Page</title>
        <link type="text/css" rel="stylesheet" href="/CarRental/css/jdeveloper.css"/>
    </head>
    <body>
        <h1>Result Parameter Test Page</h1>
        <h3>URL query parameter values are:</h3>
        <p> Checkbox One: <b> <%= request.getParameter("checkBoxOne") %> </b> </p>
        <p> Checkbox Two: <b> <%= request.getParameter("checkBoxTwo") %> </b> </p>
        <p> String One: <b> <%= request.getParameter("stringOne") %> </b> </p>
        <p> String Two: <b> <%= request.getParameter("stringTwo") %> </b> </p>
        <p> Combo Box: <b> <%= request.getParameter("comboBox") %> </b> </p>
        <h3>
            <a href="/CarRental/home.jsp">Home</a>
        </h3>
    </body>
</html>
