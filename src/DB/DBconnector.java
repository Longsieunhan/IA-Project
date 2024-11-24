/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

import static com.mysql.cj.conf.PropertyKey.PASSWORD;
import java.sql.Connection;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author nguyenthanhlong
 */
public class DBconnector
{

  private static final String USER = "root";
  private static final String PASSWORD = "mysql1";
  private static final String connectionURL = "jdbc:mysql://localhost:3306/LIST";
  private static final String dbName = "LIST";

  private Connection connection;

  public static String getUSER()
  {
    return USER;
  }

  public static String getPASSWORD()
  {
    return PASSWORD;
  }

  public static String getConnectionURL()
  {
    return connectionURL;
  }

  public static String getDbName()
  {
    return dbName;
  }

  public DBconnector()
  {
    // Khởi tạo kết nối trong hàm khởi tạo nếu cần thiết
    connect();
  }

  public void connect()
  {
    try
    {
      // Nạp driver của MySQL (Không cần nếu bạn đang sử dụng JDBC 4 trở lên)
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(connectionURL, USER, PASSWORD);
      System.out.println("Kết nối thành công!");
    }
    catch (SQLException e)
    {

      System.out.println("Kết nối thất bại!");
      e.printStackTrace();
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Không tìm thấy driver MySQL!");
      e.printStackTrace();
    }
  }

  public Connection getConnection()
  {
    return connection;
  }

  public void disconnect()
  {
    if (connection != null)
    {
      try
      {
        connection.close();
        System.out.println("Đóng kết nối thành công!");
      }
      catch (SQLException e)
      {
        System.out.println("Đóng kết nối thất bại!");
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args)
  {
    DBconnector connector = new DBconnector();
    // Thực hiện các thao tác với cơ sở dữ liệu ở đây
    connector.disconnect();
  }
}
