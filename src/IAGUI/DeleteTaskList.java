/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IAGUI;

/**
 * This class implements a GUI window for updating task information in a database.
 */

import IAGUI.IAManager.ManagerWorkingProcess;
import static IAGUI.Login.LoginPage.BLUE_COLOR;
import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class DeleteTaskList extends JFrame implements ActionListener
{

  // data entry
  private JTextField taskName;
  private JTextField taskDescription;
  private JTextField taskDeadline;
  private JTextField employeeField;
  private JTextField idField;
  // control buttons
  private JButton updateButton;
  private JButton doneButton;
  private JPanel donePanel;
  private JPanel dataPanel;

  public DeleteTaskList()
  {
    //Format the frame
    super("Delete Task Page");
    this.setBounds(100, 200, 1000, 400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setBackground(BLUE_COLOR);
    this.setLayout(new GridLayout(4, 1));

    taskName = new JTextField(20);
    taskDescription = new JTextField(20);
    taskDeadline = new JTextField(20);
    employeeField = new JTextField(20);
    idField = new JTextField(20);

    JLabel NameLabel = new JLabel("Task name");

    JLabel idLabel = new JLabel("ID");
    
// Create data entry panel
    JPanel DataPanel = new JPanel();
    DataPanel.add(idLabel);
    DataPanel.add(idField);
    DataPanel.add(NameLabel);
    DataPanel.add(taskName);

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

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();
    
    // Database connection inf
    String USER = "root";
    String PASS = "mysql1";
    String dbName = "LIST";
    String tableName = "TASK";
    String connectionURL = "jdbc:mysql://localhost:3306/LIST";
    String[] columnHeaders =
    {
      "ID", "Taskname", "Taskdescription", "Taskdeadline", "Employees"
    };

    String dbQuery = "DELETE FROM TASK WHERE taskname = ? AND ID = ?";

    if (e.getActionCommand().equals("Delete"))
    {
      try (Connection conn = DriverManager.getConnection(connectionURL, USER, PASS);)
      {
        String name = taskName.getText();
        int id = Integer.parseInt(idField.getText());

        PreparedStatement ps = conn.prepareStatement(dbQuery);
        ps.setString(1, name);
        ps.setInt(2, id);
        ps.executeUpdate();
        System.out.println("Data deleted successfully into " + tableName);
      }
      catch (SQLException se)
      {
        System.out.println("Error deleted data: " + se.getMessage());
        se.printStackTrace();
      }

    }

    if (e.getActionCommand().equals("Quit"))
    {
      new ManagerWorkingProcess();
      this.dispose();
    }

    if (e.getActionCommand().equals("Show the table"))
    {
      this.dispose();
      new DisplayTaskData(dbName, tableName, columnHeaders);
    }
  }

  public static void main(String[] arg)
  {
    new DeleteTaskList();
  }
}
