package Manager.Employee;

import Model.Employee;
import Login.LoginPage;
import Manager.Employee.EmployeeList;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
//import static IAGUI.LoginPage.GREEN_COLOR;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class UpdateEmployeeList extends JFrame implements ActionListener, ItemListener
{

  Employee currentEmployee;
  private JTextField idField;
  private JTextField nameField;
  private JTextField ageField;
  private JTextField statusField;
  private JLabel imgLabel;
  public final URL IMG_PATH = getClass().getResource("thanhlong.png");
  public static ImageIcon companyImage;
  private JComboBox<String> statusSelection;
  private JComboBox<String> genderSelection;

  String[] statusArray =
  {
    "Present", "Absent", "Sick", "Good"
  };
  String[] genderArray =
  {
    "Male", "Female"
  };

  public UpdateEmployeeList(Employee currentEmployee)
  {
    super("Update Employee Page");
    this.setBounds(100, 200, 880, 400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setBackground(LoginPage.GREEN_COLOR);
    this.setLayout(new GridLayout(4, 1));
    this.currentEmployee = currentEmployee;

    // Set up the company image
    companyImage = new ImageIcon(new ImageIcon(IMG_PATH).getImage().getScaledInstance(200, 100, Image.SCALE_DEFAULT));
    imgLabel = new JLabel(companyImage);


    // Initialize dropdown selections
    statusSelection = new JComboBox<>(statusArray);
    statusSelection.addItemListener(this);

    genderSelection = new JComboBox<>(genderArray);
    genderSelection.addItemListener(this);

    // Initialize text fields and labels for employee data
    idField = new JTextField(20);
    nameField = new JTextField(20);
    ageField = new JTextField(20);
    statusField = new JTextField(20);

    idField.setText(String.valueOf(currentEmployee.getId()));
    nameField.setText(currentEmployee.getName());
    ageField.setText(String.valueOf(currentEmployee.getAge()));
    statusSelection.setSelectedItem(currentEmployee.getStatus());
    genderSelection.setSelectedItem(currentEmployee.getGender());

    JLabel nameLabel = new JLabel("Name");
    JLabel ageLabel = new JLabel("Age");
    JLabel statusLabel = new JLabel("Status");
    JLabel idLabel = new JLabel("ID");

    // Create data entry panel
    JPanel dataPanel = new JPanel();
    dataPanel.add(idLabel);
    dataPanel.add(idField);
    dataPanel.add(nameLabel);
    dataPanel.add(nameField);
    dataPanel.add(ageLabel);
    dataPanel.add(ageField);
    dataPanel.add(statusLabel);
    dataPanel.add(statusSelection);
    dataPanel.add(new JLabel("Gender"));
    dataPanel.add(genderSelection);

    this.add(dataPanel, BorderLayout.CENTER);

    // Create and add buttons
    JButton updateButton = new JButton("Update");
    updateButton.addActionListener(this);

    JButton quitButton = new JButton("Quit");
    quitButton.addActionListener(this);

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(updateButton);
    buttonPanel.add(quitButton);

    this.add(buttonPanel, BorderLayout.CENTER);

    // Add an empty spacer panel and the image panel
    JPanel spacePanel = new JPanel();
    spacePanel.setPreferredSize(new Dimension(20, 20));

    JPanel imagePanel = new JPanel(new BorderLayout());
    imagePanel.add(imgLabel);
    imagePanel.add(spacePanel, BorderLayout.SOUTH);

    this.add(imagePanel, BorderLayout.SOUTH);

    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();
    if ("Update".equals(command))
    {
      String dbQuery = "UPDATE EMPLOYEE SET name = ?, age = ?, status = ?, gender = ? WHERE ID = ?";
      String connectionURL = "jdbc:mysql://localhost:3306/LIST";

      try (Connection conn = DriverManager.getConnection(connectionURL, "root", "mysql1"))
      {
        PreparedStatement ps = conn.prepareStatement(dbQuery);
        ps.setString(1, nameField.getText());
        ps.setInt(2, Integer.parseInt(ageField.getText()));
        ps.setString(3, (String) statusSelection.getSelectedItem());
        ps.setString(4, (String) genderSelection.getSelectedItem());
        ps.setInt(5, Integer.parseInt(idField.getText()));

        ps.executeUpdate();
        System.out.println("Data updated successfully in EMPLOYEE table.");
        JOptionPane.showMessageDialog(this, "Data updated successfully");

      }
      catch (SQLException se)
      {
        System.out.println("Error updating data: " + se.getMessage());
        se.printStackTrace();
      }
    }
    else if ("Quit".equals(command))
    {
      dispose();
      new EmployeeList("LIST", "EMPLOYEE", new String[]
      {
        "ID", "name", "age", "status", "gender"
      });
    }
  }

  @Override
  public void itemStateChanged(ItemEvent e)
  {
    // Add behavior here if you need to handle item events
  }

//  public static void main(String[] args)
//  {
//    new UpdateEmployeeList();
//  }
}
