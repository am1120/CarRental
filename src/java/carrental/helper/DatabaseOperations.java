package carrental.helper;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Random;


public class DatabaseOperations
{
  static CarRentalLogger carLogger = new CarRentalLogger();
  static XMLHelper myXMLHelper = new XMLHelper();
  String conUrl = myXMLHelper.getConnectionStringFromXML();
  String[] params = myXMLHelper.getConnectionParamsFromXML();

  public DatabaseOperations()
  {
    super();
  }

  public String buildConnectionURL(String _dbType, String _host, String _user, String _pass, String _port, String _sid)
  {
    String conUrl = null;
    String dbType = _dbType;
    String host = _host;
    String user = _user;
    String pass = _pass;
    String port = _port;
    String sid = _sid;

    if (dbType.toLowerCase().equals("sqlserver"))
    {
      conUrl = ("jdbc:sqlserver://" + host + ":" + port + ";databaseName=CarRental;user=" + user + ";password=" + pass + ";");
    }
    else if (dbType.toLowerCase().equals("oracle"))
    {
      conUrl = ("jdbc:oracle:thin:@" + host + ":" + port + ":" + sid);
    }
    else
    {
      carLogger.Logger("Unknown database type!");
      conUrl = "Unknown database type!";
    }
    //    if (strDbType.trim().toLowerCase().equals("sqlserver")) {
    //        conUrl = ("jdbc:sqlserver://" + strHost + ":" + strPort + ";databaseName=CarRental;user=" + strUser + ";password=" + strPassword + ";");
    //        //databaseName=CarRental;user=sa;password=admin;
    //    } else if (strDbType.trim().toLowerCase().equals("oracle")) {
    //        conUrl = ("jdbc:oracle:thin:@" + strHost + ":" + strPort + ":" + strSid);
    //        //jdbc:oracle:thin:@myhost:1521:orcl
    //    } else {
    //        carLogger.Logger("Unknown database type!");
    //    }

    return conUrl;
  }

  public ResultSet selectFromTable()
  {
    Connection connection;
    Statement statement;
    ResultSet rs = null;
    String query;

    try
    {
      if (conUrl.contains("sqlserver"))
      {
        query = "SELECT * FROM CarRental.dbo.Cars";
        carLogger.Logger("Loading com.microsoft.sqlserver.jdbc.SQLServerDriver driver ..");
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        carLogger.Logger("Driver loaded.");
        carLogger.Logger("Using connection string: " + conUrl + "");
        connection = DriverManager.getConnection(conUrl);
      }
      else
      {
        query = "SELECT * FROM Cars";
        carLogger.Logger("Loading oracle.jdbc.driver.OracleDriver driver ..");
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //      Class.forName("oracle.jdbc.driver.DMSFactory");
        carLogger.Logger("Driver loaded.");
        carLogger.Logger("Using connection string: " + conUrl + ":" + params[3] + ":" + params[4]);
        connection = DriverManager.getConnection(conUrl, params[3], params[4]);
        //jdbc:oracle:thin:@myhost:1521:orcl
      }
      carLogger.Logger("Executing query: " + query);
      statement = connection.createStatement();
      rs = statement.executeQuery(query);
    }
    catch (SQLException e)
    {
      carLogger.Logger("SQLException: " + e, e);
    }
    catch (ClassNotFoundException e)
    {
      carLogger.Logger("Driver Error" + e, e);
    }
    catch (Exception e)
    {
      carLogger.Logger("Exception: " + e, e);
    }
    return rs;
  }

