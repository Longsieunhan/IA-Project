/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IAGUI;

/**
 *
 * @author nguyenthanhlong
 */
import IAGUI.DisplayTaskData;
import IAGUI.IAManager.ManagerAttendance;
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
/**
 * This class implements a GUI for updating attendance records.
 * 
 * @author nguyenthanhlong
 */
public class UpdateAttendance  extends JFrame implements ActionListener
{
  // Text fields for entering data
  private JTextField IdField;
  private JTextField Name;
  private JTextField Reason;
  private JTextField Attendance;
  private JTextField Employee;

  public UpdateAttendance()
  {
    super("Edit Page");
    
    // Set frame properties
    this.setBounds(100, 200, 1400, 200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setBackground(BLUE_COLOR);
    this.setLayout(new GridLayout(4, 1));

    // Create text fields
    Name = new JTextField(20);
    Attendance = new JTextField(20);
    Reason = new JTextField(20);
    IdField = new JTextField(20);
   
    // Create labels for each field
    JLabel NameLabel = new JLabel("Name");
    JLabel AttendanceLabel = new JLabel("Attendance");
    JLabel reasonLabel = new JLabel("Reason");
    JLabel idLabel = new JLabel("ID");

// Create a panel to hold the data entry fields
    JPanel DataPanel = new JPanel();
    DataPanel.add(idLabel);
    DataPanel.add(IdField);
    DataPanel.add(NameLabel);
    DataPanel.add(Name);
    DataPanel.add(AttendanceLabel);
    DataPanel.add(Attendance);
    DataPanel.add(reasonLabel);
    DataPanel.add(Reason);

    // Add the data entry panel to the center of the frame
    this.add(DataPanel, BorderLayout.CENTER);
    
    // Create buttons for update, show table, and quit functionality
    JButton AddButton = new JButton("Update");
    AddButton.addActionListener(this);

    JButton quitButton = new JButton("Quit");
    quitButton.addActionListener(this);

    JButton ShowTheTable = new JButton("Show the table");
    ShowTheTable.addActionListener(this);

    // Create a panel to hold the buttons
    JPanel ButtonPanel = new JPanel();
    ButtonPanel.add(AddButton);
  
    this.add(ButtonPanel, BorderLayout.SOUTH);

    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new UpdateAttendance();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
     // Get data from text fields
    String name = Name.getText();
    String reason = Reason.getText();
    String attendance = Attendance.getText();
    int id = Integer.parseInt(IdField.getText()); 

    if (e.getActionCommand().equals("Update"))
    {
 // Database connection details (assuming they are the same everywhere)
      String USER = "root";
      String PASS = "mysql1";
      String connectionURL = "jdbc:mysql://localhost:3306/LIST";
      String dbName = "LIST";
      String tableName = "ATTENDANCE";
      String query = "UPDATE ATTENDANCE SET name = ?, attendance = ?,reason = ? WHERE ID = ?";

      try (Connection conn = DriverManager.getConnection(connectionURL, USER, PASS);)
      {
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, attendance);
        ps.setString(3, reason);
        ps.setInt(4, id);
        ps.executeUpdate();
        System.out.println("Data updated successfully into " + tableName);
      }
      catch (SQLException se)
      {
        System.out.println("Error inserting data: " + se.getMessage());
        se.printStackTrace();
      }
    }
    else if (e.getActionCommand().equals("Show the table"))
    {
      String dbName = "LIST";
      String tableName = "ATTENDANCE";
      String[] columnHeaders =
      {
       "ID", "Name", "Attendance", "Reason"
      };
      new ManagerAttendance(dbName, tableName, columnHeaders);
    }

    else if (e.getActionCommand().equals("Quit"))
    {

      this.dispose();

    }
  }
}
