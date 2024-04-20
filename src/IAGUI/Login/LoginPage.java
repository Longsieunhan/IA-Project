/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IAGUI.Login;

import IAGUI.Login.ForgotPasswordPage;
import IAGUI.IAEmployee.EmployeeInterface;
import IAGUI.IAManager.ManagerInterface;
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

/**
 *
 * @author nguyenthanhlong
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class LoginPage extends JFrame implements ActionListener, ItemListener
{
  // Constants

  public static final Color BLUE_COLOR = new Color(0, 200, 250);
  public static final Color BLACK_COLOR = new Color(0, 0, 0);
  public static final Font BIG_FONT = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 40);

  // Components
  private JLabel titleLabel, usernameLabel, passwordLabel;
  private JTextField usernameField;
  private JPasswordField passwordField;
  private JButton loginButton;
  private JButton forpasswordButton;
  private JButton quitButton;
  private JPanel buttonPanel;
  JComboBox<String> roleSystem;
  String[] roleArray =
  {
    "Manager", "Employee"
  };
  boolean managerFlag;
  boolean employeeFlag;

  public LoginPage()
  {
    super("Login Page");
    this.setBounds(100, 200, 600, 400);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.getContentPane().setBackground(BLUE_COLOR);
    this.setLayout(new GridLayout(4, 1));

    managerFlag = true;
    employeeFlag = true;

    // Title Label
    titleLabel = new JLabel("Login Page", SwingConstants.CENTER);
    titleLabel.setFont(BIG_FONT);
    titleLabel.setForeground(Color.WHITE);
    add(titleLabel);

    // Username Field
    JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    usernameLabel = new JLabel("Username:");
    usernameLabel.setForeground(BLACK_COLOR);
    usernameField = new JTextField(15);
    usernamePanel.add(usernameLabel);
    usernamePanel.add(usernameField);
    add(usernamePanel);

    roleSystem = new JComboBox<>(roleArray);
    roleSystem.addActionListener(this);

    // Password Field
    JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    passwordLabel = new JLabel("Password:");
    passwordLabel.setForeground(BLACK_COLOR);
    passwordField = new JPasswordField(15);
    passwordPanel.add(passwordLabel);
    passwordPanel.add(passwordField);
    passwordPanel.add(roleSystem);
    add(passwordPanel);

    // Login Button
    loginButton = new JButton("Login");
    loginButton.addActionListener(this);
    loginButton.setPreferredSize(new Dimension(150, 50)); // Set preferred size for login button

    forpasswordButton = new JButton("Forgot password");
    forpasswordButton.addActionListener(this);
    forpasswordButton.setPreferredSize(new Dimension(150, 50)); // Set preferred size for forgot password button

    quitButton = new JButton("Quit");
    quitButton.addActionListener(this);
    quitButton.setPreferredSize(new Dimension(150, 50)); // Set preferred size for quit button

    buttonPanel = new JPanel();
    buttonPanel.add(loginButton);
    buttonPanel.add(forpasswordButton);
    buttonPanel.add(quitButton);

    this.add(buttonPanel, BorderLayout.SOUTH);

    setVisible(true);
  }

  public static void main(String[] args)
  {
    new LoginPage();
  }

  @Override
  public void itemStateChanged(ItemEvent e)
  {
    managerFlag = false;
    employeeFlag = false;
    int index = roleSystem.getSelectedIndex();

    if (index == 0)
    {
      managerFlag = true;
    }
    else
    {
      employeeFlag = true;
    }
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    // Handle login button action
    String username = usernameField.getText();
    String password = new String(passwordField.getPassword());
    String command = e.getActionCommand();
    // Perform login authentication here
    if (command.equals("Login"))
    {
      this.dispose();
      new ManagerInterface();

    }
    if (command.equals("Forgot password"))
    {
      this.dispose();
      new ForgotPasswordPage();
    }
  }

}
