package IAGUI;
/*
 * This class represents the Insert Employee List page of the application.
 * It allows users to enter information for a new employee and add it to a database table.
 * It also provides buttons to view the existing employee data and return to the manager interface.
 *
 * @author nguyenthanhlong
 */
import IAGUI.DisplayEmployeeData;
import IAGUI.IAManager.ManagerInterface;
import IAGUI.IAManager.viewEmployee;
import static IAGUI.Login.LoginPage.BLUE_COLOR;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.ImageIcon;
import java.awt.Image;

public class InsertEmployeeList extends JFrame implements ActionListener
{
// Text fields for employee information
  private JTextField nameField;
  private JTextField ageField;
  private JTextField statusField;
  private JLabel imgLabel;
  public final URL IMG_PATH = getClass().getResource("thanhlong.png");
  public static ImageIcon companyImage;

  public InsertEmployeeList()
  {
    // Set frame properties
    super("Edit Page");
    this.setBounds(100, 200, 1000, 400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setBackground(BLUE_COLOR);
    this.setLayout(new GridLayout(4, 1));

    companyImage = new ImageIcon(new ImageIcon(IMG_PATH).getImage().getScaledInstance(200, 100, Image.SCALE_DEFAULT));

    // Create text fields for employee name, age, and status
    nameField = new JTextField(20);
    ageField = new JTextField(20);
    statusField = new JTextField(20);

    // Create labels for name, age, and status
    JLabel nameLabel = new JLabel("Name");
    JLabel ageLabel = new JLabel("Age");
    JLabel statusLabel = new JLabel("Status");

    JButton quitButton = new JButton("Quit");
    quitButton.addActionListener(this);

    JPanel dataPanel = new JPanel();
    dataPanel.add(nameLabel);
    dataPanel.add(nameField);
    dataPanel.add(ageLabel);
    dataPanel.add(ageField);
    dataPanel.add(statusLabel);
    dataPanel.add(statusField);

    this.add(dataPanel, BorderLayout.CENTER);

    // Create buttons for adding a new employee, showing the table, and quitting
    JButton addButton = new JButton("Add");
    addButton.addActionListener(this);

    JButton showTableButton = new JButton("Show the table");
    showTableButton.addActionListener(this);

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addButton);
    
    buttonPanel.add(quitButton);

    this.add(buttonPanel, BorderLayout.CENTER);

// Add an empty panel as spacer
    JPanel spacePanel = new JPanel();
    spacePanel.setPreferredSize(new Dimension(20, 20));

    imgLabel = new JLabel(companyImage);

// Add the image label and the spacer panel to a new panel
    JPanel imagePanel = new JPanel(new BorderLayout());
    imagePanel.add(imgLabel);
    imagePanel.add(spacePanel, BorderLayout.SOUTH);

// Add the image panel to the frame
    this.add(imagePanel, BorderLayout.SOUTH);

    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new InsertEmployeeList();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String USER = "root";
    String PASS = "mysql1";
    String connectionURL = "jdbc:mysql://localhost:3306/LIST";
    String dbName = "LIST";
    String tableName = "EMPLOYEE";
    
    String[] columnHeaders =
    {
      "ID", "name", "age", "status"
    };

    if (e.getActionCommand().equals("Add"))
    {

      String query = "INSERT INTO EMPLOYEE (name, age, status) VALUES (?, ?, ?)";

      try (Connection conn = DriverManager.getConnection(connectionURL, USER, PASS);)
      {
        String name = nameField.getText();
        int age =  Integer.parseInt(ageField.getText());
        String status = statusField.getText();

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, name);
        ps.setInt(2, age);
        ps.setString(3, status);
        ps.executeUpdate();
        System.out.println("Data inserted successfully into " + tableName);
      }
      catch (SQLException se)
      {
        System.out.println("Error inserting data: " + se.getMessage());
        se.printStackTrace();
      }

    }

    else if (e.getActionCommand().equals("Show the table"))
    {
      dispose();
      new DisplayEmployeeData(dbName, tableName, columnHeaders);
    }

    if (e.getActionCommand().equals("Quit"))
    {
      new viewEmployee();
      this.dispose();
    }
  }
}
