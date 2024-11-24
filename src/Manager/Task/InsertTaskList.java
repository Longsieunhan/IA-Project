/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager.Task;

/*
 * This class represents the Insert Task List page of the application.
 * It allows users to enter information for a new task and add it to a database table.
 * It also provides buttons to view the existing task data and quit the application.
 *
 * @author nguyenthanhlong
 */
import Util.DateLabelFormatter;
import Model.Employee;
import Login.LoginPage;
import Model.User;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
//import static IAGUI.LoginPage.GREEN_COLOR;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class InsertTaskList extends JFrame implements ActionListener, ItemListener
{

  private JDatePickerImpl Deadline;
  private JTextField Name;
  private JTextField Description;
  private JTextField Employee;
  User user;
  String selectedEmployee;

  private JComboBox employeeSelection;

  public InsertTaskList(User user)
  {
    // Set frame properties
    super("Insert Task Page");
    this.setBounds(100, 200, 1400, 400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setBackground(LoginPage.GREEN_COLOR);
    this.setLayout(new GridLayout(4, 1));
    this.user = user;
    ArrayList<Employee> employees = this.getEmployees();
    employeeSelection = new JComboBox(employees.stream().map(t -> t.getName()).toArray(String[]::new));
    employeeSelection.addItemListener(this);

    selectedEmployee = employees.size() > 0 ? employees.get(0).getName() : "";

// Create text fields for task name, description, deadline, and assigned employee
    Name = new JTextField(20);
    Description = new JTextField(20);

    UtilDateModel model = new UtilDateModel();
    Properties p = new Properties();
    p.put("text.today", "Today");
    p.put("text.month", "Month");
    p.put("text.year", "Year");
    JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
    Deadline = new JDatePickerImpl(datePanel, new DateLabelFormatter());

    // Create labels for task name, description, deadline, and assigned employee
    JLabel NameLabel = new JLabel("Name");
    JLabel DescriptionLabel = new JLabel("Description");
    JLabel DeadlineLabel = new JLabel("Deadline");
    JLabel EmployeeLabel = new JLabel("Employee");

    // Create a panel to hold the labels and text fields for task information
    JPanel DataPanel = new JPanel();
    DataPanel.add(NameLabel);
    DataPanel.add(Name);
    DataPanel.add(DescriptionLabel);
    DataPanel.add(Description);
    DataPanel.add(DeadlineLabel);
    DataPanel.add(Deadline);
    DataPanel.add(EmployeeLabel);
    DataPanel.add(employeeSelection);

    this.add(DataPanel, BorderLayout.CENTER);

    // Create buttons for adding a new task, showing the table, and quitting
    JButton AddButton = new JButton("Add");
    AddButton.addActionListener(this);

    JButton quitButton = new JButton("Quit");
    quitButton.addActionListener(this);

    JButton ShowTheTable = new JButton("Show the table");
    ShowTheTable.addActionListener(this);

// Create a panel to hold the buttons
    JPanel ButtonPanel = new JPanel();
    ButtonPanel.add(AddButton);

    ButtonPanel.add(quitButton);

    this.add(ButtonPanel, BorderLayout.SOUTH);

    this.setVisible(true);
  }

//  public static void main(String[] args)
//  {
//    new InsertTaskList();
//  }
  public static ArrayList<Employee> getEmployees()
  {
    String USER = "root";
    String PASS = "mysql1";
    String connectionURL = "jdbc:mysql://localhost:3306/LIST";
    ArrayList<Employee> employees = new ArrayList<>();

    try (Connection conn = DriverManager.getConnection(connectionURL, USER, PASS))
    {
      // Query to select all employees
      String sql = "SELECT * FROM EMPLOYEE";
      PreparedStatement stmt = conn.prepareStatement(sql);

      ResultSet rst = stmt.executeQuery();

      // Loop through the result set and create Employee objects
      while (rst.next())
      {
        String name = rst.getString("name");
        Employee employee = new Employee(name);
        employees.add(employee);
        System.out.println("Name: " + name);
      }

      // Check if the employees list is empty
      if (employees.isEmpty())
      {
        System.out.println("No account found");
        return null;
      }

    }
    catch (SQLException ex)
    {
      ex.printStackTrace();
      return null; // Return null in case of an exception
    }

    // Return employees array
    return employees;
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String name = Name.getText();
    String description = Description.getText();

    Date selectedDate = (Date) Deadline.getModel().getValue();
    String deadline = "";

    if (selectedDate != null)
    {
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      deadline = formatter.format(selectedDate);
    }
//    String employee = (String) employeeSelection.getSelectedItem();

    if (e.getActionCommand().equals("Add"))
    {
      // Database connection details
      String USER = "root";
      String PASS = "mysql1";
      String connectionURL = "jdbc:mysql://localhost:3306/LIST";
      String dbName = "LIST";
      String tableName = "TASK";
      String query = "INSERT INTO TASK (taskname, taskdescription, taskdeadline, employees) VALUES (?, ?, ?, ?)";

      try (Connection conn = DriverManager.getConnection(connectionURL, USER, PASS);)
      {
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, description);
        ps.setString(3, deadline);
        ps.setString(4, selectedEmployee);
        ps.executeUpdate();
        System.out.println("Data inserted successfully into " + tableName);
        JOptionPane.showMessageDialog(this, "Data inserted successfully");

      }
      catch (SQLException se)
      {
        System.out.println("Error inserting data: " + se.getMessage());
        se.printStackTrace();
      }
    }
    else if (e.getActionCommand().equals("Quit"))
    {
      this.dispose();
      String dbName = "LIST";
      String tableName = "TASK";
      String[] columnHeaders =
      {
        "ID", "Taskname", "Taskdescription", "Taskdeadline", "Employees"
      };
      new DisplayTaskData(dbName, tableName, columnHeaders, user);
    }
  }

  @Override
  public void itemStateChanged(ItemEvent e)
  {
    if (e.getSource() == employeeSelection && e.getStateChange() == ItemEvent.SELECTED)
    {
      // Get the selected employee name
      this.selectedEmployee = (String) employeeSelection.getSelectedItem();
 
      // Perform an action based on the selected employee
      System.out.println("Selected Employee: " + selectedEmployee);

      // Example: You could also update other fields based on employee selection here if needed
    }
  }

}
