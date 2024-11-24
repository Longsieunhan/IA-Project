/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

//import IAGUI.EmployeeInterface;
//import IAGUI.ManagerInterface;
//import Login.ForgotPasswordPage;
import Model.User;
import Manager.Interface.ManagerInterface;
import Employee.Interface.EmployeeInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class LoginPage extends JFrame implements ActionListener, ItemListener
{

  // Constants
  PreparedStatement stmt;
  public static final Color GREEN_COLOR = new Color(35, 79, 30);
  public static final Color BLACK_COLOR = new Color(0, 0, 0);
  public static final Font BIG_FONT = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40);

  // Components
  private JLabel titleLabel, usernameLabel, passwordLabel;
  private JTextField usernameField;
  private JPasswordField passwordField;
  private JButton loginButton;
  private JButton forpasswordButton;
  private JButton quitButton;
  private JPanel buttonPanel;
  boolean managerFlag;
  boolean employeeFlag;

  public LoginPage()
  {
    super("Login Page");
    this.setBounds(100, 200, 600, 400);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.getContentPane().setBackground(GREEN_COLOR);
    this.setLayout(new GridLayout(4, 1));

    managerFlag = true;
    employeeFlag = true;

    // Title Label
    titleLabel = new JLabel("Login Page", SwingConstants.CENTER);
    titleLabel.setFont(BIG_FONT);
    titleLabel.setForeground(Color.WHITE);
    add(titleLabel);

    // Username Field
    JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    usernameLabel = new JLabel("Username:");
    usernameLabel.setForeground(BLACK_COLOR);
    usernameField = new JTextField(15);
    loginPanel.add(usernameLabel);
    loginPanel.add(usernameField);

    // Password Field
    passwordLabel = new JLabel("Password:");
    passwordLabel.setForeground(BLACK_COLOR);
    passwordField = new JPasswordField(15);
    loginPanel.add(passwordLabel);
    loginPanel.add(passwordField);
    add(loginPanel);

    // Login Button
    loginButton = new JButton("Login");
    loginButton.addActionListener(this);
    loginButton.setPreferredSize(new Dimension(150, 50)); // Set preferred size for login button

    forpasswordButton = new JButton("Forgot password");
    forpasswordButton.addActionListener(this);
    forpasswordButton.setPreferredSize(new Dimension(150, 50)); // Set preferred size for forgot password button

    quitButton = new JButton("Quit");
    quitButton.addActionListener(this);
    quitButton.setPreferredSize(new Dimension(150, 50)); // Set preferred size for quit button

    buttonPanel = new JPanel();
    buttonPanel.add(loginButton);
    buttonPanel.add(forpasswordButton);
    buttonPanel.add(quitButton);

    this.add(buttonPanel, BorderLayout.SOUTH);

    setVisible(true);
  }

  public boolean CheckRole(Connection conn, String username, String role)
  {
    PreparedStatement stmt;
    LoginPage e = new LoginPage();
    return true;

  }

  public User CheckAccount(Connection conn, String username, String password)
  {
    PreparedStatement stmt;
    try
    {
      // Query to check username, password and fetch user
      String sql = "SELECT * FROM USERS WHERE username = ? AND password = ?";
      stmt = conn.prepareStatement(sql);
      stmt.setString(1, username);
      stmt.setString(2, password);

      ResultSet rst = stmt.executeQuery();
      if (!rst.next())
      {
        System.out.println("No account found");
        return null; // No account found
      }
      else
      {
        // Retrieve the user from the result set
        String role = rst.getString("role");
        String usernameFromDB = rst.getString("username");
        String passwordFromDB = rst.getString("password");
        int employeeId = rst.getInt("employee_id");
        int id = rst.getInt("ID");
        User user = new User(id, usernameFromDB, passwordFromDB, role, employeeId);
        System.out.println("Role: " + role);
        System.out.println("Name: " + username);
        return user;// Return the user's username
      }

    }
    catch (SQLException ex)
    {
      ex.printStackTrace();
      return null; // Return null in case of an exception
    }
  }

  public static void main(String[] args)
  {
    new LoginPage();
  }

  @Override
  public void itemStateChanged(ItemEvent e)
  {

  }

  @Override
  public void actionPerformed(ActionEvent e)
  {

    String USER = "root";
    String PASS = "mysql1";
    String connectionURL = "jdbc:mysql://localhost:3306/LIST";
    String dbName = "LIST";
    String tableName = "USERS";

    // Handle login button action
    String username = usernameField.getText();
    String password = new String(passwordField.getPassword());
    String command = e.getActionCommand();

    // Perform login authentication here
    if (command.equals("Login"))
    {
      try (Connection conn = DriverManager.getConnection(connectionURL, USER, PASS);)
      {
        User user = CheckAccount(conn, username, password);

        if (user == null)
        {
          // Handle case where login fails
          System.out.println("Invalid username or password");
          // Show an error message to the user
          JOptionPane.showMessageDialog(this, "Invalid username or password!");
        }
        else if (user.getRole().equals("Manager"))
        {
          // Open Manager Interface
          new ManagerInterface(user); // Open manager window
          System.out.println("Welcome, Manager!");
          this.dispose(); // Close the login window only after successful login
        }
        else if (user.getRole().equals("Employee"))
        {
          // Open Employee Interface
          new EmployeeInterface(user); // Open employee window
          System.out.println("Welcome, Employee!");
         

          this.dispose(); // Close the login window only after successful login
        }
        else
        {
          // Handle any other roles or invalid roles
          System.out.println("Unknown role: " + user);
        }
      }
      catch (SQLException se)
      {
        // SQL Exception handling (e.g., invalid username/password)
        System.out.println("Please enter your username correctly");
        JOptionPane.showMessageDialog(this, "Invalid username or password!");
      }
    }

    if (command.equals("Forgot password"))
    {
      this.dispose();
      new ForgotPasswordPage();
    }

    if (command.equals("Quit"))
    {
      this.dispose();
    }
  }
}
