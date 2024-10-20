/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IAGUI;

/**
 *
 * @author nguyenthanhlong
 */
import IAGUI.DeleteEmployeeList;
import IAGUI.IAManager.AccountManagement;
import IAGUI.IAManager.ManagerAccount;
import IAGUI.IAManager.ManagerAttendance;
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
   * This class implements a GUI for updating account information.
   *
   * @author nguyenthanhlong
   */
public class UpdateAccount extends JFrame implements ActionListener
{
// Text fields for entering data
  private JTextField idField;
  private JTextField usernameField;
  private JTextField passwordField;

 
  public UpdateAccount()
  {
    // Set frame properties
    super("Update Account Page");
    this.setBounds(100, 200, 1000, 200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setBackground(BLUE_COLOR);
    this.setLayout(new GridLayout(4, 1));

    // Create text fields
    idField = new JTextField(20);
    usernameField = new JTextField(20);
    passwordField = new JTextField(20);

    // Create labels for each field
    JLabel usernameLabel = new JLabel("Username");
    JLabel passwordLabel = new JLabel("Password");
    JLabel idLabel = new JLabel("ID");

    
  // Create a panel to hold the data entry fields
    JPanel DataPanel = new JPanel();

    DataPanel.add(usernameLabel);
    DataPanel.add(usernameField);
    DataPanel.add(passwordLabel);
    DataPanel.add(passwordField);
    DataPanel.add(idLabel);
    DataPanel.add(idField);

    this.add(DataPanel, BorderLayout.CENTER);
    
    // Create buttons for update and show table functionality
    JButton DeleteButton = new JButton("Update");
    DeleteButton.addActionListener(this);
    
    JButton quitButton = new JButton("Quit");
    quitButton.addActionListener(this);

    JButton ShowTheTable = new JButton("Show the table");
    ShowTheTable.addActionListener(this);

    JPanel ButtonPanel = new JPanel();
    ButtonPanel.add(DeleteButton);
    
    ButtonPanel.add(quitButton);

    this.add(ButtonPanel, BorderLayout.SOUTH);

    this.setVisible(true);
  }

  public static void main(String[] arg)
  {
    new UpdateAccount();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    // Database connection details (assuming they are the same everywhere)
    String USER = "root";
    String PASS = "mysql1";
    String dbName = "LIST";
    String tableName = "USERS";
    String connectionURL = "jdbc:mysql://localhost:3306/LIST";
    String[] columnNames =
    {
      "ID", "Username", "Password"
    };
    String query = "UPDATE USERS SET username = ?, password = ? WHERE ID = ?";

    if (e.getActionCommand().equals("Update"))
    {

      try (Connection conn = DriverManager.getConnection(connectionURL, USER, PASS);)
      {
        
        String username = usernameField.getText();
        String password = passwordField.getText();
        int id = Integer.parseInt(idField.getText());

        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, username);
        ps.setString(2, password);
        ps.setInt(3, id);
        ps.executeUpdate();
        System.out.println("Data updated successfully into " + tableName);

      }

      catch (SQLException se)
      {
        System.out.println("Error");
      }
    }
    else if (e.getActionCommand().equals("Show the table"))
    {
      this.dispose();
      String[] columnHeaders =
      {
        "ID", "Username", "Password"
      };
      

      new AccountManagement(dbName, tableName, columnHeaders);
      
    }
    
    else if(e.getActionCommand().equals("Quit")) {
      this.dispose();
      new ManagerAccount();
    }

  }
}
