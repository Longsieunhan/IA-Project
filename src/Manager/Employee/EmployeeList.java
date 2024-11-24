package Manager.Employee;

import Model.Employee;
import DB.JavaDBAccessIA;
import Manager.Interface.ManagerInterface;
import Model.User;
import javax.swing.JOptionPane;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * This class displays a table of employee information and provides buttons for
 * CRUD operations.
 */
public class EmployeeList extends JFrame implements ActionListener
{

  private ArrayList<ArrayList<String>> dataList;
  private Object[][] data;
  private JTable dbTable;
  private JScrollPane scrollTable;
  private JButton exit;
  private JButton updateButton;
  private JButton insertButton;
  private JButton deleteButton;
  private JPanel buttonPanel;
  private final Color GREEN_COLOR = new Color(35, 79, 30);
  private User user;
  private Employee currentEmployee;

  public EmployeeList(String dbName, String tableName, String[] tableHeaders)
  {
    super("Display Data");
    this.setBounds(100, 50, 800, 600);
    this.getContentPane().setBackground(GREEN_COLOR);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.user = user;

    JavaDBAccessIA dbObj = new JavaDBAccessIA(dbName);
    dbObj.setDbConn(); // Call setDbConn to establish connection

    dataList = dbObj.getData(tableName, tableHeaders);
    data = dbObj.to2dArray(dataList);
    dbTable = new JTable(data, tableHeaders);

    dbTable.setGridColor(Color.black);
    dbTable.setBackground(GREEN_COLOR);
    dbTable.setFont(new Font("Arial", Font.BOLD, 20));
    dbTable.setForeground(Color.YELLOW);

    dbTable.addMouseListener(new MouseAdapter()
    {
      @Override
      public void mouseClicked(MouseEvent e)
      {
        // Get the selected row index
        int selectedRow = dbTable.getSelectedRow();

        // Retrieve each column value from the selected row
        String id = dbTable.getValueAt(selectedRow, 0).toString();
        String name = dbTable.getValueAt(selectedRow, 1).toString();
        String age = dbTable.getValueAt(selectedRow, 2).toString();
        String status = dbTable.getValueAt(selectedRow, 3).toString();
        String gender = dbTable.getValueAt(selectedRow, 4).toString();

        currentEmployee = new Employee(Integer.parseInt(id), name, Integer.parseInt(age), status, gender);
        System.out.println("mouseClicked: " + currentEmployee.getId());
      }
    });

    dbTable.setRowHeight(40);
    scrollTable = new JScrollPane(dbTable);
    dbTable.setFillsViewportHeight(true);
    dbTable.setDefaultEditor(Object.class, null);

    buttonPanel = new JPanel();

    exit = new JButton("Exit");
    exit.addActionListener(this);
    buttonPanel.add(exit);

    updateButton = new JButton("Update");
    updateButton.addActionListener(this);
    buttonPanel.add(updateButton);

    insertButton = new JButton("Insert");
    insertButton.addActionListener(this);
    buttonPanel.add(insertButton);

    deleteButton = new JButton("Delete");
    deleteButton.addActionListener(this);
    buttonPanel.add(deleteButton);

    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(scrollTable, BorderLayout.NORTH);

    this.setVisible(true);
  }

  public static void main(String[] args)
  {

    String USER = "root";
    String PASS = "mysql1";
    String connectionURL = "jdbc:mysql://localhost:3306/LIST";
    String dbName = "LIST";
    String tableName = "EMPLOYEE";

    String[] columnHeaders =
    {
      "ID", "name", "age", "status", "gender"  // Adjusted to include gender
    };

    new EmployeeList(dbName, tableName, columnHeaders);
  }

  public void confirmDeletion()
  {

    System.out.println(currentEmployee.getId());

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
      String tableName = "EMPLOYEE";
      String connectionURL = "jdbc:mysql://localhost:3306/LIST";

      String[] columnHeaders =
      {
        "ID", "name", "age", "status", "gender"
      };

      // Database connection inf
      String dbQuery = "DELETE FROM EMPLOYEE WHERE ID = ?";

      try (Connection conn = DriverManager.getConnection(connectionURL, USER, PASS);)
      {
        int id = (currentEmployee.getId());

        PreparedStatement ps = conn.prepareStatement(dbQuery);
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("Data deleted successfully into " + tableName);
        this.dispose();
        new EmployeeList(dbName, tableName, columnHeaders);
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

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();

    if (command.equals("Exit"))
    {
      this.dispose();
      new ManagerInterface(this.user);
    }
    else if (command.equals("Delete"))
    {
      confirmDeletion();

    }
    else if (command.equals("Update"))
    {
      if (currentEmployee == null)
      {
        JOptionPane.showMessageDialog(this, "Select an employee to update");
        return;
      }
      this.dispose();
      new UpdateEmployeeList(currentEmployee);
    }
    else if (command.equals("Insert"))
    {
      this.dispose();
      new InsertEmployeeList();
    }
  }
}
