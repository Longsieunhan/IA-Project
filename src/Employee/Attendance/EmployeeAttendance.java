package Employee.Attendance;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 * This class implements a GUI window for employees to submit their daily attendance.
 *
*/

import Employee.Interface.EmployeeInterface;
import Model.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeAttendance extends JFrame implements ActionListener, ItemListener
{
  // Constants

  public static final Color BLUE_COLOR = new Color(35, 79, 30);
  public static final Font BIG_FONT = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40);

  // Components
  private JLabel titleLabel, reason, name;
  private JTextField reasonField, nameField;
  private JRadioButton presentButton, absentButton;
  private JButton submitButton, quitButton;
  User user;
 

  public EmployeeAttendance(User user)
  {
    super("Employee Attendance");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    getContentPane().setBackground(BLUE_COLOR);
    this.setBounds(100, 200, 1000, 800);
    setLayout(new GridLayout(5, 1)); // Increased rows for better layout
    this.user = user; 
    System.out.println(user.getUsername());

    // Title Label
    titleLabel = new JLabel("Employee Attendance", SwingConstants.CENTER);
    titleLabel.setFont(BIG_FONT);
    titleLabel.setForeground(Color.WHITE);
    reason = new JLabel("Reason for absence");
    reasonField = new JTextField(20);

    add(titleLabel);

    // Radio Buttons Panel
    JPanel radiobuttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    presentButton = new JRadioButton("Present");
    absentButton = new JRadioButton("Absent");

    
    ButtonGroup buttonGroup = new ButtonGroup();
    buttonGroup.add(presentButton);
    buttonGroup.add(absentButton);

    name = new JLabel("Name");
    nameField = new JTextField(10);

    JPanel reasonPanel = new JPanel();
    reasonPanel.add(reason);
    reasonPanel.add(reasonField);

    radiobuttonPanel.add(presentButton);
    radiobuttonPanel.add(absentButton);
    add(radiobuttonPanel);
    this.add(reasonPanel, BorderLayout.CENTER);

    // Button Panel
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    submitButton = new JButton("Submit");
    submitButton.addActionListener(this);
    quitButton = new JButton("Quit");
    quitButton.addActionListener(this);
    buttonPanel.add(submitButton);
    buttonPanel.add(quitButton);
    add(buttonPanel);

    pack(); // Adjust frame size to fit components
    setLocationRelativeTo(null); // Center the frame on the screen
    setVisible(true);
  }

//  public static void main(String[] args)
//  {
//    new EmployeeAttendance();
//  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == submitButton)
    {
      if (presentButton.isSelected())
      {
        String USER = "root";
        String PASS = "mysql1";
        String connectionURL = "jdbc:mysql://localhost:3306/LIST";

        String dbName = "LIST";
        String tableName = "ATTENDANCE";
        String[] columnHeaders =
        {
          "ID", "name", "attendance", "reason", "timestamp"
        };

        String name = this.user.getUsername();
        String attendance = "Present";
        String reason = "None";
        String query = "INSERT INTO ATTENDANCE (name, attendance, reason) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(connectionURL, USER, PASS);)
        {
          PreparedStatement ps = conn.prepareStatement(query);
          ps.setString(1, name);
          ps.setString(2, attendance);
          ps.setString(3, reason);
          ps.executeUpdate();
          System.out.println("Data inserted successfully");
        }

        catch (SQLException se)
        {
          System.out.println("Error inserting data: " + se.getMessage());
          se.printStackTrace();
        }

      }
      else if (absentButton.isSelected())
      {

        String USER = "root";
        String PASS = "mysql1";
        String connectionURL = "jdbc:mysql://localhost:3306/LIST";
        String name = user.getUsername();
        String attendance = "Absent";
        String reason = reasonField.getText();
        String query = "INSERT INTO ATTENDANCE (name, attendance, reason) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(connectionURL, USER, PASS);)
        {
          PreparedStatement ps = conn.prepareStatement(query);
          ps.setString(1, name);
          ps.setString(2, attendance);
          ps.setString(3, reason);
          ps.executeUpdate();
          System.out.println("Data inserted successfully");
        }

        catch (SQLException se)
        {
          System.out.println("Error inserting data: " + se.getMessage());
          se.printStackTrace();
        }

      }
      JOptionPane.showMessageDialog(this, "Attendance is submitted");
    }
    else if (e.getSource() == quitButton)
    {
      this.dispose();
      new EmployeeInterface(user);

    }
    
    
    

  }

  @Override
  public void itemStateChanged(ItemEvent e)
  {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }
}
