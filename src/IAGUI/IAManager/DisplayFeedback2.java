/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IAGUI.IAManager;

import IAGUI.DisplayEmployeeData;
import IAGUI.JavaDBAccessIA;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 */
public class DisplayFeedback2 extends JFrame implements ActionListener
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
  private final Color Green_Color = new Color(35, 79, 30);
  private final Color Nude_Color = new Color(235, 200, 178);
  String[] headers =
  {
    "name", "description"
  };

  public DisplayFeedback2(String dbName, String tableName, String[] tableHeaders)
  {
    super("Display data");
    this.setBounds(100, 50, 800, 600);
    this.getContentPane().setBackground(Green_Color);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    JavaDBAccessIA dbObj = new JavaDBAccessIA(dbName);
    dataList = dbObj.getData(tableName, tableHeaders);
    data = dbObj.to2dArray(dataList);
    

    dbTable = new JTable(data, tableHeaders);

    dbTable.setGridColor(Color.BLACK);
    dbTable.setBackground(Green_Color);
    dbTable.setFont(new Font("Arial", Font.BOLD, 20));
    dbTable.setForeground(Color.YELLOW);

    dbTable.setRowHeight(25);
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
    buttonPanel.add(Update);

    Delete = new JButton("Delete");
    Delete.addActionListener(this);
    buttonPanel.add(Delete);

    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(scrollTable, BorderLayout.NORTH);

    this.setVisible(true);

  }
  
  
  public static void main(String[] args) {
    String dbName = "List";
  String tableName = "FEEDBACK";
  String[] columnHeaders = { "name", "feedback"}; 
  new DisplayEmployeeData(dbName, tableName, columnHeaders);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();
     if (command.equals("Exit"))
    {
      this.dispose();
    }
  }

}
