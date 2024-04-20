/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IAGUI;

/**
 *
 * @author nguyenthanhlong
 */
import IAGUI.IAManager.ManagerInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeList extends JFrame implements ActionListener
{

  JButton quit;
  JButton Insert;
  JButton Update;
  JButton Delete;
  JButton showDBTable;
  JPanel ButtonPanel;

  public EmployeeList()
  {
    super("Employeelist");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    this.setBounds(100, 200, 600, 800);
    this.setLayout(new BorderLayout());
    this.setPreferredSize(new Dimension(1000, 600));

    JPanel textAreaPanel = new JPanel(); // Panel for JTextAreas
    textAreaPanel.setBackground(Color.BLUE); // Set background color

    JTextArea textArea1 = new JTextArea("Employee 1");
    JTextArea textArea2 = new JTextArea("Employee 2");
    JTextArea textArea3 = new JTextArea("Employee 3");

    // Customize JTextAreas
    textArea1.setBackground(Color.WHITE);
    textArea2.setBackground(Color.WHITE);
    textArea3.setBackground(Color.WHITE);

    textAreaPanel.add(textArea1);
    textAreaPanel.add(textArea2);
    textAreaPanel.add(textArea3);

     quit = new JButton("quit");
     quit.addActionListener(this);
     
     Insert = new JButton("Insert");
     Insert.addActionListener(this);
     
     Update = new JButton("Update");
     Update.addActionListener(this);
     
     Delete = new JButton("Delete");
     Delete.addActionListener(this);
     
     showDBTable = new JButton("Show table");
     showDBTable.addActionListener((this));
    
    ButtonPanel = new JPanel();
    ButtonPanel.add(Insert);
    ButtonPanel.add(Update);
    ButtonPanel.add(Delete);
    ButtonPanel.add(quit);
    ButtonPanel.add(showDBTable);
    
    this.add(ButtonPanel, BorderLayout.SOUTH);
    
    ButtonPanel.add(Insert);
    ButtonPanel.add(Update);
    ButtonPanel.add(Delete);
    ButtonPanel.add(quit);
    
    

    textArea1.setPreferredSize(new Dimension(300, 200));
    textArea2.setPreferredSize(new Dimension(300, 200));
    textArea3.setPreferredSize(new Dimension(300, 200));

    add(textAreaPanel, BorderLayout.CENTER);

    pack(); // Adjust the frame size based on contents
    setLocationRelativeTo(null); // Center the frame
    setVisible(true);
  }

  public static void main(String[] args)
  {
    SwingUtilities.invokeLater(() -> new EmployeeList());
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();
    
    if(command.equals("quit")) {
    this.dispose();
    new ManagerInterface();
  }
  
  if (command.equals("Insert")) {
    this.dispose();
    new InsertEmployeeList();
  }
  
  if (command.equals("Update")) {
    this.dispose();
    new UpdateEmployeeList();
  }
  if (command.equals("Delete")) {
    this.dispose();
    new DeleteEmployeeList();
  }
  if (command.equals("Show table")) {
     String dbName = "List";
      String tableName = "EmployeeList";
      String[] columnHeaders =
      {
        "employee_name", "employee_age", "condition"
      };
      new DisplayEmployeeData(dbName, tableName, columnHeaders);
  }
  
  }
}/// DBEmployeeAccess objAccess = new DBEmployeeAccess(dbName);
 /// Connection myDBConn = objAccess.getDbConn();
 /// try {
////PrepareStatment ps = myDBConn.prepareStatement(Query)
////ps.setString();
////ps.setInt();
////ps.String();
////ps.executeUpdate();
////System.out.println("Data successfully inserted into" + tableName);
////
////}
////catch(SQLException xe) {
////System.out.println("Error inserting data");
////}