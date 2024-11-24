package Manager.Feedback;

/*
 * This class represents the Display Feedback page of the application.
 * It displays feedback data retrieved from a database table in a JTable component.
 * It provides buttons for exiting the window and deleting a feedback record.
 *
 */
import DB.JavaDBAccessIA;
import Manager.Interface.ManagerInterface;
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author nguyenthanhlong
 */
public class DisplayFeedback extends JFrame implements ActionListener
{

// Data structures to hold feedback information
  private ArrayList<ArrayList<String>> dataList;
  private Object[][] data;

  // Swing components for displaying and interacting with the data
  private JTable dbTable;
  private JScrollPane scrollTable;
  private JTableHeader header;
  private TableColumn column;

  // Buttons for various functionalities
  private JButton exit;
  private JButton Update;
  private JButton Insert;
  private JButton Delete;
  private JPanel buttonPanel;
  private final Color Green_Color = new Color(35, 79, 30);
  private final Color Nude_Color = new Color(189,183,107);
  String[] headers =
  {
    "name", "feedback"
  };

  // Database connection details
  String USER = "root";
  String PASS = "mysql1";
  String connectionURL = "jdbc:mysql://localhost:3306/LIST";

  User user;

  public DisplayFeedback(String dbName, String tableName, String[] tableHeaders, User user)
  {
    // Set frame properties
    super("Display data");
    this.setBounds(100, 50, 800, 600);
    this.getContentPane().setBackground(Green_Color);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.user = user;

    // Create a JavaDBAccessIA object to interact with the database
    JavaDBAccessIA dbObj = new JavaDBAccessIA(dbName);
    dbObj.setDbConn(); // Call setDbConn to establish connection

    // Retrieve feedback data from the database table
    dataList = dbObj.getData(tableName, tableHeaders);
    data = dbObj.to2dArray(dataList);
    dbTable = new JTable(data, tableHeaders);

    // Set table formatting
    dbTable.setGridColor(Color.black);
    dbTable.setBackground(Green_Color);
    dbTable.setFont(new Font("Arial", Font.BOLD, 20));
    dbTable.setForeground(Color.WHITE);

    dbTable.setRowHeight(40);
    scrollTable = new JScrollPane();
    scrollTable.getViewport().add(dbTable);
    dbTable.setFillsViewportHeight(true);

    // Create a panel to hold the buttons
    this.buttonPanel = new JPanel();
    this.exit = new JButton("Exit");
    this.exit.addActionListener(this);
    buttonPanel.add(exit);

    Delete = new JButton("Delete");
    Delete.addActionListener(this);

    // Add the button panel to the south and the scroll pane to the north of the frame
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(scrollTable, BorderLayout.NORTH);

    this.setVisible(true);

  }

//  public static void main(String[] args)
//  {
//    String dbName = "LIST";
//    String tableName = "FEEDBACK";
//    String[] columnHeaders =
//    {
//      "ID", "name", "feedback"
//    };
//
//    new DisplayFeedback(dbName, tableName, columnHeaders, this.user);
//
//  }
  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();
    // Handle button clicks
    if (command.equals("Exit"))
    {
      new ManagerInterface(user);
      this.dispose();
    }

    if (command.equals("Delete"))
    {
    }

  }
}
