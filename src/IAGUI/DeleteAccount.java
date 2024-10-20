/*
 * This class represents the Delete Account page of the application.
 * It allows users to delete their accounts by entering their username and password.
 *
 */
package IAGUI;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DeleteAccount extends JFrame implements ActionListener
{

  // Text fields for username and password
  private JTextField usernameField;
  private JTextField idField;

  public DeleteAccount()
  {
    super("Delete Account Page");

    // Set frame properties
    this.setBounds(100, 200, 1000, 200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setBackground(BLUE_COLOR);
    this.setLayout(new GridLayout(4, 1));

    // Create text fields and labels
    usernameField = new JTextField(20);
    idField = new JTextField(20);
    JLabel usernameLabel = new JLabel("Username");
    JLabel idLabel = new JLabel("ID");

    // Create panel to hold username and password fields and labels
    JPanel DataPanel = new JPanel();
    DataPanel.add(usernameLabel);
    DataPanel.add(usernameField);
    DataPanel.add(idLabel);
    DataPanel.add(idField);

    // Add DataPanel to the center of the frame
    this.add(DataPanel, BorderLayout.CENTER);

    // Create buttons for delete and show table functionality
    JButton DeleteButton = new JButton("Delete");
    DeleteButton.addActionListener(this);
    JButton ShowTheTable = new JButton("Show the table");
    ShowTheTable.addActionListener(this);
    JButton quitButton = new JButton("Quit");
    quitButton.addActionListener(this);
    // Create panel to hold the buttons
    JPanel ButtonPanel = new JPanel();
    ButtonPanel.add(DeleteButton);

    ButtonPanel.add(quitButton);

    // Add ButtonPanel to the south of the frame
    this.add(ButtonPanel, BorderLayout.SOUTH);

    // Make the frame visible
    this.setVisible(true);
  }

  public static void main(String[] arg)
  {
    new DeleteAccount();
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

    // Delete query based on username and password
    String query = "DELETE FROM USERS WHERE username = ? AND ID = ?";

    if (e.getActionCommand().equals("Delete"))
    {
      JOptionPane.showMessageDialog(this, "Data is deleted");
      try (Connection conn = DriverManager.getConnection(connectionURL, USER, PASS);)
      {
        String username = usernameField.getText();
        int id = Integer.parseInt(idField.getText());

        // Prepare the statement with username and password
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, username);
        ps.setInt(2, id);

        // Execute the update and print success message
        ps.executeUpdate();
        System.out.println("Data deleted successfully into " + tableName);

      }
      catch (SQLException se)
      {
        System.out.println("Error deleting data: " + se.getMessage());
      }
    }
    else if (e.getActionCommand().equals("Show the table"))
    {
      // Call AccountManagement class to show the table (implementation not shown here)
      new AccountManagement(dbName, tableName, columnNames);

    }

    else if (e.getActionCommand().equals("Quit"))
    {
      this.dispose();
      new ManagerAccount();
    }

  }
}