  public String insertBulkQuery(String _name, int _numCars)
  {
    String name = _name;
    int numCars = _numCars;
    String message = "started";
    XMLHelper myXMLHelper = new XMLHelper();
    CarRentalLogger carLogger = new CarRentalLogger();
    PreparedStatement ps;
    String[] params = myXMLHelper.getConnectionParamsFromXML();
    String conUrl = myXMLHelper.getConnectionStringFromXML();
    Random myRandom = new Random();
    // important vars

    Connection connection;
    Statement statement;
    ResultSet rs;

    String queryInsert = "INSERT INTO Cars VALUES(?,?)";
    String queryCommit = "commit";

    carLogger.Logger("Attempting to insert " + numCars + " cars ..");
    try
    {
      if (conUrl.contains("sqlserver"))
      {
        carLogger.Logger("Loading com.microsoft.sqlserver.jdbc.SQLServerDriver driver ..");
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        carLogger.Logger("Driver loaded.");
        //do inserts
        connection = DriverManager.getConnection(conUrl);
        statement = connection.createStatement();
        ps = connection.prepareStatement(queryInsert);
        for (int i = 0; i < numCars; i++)
        {
          ps = connection.prepareStatement(queryInsert);
          ps.setString(1, (name + "_" + i));
          ps.setString(2, String.valueOf(myRandom.nextInt(200000)));
          ps.execute();
          ps.close();
        }
      }
      else
      {
        connection = DriverManager.getConnection(conUrl, params[3], params[4]);
        statement = connection.createStatement();
        ps = connection.prepareStatement(queryInsert);
        carLogger.Logger("Loading oracle.jdbc.driver.OracleDriver driver ..");
        Class.forName("oracle.jdbc.driver.OracleDriver");
        carLogger.Logger("Driver loaded.");
        for (int i = 0; i < numCars; i++)
        {
          statement.executeUpdate("INSERT INTO Cars Values(carid_seq.nextval, '" + name + "_" + i + "', " + myRandom.nextInt(200000) + ")");
          statement.executeUpdate(queryCommit);
        }
      }

      message = "ok";
    }
    catch (SQLException e)
    {
      carLogger.Logger("SQLException: " + e, e);
      message = "SQLException: " + e;
      return message;
    }
    catch (ClassNotFoundException e)
    {
      carLogger.Logger("Driver Error" + e, e);
      message = "Driver Error: " + e;
      return message;
    }
    catch (Exception e)
    {
      carLogger.Logger("Exception: " + e, e);
      message = "Exception: " + e;
      return message;
    }

    return message;
  }

  public String insertQuery(String _name, String _miles)
  {
    String message = "started";
    XMLHelper myXMLHelper = new XMLHelper();
    CarRentalLogger carLogger = new CarRentalLogger();
    PreparedStatement ps;
    String[] params = myXMLHelper.getConnectionParamsFromXML();
    String conUrl = myXMLHelper.getConnectionStringFromXML();
    // important vars
    String name = _name;
    String miles = _miles;
    Connection connection;
    Statement statement;
    ResultSet rs;

    String queryInsert = "INSERT INTO Cars VALUES(?,?)";
    String queryCommit = "commit";

    try
    {
      if (conUrl.contains("sqlserver"))
      {
        carLogger.Logger("Loading com.microsoft.sqlserver.jdbc.SQLServerDriver driver ..");
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        carLogger.Logger("Driver loaded.");
        carLogger.Logger("Using connection string: " + conUrl + "");
        connection = DriverManager.getConnection(conUrl);
        statement = connection.createStatement();
        carLogger.Logger("Preparing insert statement ..");
        queryInsert = "INSERT INTO Cars VALUES(?,?)";
        ps = connection.prepareStatement(queryInsert);
        carLogger.Logger("Trying query : " + queryInsert + " with " + name + " and " + miles);
        if (name != null && miles != null)
        {
          ps.setString(1, name);
          ps.setString(2, miles);
          ps.execute();
          ps.close();
        }
      }
      else
      {
        carLogger.Logger("Loading oracle.jdbc.driver.OracleDriver driver ..");
        Class.forName("oracle.jdbc.driver.OracleDriver");
        carLogger.Logger("Driver loaded.");
        carLogger.Logger("Using connection string: " + conUrl + ":" + params[3] + ":" + params[4]);
        connection = DriverManager.getConnection(conUrl, params[3], params[4]);

        statement = connection.createStatement();
        carLogger.Logger("Preparing insert statement ..");
        queryInsert = "INSERT INTO Cars VALUES(?,?)";
        // ps = connection.prepareStatement(queryInsert);
        carLogger.Logger("Trying query : " + queryInsert + " with " + name + " and " + miles);
        statement.executeUpdate("INSERT INTO Cars Values(carid_seq.nextval, '" + name + "', " + miles + ")");
        statement.executeUpdate(queryCommit);

      }
      message = "ok";
    }
    catch (SQLException e)
    {
      carLogger.Logger("SQLException: " + e, e);
      message = "SQLException: " + e;
      return message;
    }
    catch (ClassNotFoundException e)
    {
      carLogger.Logger("Driver Error" + e, e);
      message = "Driver Error: " + e;
      return message;
    }
    catch (Exception e)
    {
      carLogger.Logger("Exception: " + e, e);
      message = "Exception: " + e;
      return message;
    }
    return message;
  }

