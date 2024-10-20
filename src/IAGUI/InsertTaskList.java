/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IAGUI;

/*
 * This class represents the Insert Task List page of the application.
 * It allows users to enter information for a new task and add it to a database table.
 * It also provides buttons to view the existing task data and quit the application.
 *
 * @author nguyenthanhlong
 */
import IAGUI.DisplayTaskData;
import IAGUI.DisplayTaskData;
import IAGUI.IAManager.ManagerWorkingProcess;
import IAGUI.JavaDBAccessIA;
import static IAGUI.Login.LoginPage.BLUE_COLOR;
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

public class InsertTaskList extends JFrame implements ActionListener
{

  private JTextField Name;
  private JTextField Description;
  private JTextField Deadline;
  private JTextField Employee;

  public InsertTaskList()
  {
    // Set frame properties
    super("Insert Task Page");
    this.setBounds(100, 200, 1400, 400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setBackground(BLUE_COLOR);
    this.setLayout(new GridLayout(4, 1));

// Create text fields for task name, description, deadline, and assigned employee
    Name = new JTextField(20);
    Description = new JTextField(20);
    Deadline = new JTextField(20);
    Employee = new JTextField(20);
    
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
    DataPanel.add(Employee);

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

  public static void main(String[] args)
  {
    new InsertTaskList();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String name = Name.getText();
    String description = Description.getText();
    String deadline = Deadline.getText();
    String employee = Employee.getText();

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
        ps.setString(4, employee);
        ps.executeUpdate();
        System.out.println("Data inserted successfully into " + tableName);
      }
      catch (SQLException se)
      {
        System.out.println("Error inserting data: " + se.getMessage());
        se.printStackTrace();
      }
    }
    else if (e.getActionCommand().equals("Show the table"))
    {
      dispose();
      String dbName = "LIST";
      String tableName = "TASK";
      String[] columnHeaders =
      {
       "ID", "Taskname", "Taskdescription", "Taskdeadline", "Employees"
      };
      new DisplayTaskData(dbName, tableName, columnHeaders);
    }

    else if (e.getActionCommand().equals("Quit"))
    {
      new ManagerWorkingProcess();
      this.dispose();

    }
  }
}
