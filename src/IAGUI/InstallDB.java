package IAGUI;

import com.sun.source.tree.ExpressionStatementTree;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;

public class InstallDB
{

  public static void main(String[] args)
  {
    String newDbName = "LIST";
    JavaDBAccessIA objDb = new JavaDBAccessIA();
//     Create the database
    if (objDb.createDB(newDbName))
    {
      System.out.println("Database created successfully: " + newDbName);

      // Define and create tables
      //createTables(objDb, newDbName);
    }
    else
    {
      System.out.println("Failed to create database: " + newDbName);
    }
    //dropTables(objDb, newDbName);
    createTables();

  }

  private static void createTables()
  {
    String DBURL = "jdbc:mysql://localhost:3306/LIST";
    String USER = "root";
    String PASS = "mysql1";

    try (Connection conn = DriverManager.getConnection(DBURL, USER, PASS); Statement stmt = conn.createStatement();)
    {
      // Define table creation queries for USERS, EMPLOYEE, TASK, ATTENDANCE, FEEDBACK, NOTIFICATION, FORGOTPASS
      String sql = "CREATE TABLE USERS "
        + "(ID INTEGER not NULL AUTO_INCREMENT, "
        + " username VARCHAR(255), "
        + " password VARCHAR(255), "
        + " role VARCHAR(255),"
        + " PRIMARY KEY (ID));"
        + "INSERT INTO `LIST`.`USERS`\n"
        + "(`ID`,\n"
        + "`username`,\n"
        + "`password`,\n"
        + "`role`)\n"
        + "VALUES\n"
        + "(<{ID: }>,\n"
        + "<{username: }>,\n"
        + "<{password: }>);"
        + "";

      String sql1 = "CREATE TABLE EMPLOYEE "
        + "(ID INTEGER not NULL AUTO_INCREMENT, "
        + " name VARCHAR(255), "
        + " age INTEGER, "
        + " status VARCHAR(255), "
        + " PRIMARY KEY (ID))";

      String sql2 = "CREATE TABLE TASK"
        + "(ID INTEGER not NULL AUTO_INCREMENT, "
        + " taskname VARCHAR(255), "
        + " taskdescription VARCHAR(255), "
        + " taskdeadline VARCHAR(255), "
        + " employees VARCHAR(255), "
        + " PRIMARY KEY (ID))";

      String sql3 = "CREATE TABLE ATTENDANCE"
        + "(ID INTEGER not NULL AUTO_INCREMENT, "
        + " name VARCHAR(255), "
        + " attendance VARCHAR(255), "
        + " reason VARCHAR(255), "
        + " PRIMARY KEY (ID))";

      String sql4 = "CREATE TABLE FEEDBACK"
        + " (ID INTEGER not NULL AUTO_INCREMENT, "
        + " name VARCHAR(255), "
        + " feedback VARCHAR(255), "
        + " PRIMARY KEY (ID))";

      String sql5 = "CREATE TABLE NOTIFICATION"
        + " (ID INTEGER not NULL AUTO_INCREMENT, "
        + " notification VARCHAR(255), "
        + " PRIMARY KEY (ID))";

      String sql6 = "CREATE TABLE FORGOTPASS"
        + " (ID INTEGER not NULL AUTO_INCREMENT, "
        + " username VARCHAR(255), "
        + " password VARCHAR(255), "
        + " confirmedpassword VARCHAR(255), "
        + " PRIMARY KEY (ID))";

      // Execute the following queries to create table. For example, this would execute 
      // the fifth query, which is creating NOTIFICATION table
      stmt.executeUpdate(sql);

      System.out.println("Table created!!");
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

//    // Define table creation queries
//    String[] tableQueries =
//    {
//      "CREATE TABLE EmployeeList (Id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1)" + ",employee_name varchar(12), employee_age int, condition varchar(12))",
//      "CREATE TABLE TaskList (Id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), task_name varchar(12)" + ", task_description varchar(40), task_deadline varchar(12))",
//      "CREATE TABLE AttendanceList (Id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY START WITH 1, INCREMENT 1), name varchar(40), attendance varchar(40), reason varchar(40))",
//      "CREATE TABLE AccountList (Id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1)" + ", username varchar(40), password varchar(40))",
//      "CREATE TABLE FeedbackList (Id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1)" + ", name varchar(20), description varchar(40))"
//
//    };
//
//    for (String tableQuery : tableQueries)
//    {
//      if (objDb.createTable(tableQuery, dbName))
//      {
//        System.out.println("Table created successfully.");
//      }
//      else
//      {
//        System.out.println("Failed to create table.");
//      }
//    }
  }

  String[] tableNames =
  {
    "AttendanceList",
    "EmployeeList"
  };

  // Drop the specified table
  public static void dropTables(JavaDBAccessIA objDb, String newDbName)
  {

    String DBURL = "jdbc:mysql://localhost:3306/LIST";
    String USER = "root";
    String PASS = "mysql1";
    try (Connection conn = DriverManager.getConnection(DBURL, USER, PASS); Statement stmt = conn.createStatement();)
    {
      String sql = "DROP TABLE ATTENDANCE";
      stmt.executeUpdate(sql);
      System.out.println("Table deleted!!");
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
//    JavaDBAccessIA objAccess = new JavaDBAccessIA(newDbName);
//    objAccess.setDbConn();
//    Connection myDbConn = objAccess.getDbConn();
//    for (String tableName : tableNames)
//    {
//      String dropQuery = "DROP TABLE " + tableName;
//      try
//      {
//        Statement ps = myDbConn.createStatement();
//        ps.executeUpdate("DROP TABLE EmployeeList");
//        System.out.println("Table " + tableName + " dropped successfully.");
//      }
//      catch (SQLException ex)
//      {
//        System.out.println("Failed to drop table " + tableName + ": " + ex.getMessage());
//        ex.printStackTrace();
//      }
//    }
  }
}