  public String createObjects()
  {
    String conUrl = myXMLHelper.getConnectionStringFromXML();
    String[] params = myXMLHelper.getConnectionParamsFromXML();
    String dbType;
    String message = "started";
    Connection connection;
    carLogger.Logger("Attempting to create tables ..");

    try
    {
      if (conUrl.contains("sqlserver"))
      {
        dbType = "SQLServer";
        carLogger.Logger("Loading com.microsoft.sqlserver.jdbc.SQLServerDriver driver ..");
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        carLogger.Logger("Driver loaded.");
        carLogger.Logger("Using connection string: " + conUrl);
        connection = DriverManager.getConnection(conUrl);
      }

      else
      {
        dbType = "Oracle";
        carLogger.Logger("Loading oracle.jdbc.driver.OracleDriver driver ..");
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //Class.forName("oracle.jdbc.driver.DMSFactory");
        carLogger.Logger("Driver loaded.");
        carLogger.Logger("Using connection string: " + conUrl + " with User: " + params[3] + " and password: " + params[4]);
        connection = DriverManager.getConnection(conUrl, params[3], params[4]);
      }
      Statement st = connection.createStatement();

      if (dbType == "SQLServer")
      {
        int rs = st.executeUpdate("CREATE TABLE CarRental.dbo.Cars(P_Id int PRIMARY KEY IDENTITY,Name varchar(255) NOT NULL,Miles int NOT NULL)");
        rs = st.executeUpdate("INSERT INTO CarRental.dbo.Cars VALUES ('test', 12345)");
      }
      else
      {
        int rs = st.executeUpdate("CREATE TABLE Cars(P_Id INT PRIMARY KEY,Name VARCHAR(255) NOT NULL,Miles INT NOT NULL)");
        rs = st.executeUpdate("create sequence carid_seq start with 1 increment by 1 nomaxvalue");
        rs = st.executeUpdate("INSERT INTO Cars (P_Id, Name, Miles) VALUES (carid_seq.nextval, 'test', 12345)");
        rs = st.executeUpdate("commit");
      }
      message = "ok";
    }
    catch (SQLException e)
    {
      carLogger.Logger("SQLException: " + e, e);
      message = "SQLException: " + e;
      return message;
    }
    catch (ClassNotFoundException e)
    {
      carLogger.Logger("Driver Error" + e, e);
      message = "Driver Error: " + e;
      return message;
    }
    catch (Exception e)
    {
      carLogger.Logger("Exception: " + e, e);
      message = "Exception: " + e;
      return message;
    }
    return message;
  }

