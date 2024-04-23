/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IAGUI;

/**
 *
 * @author nguyenthanhlong
 */
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

public class About extends JFrame implements ActionListener, ItemListener
{
  // Constants

  public static final Color BLUE_COLOR = new Color(35, 79, 30);
  public static final Color BLACK_COLOR = new Color(0, 0, 0);
  public static final Font BIG_FONT = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 40);

  // Components
  private JLabel titleLabel, usernameLabel, passwordLabel;


  public About()
  {
    super("About");
    this.setBounds(100, 200, 600, 400);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.getContentPane().setBackground(BLUE_COLOR);
    this.setLayout(new GridLayout(4, 1));

    
    titleLabel = new JLabel("Login Page", SwingConstants.CENTER);
    titleLabel.setFont(BIG_FONT);
    titleLabel.setForeground(Color.WHITE);
    add(titleLabel);

    // Username Field
   
    setVisible(true);
  }

  public static void main(String[] args)
  {
    new About();
  }


  @Override
  public void actionPerformed(ActionEvent e)
  {
   
  }

  @Override
  public void itemStateChanged(ItemEvent e)
  {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

}
