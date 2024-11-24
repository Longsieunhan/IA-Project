package DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;

public class InstallDB
{

  public static void main(String[] args)
  {
    String newDbName = "LIST";
    JavaDBAccessIA objDb = new JavaDBAccessIA();

    // Create the database
    if (objDb.createDB(newDbName))
    {
      System.out.println("Database created successfully: " + newDbName);
    }
    else
    {
      System.out.println("Failed to create database: " + newDbName);
    }
    createTables();
    
  }

  private static void createTables()
  {
    String DBURL = "jdbc:mysql://localhost:3306/LIST";
    String USER = "root";
    String PASS = "mysql1";

    // Define table creation statements
    String sqlUsers = "CREATE TABLE USERS ("
      + "ID INTEGER not NULL AUTO_INCREMENT, "
      + "username VARCHAR(255), "
      + "password VARCHAR(255), "
      + "role VARCHAR(255), "
      + "PRIMARY KEY (ID))";

    String sqlEmployee = "CREATE TABLE EMPLOYEE ("
      + "ID INTEGER not NULL AUTO_INCREMENT, "
      + "name VARCHAR(255), "
      + "age INTEGER, "
      + "status VARCHAR(255), "
      + "gender VARCHAR(255), "
      + "PRIMARY KEY (ID))";

    String sqlTask = "CREATE TABLE TASK ("
      + "ID INTEGER not NULL AUTO_INCREMENT, "
      + "taskname VARCHAR(255), "
      + "taskdescription VARCHAR(255), "
      + "taskdeadline VARCHAR(255), "
      + "employees VARCHAR(255), "
      + "PRIMARY KEY (ID))";

    String sqlAttendance = "CREATE TABLE ATTENDANCE ("
      + "ID INTEGER not NULL AUTO_INCREMENT, "
      + "name VARCHAR(255), "
      + "attendance VARCHAR(255), "
      + "reason VARCHAR(255), "
      + "checkin_time TIMESTAMP(6),"
      + "PRIMARY KEY (ID))";

    String sqlFeedback = "CREATE TABLE FEEDBACK ("
      + "ID INTEGER not NULL AUTO_INCREMENT, "
      + "name VARCHAR(255), "
      + "feedback VARCHAR(255), "
      + "PRIMARY KEY (ID))";

    String sqlNotification = "CREATE TABLE NOTIFICATION ("
      + "ID INTEGER not NULL AUTO_INCREMENT, "
      + "notification VARCHAR(255), "
      + "PRIMARY KEY (ID))";

    String sqlForgotPass = "CREATE TABLE FORGOTPASS ("
      + "ID INTEGER not NULL AUTO_INCREMENT, "
      + "username VARCHAR(255), "
      + "password VARCHAR(255), "
      + "confirmedpassword VARCHAR(255), "
      + "PRIMARY KEY (ID))";
    
    
    // Execute all table creation queries
    try (Connection conn = DriverManager.getConnection(DBURL, USER, PASS); Statement stmt = conn.createStatement())
    {

      stmt.executeUpdate(sqlAttendance);

      System.out.println("Tables created successfully!");
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  private static void dropTables()
  {
    String DBURL = "jdbc:mysql://localhost:3306/LIST";
    String USER = "root";
    String PASS = "mysql1";

    // Define DROP TABLE statements
    String[] dropStatements =
    {

      "DROP TABLE IF EXISTS ATTENDANCE",
    };

    // Execute each DROP TABLE statement
    try (Connection conn = DriverManager.getConnection(DBURL, USER, PASS); Statement stmt = conn.createStatement())
    {

      for (String sql : dropStatements)
      {
        stmt.executeUpdate(sql);
        System.out.println("Dropped table: " + sql.split(" ")[2]);
      }

      System.out.println("All tables dropped successfully!");
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

}
