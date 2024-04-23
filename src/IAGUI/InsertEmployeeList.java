package IAGUI;

import static IAGUI.Login.LoginPage.BLUE_COLOR;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class InsertEmployeeList extends JFrame implements ActionListener
{

  private JTextField nameField;
  private JTextField ageField;
  private JTextField conditionField;
  private JLabel idLabel;
  private JTextField idField;
  

  public InsertEmployeeList()
  {
    super("Edit Page");
    this.setBounds(100, 200, 900, 400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setBackground(BLUE_COLOR);
    this.setLayout(new GridLayout(4, 1));

    nameField = new JTextField(20);
    ageField = new JTextField(20);
    conditionField = new JTextField(20);

    JLabel nameLabel = new JLabel("Name");
    JLabel ageLabel = new JLabel("Age");
    JLabel conditionLabel = new JLabel("Condition");

    idLabel = new JLabel("ID");
    idField = new JTextField(20);

    JButton quitButton = new JButton("Quit");
    quitButton.addActionListener(this);
    
    JPanel dataPanel = new JPanel();
    dataPanel.add(idLabel);
    dataPanel.add(idField);
    dataPanel.add(nameLabel);
    dataPanel.add(nameField);
    dataPanel.add(ageLabel);
    dataPanel.add(ageField);
    dataPanel.add(conditionLabel);
    dataPanel.add(conditionField);

    this.add(dataPanel, BorderLayout.CENTER);

    JButton addButton = new JButton("Add");
    addButton.addActionListener(this);

    JButton showTableButton = new JButton("Show the table");
    showTableButton.addActionListener(this);

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addButton);
    buttonPanel.add(showTableButton);
    buttonPanel.add(quitButton);

    this.add(buttonPanel, BorderLayout.SOUTH);

    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new InsertEmployeeList();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {

    if (e.getActionCommand().equals("Add"))
    {
    String dbName = "List";
    String tableName = "EmployeeList";
    String[] columnHeaders =
    {
      "Id", "employee_name", "employee_age", "condition"
    };
      String query = "INSERT INTO EmployeeList (Id, employee_name, employee_age, condition) VALUES (?, ?, ?, ?)";

      JavaDBAccessIA objAccess = new JavaDBAccessIA(dbName);
      objAccess.setDbConn();
      Connection myDbConn = objAccess.getDbConn();

      try
      {
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String condition = conditionField.getText();
        int id = Integer.parseInt(idField.getText());
        
        PreparedStatement ps = myDbConn.prepareStatement(query);
        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setInt(3, age);
        ps.setString(4, condition);
        ps.executeUpdate();
        System.out.println("Data inserted successfully into " + tableName);
      }
      catch (SQLException se)
      {
        System.out.println("Error inserting data: " + se.getMessage());
        se.printStackTrace();
      } finally
      {
        try
        {
          myDbConn.close();
        }
        catch (SQLException ex)
        {
          System.out.println("Error closing database connection: " + ex.getMessage());
          ex.printStackTrace();
        }
      }
    }
    else if (e.getActionCommand().equals("Show the table"))
    {
      String dbName = "List";
      String tableName = "EmployeeList";
      String[] columnHeaders =
      {
       "Id", "employee_name", "employee_age", "condition"
      };
      new DisplayEmployeeData(dbName, tableName, columnHeaders);
    }
    if(e.getActionCommand().equals("Quit")) {
      new EmployeeList();
      this.dispose();
    }
  }
}
