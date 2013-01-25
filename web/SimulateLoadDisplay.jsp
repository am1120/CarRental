<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page import="carrental.helper.XMLHelper,java.sql.Connection,java.sql.DriverManager,
         java.sql.ResultSet,java.sql.SQLException,java.sql.Statement,carrental.helper.CarRentalLogger, carrental.helper.DatabaseOperations"%>
<%! XMLHelper myXMLHelper = new XMLHelper();%>
<% int operations = 5;%>
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="css/jdeveloper.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simulate Load Display</title>
    </head>

    <h1>Simulate Load Servlet</h1>
    <code>Simulating operations</code><br/><br/>
    <h3>Recent load simulated:</h3>
    <table class =borders cellspacing="2" cellpadding="3" border="1">
        <tr>
            <th>SQL statements:</th>
            <th>Java methods:</th>
        </tr>
        <tr>
            <td><textarea name="txtArea1" cols="75" rows="20" style="background-color:InfoBackground;resize:none;" readonly="readonly">some data</textarea></td>
            <td><textarea name="txtArea2" cols="75" rows="20" style="background-color:InfoBackground;resize:none;" readonly="readonly">some data</textarea></td>
        </tr>
    </table>
    <h4>All simulated data is saved to the log "SimulatedData.txt".</h4><br/>
    <form name="simulateload" action="simulateload" method="post">
        <input type="submit" name="submit" value="Stop Simulating Load!"/><br/>
    </form>
</body></html>
