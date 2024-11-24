package Manager.Account;

import Login.LoginPage;
import Model.User;
import Manager.Account.AccountManagement;
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
import javax.swing.JPanel;
import javax.swing.JTextField;
//import static IAGUI.LoginPage.GREEN_COLOR;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 * This class implements a GUI for updating account information.
 */
public class UpdateAccount extends JFrame implements ActionListener, ItemListener
{

  // Text fields for entering data
  private JTextField idField;
  private JTextField usernameField;
  private JTextField passwordField;
  User user;
  User currentUser;
  String selectedRole;
  private JComboBox<String> roleSelection;
  String[] roleArray =
  {
    "Manager", "Employee"
  };

  public UpdateAccount(User user, User currentUser)
  {
    // Set frame properties
    super("Update Account Page");
    this.setBounds(100, 200, 1200, 200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setBackground(LoginPage.GREEN_COLOR);
    this.setLayout(new GridLayout(4, 1));
    this.user = user;
    this.currentUser = currentUser;


    // Initialize roleSelection ComboBox before setting the selected item
    roleSelection = new JComboBox<>(roleArray);
    roleSelection.addItemListener(this);
    roleSelection.setSelectedItem(currentUser.getRole());
    roleSelection.setSelectedItem(currentUser.getRole() != null && !currentUser.getRole().isEmpty() ? currentUser.getRole() : roleArray[0]);


    // Create text fields
    idField = new JTextField(20);
    usernameField = new JTextField(20);
    passwordField = new JTextField(20);

    idField.setText(String.valueOf(currentUser.getId()));
    usernameField.setText(currentUser.getUsername());
    passwordField.setText(String.valueOf(currentUser.getPassword()));

    // Create labels for each field
    JLabel usernameLabel = new JLabel("Username");
    JLabel passwordLabel = new JLabel("Password");
    JLabel idLabel = new JLabel("ID");

    // Create a panel to hold the data entry fields
    JPanel DataPanel = new JPanel();
    DataPanel.add(idLabel);
    DataPanel.add(idField);
    DataPanel.add(usernameLabel);
    DataPanel.add(usernameField);
    DataPanel.add(passwordLabel);
    DataPanel.add(passwordField);
    DataPanel.add(roleSelection);

    this.add(DataPanel, BorderLayout.CENTER);

    // Create buttons for update and quit functionality
    JButton updateButton = new JButton("Update");
    updateButton.addActionListener(this);

    JButton quitButton = new JButton("Quit");
    quitButton.addActionListener(this);

    JPanel ButtonPanel = new JPanel();
    ButtonPanel.add(updateButton);
    ButtonPanel.add(quitButton);

    this.add(ButtonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
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
      "ID", "username", "password", "role"
    };
    String query = "UPDATE USERS SET username = ?, password = ?, role = ? WHERE ID = ?";

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
        ps.setString(3, selectedRole);
        ps.setInt(4, id);
        ps.executeUpdate();

        System.out.println("Data updated successfully into " + tableName);
        JOptionPane.showMessageDialog(this, "Data updated successfully");

      }
      catch (SQLException se)
      {
        System.out.println("Error inserting data: " + se.getMessage());
        se.printStackTrace();
      }
    }
    else if (e.getActionCommand().equals("Quit"))
    {
      this.dispose();
      new AccountManagement(columnNames, user);
    }
  }

  @Override
  public void itemStateChanged(ItemEvent e)
  {
    if (e.getSource() == roleSelection && e.getStateChange() == ItemEvent.SELECTED)
    {
      // Get the selected role
      this.selectedRole = (String) roleSelection.getSelectedItem();
      System.out.println("Selected Role: " + selectedRole);
    }
  }
}
