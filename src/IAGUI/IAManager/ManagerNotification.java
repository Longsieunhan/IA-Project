package IAGUI.IAManager;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JTextArea;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ManagerNotification extends JFrame implements ActionListener
{

  private JPanel buttonPanel;
  private JLabel titleLabel;
  private JTextArea NotificationText;
  private JButton submitButton, quitButton;
  public static final Color BLUE_COLOR = new Color(35, 79, 30);
  public static final Font BIG_FONT = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40);

  public ManagerNotification()
  {
    super("Manager Notification");
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.getContentPane().setBackground(BLUE_COLOR);
    this.setBounds(100, 200, 800, 400);
    this.setLayout(new BorderLayout());

    titleLabel = new JLabel("Manager Notification", JLabel.CENTER);
    titleLabel.setFont(BIG_FONT);
    titleLabel.setForeground(Color.WHITE);
    this.add(titleLabel, BorderLayout.NORTH);

    NotificationText = new JTextArea();
    NotificationText.setPreferredSize(new Dimension(400, 400));

    JPanel feedbackPanel = new JPanel();
    feedbackPanel.add(NotificationText);
    this.add(feedbackPanel, BorderLayout.CENTER);

    submitButton = new JButton("Submit");
    submitButton.setPreferredSize(new Dimension(150, 50));
    submitButton.addActionListener(this);

    quitButton = new JButton("Quit");
    quitButton.setPreferredSize(new Dimension(150, 50));
    quitButton.addActionListener(this);

    buttonPanel = new JPanel();
    buttonPanel.add(submitButton);
    buttonPanel.add(quitButton);
    add(buttonPanel, BorderLayout.SOUTH);

    setLocationRelativeTo(null);
    setVisible(true);
  }

  public static void main(String[] args)
  {
    new ManagerNotification();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    
    if (e.getSource() == submitButton)
    {
      JOptionPane.showMessageDialog(this, "Notification is submitted");
      String Notification = NotificationText.getText();

      String USER = "root";
      String PASS = "mysql1";
      String connectionURL = "jdbc:mysql://localhost:3306/LIST";
      String dbName = "LIST";
      String tableName = "NOTIFICATION";
      String query = "INSERT INTO NOTIFICATION (notification) VALUES (?)";

      try (Connection conn = DriverManager.getConnection(connectionURL, USER, PASS);)
      {
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, Notification);
        ps.executeUpdate();
        System.out.println("Data inserted successfully into " + tableName);
      }
      catch (SQLException se)
      {
        System.out.println("Error inserting data: " + se.getMessage());
        se.printStackTrace();
      }
      // Process the feedback
    }
    else if (e.getSource() == quitButton)
    {
      new ManagerInterface();
      dispose();
    }
  }
}
