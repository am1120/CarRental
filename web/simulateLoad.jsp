<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
        <title>Simulate Application Load</title>
        <link type="text/css" rel="stylesheet" href="css/jdeveloper.css"/>
        <script language="JavaScript" src="modaldbox.js"></script>
        <link type="text/css" rel="stylesheet" href="css/modaldbox.css"/>
    </head>
    <body>
        <h1>Simulate Application Load</h1>

        <form name="simulateload" action="simulateload" method="post">
            <!-- onsubmit="return validateForm()"> -->
            <p>
                <input type="radio" name="type" value="addtojdbc" checked="checked"/>Add to Database<br/><input type="radio"
                                                                                                                name="type"
                                                                                                                value="addtoservletcontext"
                                                                                                                disabled="true"/>Add
                to
                Servlet
                Context<br/>
            </p>
            <p>&nbsp;</p>
            <p align="left">
                Perform 
                <select name="operations">
                    <option value="5">5</option>
                    <option value="10">10</option>
                    <option value="50">50</option>
                    <option value="100">100</option>
                </select>

                <a href="simulateLoadOperations.jsp">operations</a>
                every 
                <select name="interval">
                    <option value="10s">10 seconds</option>
                    <option value="60s">60 seconds</option>
                    <option value="5m">5 minutes</option>
                    <option value="15m">15 minutes</option>
                    <option value="1h">1 hour</option>
                    <option value="indefinitely">Indefinitely</option>
                </select>
                for 
                <select name="length">
                    <option value="1h">1 Hour</option>
                    <option value="3h">3 Hours</option>
                    <option value="6h">6 Hours</option>
                    <option value="12h">12 Hours</option>
                    <option value="1d">1 Day</option>
                    <option value="indefinitely">Indefinitely</option>
                </select>.
            </p>
            <h4>
                <br/>
                Note you must have a database connection configured and working properly! &nbsp;Use 
                <a href="configDb.jsp">this</a>
                page to configure your database connection.
            </h4>
            <p>
                <input type="submit" name="submit" value="begin"/>
            </p>
            <input type="hidden" name="running" value="running"/>
        </form>
    </body>
</html>