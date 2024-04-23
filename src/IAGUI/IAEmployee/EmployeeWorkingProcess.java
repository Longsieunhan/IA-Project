/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IAGUI.IAEmployee;

/**
 *
 * @author nguyenthanhlong
 */
import static IAGUI.Login.LoginPage.BLUE_COLOR;
import IAGUI.IAEmployee.EmployeeInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class EmployeeWorkingProcess extends JFrame implements ActionListener {

    private JLabel titleLabel;
    private JButton taskInsertButton;
    private JButton taskUpdateButton;
    private JButton taskDeleteButton;
    private JButton employeeInsertButton;
    private JButton employeeUpdateButton;
    private JButton employeeDeleteButton;
    private JTextArea taskLabel;
    private JPanel taskPanel;
    private JPanel employeePanel;
    private JPanel buttonPanel;
    private JButton quitButton;

    public EmployeeWorkingProcess() {
        super("Manager Working Process");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.BLUE);
        this.setBounds(100, 200, 600, 400);
        this.setLayout(new BorderLayout());

        titleLabel = new JLabel("Employee Working Process", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

        

        taskLabel = new JTextArea("Taskname-Deadline");
        taskLabel.setPreferredSize(new Dimension(200, 100));
        taskLabel.setFont(new Font("Arial", Font.PLAIN, 16)); 

        taskInsertButton = new JButton("Insert");
        taskUpdateButton = new JButton("Update");
        taskDeleteButton = new JButton("Delete");
        quitButton = new JButton("Quit");

        taskPanel = new JPanel();
        buttonPanel= new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS)); 
        taskPanel.add(taskLabel);
        buttonPanel.add(taskInsertButton);
        buttonPanel.add(taskUpdateButton);
        buttonPanel.add(taskDeleteButton);
        buttonPanel.add(quitButton);

        add(taskPanel, BorderLayout.CENTER); 
        add(buttonPanel, BorderLayout.SOUTH); 

        // Employee Panel
        employeePanel = new JPanel();
        employeePanel.setLayout(new BoxLayout(employeePanel, BoxLayout.Y_AXIS)); 
        employeeInsertButton = new JButton("Insert");
        employeeUpdateButton = new JButton("Update");
        employeeDeleteButton = new JButton("Delete");

        employeePanel.add(new JLabel("Employee Panel"));
        employeePanel.add(employeeInsertButton);
        employeePanel.add(employeeUpdateButton);
        employeePanel.add(employeeDeleteButton);

        add(employeePanel, BorderLayout.EAST); // Add employeePanel to the right side of the frame

        // Add action listeners to the buttons
        taskInsertButton.addActionListener(this);
        taskUpdateButton.addActionListener(this);
        taskDeleteButton.addActionListener(this);
        employeeInsertButton.addActionListener(this);
        employeeUpdateButton.addActionListener(this);
        employeeDeleteButton.addActionListener(this);
        quitButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button clicks
        if (e.getSource() == taskInsertButton) {
            JOptionPane.showMessageDialog(this, "Task inserted click");
        } else if (e.getSource() == taskUpdateButton) {
            JOptionPane.showMessageDialog(this, "Task update click");
        } else if (e.getSource() == taskDeleteButton) {
            JOptionPane.showMessageDialog(this, "Task delete click");
        } else if (e.getSource() == employeeInsertButton) {
            JOptionPane.showMessageDialog(this, "Employee inserted click");
        } else if (e.getSource() == employeeUpdateButton) {
            JOptionPane.showMessageDialog(this, "Employee update click");
        } else if (e.getSource() == employeeDeleteButton) {
            JOptionPane.showMessageDialog(this, "Employee delete click");
        }
        else if (e.getSource() == quitButton) { 
          this.dispose();
          new EmployeeInterface();
    }
    }
    public static void main(String[] args) {
        new EmployeeWorkingProcess();
    }
}


