/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IAGUI.IAEmployee;
/**
 * This class implements a GUI window for employees to submit feedback.
 */
import static IAGUI.Login.LoginPage.BLUE_COLOR;
import IAGUI.IAManager.ManagerFeedback;
import IAGUI.JavaDBAccessIA;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JTextArea;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class EmployeeFeedback extends JFrame implements ActionListener
{

  private JPanel buttonPanel;
  private JLabel titleLabel;
  private JTextArea nameField;
  private JTextArea feedbackField;
  private JButton submitButton, quitButton;
  private JLabel nameLabel;
  private JLabel descriptionLabel;
  public static final Color BLUE_COLOR = new Color(35, 79, 30);
  public static final Font BIG_FONT = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40);
  private ManagerFeedback managerFeedback;
  private String feedbackText;

  public EmployeeFeedback()
  {
    super("Employee Feedback");
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.getContentPane().setBackground(BLUE_COLOR);
    this.setBounds(100, 200, 600, 400);
    this.setLayout(new BorderLayout());
    this.setLayout(new GridLayout(4, 1));

    titleLabel = new JLabel("Employee Feedback", JLabel.CENTER);
    titleLabel.setFont(BIG_FONT);
    titleLabel.setForeground(Color.WHITE);
    this.add(titleLabel, BorderLayout.NORTH);

    nameLabel = new JLabel("Name");

    nameField = new JTextArea();
    nameField.setPreferredSize(new Dimension(200, 50));

    descriptionLabel = new JLabel("Description");

    feedbackField = new JTextArea();
    feedbackField.setPreferredSize(new Dimension(200, 50));

    JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    namePanel.add(nameLabel);
    namePanel.add(nameField);
    add(namePanel); // Add namePanel to the center of the frame

    JPanel descriptionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    descriptionPanel.add(descriptionLabel);
    descriptionPanel.add(feedbackField);
    add(descriptionPanel);

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
    new EmployeeFeedback();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();
    // db info
    String dbName = "LIST";
    String tableName = "FEEDBACK";
    String USER = "root";
    String PASS = "mysql1";
    String connectionURL = "jdbc:mysql://localhost:3306/LIST";
    String[] columnHeaders =
    {
      "ID", "name", "feedback"
    };

    // db query
    String dbQuery = "INSERT INTO FEEDBACK (name, feedback) VALUES (?, ?)";
    // attributes   

    if (command.equals("Submit"))
    {

      try (Connection conn = DriverManager.getConnection(connectionURL, USER, PASS);)
      {
        String name = nameField.getText();
        String feedback = feedbackField.getText();

        PreparedStatement ps = conn.prepareStatement(dbQuery);
        ps.setString(1, name);
        ps.setString(2, feedback);
        ps.executeUpdate();
        System.out.println("Data inserted successfully into " + tableName);
        nameField.setText("");
        feedbackField.setText("");
        JOptionPane.showMessageDialog(this, "Feedback is submitted");
      }
      catch (SQLException se)
      {
        System.out.println("Error updating data: " + se.getMessage());
        se.printStackTrace();

      }
      System.out.println("Data is updated");
    }

    else if (e.getSource() == quitButton)
    {
      new EmployeeInterface();
      dispose();
    }
  }

}
