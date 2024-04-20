/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IAGUI;

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

  public void setDbConn()
  {
    String connectionURL = "jdbc:derby:" + this.dbName + ";create=true";
    this.dbConn = null;
    try
    {
      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
      this.dbConn = DriverManager.getConnection(connectionURL);
    }
    catch (ClassNotFoundException ex)
    {
      System.out.println("Driver not found, check library ");
    }
    catch (SQLException se)
    {
      System.out.println("SQL Connection error!");
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

  public boolean createDB(String newDbName)
  {
    setDbName(newDbName);
    String connectionURL = "jdbc:derby:" + this.dbName + ";create=true";
    this.dbConn = null;
    try
    {
      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
      this.dbConn = DriverManager.getConnection(connectionURL);
      System.out.println("New database created: " + this.dbName);
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

  public ArrayList<ArrayList<String>> getData(String tableName, String[] tableHeaders)
  {
    int columnCount = tableHeaders.length;
    Statement s = null;
    ResultSet rs = null;
    String dbQuery = "SELECT * FROM " + tableName;
    data = new ArrayList<>();

    try
    {
      s = this.dbConn.createStatement();
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
    String dbName = "List";
    String tableName = "EmployeeList";
    String dbQuery = "INSERT INTO EmployeeList Values (?,?,?)";
    String[] ColumnNames =
    {
      "employee_name", "employee_age", "condition"
    };
    
    String dbName1 = "List1";
    String tableName1 = "TaskList";
    String dbQuery1 = "INSERT INTO TaskList Values(?,?,?)";
    String[] ColumnNames1 = {
       "task_name", "task_description", "task_deadline"
    };
    
    

    JavaDBAccessIA objAccess = new JavaDBAccessIA(dbName);
    Connection myDBConn = objAccess.getDbConn();
    
    JavaDBAccessIA objAccess1 = new JavaDBAccessIA(dbName1);
    Connection myDBConn1 = objAccess1.getDbConn();

    String task_name = "projectA";
    String task_description="Submit presentation";
    String task_deadline="20/4/2024";

    String name = "Long";
    int age = 12;
    String condition = "Good";

    try
    {
      PreparedStatement ps = myDBConn.prepareStatement(dbQuery);
      ps.setString(1, name);
      ps.setInt(2, age);
      ps.setString(3, condition);
      ps.executeUpdate();
      System.out.println("Data inserted successfully into " + tableName);
    }
    catch (SQLException se)
    {
      System.out.println("Error inserting data: " + se.getMessage());
    }

    ArrayList<ArrayList<String>> myData = objAccess.getData(tableName, ColumnNames);
    System.out.println(myData);
    
     try
    {
      PreparedStatement se = myDBConn1.prepareStatement(dbQuery1);
      se.setString(1, task_name);
      se.setString(2, task_description);
      se.setString(3, task_deadline);
      se.executeUpdate();
      System.out.println("Data inserted successfully into " + tableName1);
    }
    catch (SQLException se)
    {
      System.out.println("Error inserting data: " + se.getMessage());
    }

    ArrayList<ArrayList<String>> myData1 = objAccess1.getData(tableName1, ColumnNames1);
    System.out.println(myData1);
    
  }
  
}
