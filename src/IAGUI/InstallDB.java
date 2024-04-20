package IAGUI;

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
      "CREATE TABLE EmployeeList (employee_name varchar(12), employee_age int, condition varchar(12))",
      "CREATE TABLE TaskList (task_name varchar(12), task_description varchar(40), task_deadline varchar(12))",
      "CREATE TABLE AccountList (username varchar(40), password varchar(40))",
      "CREATE TABLE FeedbackList (name varchar(20), description varchar(40))"
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
}
