/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IAGUI.Login;

/**
 *
 * @author nguyenthanhlong
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

/**
 *
 * @author nguyenthanhlong
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ForgotPasswordPage extends JFrame implements ActionListener {
    // Constants
    public static final Color BLUE_COLOR = new Color(35, 79, 30);
    public static final Color BLACK_COLOR = new Color(0, 0, 0);
    public static final Font BIG_FONT = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 40);

    // Components
    private JLabel titleLabel, usernameLabel, passwordLabel, confirmpasswordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmpasswordField;
    private JButton Change;
    private JButton Quit; 
    private JButton forpasswordButton;
    private JPanel Buttonpanel;

    public ForgotPasswordPage() {
        super("Forgot password page");
        this.setBounds(100, 200, 600, 400);
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
        usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(BLACK_COLOR);
        usernameField = new JTextField(15);
        
        // Password Field
        passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(BLACK_COLOR);
        passwordField = new JPasswordField(15);

       
        confirmpasswordLabel = new JLabel("Confirm Password:"); // Added confirm password field
        confirmpasswordLabel.setForeground(BLACK_COLOR);
        confirmpasswordField = new JPasswordField(15);
   

        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);
        usernamePanel.add(passwordLabel);
        usernamePanel.add(passwordField);
        usernamePanel.add(confirmpasswordLabel);
        usernamePanel.add(confirmpasswordField);
        this.add(usernamePanel,BorderLayout.CENTER);
        

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

    public static void main(String[] args) {
        new ForgotPasswordPage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle login button action
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
         String command = e.getActionCommand();
        // Perform login authentication here
        if (command.equals("Change")) {
           JOptionPane.showMessageDialog(this, "The password changes successfully");
        }
        
        else if(command.equals("Quit")) {
          this.dispose();
          new LoginPage();
        }
}
}

