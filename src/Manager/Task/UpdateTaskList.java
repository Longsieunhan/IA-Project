/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager.Task;

/**
 * This class implements a GUI window for updating task information in a
 * database.
 */
import Util.DateLabelFormatter;
import Model.Employee;
import Login.LoginPage;
import Model.Task;
import Model.User;
import Manager.Task.DisplayTaskData;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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

public class UpdateTaskList extends JFrame implements ActionListener, ItemListener
{

  // data entry
  private JTextField taskName;
  private JTextField taskDescription;
  private JTextField taskDeadline;
  private JTextField employeeField;
  private JTextField idField;
  // control buttons
  private JButton updateButton;
  private JButton doneButton;
  private JPanel donePanel;
  private JPanel dataPanel;
  User user;
  String selectedEmployee;
  Task currentTask;
  private JComboBox employeeSelection;
  private JDatePickerImpl Deadline;

  public UpdateTaskList(User user, Task currentTask)
  {
    //Format the frame
    super("Update Task Page");
    this.setBounds(100, 200, 1000, 400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setBackground(LoginPage.GREEN_COLOR);
    this.setLayout(new GridLayout(4, 1));
    this.user = user;
    this.currentTask = currentTask;
    taskName = new JTextField(20);
    taskDescription = new JTextField(20);
    taskDeadline = new JTextField(20);
    employeeField = new JTextField(20);
    idField = new JTextField(20);
    ArrayList<Employee> employees = this.getEmployees();
    employeeSelection = new JComboBox(employees.stream().map(t -> t.getName()).toArray(String[]::new));
    employeeSelection.addItemListener(this);
    selectedEmployee = employees.size() > 0 ? employees.get(0).getName() : "";

    // Set values to input
    idField.setText(String.valueOf(currentTask.getID()));
    taskName.setText(currentTask.getTaskName());
    taskDescription.setText(currentTask.getTaskDescription());
    taskDeadline.setText(currentTask.getDeadline());
    employeeSelection.setSelectedItem(currentTask.getEmployees());

    UtilDateModel model = new UtilDateModel();
    Properties p = new Properties();
    p.put("text.today", "Today");
    p.put("text.month", "Month");
    p.put("text.year", "Year");
    JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
    Deadline = new JDatePickerImpl(datePanel, new DateLabelFormatter());

    JLabel NameLabel = new JLabel("Task name");
    JLabel DescriptionLabel = new JLabel("Task description");
    JLabel TaskDeadlineLabel = new JLabel("Task deadline");
    JLabel employeeLabel = new JLabel("Employee");
    JLabel idLabel = new JLabel("ID");

// Create data entry panel
    JPanel DataPanel = new JPanel();
    DataPanel.add(idLabel);
    DataPanel.add(idField);
    DataPanel.add(NameLabel);
    DataPanel.add(taskName);
    DataPanel.add(DescriptionLabel);
    DataPanel.add(taskDescription);
    DataPanel.add(TaskDeadlineLabel);
    DataPanel.add(Deadline);
    DataPanel.add(employeeLabel);
    DataPanel.add(employeeSelection);

    this.add(DataPanel, BorderLayout.CENTER);

    JButton UpdateButton = new JButton("Update");
    UpdateButton.addActionListener(this);

    JButton quitButton = new JButton("Quit");
    quitButton.addActionListener(this);

    JPanel ButtonPanel = new JPanel();
    ButtonPanel.add(UpdateButton);
    ButtonPanel.add(quitButton);

    this.add(ButtonPanel, BorderLayout.SOUTH);

    this.setVisible(true);

  }

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
    String command = e.getActionCommand();

    // Database connection inf
    String USER = "root";
    String PASS = "mysql1";
    String dbName = "LIST";
    String tableName = "TASK";
    String connectionURL = "jdbc:mysql://localhost:3306/LIST";
    String[] columnHeaders =
    {
      "ID", "Taskname", "Taskdescription", "Taskdeadline", "Employees"
    };

    String dbQuery = "UPDATE TASK SET taskname = ?, taskdescription = ?, taskdeadline= ?, employees = ? WHERE ID = ?";

    if (e.getActionCommand().equals("Update"))
    {
      try (Connection conn = DriverManager.getConnection(connectionURL, USER, PASS);)
      {
        String name = taskName.getText();
        String description = taskDescription.getText();
        int id = Integer.parseInt(idField.getText());
        Date selectedDate = (Date) Deadline.getModel().getValue();

        String deadline = "";

        if (selectedDate != null)
        {
          SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
          deadline = formatter.format(selectedDate);
        }

        PreparedStatement ps = conn.prepareStatement(dbQuery);
        ps.setString(1, name);
        ps.setString(2, description);
        ps.setString(3, deadline);
        ps.setString(4, selectedEmployee);
        ps.setInt(5, id);
        ps.executeUpdate();
        System.out.println("Data updated successfully into " + tableName);
        JOptionPane.showMessageDialog(this, "Data updated successfully");

      }
      catch (SQLException se)
      {
        System.out.println("Error inserting data: " + se.getMessage());
        se.printStackTrace();
      }

    }

    if (e.getActionCommand().equals("Quit"))
    {
      this.dispose();
      new DisplayTaskData(dbName, tableName, columnHeaders, user);

    }

  }

//  public static void main(String[] arg)
//  {
//    new UpdateTaskList();
//  }
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
