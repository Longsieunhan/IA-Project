/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IAGUI.IAEmployee;

import IAGUI.IAEmployee.EmployeeInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author nguyenthanhlong
 */
public class EmployeeNotification extends JFrame implements ActionListener {

    private JPanel buttonPanel;
    private JLabel titleLabel;
    private JTextArea NotificationText;
    private JButton Edit;
    private JButton Delete; 
    private JButton  quitButton;
    public static final Color BLUE_COLOR = new Color(0, 200, 250);
    public static final Font BIG_FONT = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 40);

    public EmployeeNotification() {
        super("Employee Feedback");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.BLUE);
        this.setBounds(100, 200, 600, 400);
        this.setLayout(new BorderLayout());

        titleLabel = new JLabel("Employee Notification", JLabel.CENTER);
        titleLabel.setFont(BIG_FONT);
        titleLabel.setForeground(Color.WHITE);
        this.add(titleLabel, BorderLayout.NORTH);

        NotificationText = new JTextArea("Notification sent by the manager is shown here");
        NotificationText.setPreferredSize(new Dimension(400, 100));
        
        JPanel feedbackPanel = new JPanel();
        feedbackPanel.add(NotificationText);
        this.add(feedbackPanel, BorderLayout.CENTER);
        
        Edit = new JButton("Edit");
        Edit.addActionListener(this);
        
        Delete = new JButton("Delete");
        Delete.addActionListener(this);

        quitButton = new JButton("Quit");
        quitButton.setPreferredSize(new Dimension(150, 50));
        quitButton.addActionListener(this);

        buttonPanel = new JPanel();
        buttonPanel.add(quitButton);
        buttonPanel.add(Edit);
        buttonPanel.add(Delete);
        add(buttonPanel, BorderLayout.SOUTH);

        
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    public static void main(String[] args) {
        new EmployeeNotification();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            String notification = NotificationText.getText();
            // Process the feedback
         if (e.getSource() == quitButton) {
            // Handle quit button action
            new EmployeeInterface();
            this.dispose(); // Close the frame
        }
    }
}
