/*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package IAGUI.IAEmployee;
//
///**
// *
// * @author nguyenthanhlong
// */
//import IAGUI.DisplayEmployeeData;
//import IAGUI.IAManager.ManagerFeedback;
//import IAGUI.JavaDBAccessIA;
//import static IAGUI.Login.LoginPage.BLUE_COLOR;
//import java.awt.BorderLayout;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.PreparedStatement;
//
//public class EditFeedback extends JFrame implements ActionListener
//{
//
//  public static JTextField Name;
//  public static JTextField Description;
//  public static String nameString;
//  public static String descriptionString;
//
//  public EditFeedback()
//  {
//    super("Edit Page");
//    this.setBounds(100, 200, 600, 400);
//    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    this.getContentPane().setBackground(BLUE_COLOR);
//    this.setLayout(new GridLayout(4, 1));
//
//    Name = new JTextField(20);
//    Description = new JTextField(20);
//
//    JLabel NameLabel = new JLabel("Name");
//    JLabel AgeLabel = new JLabel("Description");
//
//    JPanel DataPanel = new JPanel();
//    DataPanel.add(NameLabel);
//    DataPanel.add(Name);
//    DataPanel.add(AgeLabel);
//    DataPanel.add(Description);
//
//    this.add(DataPanel, BorderLayout.CENTER);
//
//    JButton AddButton = new JButton("Add");
//    AddButton.addActionListener(this);
//
//    JButton ShowTheTable = new JButton("Show the table");
//    ShowTheTable.addActionListener(this);
//
//    JPanel ButtonPanel = new JPanel();
//    ButtonPanel.add(AddButton);
//    ButtonPanel.add(ShowTheTable);
//
//    this.add(ButtonPanel, BorderLayout.SOUTH);
//
//    this.setVisible(true);
//  }
//
//  public static void main(String[] args)
//  {
//    new EditFeedback();
//  }
//
//  @Override
//  public void actionPerformed(ActionEvent e)
//  {
//
//    nameString = Name.getText();
//    descriptionString = Description.getText();
//
//    String command = e.getActionCommand();
//
//    if (command.equals("Add"))
//    {
//      new ManagerFeedback(EditFeedback.nameString);
//      System.out.println("this has sent to employee");
//      Name.setText("");
//      Description.setText("");
//    }
//  }
//}