  // public String createDatabase()
  //  {
  //    String conUrl = myXMLHelper.getConnectionStringFromXML();
  //    String[] params = myXMLHelper.getConnectionParamsFromXML();
  //
  //    String message = "asdf";
  //    Connection connection;
  //    carLogger.Logger("Attempting to create database ..");
  //    try
  //    {
  //      if (conUrl.contains("sqlserver"))
  //      {
  //        carLogger.Logger("Loading com.microsoft.sqlserver.jdbc.SQLServerDriver driver ..");
  //        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
  //        carLogger.Logger("Driver loaded.");
  //        carLogger.Logger("Using connection string: " + conUrl);
  //        connection = DriverManager.getConnection(conUrl);
  //      }
  //
  //      else
  //      {
  //        carLogger.Logger("Loading oracle.jdbc.driver.OracleDriver driver ..");
  //        Class.forName("oracle.jdbc.driver.OracleDriver");
  //        //Class.forName("oracle.jdbc.driver.DMSFactory");
  //        carLogger.Logger("Driver loaded.");
  //        carLogger.Logger("Using connection string: " + conUrl + " with User: " + params[3] + " and password: " + params[4]);
  //        connection = DriverManager.getConnection(conUrl, params[3], params[4]);
  //      }
  //
  //      Statement st = (Statement) connection.createStatement();
  //      int rs = st.executeUpdate("CREATE DATABASE CarRental");
  //
  //    }
  //    catch (SQLException e)
  //    {
  //      carLogger.Logger("SQLException: " + e, e);
  //      message = "SQLException: " + e;
  //      return message;
  //    }
  //    catch (ClassNotFoundException e)
  //    {
  //      carLogger.Logger("Driver Error" + e, e);
  //      message = "Driver Error: " + e;
  //      return message;
  //    }
  //    catch (Exception e)
  //    {
  //      carLogger.Logger("Exception: " + e, e);
  //      message = "Exception: " + e;
  //      return message;
  //    }
  //    return message;
  //
  //  }

  public String getJDBCConnectionStatus()
  {
    carLogger.Logger("Testing if connection is valid.");
    Connection connection;
    String message = "started";

    try
    {
      if (conUrl.contains("sqlserver"))
      {
        carLogger.Logger("Loading com.microsoft.sqlserver.jdbc.SQLServerDriver driver ..");
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        carLogger.Logger("Driver loaded.");
        carLogger.Logger("Using connection string: " + conUrl);
        connection = DriverManager.getConnection(conUrl);
      }
      else
      {
        carLogger.Logger("Loading oracle.jdbc.driver.OracleDriver driver ..");
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //Class.forName("oracle.jdbc.driver.DMSFactory");
        carLogger.Logger("Driver loaded.");
        carLogger.Logger("Using connection string: " + conUrl + " with User: " + params[3] + " and password: " + params[4]);
        connection = DriverManager.getConnection(conUrl, params[3], params[4]);
      }
        carLogger.Logger("Connection.ToString(): " + connection.toString());
      if (connection.isValid(2))
      {
        message = "<b>SUCCESS!</b> A JDBC connection has been established.";
      }
      else // will never be reached ...
      {
        message = "<b>FAILURE!</b> A JDBC connection could not be established.  Check the following: \n" +
            "- Enable TCP/IP connections using SQLServer Configuration Manager \n" +
            "- Ensure the SQLServer Browser service is running.";
      }
//    message = "<b>SUCCESS!</b> A JDBC connection has been established.";
    }
    catch (SQLException e)
    {
      carLogger.Logger("SQLException: " + e, e);
      if (e.getMessage().contains("Cannot open database \"CarRental\" requested by the login."))
      {
        message = "<b>SUCCESS!</b> A JDBC connection has been established, but the database does not exist yet.";
      }
      else
      {
        message = "SQLException: " + e;
      }

      return message;
    }
    catch (ClassNotFoundException e)
    {
      carLogger.Logger("Driver Error" + e, e);
      message = "Driver Error: " + e;
    }
    catch (Exception e)
    {
      carLogger.Logger("Exception: " + e, e);
      message = "Exception: " + e;
    }
    return message;
  }
}
