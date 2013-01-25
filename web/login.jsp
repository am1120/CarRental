<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="carrental.helper.CarRentalLogger" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"></meta>
        <title>Login</title>
        <link type="text/css" rel="stylesheet" href="css/jdeveloper.css"/>
    </head>
    <body>
         <div align="center"> 
            <h1>CarRental Login Form</h1>
             
            <form name="login" action="home.jsp" method="post">
                <table>
                    <tr>
                        <td>Username :</td>
                        <td>
                            <input name="username" size="15" type="text"/>
                        </td>
                    </tr>
                     
                    <tr>
                        <td>Password :</td>
                        <td>
                            <input name="password" size="15" type="password"/>
                        </td>
                    </tr>
                </table>
                <br/>
                <input type="submit" name="submit" value="Login"/>
            </form>
         </div> 
    </body>
</html>