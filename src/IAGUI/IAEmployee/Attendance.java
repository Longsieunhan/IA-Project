/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IAGUI.IAEmployee;

/**
 *
 * @author nguyenthanhlong
 */
import static IAGUI.Login.LoginPage.BLUE_COLOR;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import IAGUI.JavaDBAccessIA;

import IAGUI.JavaDBAccessIA;
import javax.swing.JTextField;

public class Attendance extends JFrame implements ActionListener
{

  private JTextField nameField;
  private JCheckBox presentCheckBox;
  private JCheckBox absentCheckBox;
  private JTextField reasonField;

  public Attendance()
  {
    super("Attendance Page");
    this.setBounds(100, 200, 600, 200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setBackground(BLUE_COLOR);
    this.setLayout(new GridLayout(4, 1));

    nameField = new JTextField(20);
    presentCheckBox = new JCheckBox("Present");
    absentCheckBox = new JCheckBox("Absent");
    reasonField = new JTextField(20);

    JLabel nameLabel = new JLabel("Name");
    JLabel reasonLabel = new JLabel("Reason for Absence");

    JPanel dataPanel = new JPanel();
    dataPanel.add(nameLabel);
    dataPanel.add(nameField);
    dataPanel.add(presentCheckBox);
    dataPanel.add(absentCheckBox);
    dataPanel.add(reasonLabel);
    dataPanel.add(reasonField);

    this.add(dataPanel, BorderLayout.CENTER);

    JButton recordButton = new JButton("Record Attendance");
    recordButton.addActionListener(this);

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(recordButton);

    this.add(buttonPanel, BorderLayout.SOUTH);

    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new Attendance();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String dbName = "List";
    String tableName = "EmployeeAttendance";
    String[] columnHeaders =
    {
      "Name", "Attendance", "Reason"
    };
    String query = "INSERT INTO EmployeeAttendance (Name, Attendance, Reason) VALUES (?, ?, ?)";

    JavaDBAccessIA objAccess = new JavaDBAccessIA(dbName);
    objAccess.setDbConn();
    Connection myDbConn = objAccess.getDbConn();

    try
    {
      String name = nameField.getText();
      String attendance = "";
      if (presentCheckBox.isSelected())
      {
        attendance = "Present";
      }
      else if (absentCheckBox.isSelected())
      {
        attendance = "Absent";
      }
      String reason = reasonField.getText();

      PreparedStatement ps = myDbConn.prepareStatement(query);
      ps.setString(1, name);
      ps.setString(2, attendance);
      ps.setString(3, reason);
      ps.executeUpdate();
      System.out.println("Attendance recorded successfully for " + name);
    }
    catch (SQLException se)
    {
      System.out.println("Error recording attendance: " + se.getMessage());
      se.printStackTrace();
    } finally
    {
      try
      {
        myDbConn.close();
      }
      catch (SQLException ex)
      {
        System.out.println("Error closing database connection: " + ex.getMessage());
        ex.printStackTrace();
      }
    }
  }
}
