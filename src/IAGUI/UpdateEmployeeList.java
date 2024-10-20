/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 /*
 * This class implements a GUI window for updating employee information in a database.
 */
package IAGUI;

import IAGUI.IAManager.EmployeeList;
import IAGUI.IAManager.viewEmployee;
import static IAGUI.Login.LoginPage.BLUE_COLOR;
import IAGUI.JavaDBAccessIA;
import static IAGUI.Login.LoginPage.BLUE_COLOR;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class UpdateEmployeeList extends JFrame implements ActionListener
{

  private JTextField idField;
  private JTextField nameField;
  private JTextField ageField;
  private JTextField statusField;
  private JLabel imgLabel;
  public final URL IMG_PATH = getClass().getResource("thanhlong.png");
  public static ImageIcon companyImage;

  public UpdateEmployeeList()
  {
    // Set frame properties
    super("Update Employee Page");
    this.setBounds(100, 200, 880, 400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setBackground(BLUE_COLOR);
    this.setLayout(new GridLayout(4, 1));

    companyImage = new ImageIcon(new ImageIcon(IMG_PATH).getImage().getScaledInstance(200, 100, Image.SCALE_DEFAULT));

    // Create text fields and labels for employee data
    idField = new JTextField(20);
    nameField = new JTextField(20);
    ageField = new JTextField(20);
    statusField = new JTextField(20);

    JLabel nameLabel = new JLabel("Name");
    JLabel ageLabel = new JLabel("Age");
    JLabel statusLabel = new JLabel("Status");
    JLabel idLabel = new JLabel("ID");

    // Create quit button
    JButton quitButton = new JButton("Quit");
    quitButton.addActionListener(this);

    // Create data entry panel
    JPanel dataPanel = new JPanel();
    dataPanel.add(nameLabel);
    dataPanel.add(nameField);
    dataPanel.add(ageLabel);
    dataPanel.add(ageField);
    dataPanel.add(statusLabel);
    dataPanel.add(statusField);
    dataPanel.add(idLabel);
    dataPanel.add(idField);

    this.add(dataPanel, BorderLayout.CENTER);

    JButton addButton = new JButton("Update");
    addButton.addActionListener(this);

    JButton showTableButton = new JButton("Show the table");
    showTableButton.addActionListener(this);

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addButton);
   
    buttonPanel.add(quitButton);

    this.add(buttonPanel, BorderLayout.CENTER);

// Add an empty panel as spacer
    JPanel spacePanel = new JPanel();
    spacePanel.setPreferredSize(new Dimension(20, 20));

    imgLabel = new JLabel(companyImage);

// Add the image label and the spacer panel to a new panel
    JPanel imagePanel = new JPanel(new BorderLayout());
    imagePanel.add(imgLabel);
    imagePanel.add(spacePanel, BorderLayout.SOUTH);

// Add the image panel to the frame
    this.add(imagePanel, BorderLayout.SOUTH);

    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();
    // db info
    String dbName = "LIST";
    String tableName = "EMPLOYEE";
    String[] columnHeaders =
    {
      "ID", "Name", "Age", "Status"
    };
    // connect to db

    // db query
    String dbQuery = "UPDATE EMPLOYEE SET name = ?, age = ?, status = ? WHERE ID = ?";
    // attributes   
    if (command.equals("Update"))
    { 
      String USER = "root";
      String PASS = "mysql1";
      String connectionURL = "jdbc:mysql://localhost:3306/LIST";
      try (Connection conn = DriverManager.getConnection(connectionURL, USER, PASS);)
      {
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String status = statusField.getText();
        int id = Integer.parseInt(idField.getText());

        PreparedStatement ps = conn.prepareStatement(dbQuery);
        ps.setString(1, name);
        ps.setInt(2, age);
        ps.setString(3, status);
        ps.setInt(4, id);
        ps.executeUpdate();
        System.out.println("Data updated successfully into " + tableName);
      }
      catch (SQLException se)
      {
        System.out.println("Error updating data: " + se.getMessage());
        se.printStackTrace();

      }

    }

    else if (command.equals("Show the table"))
    {
      dispose();
      new EmployeeList(dbName, tableName, columnHeaders);
    }

    else if (command.equals("Quit"))
    {
      dispose();
      new viewEmployee();
    }

  }

  public static void main(String[] arg)
  {
    new UpdateEmployeeList();
  }
}
