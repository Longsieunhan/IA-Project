/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IAGUI;


/*
 * This class represents the Display User Data page of the application.
 * It displays user data retrieved from a database table in a JTable component.
 * It currently only provides a button to exit the window.
 *
 * @author nguyenthanhlong
 */
import static IAGUI.Login.LoginPage.BLUE_COLOR;
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


public class DisplayUser extends JFrame implements ActionListener
{
// Data structures to hold user information
  private ArrayList<ArrayList<String>> dataList;
  private Object[][] data;
  
   // Swing components for displaying and interacting with the data
  private JTable dbTable;
  private JScrollPane scrollTable;
  private JTableHeader header;
  private TableColumn column;
  private JButton exit;
  private JPanel buttonPanel;
  private final Color Green_Color = new Color(77, 144, 80);
  private final Color Nude_Color = new Color(235, 200, 178);
  String[] headers =
  {
    "ID", "username", "password"
  };

  public DisplayUser(String dbName, String tableName, String[] tableHeaders)
  {
    
     // Set frame properties
    super("Display data");
    this.setBounds(100, 50, 800, 600);
    this.getContentPane().setBackground(Color.cyan);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

     // Create a JavaDBAccessIA object to interact with the database
    JavaDBAccessIA dbObj = new JavaDBAccessIA(dbName);
    dbObj.setDbConn();
    dataList = dbObj.getData(tableName, tableHeaders);
    data = dbObj.to2dArray(dataList);
    dbTable = new JTable(data, tableHeaders);


    // Set table formatting
    dbTable.setGridColor(Color.BLACK);
    dbTable.setBackground(Green_Color);
    dbTable.setFont(new Font("Arial", Font.BOLD, 20));
    dbTable.setForeground(Color.YELLOW);

    header = dbTable.getTableHeader();
    header.setBackground(Nude_Color);
    header.setForeground(Color.WHITE);
    header.setFont(new Font("Arial", Font.BOLD, 20));
    dbTable.setRowHeight(200);
    
    // Create a JScrollPane to hold the JTable with scroll functionality
    scrollTable = new JScrollPane();
    scrollTable.getViewport().add(dbTable);
    dbTable.setFillsViewportHeight(true);

    this.buttonPanel = new JPanel();
    this.exit = new JButton("Exit");
    this.exit.addActionListener(this);
    buttonPanel.add(exit);

    
    // Add the button panel to the south and the scroll pane to the north of the frame
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(scrollTable, BorderLayout.NORTH);

    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    String dbName = "LIST";
    String tableName = "USERS";
    String[] columnHeaders =
    {
     "username", "password"
    };
    new DisplayUser(dbName, tableName, columnHeaders);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {

    String command = e.getActionCommand();

    if (command.equals("Exit"))
    {
      this.dispose();
    }

  }

}
