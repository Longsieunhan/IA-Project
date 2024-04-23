/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IAGUI;

import static IAGUI.Login.LoginPage.BLUE_COLOR;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
public class DeleteTaskList extends JFrame implements ActionListener
{

  private JTextField Name;
  private JTextField Age;
  private JTextField Condition;
  private JCheckBox checkBox;
  private JLabel idLabel;
  private JTextField idField;

  public DeleteTaskList()
  {

    super("Edit Page");
    this.setBounds(100, 200, 900, 400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setBackground(BLUE_COLOR);
    this.setLayout(new GridLayout(4, 1));

    Name = new JTextField(20);
    Age = new JTextField(20);
    Condition = new JTextField(20);
    idLabel = new JLabel("Id");
    idField = new JTextField(20);
    checkBox = new JCheckBox();

    JLabel NameLabel = new JLabel("Name");
    JLabel AgeLabel = new JLabel("Age");
    JLabel ConditionLabel = new JLabel("Condition");

    JPanel DataPanel = new JPanel();
    DataPanel.add(idLabel);
    DataPanel.add(idField);
    DataPanel.add(NameLabel);
    DataPanel.add(Name);
    DataPanel.add(AgeLabel);
    DataPanel.add(Age);
    DataPanel.add(ConditionLabel);
    DataPanel.add(Condition);

    this.add(DataPanel, BorderLayout.CENTER);

    JButton DeleteButton = new JButton("Delete");
    DeleteButton.addActionListener(this);

    JButton ShowTheTable = new JButton("Show the table");
    ShowTheTable.addActionListener(this);

    JPanel ButtonPanel = new JPanel();
    ButtonPanel.add(DeleteButton);
    ButtonPanel.add(ShowTheTable);

    this.add(ButtonPanel, BorderLayout.SOUTH);

    this.setVisible(true);
  }

  public static void main(String[] arg)
  {
    new DeleteEmployeeList();
  }

  // fix database - thieu id (primary key, identified, auto-increment) in tables
  // checkbox to update and delete
  @Override
  public void actionPerformed(ActionEvent e)
  {

    String dbName = "List";
    String tableName = "EmployeeList";
    String[] columnNames = {"Id",",name","age","condition"};
    String query = "DELETE FROM EmployeeList WHERE ID=? AND employee_name = ? AND employee_age = ? AND condition = ?";

    JavaDBAccessIA objAccess = new JavaDBAccessIA(dbName);
    Connection myDbConn = objAccess.getDbConn();

    if (e.getActionCommand().equals("Delete"))
    {
      System.out.println("Data is deleted");
      try
      {
        String name = Name.getText();
        int age = Integer.parseInt(Age.getText());
        String condition = Condition.getText();
        int id = Integer.parseInt(idField.getText());
        PreparedStatement ps = myDbConn.prepareStatement(query);
        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setInt(3, age);
        ps.setString(5, condition);
        ps.executeUpdate();
        System.out.println("Data delete successfully into " + tableName);

      }

      catch (SQLException se)
      {
        System.out.println("Need a number");
      }
    }
  }
}
