package IAGUI;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InstallDB
{

  public static void main(String[] args)
  {
    String newDbName = "List";
    JavaDBAccessIA objDb = new JavaDBAccessIA();

    // Create the database
    if (objDb.createDB(newDbName))
    {
      System.out.println("Database created successfully: " + newDbName);

      // Define and create tables
      createTables(objDb, newDbName);
    }
    else
    {
      System.out.println("Failed to create database: " + newDbName);
    }
  }

  private static void createTables(JavaDBAccessIA objDb, String dbName)
  {
    // Define table creation queries
    String[] tableQueries =
    {
      "CREATE TABLE EmployeeList (Id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1)" + ",employee_name varchar(12), employee_age int, condition varchar(12))",
//      "CREATE TABLE TaskList (Id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), task_name varchar(12)" + ", task_description varchar(40), task_deadline varchar(12))",
//      "CREATE TABLE AttendanceList (Id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY START WITH 1, INCREMENT 1), name varchar(40), attendance varchar(40), reason varchar(40))",
//      "CREATE TABLE AccountList (Id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1)" + ", username varchar(40), password varchar(40))",
//      "CREATE TABLE FeedbackList (Id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1)" + ", name varchar(20), description varchar(40))"

    };

    for (String tableQuery : tableQueries)
    {
      if (objDb.createTable(tableQuery, dbName))
      {
        System.out.println("Table created successfully.");
      }
      else
      {
        System.out.println("Failed to create table.");
      }
    }
  }

  String[] tableNames =
  {
    "AttendanceList",
    "EmployeeList"
  };

  private void dropTables(JavaDBAccessIA objDb, String dbName)
  {
    String[] tableNames =
    {
      
      "EmployeeList"
    };

    JavaDBAccessIA objAccess = new JavaDBAccessIA(dbName);
    objAccess.setDbConn();
    Connection myDbConn = objAccess.getDbConn();
    for (String tableName : tableNames)
    {
      String dropQuery = "DROP TABLE " + tableName;
      try
      {
        PreparedStatement ps = myDbConn.prepareStatement(dropQuery);
        ps.executeUpdate();
        System.out.println("Table " + tableName + " dropped successfully.");
      }
      catch (SQLException ex)
      {
        System.out.println("Failed to drop table " + tableName + ": " + ex.getMessage());
        ex.printStackTrace();
      }
    }
  }
}
