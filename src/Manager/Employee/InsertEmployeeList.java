package Manager.Employee;

/*
 * This class represents the Insert Employee List page of the application.
 * It allows users to enter information for a new employee and add it to a database table.
 * It also provides buttons to view the existing employee data and return to the manager interface.
 *
 * @author nguyenthanhlong
 */
import Login.LoginPage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
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
//import static IAGUI.LoginPage.GREEN_COLOR;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class InsertEmployeeList extends JFrame implements ActionListener, ItemListener
{
// Text fields for employee information

  private JTextField nameField;
  private JTextField ageField;
  private JTextField statusField;
  private JLabel imgLabel;
//  public final URL IMG_PATH = getClass().getResource("thanhlong.png");
//  public static ImageIcon companyImage;
  private JComboBox statusSelection;
  String[] statusArray =
  {
    "Present", "Absent", "Sick", "Good"
  };
  private JComboBox genderSelection;
  String[] genderArray =
  {
    "Male", "Female"
  };

  public InsertEmployeeList()
  {
    // Set frame properties
    super("Edit Page");
    this.setBounds(100, 200, 1000, 400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setBackground(LoginPage.GREEN_COLOR);
    this.setLayout(new GridLayout(4, 1));

    statusSelection = new JComboBox(statusArray);
    statusSelection.addItemListener(this);

    genderSelection = new JComboBox(genderArray);
    genderSelection.addItemListener(this);

//    companyImage = new ImageIcon(new ImageIcon(IMG_PATH).getImage().getScaledInstance(200, 100, Image.SCALE_DEFAULT));

    // Create text fields for employee name, age, and status
    nameField = new JTextField(20);
    ageField = new JTextField(20);
    statusField = new JTextField(20);

    // Create labels for name, age, and status
    JLabel nameLabel = new JLabel("Name");
    JLabel ageLabel = new JLabel("Age");
    JLabel statusLabel = new JLabel("Status");
    JLabel genderLabel = new JLabel("Gender");

    JButton quitButton = new JButton("Quit");
    quitButton.addActionListener(this);

    JPanel dataPanel = new JPanel();
    dataPanel.add(nameLabel);
    dataPanel.add(nameField);
    dataPanel.add(ageLabel);
    dataPanel.add(ageField);
    dataPanel.add(statusLabel);
    dataPanel.add(statusSelection);
    dataPanel.add(genderLabel);
    dataPanel.add(genderSelection);

    this.add(dataPanel, BorderLayout.CENTER);

    // Create buttons for adding a new employee, showing the table, and quitting
    JButton addButton = new JButton("Add");
    addButton.addActionListener(this);

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addButton);

    buttonPanel.add(quitButton);

    this.add(buttonPanel, BorderLayout.CENTER);

// Add an empty panel as spacer
    JPanel spacePanel = new JPanel();
    spacePanel.setPreferredSize(new Dimension(20, 20));

//    imgLabel = new JLabel(companyImage);

// Add the image label and the spacer panel to a new panel
//    JPanel imagePanel = new JPanel(new BorderLayout());
//    imagePanel.add(imgLabel);
//    imagePanel.add(spacePanel, BorderLayout.SOUTH);

// Add the image panel to the frame
//    this.add(imagePanel, BorderLayout.SOUTH);

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
      "ID", "name", "age", "status", "gender"  // Adjusted to include gender
    };
    if (e.getActionCommand().equals("Add"))
    {

      String query = "INSERT INTO EMPLOYEE (name, age, status, gender) VALUES (?, ?, ?, ?)";

      try (Connection conn = DriverManager.getConnection(connectionURL, USER, PASS);)
      {
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String status = (String) statusSelection.getSelectedItem();
        String gender = (String) genderSelection.getSelectedItem();

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, name);
        ps.setInt(2, age);
        ps.setString(3, status); 
        ps.setString(4, gender);
        ps.executeUpdate();
        System.out.println("Data inserted successfully into " + tableName);
        JOptionPane.showMessageDialog(this, "Data inserted successfully");

      }
      catch (SQLException se)
      {
        System.out.println("Error inserting data: " + se.getMessage());
        se.printStackTrace();
      }

    }
    if (e.getActionCommand().equals("Quit"))
    {
      new EmployeeList(dbName, tableName, columnHeaders);
      this.dispose();
    }
  }

  @Override
  public void itemStateChanged(ItemEvent e)
  {

  }

}
 