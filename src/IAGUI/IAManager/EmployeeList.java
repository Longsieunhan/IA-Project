package IAGUI.IAManager;;

import static IAGUI.Login.LoginPage.BLUE_COLOR;
import IAGUI.UpdateEmployeeList;
import IAGUI.DeleteEmployeeList;
import IAGUI.InsertEmployeeList;
import IAGUI.JavaDBAccessIA;
import IAGUI.JavaDBAccessIA;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import static IAGUI.Login.LoginPage.BLUE_COLOR;
import IAGUI.UpdateEmployeeList;
import IAGUI.DeleteEmployeeList;
import IAGUI.JavaDBAccessIA;
import IAGUI.JavaDBAccessIA;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 */
public class EmployeeList extends JFrame implements ActionListener
{

  private ArrayList<ArrayList<String>> dataList;
  private Object[][] data;
  private JTable dbTable;
  private JScrollPane scrollTable;
  private JTableHeader header;
  private TableColumn column;
  private JButton exit;
  private JButton Update;
  private JButton Insert;
  private JButton Delete;
  private JPanel buttonPanel;
  private final Color Green_Color = new Color(77, 144, 80);
  private final Color Nude_Color = new Color(235, 200, 178);
  String[] headers =
  {
    "name", "feedback"
  };
  String USER = "root";
  String PASS = "mysql1";
  String connectionURL = "jdbc:mysql://localhost:3306/LIST";

  public EmployeeList(String dbName, String tableName, String[] tableHeaders)
  {
    super("Display data");
    this.setBounds(100, 50, 800, 600);
    this.getContentPane().setBackground(Green_Color);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    JavaDBAccessIA dbObj = new JavaDBAccessIA(dbName);
    dbObj.setDbConn(); // Call setDbConn to establish connection

    dataList = dbObj.getData(tableName, tableHeaders);
    data = dbObj.to2dArray(dataList);
    dbTable = new JTable(data, tableHeaders);

    dbTable.setGridColor(Color.black);
    dbTable.setBackground(Green_Color);
    dbTable.setFont(new Font("Arial", Font.BOLD, 20));
    dbTable.setForeground(Color.YELLOW);

    dbTable.setRowHeight(40);
    scrollTable = new JScrollPane();
    scrollTable.getViewport().add(dbTable);
    dbTable.setFillsViewportHeight(true);

    this.buttonPanel = new JPanel();
    this.exit = new JButton("Exit");
    this.exit.addActionListener(this);
    buttonPanel.add(exit);

    Update = new JButton("Update");
    Update.addActionListener(this);
    buttonPanel.add(Update);

    Insert = new JButton("Insert");
    Insert.addActionListener(this);
    buttonPanel.add(Insert);

    Delete = new JButton("Delete");
    Delete.addActionListener(this);
    buttonPanel.add(Delete);

    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(scrollTable, BorderLayout.NORTH);

    this.setVisible(true);

  }

  public static void main(String[] args)
  {
    String dbName = "LIST";
    String tableName = "EMPLOYEE";
    String[] columnHeaders =
    {
      "ID", "Name", "Age", "Status"
    };

    new EmployeeList(dbName, tableName, columnHeaders);

  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();

    if (command.equals("Exit"))
    {
      this.dispose();
     new viewEmployee();
    }
    else if (command.equals("Delete"))
    {
      this.dispose();
      new DeleteEmployeeList();
    }
    else if (command.equals("Update"))
    {
      this.dispose();
      new UpdateEmployeeList();
    }
    else if (command.equals("Insert"))
    {
      this.dispose();
      new InsertEmployeeList();
    }

  }
}
