/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IAGUI;

/*
 * This class represents the Insert Account page of the application.
 * It allows users to enter a username and password for a new account.
 * It provides buttons to insert the new account or view the existing accounts table.
 *
 * @author nguyenthanhlong
 */
import IAGUI.DeleteEmployeeList;
import IAGUI.IAManager.AccountManagement;
import IAGUI.IAManager.ManagerAccount;
import static IAGUI.Login.LoginPage.BLUE_COLOR;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author nguyenthanhlong
 */
public class InsertAccount extends JFrame implements ActionListener
{
// Text fields for username and password input
  private JTextField usernameField;
  private JTextField passwordField;

  public InsertAccount()
  {
// Set frame properties
    super("Insert Account Page");
    this.setBounds(100, 200, 900, 400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setBackground(BLUE_COLOR);
    this.setLayout(new GridLayout(4, 1));

    // Create text fields for username and password
    usernameField = new JTextField(20);
    passwordField = new JTextField(20);

    // Create labels for username and password
    JLabel usernameLabel = new JLabel("Username");
    JLabel passwordLabel = new JLabel("Password");

    
    // Create a panel to hold the username and password labels and fields
    JPanel DataPanel = new JPanel();

    DataPanel.add(usernameLabel);
    DataPanel.add(usernameField);
    DataPanel.add(passwordLabel);
    DataPanel.add(passwordField);

    this.add(DataPanel, BorderLayout.CENTER);

    
     // Create buttons for inserting a new account and showing the accounts table
    JButton DeleteButton = new JButton("Insert");
    DeleteButton.addActionListener(this);

    JButton ShowTheTable = new JButton("Show the table");
    ShowTheTable.addActionListener(this);
    
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

  @Override
  public void actionPerformed(ActionEvent e)
  {

    // Database connection details
    String USER = "root";
    String PASS = "mysql1";
    String dbName = "LIST";
    String tableName = "USERS";
    String connectionURL = "jdbc:mysql://localhost:3306/LIST";
    String[] columnNames =
    {
      "ID", "username", "password"
    };
    String query = "INSERT INTO USERS (username, password) VALUES (?, ?)";

    if (e.getActionCommand().equals("Insert"))
    {
      System.out.println("Data is inserted");
      try (Connection conn = DriverManager.getConnection(connectionURL, USER, PASS);)
      {
        String username = usernameField.getText();
        String password = passwordField.getText();

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, password);
        ps.executeUpdate();
        System.out.println("Data inserted successfully into " + tableName);

      }

      catch (SQLException se)
      {
        System.out.println("Error");
      }
    }

      if (e.getActionCommand().equals("Show the table"))
      {
        this.dispose();
        String[] columnHeaders =
        {
          "ID", "Username", "Password"
        };

        new AccountManagement(dbName, tableName, columnHeaders);
      }
      
      if(e.getActionCommand().equals("Quit")) {
        dispose();
        new ManagerAccount();
        
      }
    
  }
}
