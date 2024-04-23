/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IAGUI;

/**
 *
 * @author nguyenthanhlong
 */
import static IAGUI.Login.LoginPage.BLUE_COLOR;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class UpdateTaskList extends JFrame implements ActionListener
{

  // data entry
  private JTextField taskName;
  private JTextField taskDescription;
  private JTextField taskDeadline;
  private JLabel idLabel;
  private JTextField idField;
  // control buttons
  private JButton updateButton;
  private JButton doneButton;
  private JPanel donePanel;
  private JPanel dataPanel;

  public UpdateTaskList()
  {
    //Format the frame
    super("Edit Page");
    this.setBounds(100, 200, 1000, 400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setBackground(BLUE_COLOR);
    this.setLayout(new GridLayout(4, 1));

    taskName = new JTextField(20);
    taskDescription = new JTextField(20);
    taskDeadline = new JTextField(20);

    JLabel NameLabel = new JLabel("Task name");
    JLabel DescriptionLabel = new JLabel("Task description");
    JLabel TaskDeadlineLabel = new JLabel("Task deadline");
     idLabel = new JLabel("Id");
     idField = new JTextField(20);
    
    
    JPanel DataPanel = new JPanel();
    DataPanel.add(idLabel);
    DataPanel.add(idField);
    DataPanel.add(NameLabel);
    DataPanel.add(taskName);
    DataPanel.add(DescriptionLabel);
    DataPanel.add(taskDescription);
    DataPanel.add(TaskDeadlineLabel);
    DataPanel.add(taskDeadline);

    this.add(DataPanel, BorderLayout.CENTER);

    JButton UpdateButton = new JButton("Update");
    UpdateButton.addActionListener(this);

    JButton ShowTheTable = new JButton("Show the table");
    ShowTheTable.addActionListener(this);

    JPanel ButtonPanel = new JPanel();
    ButtonPanel.add(UpdateButton);
    ButtonPanel.add(ShowTheTable);

    this.add(ButtonPanel, BorderLayout.SOUTH);

    this.setVisible(true);

  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();
    // db info
    String dbName = "List";
    String tableName = "TaskList";
    String[] columnHeaders =
    {
      "Id", "task_name", "task_description", "task_deadline"
    };
    // connect to db
    JavaDBAccessIA objDb = new JavaDBAccessIA(dbName);
    Connection myDbConn = objDb.getDbConn();
    // db query
    String dbQuery = "UPDATE EmployeeList SET Id = ?,task_name =?, task_description =?, task_deadline=? WHERE Id=?";
    // attributes   

    if (command.equals("Update"))
    {
      // receive data from text fields
      try
      {
        String name = taskName.getText();
        int description = Integer.parseInt(taskDescription.getText());
        String deadline = taskDeadline.getText();
        int id = Integer.parseInt(idField.getText());
        
        PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setInt(3, description);
        ps.setString(4, deadline);
        ps.executeUpdate();
        System.out.println("Data updated successfully into " + tableName);
      }
      catch (SQLException se)
      {
        System.out.println("Error updating data: " + se.getMessage());
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
      System.out.println("Data is updated");
    }

  }

  public static void main(String[] arg)
  {
    new UpdateTaskList();
  }
}
