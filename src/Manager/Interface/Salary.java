/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager.Interface;

import Login.LoginPage;
import Model.Employee;
import Model.User;
import Util.DateLabelFormatter;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author nguyenthanhlong
 */
public class Salary extends JFrame implements ActionListener, ItemListener
{

  String USER = "root";
  String PASS = "mysql1";
  String connectionURL = "jdbc:mysql://localhost:3306/LIST";
  private JDatePickerImpl dateStart;
  private JDatePickerImpl dateFinish;
  private JTextField Name;
  private JTextField Description;
  private JTextField Employee;
  String selectedEmployee;
  User user;

  private JComboBox employeeSelection;

  public Salary()
  {
    // Set frame properties
    super("Insert Task Page");
    this.setBounds(100, 200, 1400, 400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setBackground(LoginPage.GREEN_COLOR);
    this.setLayout(new GridLayout(4, 1));
    ArrayList<Employee> employees = this.getEmployees();
    this.selectedEmployee = employees.get(0).getName();
    employeeSelection = new JComboBox(employees.stream().map(t -> t.getName()).toArray(String[]::new));
    employeeSelection.addItemListener(this);

// Create separate models for the start and end dates
    UtilDateModel startModel = new UtilDateModel();
    UtilDateModel endModel = new UtilDateModel();
    Properties p = new Properties();
    p.put("text.today", "Today");
    p.put("text.month", "Month");
    p.put("text.year", "Year");

    JDatePanelImpl datePanel = new JDatePanelImpl(startModel, p);
    JDatePanelImpl datePanel1 = new JDatePanelImpl(endModel, p);
    dateStart = new JDatePickerImpl(datePanel, new DateLabelFormatter());
    dateFinish = new JDatePickerImpl(datePanel1, new DateLabelFormatter());

    // Create labels for task name, description, deadline, and assigned employee
    JLabel dateBegin = new JLabel("Date begin");
    JLabel dateEnd = new JLabel("Date end");
    JLabel employeeName = new JLabel("Employee's name");

    // Create a panel to hold the labels and text fields for task information
    JPanel dataPanel = new JPanel();
    dataPanel.add(dateBegin);
    dataPanel.add(dateStart); // Add dateStart to dataPanel
    dataPanel.add(dateEnd);
    dataPanel.add(dateFinish); // Add dateFinish to dataPanel
    dataPanel.add(employeeName);
    dataPanel.add(employeeSelection); // Add employee selection JComboBox

    this.add(dataPanel, BorderLayout.CENTER);

    // Create buttons for adding a new task, showing the table, and quitting
    JButton quitButton = new JButton("Quit");
    quitButton.addActionListener(this);

    JButton calculateButton = new JButton("Calculate");
    calculateButton.addActionListener(this);

    JButton historyButton = new JButton("history");
    historyButton.addActionListener(this);

    JButton saveButton = new JButton("Save");
    historyButton.addActionListener(this);

// Create a panel to hold the buttons
    JPanel ButtonPanel = new JPanel();
    ButtonPanel.add(calculateButton);
    ButtonPanel.add(historyButton);
    ButtonPanel.add(saveButton);
    ButtonPanel.add(quitButton);

    this.add(ButtonPanel, BorderLayout.SOUTH);

    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new Salary();
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
        int id = rst.getInt("id");
        Employee employee = new Employee(name, id);
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

  public int getTime(String attendanceDate)
  {
    String[] parts = attendanceDate.split(" ");
    String time = parts[1];
    String[] timeparts = time.split(":");
    int hour = Integer.valueOf(timeparts[0]);
    int minute = Integer.valueOf(timeparts[1]);
    int workingTime = (hour * 60) + minute;

    return workingTime;
  }

  public List<String> getDatesBetween(Date startDate, Date endDate)
  {
    String USER = "root";
    String PASS = "mysql1";
    String connectionURL = "jdbc:mysql://localhost:3306/LIST";
    List<String> dates = new ArrayList<>();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    // Convert Dates to LocalDate for iteration
    LocalDate start = LocalDate.parse(formatter.format(startDate));
    LocalDate end = LocalDate.parse(formatter.format(endDate));

    // Iterate from start date to end date
    while (!start.isAfter(end))
    {

      dates.add(start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
      start = start.plusDays(1);
      String query = "SELECT * FROM ATTENDANCE WHERE DATE (timestamp) = ? AND name = ?";
      try (Connection conn = DriverManager.getConnection(connectionURL, USER, PASS); PreparedStatement stmt = conn.prepareStatement(query))
      {
        stmt.setString(1, start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        stmt.setString(2, selectedEmployee);

        ResultSet rs = stmt.executeQuery();

        List<String> dailyWorkingTimes = new ArrayList<>();

        while (rs.next())
        {

          String attendanceDate = rs.getString("timestamp");
          String employeeName = rs.getString("name");
//          System.out.println("Attendance Date: " + attendanceDate + getTime(attendanceDate) + " " + employeeName);
          System.out.println(dailyWorkingTimes + "daily");
          String date = attendanceDate.split(" ")[0];
          boolean isRepeated = false;

          for (String existingDate : dailyWorkingTimes)
          {
            if (existingDate.equals(date))
            {
              isRepeated = true;
              break;
            }
          }

          // If the date is not repeated, add it to the list
          if (!isRepeated)
          {
            dailyWorkingTimes.add(date);
          }
//          System.out.println(date + "daily1");
        }
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }
    return dates;
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    if (e.getActionCommand().equals("Quit"))
    {
      this.dispose();
//      new ManagerInterface(user);
    }
    if (e.getActionCommand().equals("Calculate"))
    {
      Date selectedDate = (Date) dateStart.getModel().getValue();
      Date selectedDate1 = (Date) dateFinish.getModel().getValue();

      String startString = "";
      String endString = "";
      if (selectedDate != null && selectedDate1 != null)
      {
        // Check if end date is before start date
        if (selectedDate1.before(selectedDate))
        {
          JOptionPane.showMessageDialog(this, "End date cannot be earlier than start date.", "Invalid Date Range", JOptionPane.ERROR_MESSAGE);
          return; // Exit the method if the dates are invalid
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        startString = formatter.format(selectedDate);
        endString = formatter.format(selectedDate1);
      }
      if (selectedEmployee == null)
      {
        JOptionPane.showMessageDialog(this, "Choose the employee", "Invalid employee", JOptionPane.ERROR_MESSAGE);

      }
//      System.out.print(selectedEmployee);
//      System.out.println(startString);
//      System.out.println(endString);

      List<String> dateRange = getDatesBetween(selectedDate, selectedDate1);

      // Print the generated dates (or use them as needed)
//      System.out.println("Generated Dates:");
//      dateRange.forEach(System.out::println);
      JOptionPane.showMessageDialog(this, "Dates have been successfully generated.", "Success", JOptionPane.INFORMATION_MESSAGE);
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
