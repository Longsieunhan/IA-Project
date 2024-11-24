/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager.Account;

/*
 * This class represents the Insert Account page of the application.
 * It allows users to enter a username and password for a new account.
 * It provides buttons to insert the new account or view the existing accounts table.
 *
 * @author nguyenthanhlong
 */
import Login.LoginPage;
import Model.Employee;
import Model.User;
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
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author nguyenthanhlong
 */
public class InsertAccount extends JFrame implements ActionListener, ItemListener
{
// Text fields for username and password input

  Integer selectedEmployee;
  private JTextField usernameField;
  private JTextField passwordField;
  User user;
  private JComboBox roleSelection;
  String[] roleArray =
  {
    "Manager", "Employee"
  };
  private JComboBox employeeSelection;
  private ArrayList<Employee> employees = new ArrayList<>();

  public InsertAccount()
  {
// Set frame properties
    super("Insert Account Page");
    this.setBounds(100, 200, 900, 400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setBackground(LoginPage.GREEN_COLOR);
    this.setLayout(new GridLayout(4, 1));

    employees = this.getEmployees();
    employeeSelection = new JComboBox(employees.stream().map(t -> t.getName()).toArray(String[]::new));
    employeeSelection.addItemListener(this);
    selectedEmployee = employees.size() > 0 ? employees.get(0).getId() : 0;

    // Create text fields for username and password
    usernameField = new JTextField(20);
    passwordField = new JTextField(20);

    roleSelection = new JComboBox<>(roleArray);
    roleSelection.addItemListener(this);
    roleSelection.setSelectedItem(roleArray[0]);

    // Create labels for username and password
    JLabel usernameLabel = new JLabel("Username");
    JLabel passwordLabel = new JLabel("Password");

    // Create a panel to hold the username and password labels and fields
    JPanel DataPanel = new JPanel();

    DataPanel.add(usernameLabel);
    DataPanel.add(usernameField);
    DataPanel.add(passwordLabel);
    DataPanel.add(passwordField);
    DataPanel.add(roleSelection);
    DataPanel.add(employeeSelection);

    this.add(DataPanel, BorderLayout.CENTER);

    // Create buttons for inserting a new account and showing the accounts table
    JButton DeleteButton = new JButton("Insert");
    DeleteButton.addActionListener(this);

    JButton quitButton = new JButton("Quit");
    quitButton.addActionListener(this);

    // Create a panel to hold the buttons
    JPanel ButtonPanel = new JPanel();
    ButtonPanel.add(DeleteButton);

    ButtonPanel.add(quitButton);

    this.add(ButtonPanel, BorderLayout.SOUTH);

    this.setVisible(true);
  }

  public static void main(String[] arg)
  {
    new InsertAccount();
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
        int id = rst.getInt("ID");
        int age = rst.getInt("age");
        String status = rst.getString("status");
        String gender = rst.getString("gender");
        Employee employee = new Employee(id, name, age, status, gender);
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

    // Database connection details
    String USER = "root";
    String PASS = "mysql1";
    String dbName = "LIST";
    String tableName = "USERS";
    String connectionURL = "jdbc:mysql://localhost:3306/LIST";

    String query = "INSERT INTO USERS (username, password, role, employee_id) VALUES (?, ?, ?, ?)";

    if (e.getActionCommand().equals("Insert"))
    {
      System.out.println("Data is inserted");
      try (Connection conn = DriverManager.getConnection(connectionURL, USER, PASS);)
      {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String role = (String) roleSelection.getSelectedItem();

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, password);
        ps.setString(3, role);
        ps.setInt(4, this.selectedEmployee);
        ps.executeUpdate();
        System.out.println("Data inserted successfully into " + tableName);
        JOptionPane.showMessageDialog(this, "Data inserted successfully");

      }

      catch (SQLException se)
      {
        System.out.println("Error");
      }
    }

    if (e.getActionCommand().equals("Quit"))
    {
      this.dispose();
      String[] columnHeaders =
      {
        "ID", "Username", "Password", "Role"
      };

      new AccountManagement(columnHeaders, user);

    }

  }

  @Override
  public void itemStateChanged(ItemEvent e)
  {
    if (e.getSource() == employeeSelection && e.getStateChange() == ItemEvent.SELECTED)
    {
      // Get the selected employee name
      int employeeID = employees.stream()
        .filter(m->m.getName().equals((String)employeeSelection.getSelectedItem()))
        .findFirst().get().getId();
      this.selectedEmployee = employeeID;

      // Perform an action based on the selected employee
      System.out.println("Selected Employee: " + selectedEmployee);

      // Example: You could also update other fields based on employee selection here if needed
    }
  }
}
