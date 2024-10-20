/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IAGUI;

/*
 * This class represents the Delete Feedback page of the application.
 * It allows users to delete feedback entries from the database by entering 
 * the name of the person who provided the feedback and the feedback text.
 *
 */
import IAGUI.IAManager.ManagerFeedback;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author nguyenthanhlong
 */
public class DeleteFeedback extends JFrame implements ActionListener
{
  
// Text fields for name and feedback content
  private JTextField Name, idField;
  private JTextField Feedback;
  private JTextField status;
  private JCheckBox checkBox;

  public DeleteFeedback()
  {
// Set frame properties
    super("Delete Feedback Page");
    this.setBounds(100, 200, 1000, 400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setBackground(BLUE_COLOR);
    this.setLayout(new GridLayout(4, 1));

    Name = new JTextField(20);
    idField = new JTextField(20);

    JLabel NameLabel = new JLabel("name");

    JLabel idLabel = new JLabel("ID");
    
// Create data entry panel
    JPanel DataPanel = new JPanel();
    DataPanel.add(idLabel);
    DataPanel.add(idField);
    DataPanel.add(NameLabel);
    DataPanel.add(Name);

    this.add(DataPanel, BorderLayout.CENTER);

    JButton DeleteButton = new JButton("Delete");
    DeleteButton.addActionListener(this);

    JButton ShowTheTable = new JButton("Show the table");
    ShowTheTable.addActionListener(this);

    JButton quitButton = new JButton("Quit");
    quitButton.addActionListener(this);

    JPanel ButtonPanel = new JPanel();
    ButtonPanel.add(DeleteButton);
    
    ButtonPanel.add(quitButton);

    this.add(ButtonPanel, BorderLayout.SOUTH);

    this.setVisible(true);
  }

  public static void main(String[] arg)
  {
    new DeleteFeedback();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
 // Database connection details
    String DBURL = "jdbc:mysql://localhost:3306/LIST";
    String USER = "root";
    String PASS = "mysql1";
    String dbName = "LIST";
    String tableName = "FEEDBACK";
    String[] columnNames =
    {
      "ID", "Names", "Feedback"
    };
    String query = "DELETE FROM FEEDBACK WHERE  ID = ? AND name = ?";

    if (e.getActionCommand().equals("Delete"))
    {

      try (Connection conn = DriverManager.getConnection(DBURL, USER, PASS);)
      {
        String name = Name.getText();
        int id = Integer.parseInt(idField.getText());

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ps.setString(2, name);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(this,"Data delete successfully");

      }

      catch (SQLException se)
      {
        System.out.println("Error inserting data" + se.getMessage());
        se.printStackTrace();
      }
    }
    if (e.getActionCommand().equals("Show the table"))
    {
     
      String[] columnHeaders =
      {
        "ID", "Names", "Feedback" 
      };

      new DisplayFeedback(dbName, tableName, columnHeaders);
    }
    
    if(e.getActionCommand().equals("Quit")) {
      new ManagerFeedback();
      dispose();
    }
  }
}
