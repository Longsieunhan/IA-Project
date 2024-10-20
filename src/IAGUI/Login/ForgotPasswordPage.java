/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IAGUI.Login;

/**
 *
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author nguyenthanhlong
 */
public class ForgotPasswordPage extends JFrame implements ActionListener
{
  // Constants

  public static final Color BLUE_COLOR = new Color(35, 79, 30);
  public static final Color BLACK_COLOR = new Color(0, 0, 0);
  public static final Font BIG_FONT = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40);

  // Components
  public JLabel titleLabel, usernameLabel, passwordLabel, confirmpasswordLabel, idLabel;
  public JTextField usernameField, passwordField, confirmpasswordField;
  ;
    public JButton Change;
  public JButton Quit;
  public JButton forpasswordButton;
  public JPanel Buttonpanel;
  public JTextField idTextfield;

  public ForgotPasswordPage()
  {
    super("Forgot password page");
    this.setBounds(100, 200, 1000, 400);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.getContentPane().setBackground(BLUE_COLOR);
    this.setLayout(new GridLayout(4, 1));

    // Title Label
    titleLabel = new JLabel("Forgot password page", SwingConstants.CENTER);
    titleLabel.setFont(BIG_FONT);
    titleLabel.setForeground(Color.WHITE);
    add(titleLabel);

    // Username Field
    JPanel usernamePanel = new JPanel();

    idTextfield = new JTextField(20);
    idLabel = new JLabel("ID:");

    usernameLabel = new JLabel("Username:");
    usernameLabel.setForeground(BLACK_COLOR);
    usernameField = new JTextField(20);

    // Password Field
    passwordLabel = new JLabel("Password:");
    passwordLabel.setForeground(BLACK_COLOR);
    passwordField = new JTextField(20);

    usernamePanel.add(idLabel);
    usernamePanel.add(idTextfield);
    usernamePanel.add(usernameLabel);
    usernamePanel.add(usernameField);
    usernamePanel.add(passwordLabel);
    usernamePanel.add(passwordField);
    this.add(usernamePanel, BorderLayout.CENTER);

    // Login Button
    Change = new JButton("Change");
    Change.addActionListener(this);
    Change.setPreferredSize(new Dimension(75, 25));

    Quit = new JButton("Quit");
    Quit.addActionListener(this);
    Quit.setPreferredSize(new Dimension(75, 25));

    Buttonpanel = new JPanel();
    Buttonpanel.add(Change);
    Buttonpanel.add(Quit);
    this.add(Buttonpanel, BorderLayout.SOUTH);

    setVisible(true);
  }

  public static void main(String[] args)
  {
    new ForgotPasswordPage();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String USER = "root";
    String PASS = "mysql1";
    String dbName = "LIST";
    String tableName = "USERS";
    String connectionURL = "jdbc:mysql://localhost:3306/LIST";
    String[] columnHeaders =
    {
      "ID", "Username", "Password"
    };

    // Handle login button action
    String command = e.getActionCommand();
    String dbQuery = "UPDATE USERS SET username =?, password =? WHERE ID=? ";
    // Perform login authentication here
    if (command.equals("Change"))
    {
      String username = usernameField.getText();
      String password = passwordField.getText();
      int id = Integer.parseInt(idTextfield.getText());

      try (Connection conn = DriverManager.getConnection(connectionURL, USER, PASS);)
      {
        PreparedStatement ps = conn.prepareStatement(dbQuery);
        ps.setString(1, username);
        ps.setString(2, password);
        ps.setInt(3, id);
        ps.executeUpdate();
        System.out.println("Data update successfully into " + tableName);
        usernameField.setText("");
        passwordField.setText("");
        JOptionPane.showMessageDialog(this, "The password changes successfully");
      }
      catch (SQLException se)
      {
        System.out.println("Error inserting data: " + se.getMessage());
        se.printStackTrace();
      }

    }

    else if (command.equals("Quit"))
    {
      this.dispose();
      new LoginPage();
    }
  }
}

