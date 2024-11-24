/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Employee.Notification;
/**
 * This class displays employee notifications in a table format.
 */

import Employee.Interface.EmployeeInterface;
import DB.JavaDBAccessIA;
import Model.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 */
public class EmployeeNotification extends JFrame implements ActionListener
{

 private ArrayList<ArrayList<String>> dataList; // Stores raw data retrieved from the database
  private Object[][] data; // Two-dimensional array used for the JTable
  private JTable dbTable; // The table displaying employee notifications
  private JScrollPane scrollTable; // Scroll pane for the table
  private JTableHeader header;
  private TableColumn column;
  private JButton exit;
  private JButton Update;
  private JButton Insert;
  private JButton Delete;
  private JPanel buttonPanel;
  private final Color Green_Color = new Color(35, 79, 30);
  private final Color Nude_Color = new Color(235, 200, 178);
  String[] headers =
  {
    "notification"
  };
  String USER = "root";
  String PASS = "mysql1";
  String connectionURL = "jdbc:mysql://localhost:3306/LIST";
  User user; 

  public EmployeeNotification(String dbName, String tableName, String[] tableHeaders, User user)
  {
    super("Display notification");
    this.setBounds(100, 50, 800, 600);
    this.getContentPane().setBackground(Green_Color);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.user = user;

    JavaDBAccessIA dbObj = new JavaDBAccessIA(dbName);
    dbObj.setDbConn(); // Call setDbConn to establish connection

    dataList = dbObj.getData(tableName, tableHeaders);
    data = dbObj.to2dArray(dataList);
    dbTable = new JTable(data, tableHeaders);

    dbTable.setGridColor(Color.red);
    dbTable.setBackground(Green_Color);
    dbTable.setFont(new Font("Arial", Font.BOLD, 20));
    dbTable.setForeground(Color.YELLOW);

    dbTable.setRowHeight(40);
    scrollTable = new JScrollPane();
    scrollTable.getViewport().add(dbTable);
    dbTable.setFillsViewportHeight(true);

    this.buttonPanel = new JPanel();
    this.exit = new JButton("Quit");
    this.exit.addActionListener(this);
    buttonPanel.add(exit);


    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(scrollTable, BorderLayout.NORTH);

    this.setVisible(true);

  }

//  public static void main(String[] args)
//  {
//    //db infor
//    String dbName = "LIST";
//    String tableName = "NOTIFICATION";
//    String[] columnHeaders =
//    {
//      "ID", "notification"
//    };
//
//    new EmployeeNotification(dbName, tableName, columnHeaders);
//  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();

    if (command.equals("Quit"))
    {
      new EmployeeInterface(user);
      this.dispose();
    }
  }
}
