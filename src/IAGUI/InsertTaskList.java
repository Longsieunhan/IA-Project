/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IAGUI;

/**
 *
 * @author nguyenthanhlong
 */

import IAGUI.IAManager.ManagerWorkingProcess;
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

public class InsertTaskList extends JFrame implements ActionListener {

    private JTextField Name;
    private JTextField Description;
    private JTextField Deadline;

    public InsertTaskList() {
        super("Edit Page");
        this.setBounds(100, 200, 600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(BLUE_COLOR);
        this.setLayout(new GridLayout(4, 1));

        Name = new JTextField(20);
        Description = new JTextField(20);
        Deadline = new JTextField(20);

        JLabel NameLabel = new JLabel("Name");
        JLabel DescriptionLabel = new JLabel("Description");
        JLabel DeadlineLabel = new JLabel("Deadline");

        JPanel DataPanel = new JPanel();
        DataPanel.add(NameLabel);
        DataPanel.add(Name);
        DataPanel.add(DescriptionLabel);
        DataPanel.add(Description);
        DataPanel.add(DeadlineLabel);
        DataPanel.add(Deadline);

        this.add(DataPanel, BorderLayout.CENTER);

        JButton AddButton = new JButton("Add");
        AddButton.addActionListener(this);
        
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(this);

        JButton ShowTheTable = new JButton("Show the table");
        ShowTheTable.addActionListener(this);

        JPanel ButtonPanel = new JPanel();
        ButtonPanel.add(AddButton);
        ButtonPanel.add(ShowTheTable);

        this.add(ButtonPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new InsertTaskList();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = Name.getText();
        String description = Description.getText();
        Integer deadline = Integer.parseInt(Deadline.getText());

        if (e.getActionCommand().equals("Add")) {
            String dbName = "Task";
            String tableName = "TaskList";
            String query = "INSERT INTO TaskList (task_name, task_description, task_deadline) VALUES (?, ?, ?)";

            JavaDBAccessIA objAccess = new JavaDBAccessIA(dbName);
            Connection myDbConn = objAccess.getDbConn();

            try {
                PreparedStatement ps = myDbConn.prepareStatement(query);
                ps.setString(1, name);
                ps.setString(2, description);
                ps.setInt(3, deadline);
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
            String tableName = "TaskList";
            String[] columnHeaders = { "task_name", "task_description", "task_deadline" };
            new DisplayTaskData(dbName, tableName, columnHeaders);
        }
        
        else if (e.getActionCommand().equals("Quit")) {
          this.dispose();
          new ManagerWorkingProcess();
        }
    }
}

