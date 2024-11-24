/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

/*
 * This class provides functionalities to interact with a MySQL database.
 */
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JavaDBAccessIA
{

  private String dbName;
  private Connection dbConn;
  private ArrayList<ArrayList<String>> data;

  public JavaDBAccessIA()
  {
    dbName = "";
    dbConn = null;
    data = null;
  }

  // This constructor takes a database name as input, sets dbName, and attempts to establish a connection using the provided name. It also initializes data to null.
  public JavaDBAccessIA(String dbName)
  {
    setDbName(dbName);
    setDbConn();
    data = null;
  }

  public String getDbName()
  {
    return dbName;
  }

  public void setDbName(String dbName)
  {
    this.dbName = dbName;
  }

  public Connection getDbConn()
  {
    return dbConn;
  }

// Establishes a connection to the database using JDBC. It uses the dbName property to construct the connection URL.
  public void setDbConn()
  {
    String connectionURL = "jdbc:mysql://localhost:3306/" + this.dbName;
    this.dbConn = null;
    try
    {
      Class.forName("com.mysql.cj.jdbc.Driver");
      this.dbConn = DriverManager.getConnection(connectionURL, "root", "mysql1");
    }
    catch (ClassNotFoundException ex)
    {
      System.out.println("Driver not found, check library ");
    }
    catch (SQLException se)
    {
      System.out.println("SQL Connection error 1!");
    }
  }

  public void closeDbConn()
  {
    try
    {

      this.dbConn.close();
    }
    catch (SQLException se)
    {
      System.out.println("Db closing Error");
    }
  }

// Attempts to create a new database with the provided name. It returns true on success and false on failure.
  public boolean createDB(String newDbName)
  {
    setDbName(newDbName);
    Connection newConn;
    String connectionURL = "jdbc:mysql://localhost:3306/";
    String query = "CREATE DATABASE " + this.dbName;
    this.dbConn = null;
    try
    {
      Class.forName("com.mysql.cj.jdbc.Driver");
      newConn = DriverManager.getConnection(connectionURL, "root", "mysql1");
      Statement s = newConn.createStatement();
      s.executeUpdate(query);
      System.out.println("New database created: " + this.dbName);
      newConn.close();
      return true;
    }
    catch (ClassNotFoundException ex)
    {
      System.out.println("Driver not found, check library");
      return false;
    }
    catch (SQLException se)
    {
      System.out.println("SQL connection error, database was not created");
      return false;
    }
  }

  // This method retrieves data from a specified table. It takes the table name and an array of column headers as input.
  // It returns an ArrayList<ArrayList<String>> where each inner ArrayList represents a row of data, 
  // and each element within the inner ArrayList corresponds to a column value.
  public ArrayList<ArrayList<String>> getData(String tableName, String[] tableHeaders)
  {

    String USER = "root";
    String PASS = "mysql1";
    String dbName = "LIST";
    String connectionURL = "jdbc:mysql://localhost:3306/LIST";
    int columnCount = tableHeaders.length;
    PreparedStatement s = null;
    ResultSet rs = null;
    String dbQuery = "SELECT * FROM " + tableName;
    data = new ArrayList<>();

    try (Connection conn = DriverManager.getConnection(connectionURL, USER, PASS);)
    {
      s = conn.prepareStatement(dbQuery);
      rs = s.executeQuery(dbQuery);

      while (rs.next())
      {
        ArrayList<String> row = new ArrayList<>();

        for (int i = 0; i < columnCount; i++)
        {
          String cell = rs.getString(tableHeaders[i]);
          row.add(cell);
        }
        this.data.add(row);
      }
    }
    catch (SQLException nt)
    {
      System.out.println("SQL error: Not able to get data");
    }

    return this.data;
  }

  public void setData(ArrayList<ArrayList<String>> data)
  {
    this.data = data;
  }

  public boolean createTable(String newTable, String dbName)
  {
    System.out.println(newTable);
    setDbName(dbName);
    setDbConn();
    Statement s;
    try
    {
      s = this.dbConn.createStatement();
      s.execute(newTable);
      System.out.println("New table created!");
      this.dbConn.close();
      return true;
    }
    catch (SQLException se)
    {
      System.out.println("Error creating table " + newTable);
      return false;
    }
  }

  public Object[][] to2dArray(ArrayList<ArrayList<String>> data)
  {
    if (data.size() == 0)
    {
      Object[][] dataArray = new Object[0][0];
      return dataArray;
    }
    else
    {
      int columnCount = data.get(0).size();
      Object[][] dataArray = new Object[data.size()][columnCount];
      for (int r = 0; r < data.size(); r++)
      {
        ArrayList<String> row = data.get(r);
        for (int c = 0; c < columnCount; c++)
        {
          dataArray[r][c] = row.get(c);
        }
      }
      return dataArray;
    }
  }

  public static void main(String[] args)
  {
    String USER = "root";
    String PASS = "mysql1";
    String dbName = "LIST";
    String tableName = "EMPLOYEE";
    String connectionURL = "jdbc:mysql://localhost:3306/LIST";
    String dbQuery = "INSERT INTO EMPLOYEE (name, age, status) VALUES (?, ?, ?)";
    String[] ColumnNames =
    {
      "ID", "name", "age", "status"
    };

    String name = "Long";
    int age = 12;
    String status = "Good";

    try (Connection conn = DriverManager.getConnection(connectionURL, USER, PASS);)
    {
      PreparedStatement ps = conn.prepareStatement(dbQuery);
      ps.setString(1, name);
      ps.setInt(2, age);
      ps.setString(3, status);
      ps.executeUpdate();
      System.out.println("Data inserted successfully into " + tableName);
    }
    catch (SQLException se)
    {
      System.out.println("Error inserting data: " + se.getMessage());
    }

    /*   ******TASK*****
 Public void insertTaskList(String taskname, String taskdescription, String taskdeadline) {
     String query = "INSERT INTO TASK (taskname, taskdescription, taskdeadline, employees) VALUES (?, ?, ?)";
//    try
//    {
//      PreparedStatement se = myDBConn1.prepareStatement(dbQuery1);
//      se.setString(1, taskname);
//      se.setString(2, taskdescription);
//      se.setString(3, taskdeadline);
        se.setString(4, employees); 
//      se.executeUpdate();
//      System.out.println("Data inserted successfully into " + tableName1);
//    }
//    catch (SQLException se)
//    {
//      System.out.println("Error inserting data: " + se.getMessage());
//    }
//
  }
******EMPLOYEE*****
//  public void InsertEmployeeList(String name, int age, String condition)
//  {
//    
//    String query = "INSERT INTO EMPLOYEE (name, age, condition) VALUES (?, ?, ?)";
//    
//    try
//    {
//      PreparedStatement ps = this.dbConn.prepareStatement(query);
//      ps.setString(1, name);
//      ps.setInt(2, age);
//      ps.setString(3, condition);
//      ps.executeUpdate();
//      System.out.println("Data inserted succesfully");
//
//    }
//    catch (SQLException se)
//    {
//      System.out.println("Error inserting data: " + se.getMessage());
//      se.printStackTrace();
//    }

//  }
    
    ******ATTENDANCE*****
    public void insertAttendace(String name, String attendace) {
     {
//    
//    String query = "INSERT INTO ATTENDACE (name, attendance, reason) VALUES (?, ?, ?)";
//    
//    try
//    {
//      PreparedStatement ps = this.dbConn.prepareStatement(query);
//      ps.setString(1, name);
//      ps.setString(2, attendance);
        ps.setString(3, reason);
//      ps.executeUpdate();
//      System.out.println("Data inserted succesfully");
//
//    }
//    catch (SQLException se)
//    {
//      System.out.println("Error inserting data: " + se.getMessage());
//      se.printStackTrace();
//    }

//  }
    
    ******FEEDBACK*****
    public void insertFeedback(String name, String attendace) {
     {
//    
//    String query = "INSERT INTO FEEDBACK (name, feedback) VALUES (?, ?)";
//    
//    try
//    {
//      PreparedStatement ps = this.dbConn.prepareStatement(query);
//      ps.setString(1, name);
        ps.setString(2, feedback); 
//      ps.executeUpdate();
//      System.out.println("Data inserted succesfully");
//
//    }
//    catch (SQLException se)
//    {
//      System.out.println("Error inserting data: " + se.getMessage());
//      se.printStackTrace();
//    }

        ******NOTIFICATION*****
    public void insertNotification(String notification) {
     {
//    
//    String query = "INSERT INTO NOTIFACATION (notification) VALUES (?, ?)";
//    
//    try
//    {
//      PreparedStatement ps = this.dbConn.prepareStatement(query);
//      ps.setString(1, notification);
//      ps.executeUpdate();
//      System.out.println("Data inserted succesfully");
//
//    }
//    catch (SQLException se)
//    {
//      System.out.println("Error inserting data: " + se.getMessage());
//      se.printStackTrace();
//    }
  
     */
  }
}
