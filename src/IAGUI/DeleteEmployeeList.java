/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IAGUI;

/*
 * This class represents the Delete Employee List page of the application.
 * It allows users to delete employee records from the database by entering 
 * the employee's name, age, and status.
 *
 */
import IAGUI.IAManager.viewEmployee;
import static IAGUI.Login.LoginPage.BLUE_COLOR;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author nguyenthanhlong
 */
public class DeleteEmployeeList extends JFrame implements ActionListener
{
// Text fields for employee name, age, and status

  private JTextField Name;
  private JTextField Age;
  private JTextField status;
  private JCheckBox checkBox;
  private JLabel imgLabel;
  public final URL IMG_PATH = getClass().getResource("thanhlong.png");
  public static ImageIcon companyImage;
  private JTextField idField;

  public DeleteEmployeeList()
  {
// Set frame properties
    super("Delete Employee Page");
    this.setBounds(100, 200, 900, 400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setBackground(BLUE_COLOR);
    this.setLayout(new GridLayout(4, 1));

    companyImage = new ImageIcon(new ImageIcon(IMG_PATH).getImage().getScaledInstance(200, 100, Image.SCALE_DEFAULT));

    // Create text fields and labels for name, age, and status
    Name = new JTextField(20);
    idField = new JTextField(20);

    JLabel NameLabel = new JLabel("Name");
    JLabel idLabel = new JLabel("ID");

    // Create a panel to hold the name, age, and status fields and labels
    JPanel DataPanel = new JPanel();
    DataPanel.add(idLabel);
    DataPanel.add(idField);
    DataPanel.add(NameLabel);
    DataPanel.add(Name);

    // Add the DataPanel to the center of the frame
    this.add(DataPanel, BorderLayout.CENTER);

    // Add an empty panel as spacer
    JPanel spacePanel = new JPanel();
    spacePanel.setPreferredSize(new Dimension(20, 20));

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

    imgLabel = new JLabel(companyImage);

// Add the image label and the spacer panel to a new panel
    JPanel imagePanel = new JPanel(new BorderLayout());
    imagePanel.add(imgLabel);
    imagePanel.add(spacePanel, BorderLayout.SOUTH);

    // Add the image panel to the frame
    this.add(imagePanel, BorderLayout.SOUTH);

// Create buttons for delete and show table functionality
    this.setVisible(true);

  }

  public static void main(String[] arg)
  {
    new DeleteEmployeeList();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    // Database connection details
    String DBURL = "jdbc:mysql://localhost:3306/LIST";
    String USER = "root";
    String PASS = "mysql1";
    String dbName = "LIST";
    String tableName = "EMPLOYEE";
    String[] columnNames =
    {
      "ID", ",name", "age", "status"
    };
    // Delete query based on name, age, and status
    String query = "DELETE FROM EMPLOYEE WHERE name = ? AND id = ?";

    if (e.getActionCommand().equals("Delete"))
    {
      try (Connection conn = DriverManager.getConnection(DBURL, USER, PASS);)
      {
        String name = Name.getText();
        int id = Integer.parseInt(idField.getText());
// Prepare the statement with employee details
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, name);
        ps.setInt(2, id);
        ps.executeUpdate();
        System.out.println("Data delete successfully into " + tableName);
      }
      catch (SQLException se)
      {
        System.out.println("Error inserting data" + se.getMessage());
        se.printStackTrace();
      }
    }
    if (e.getActionCommand().equals("Show the table"))
    {
      dispose();
      String[] columnHeaders =
      {
        "ID", "name", "age", "status"
      };

      new DisplayEmployeeData(dbName, tableName, columnHeaders);
    }
    
    if (e.getActionCommand().equals("Quit")) {
      this.dispose();
      new viewEmployee();

    }
  }
}
