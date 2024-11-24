/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager.Task;

import DB.JavaDBAccessIA;
import Manager.Interface.ManagerInterface;
import Model.Task;
import Model.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/*
 * This class represents the Display Task Data page of the application.
 * It displays task data retrieved from a database table in a JTable component.
 * It currently only provides a button to exit the window.
 *
 */
/**
 *
 */
public class DisplayTaskData extends JFrame implements ActionListener
{

  // Data structures to hold task information
  private ArrayList<ArrayList<String>> dataList;
  private Object[][] data;

  // Swing components for displaying and interacting with the data
  User user;
  Task currentTask;
  private JTable dbTable;
  private JScrollPane scrollTable;
  private JTableHeader header;
  private TableColumn column;
  private JButton exit;
  public JButton taskInsertButton;
  public JButton taskUpdateButton;
  public JButton taskDeleteButton;
  public JButton showtable;
  private JPanel buttonPanel;
  private final Color Green_Color = new Color(35, 79, 30);
  private final Color Nude_Color = new Color(189, 183, 107);

  public DisplayTaskData(String dbName, String tableName, String[] tableHeaders, User user)
  {
// Set frame properties
    super("Display data");
    this.setBounds(100, 50, 1000, 600);
    this.getContentPane().setBackground(Green_Color);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.user = user;
    taskInsertButton = new JButton("Insert");
    taskInsertButton.addActionListener(this);
    taskDeleteButton = new JButton("Delete");
    taskDeleteButton.addActionListener(this);
    taskUpdateButton = new JButton("Update");
    taskUpdateButton.addActionListener(this);

    // Create a JavaDBAccessIA object to interact with the database
    JavaDBAccessIA dbObj = new JavaDBAccessIA(dbName);
    dbObj.setDbConn();

    // Retrieve task data from the database table
    dataList = dbObj.getData(tableName, tableHeaders);
    data = dbObj.to2dArray(dataList);
    dbTable = new JTable(data, tableHeaders);
    dbTable.setDefaultEditor(Object.class, null);

    // Set table formatting
    dbTable.setGridColor(Color.black);
    dbTable.setBackground(Green_Color);
    dbTable.setFont(new Font("Arial", Font.BOLD, 20));
    dbTable.setForeground(Color.WHITE);

    // Add MouseListener to JTable
    dbTable.addMouseListener(new MouseAdapter()
    {
      @Override
      public void mouseClicked(MouseEvent e)
      {
        // Get the selected row index
        int selectedRow = dbTable.getSelectedRow();

        // Retrieve each column value from the selected row
        String id = dbTable.getValueAt(selectedRow, 0).toString();
        String taskName = dbTable.getValueAt(selectedRow, 1).toString();
        String taskDescription = dbTable.getValueAt(selectedRow, 2).toString();
        String taskDeadline = dbTable.getValueAt(selectedRow, 3).toString();
        String employees = dbTable.getValueAt(selectedRow, 4).toString();

        currentTask = new Task(Integer.valueOf(id), taskName, taskDescription, taskDeadline, employees);
        System.out.println("mouseClicked: "+ currentTask.getID());

      }
    });

    // Set header formatting
    header = dbTable.getTableHeader();
    header.setBackground(Nude_Color);
    header.setForeground(Color.WHITE);
    header.setFont(new Font("Arial", Font.BOLD, 20));

    // Create a JScrollPane to hold the JTable with scroll functionality
    dbTable.setRowHeight(200);
    scrollTable = new JScrollPane();
    scrollTable.getViewport().add(dbTable);
    dbTable.setFillsViewportHeight(true);

    // Create a panel to hold the buttons
    this.buttonPanel = new JPanel();
    this.exit = new JButton("Exit");
    this.exit.addActionListener(this);
    buttonPanel.add(exit);
    buttonPanel.add(taskInsertButton);
    buttonPanel.add(taskUpdateButton);
    buttonPanel.add(taskDeleteButton);

    // Add the button panel to the south and the scroll pane to the north of the frame
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(scrollTable, BorderLayout.NORTH);

    this.setVisible(true);
  }

  public void confirmDeletion()
  {

    System.out.println(currentTask.getID());

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

      String USER = "root";
      String PASS = "mysql1";
      String dbName = "LIST";
      String tableName = "TASK";
      String connectionURL = "jdbc:mysql://localhost:3306/LIST";

      String[] columnHeaders =
      {
        "ID", "Taskname", "Taskdescription", "Taskdeadline", "Employees"
      };

      // Database connection inf
      String dbQuery = "DELETE FROM TASK WHERE ID = ?";

      try (Connection conn = DriverManager.getConnection(connectionURL, USER, PASS);)
      {
        int id = (currentTask.getID());

        PreparedStatement ps = conn.prepareStatement(dbQuery);
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("Data deleted successfully into " + tableName);
        this.dispose();
        new DisplayTaskData(dbName, tableName, columnHeaders, user);
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

  public static void main(String[] args)
  {
    String dbName = "LIST";
    String tableName = "TASK";
    String[] columnHeaders =
    {
      "ID", "Taskname", "Taskdescription", "Taskdeadline", "Employees"
    };
    new DisplayTaskData(dbName, tableName, columnHeaders, new User());
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    // Handle button clicks
    String command = e.getActionCommand();

    if (command.equals("Insert"))
    {
      this.dispose();
      new InsertTaskList(user);
    }
    if (command.equals("Update"))
    {
      if (currentTask == null)
      {
        JOptionPane.showMessageDialog(this, "Select to update");
        return;
      }
      this.dispose();
      new UpdateTaskList(user, currentTask);
    }
    if (command.equals("Delete"))
    {

      confirmDeletion(); 
    }
    if (command.equals("Exit"))
    {

      this.dispose();
      new ManagerInterface(user);
    }
  }

}
