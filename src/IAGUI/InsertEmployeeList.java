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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class InsertEmployeeList extends JFrame implements ActionListener {

    private JTextField Name;
    private JTextField Age;
    private JTextField Condition;

    public InsertEmployeeList() {
        super("Edit Page");
        this.setBounds(100, 200, 600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(BLUE_COLOR);
        this.setLayout(new GridLayout(4, 1));

        Name = new JTextField(20);
        Age = new JTextField(20);
        Condition = new JTextField(20);

        JLabel NameLabel = new JLabel("Name");
        JLabel AgeLabel = new JLabel("Age");
        JLabel ConditionLabel = new JLabel("Condition");

        JPanel DataPanel = new JPanel();
        DataPanel.add(NameLabel);
        DataPanel.add(Name);
        DataPanel.add(AgeLabel);
        DataPanel.add(Age);
        DataPanel.add(ConditionLabel);
        DataPanel.add(Condition);

        this.add(DataPanel, BorderLayout.CENTER);

        JButton AddButton = new JButton("Add");
        AddButton.addActionListener(this);

        JButton ShowTheTable = new JButton("Show the table");
        ShowTheTable.addActionListener(this);

        JPanel ButtonPanel = new JPanel();
        ButtonPanel.add(AddButton);
        ButtonPanel.add(ShowTheTable);

        this.add(ButtonPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new InsertEmployeeList();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = Name.getText();
        int age = Integer.parseInt(Age.getText());
        String condition = Condition.getText();

        if (e.getActionCommand().equals("Add")) {
            String dbName = "List";
            String tableName = "EmployeeList";
            String query = "INSERT INTO EmployeeList (employee_name, employee_age, condition) VALUES (?, ?, ?)";

            JavaDBAccessIA objAccess = new JavaDBAccessIA(dbName);
            Connection myDbConn = objAccess.getDbConn();

            try {
                PreparedStatement ps = myDbConn.prepareStatement(query);
                ps.setString(1, name);
                ps.setInt(2, age);
                ps.setString(3, condition);
                ps.executeUpdate();
                System.out.println("Data inserted successfully into " + tableName);
            } catch (SQLException se) {
                System.out.println("Error inserting data: " + se.getMessage());
                se.printStackTrace();
            } finally {
                try {
                    myDbConn.close();
                } catch (SQLException ex) {
                    System.out.println("Error closing database connection: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        } else if (e.getActionCommand().equals("Show the table")) {
            String dbName = "List";
            String tableName = "EmployeeList";
            String[] columnHeaders = { "employee_name", "employee_age", "condition" };
            new DisplayEmployeeData(dbName, tableName, columnHeaders);
        }
    }
}
