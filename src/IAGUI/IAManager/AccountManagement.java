/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IAGUI.IAManager;

/*
 * This class displays account data (ID, Username, Password) in a table format 
 * and allows actions like Update, Insert, and Delete through separate windows.
 */
import IAGUI.DeleteAccount;
import IAGUI.InsertAccount;
import static IAGUI.Login.LoginPage.BLUE_COLOR;
import IAGUI.UpdateEmployeeList;
import IAGUI.DeleteEmployeeList;
import IAGUI.InsertEmployeeList;
import IAGUI.JavaDBAccessIA;
import IAGUI.JavaDBAccessIA;
import IAGUI.UpdateAccount;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;


public class AccountManagement extends JFrame implements ActionListener
{
// Declare variables
  private ArrayList<ArrayList<String>> dataList;
  private Object[][] data;
  private JTable dbTable;
  private JScrollPane scrollTable;
  private JTableHeader header;
  private TableColumn column;
  private JButton exit;
  private JButton Update;
  private JButton Insert;
  private JButton Delete;
  private JPanel buttonPanel;
  private final Color Green_Color = new Color(77, 144, 80);
  private final Color Nude_Color = new Color(235, 200, 178);
  String[] headers =
  {
    "ID", "Username", "Password", "Role"
  };
  String USER = "root";
  String PASS = "mysql1";
  String connectionURL = "jdbc:mysql://localhost:3306/LIST";

  public AccountManagement(String dbName, String tableName, String[] tableHeaders)
  {
    
    // Adjust the frame 
    super("Display data");
    this.setBounds(100, 50, 800, 600);
    this.getContentPane().setBackground(Green_Color);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    JavaDBAccessIA dbObj = new JavaDBAccessIA(dbName);
    dbObj.setDbConn(); // Call setDbConn to establish connection

    // Get the data 
    dataList = dbObj.getData(tableName, tableHeaders);
    data = dbObj.to2dArray(dataList);
    dbTable = new JTable(data, tableHeaders);

    
    // Set the table
    dbTable.setGridColor(Color.BLACK);
    dbTable.setBackground(Green_Color);
    dbTable.setFont(new Font("Arial", Font.BOLD, 20));
    dbTable.setForeground(Color.YELLOW);

    dbTable.setRowHeight(40);
    scrollTable = new JScrollPane();
    scrollTable.getViewport().add(dbTable);
    dbTable.setFillsViewportHeight(true);

    
    // Set the buttons 
    this.buttonPanel = new JPanel();
    this.exit = new JButton("Exit");
    this.exit.addActionListener(this);
    buttonPanel.add(exit);
    

    Update = new JButton("Update");
    Update.addActionListener(this);
    buttonPanel.add(Update);

    Insert = new JButton("Insert");
    Insert.addActionListener(this);
    buttonPanel.add(Insert);

    Delete = new JButton("Delete");
    Delete.addActionListener(this);
    buttonPanel.add(Delete);

    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(scrollTable, BorderLayout.NORTH);

    this.setVisible(true);

  }
  
  // Set the frame as a table 

  public static void main(String[] args)
  {
    String dbName = "LIST";
    String tableName = "USERS";
    String[] columnHeaders =
    {
      "ID", "Username", "Password"
    };

    new AccountManagement(dbName, tableName, columnHeaders);

  }
  
  // Button's function 

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();

    if (command.equals("Exit"))
    {
      new ManagerAccount();
      this.dispose();
    }
    else if (command.equals("Delete"))
    {
      this.dispose();
      new DeleteAccount();
    }
    else if (command.equals("Update"))
    {
      this.dispose();
      new UpdateAccount();
    }
    else if (command.equals("Insert"))
    {
      this.dispose();
      new InsertAccount();
    }

  }
}
