package Manager.Notification;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 */
import Model.Employee;
import Manager.Interface.ManagerInterface;
import Model.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ManagerNotification extends JFrame implements ActionListener, ItemListener
{

  private JComboBox employeeSelection;
  private JPanel buttonPanel;
  private JLabel titleLabel;
  private JTextArea NotificationText;
  private JButton submitButton, quitButton;
  public static final Color BLUE_COLOR = new Color(35, 79, 30);
  public static final Font BIG_FONT = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40);
  User user;

  public ManagerNotification(User user)
  {
    super("Manager Notification");
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.getContentPane().setBackground(BLUE_COLOR);
    this.setBounds(100, 200, 800, 400);
    this.setLayout(new BorderLayout());
    this.user = user;

    titleLabel = new JLabel("Manager Notification", JLabel.CENTER);
    titleLabel.setFont(BIG_FONT);
    titleLabel.setForeground(Color.WHITE);
    this.add(titleLabel, BorderLayout.NORTH);

    ArrayList<Employee> employees = this.getEmployees();
    employeeSelection = new JComboBox(employees.stream().map(t -> t.getName()).toArray(String[]::new));
    employeeSelection.addItemListener(this);

    NotificationText = new JTextArea();
    NotificationText.setPreferredSize(new Dimension(400, 400));

    JPanel feedbackPanel = new JPanel();
    feedbackPanel.add(NotificationText);
    this.add(feedbackPanel, BorderLayout.CENTER);

    submitButton = new JButton("Submit");
    submitButton.setPreferredSize(new Dimension(150, 50));
    submitButton.addActionListener(this);

    quitButton = new JButton("Quit");
    quitButton.setPreferredSize(new Dimension(150, 50));
    quitButton.addActionListener(this);

    buttonPanel = new JPanel();
    buttonPanel.add(submitButton);
    buttonPanel.add(quitButton);
    add(buttonPanel, BorderLayout.SOUTH);

    setLocationRelativeTo(null);
    setVisible(true);
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

//  public static void main(String[] args)
//  {
//    new ManagerNotification();
//  }
  @Override
  public void actionPerformed(ActionEvent e)
  {

    if (e.getSource() == submitButton)
    {
      JOptionPane.showMessageDialog(this, "Notification is submitted");
      String Notification = NotificationText.getText();

      String USER = "root";
      String PASS = "mysql1";
      String connectionURL = "jdbc:mysql://localhost:3306/LIST";
      String dbName = "LIST";
      String tableName = "NOTIFICATION";
      String query = "INSERT INTO NOTIFICATION (notification) VALUES (?)";

      try (Connection conn = DriverManager.getConnection(connectionURL, USER, PASS);)
      {
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, Notification);
        ps.executeUpdate();
        System.out.println("Data inserted successfully into " + tableName);
      }
      catch (SQLException se)
      {
        System.out.println("Error inserting data: " + se.getMessage());
        se.printStackTrace();
      }
      // Process the feedback
    }
    else if (e.getSource() == quitButton)
    {
      new ManagerInterface(user);
      dispose();
    }
  }

  @Override
  public void itemStateChanged(ItemEvent e)
  {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }
}
