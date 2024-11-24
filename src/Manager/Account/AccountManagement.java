/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager.Account;

/*
 * This class displays currentAccount data (ID, Username, Password) in a table format 
 * and allows actions like Update, Insert, and Delete through separate windows.
 */
import DB.DBconnector;
import DB.JavaDBAccessIA;
import Manager.Interface.ManagerInterface;
import Model.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class AccountManagement extends JFrame implements ActionListener, ItemListener
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
  private final Color Green_Color = new Color(35, 79, 30);
  private final Color Nude_Color = new Color(235, 200, 178);
  String[] headers =
  {
    "ID", "Username", "Password", "Role"
  };
  String USER = "root";
  String PASS = "mysql1";
  String connectionURL = "jdbc:mysql://localhost:3306/LIST";
  User user;
  User currentUser;
  private JComboBox roleSelection;
  String[] roleArray =
  {
    "Manager", "Employee"
  };
  private static final String tableName = "USERS";

  public AccountManagement(String[] tableHeaders, User user)
  {

    // Adjust the frame 
    super("Display data");
    this.setBounds(100, 50, 800, 600);
    this.getContentPane().setBackground(Green_Color);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.user = user;

    JavaDBAccessIA dbObj = new JavaDBAccessIA(DBconnector.getDbName());
    dbObj.setDbConn(); // Call setDbConn to establish connection
    
    // Get the data 
    dataList = dbObj.getData(tableName, tableHeaders);
    data = dbObj.to2dArray(dataList);
    dbTable = new JTable(data, tableHeaders);
    dbTable.setDefaultEditor(Object.class, null);

    // Set the table
    dbTable.setGridColor(Color.BLACK);
    dbTable.setBackground(Green_Color);
    dbTable.setFont(new Font("Arial", Font.BOLD, 20));
    dbTable.setForeground(Color.YELLOW);

    dbTable.setRowHeight(40);
    scrollTable = new JScrollPane();
    scrollTable.getViewport().add(dbTable);
    dbTable.setFillsViewportHeight(true);

    dbTable.addMouseListener(new MouseAdapter()
    {
      @Override
      public void mouseClicked(MouseEvent e)
      {
        // Get the selected row index
        int selectedRow = dbTable.getSelectedRow();

        // Retrieve each column value from the selected row
        String id = dbTable.getValueAt(selectedRow, 0).toString();
        String username = dbTable.getValueAt(selectedRow, 1).toString();
        String password = dbTable.getValueAt(selectedRow, 2).toString();
        String role = dbTable.getValueAt(selectedRow, 3).toString();

        currentUser = new User(Integer.valueOf(id), username, password, role);
        System.out.println("mouseClicked: " + currentUser.getId());

      }
    });

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

  public void confirmDeletion()
  {

    System.out.println(currentUser.getId());

    // Display confirmation dialog
    int response = JOptionPane.showConfirmDialog(
      this, // Parent component
      "Are you sure you want to delete this item?", // Message
      "Confirm Deletion", // Title
      JOptionPane.YES_NO_OPTION, // Options: Yes and No
      JOptionPane.WARNING_MESSAGE // Type of message
    );

    // Check user response
    if (response == JOptionPane.YES_OPTION)

    // Proceed with deletion
    {

      String tableName = "USERS";
      DBconnector dbconnector = new DBconnector();
      dbconnector.connect();

      String[] columnHeaders =
      {
        "ID", "username", "password", "role"
      };

      // Database connection inf
      String dbQuery = "DELETE FROM USERS WHERE ID = ?";

      try
      {
        int id = (currentUser.getId());

        PreparedStatement ps = dbconnector.getConnection().prepareStatement(dbQuery);
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("Data deleted successfully into " + tableName);
        this.dispose();
        new AccountManagement(columnHeaders, user);
      }
      catch (SQLException se)
      {
        System.out.println("Error deleted data: " + se.getMessage());
        se.printStackTrace();
      }

    }
    else
    {
      // User chose not to delete, or closed the dialog
      System.out.println("Deletion canceled.");
    }
  }

  // Set the frame as a table 
//  public static void main(String[] args)
//  {
//    String dbName = "LIST";
//    String tableName = "USERS";
//    String[] columnHeaders =
//    {
//      "ID", "Username", "Password", "Role"
//    };
//
//    new AccountManagement(dbName, tableName, columnHeaders,);
//
//  }
  // Button's function 
  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();

    if (command.equals("Exit"))
    {
      this.dispose();
      new ManagerInterface(user);
    }
    else if (command.equals("Delete"))
    {
      confirmDeletion();
    }
    else if (command.equals("Update"))
    {
      if (currentUser == null)
      {
        JOptionPane.showMessageDialog(this, "Select to update");
        return;
      }
      this.dispose();
      new UpdateAccount(user, currentUser);
    }
    else if (command.equals("Insert"))
    {
      this.dispose();
      new InsertAccount();
    }

  }

  @Override
  public void itemStateChanged(ItemEvent e)
  {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }
}
