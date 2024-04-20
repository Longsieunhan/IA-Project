/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IAGUI.IAEmployee;

/**
 *
 * @author nguyenthanhlong
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EmployeeAttendance extends JFrame implements ActionListener {
    // Constants
    public static final Color BLUE_COLOR = new Color(0, 200, 250);
    public static final Font BIG_FONT = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 40);

    // Components
    private JLabel titleLabel;
    private JRadioButton presentButton, absentButton;
    private JButton submitButton, quitButton;

    public EmployeeAttendance() {
        super("Employee Attendance");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(BLUE_COLOR);
        this.setBounds(100, 200, 600, 400);
        setLayout(new GridLayout(5, 1)); // Increased rows for better layout

        // Title Label
        titleLabel = new JLabel("Employee Attendance", SwingConstants.CENTER);
        titleLabel.setFont(BIG_FONT);
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel);

        // Radio Buttons Panel
        JPanel radiobuttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        presentButton = new JRadioButton("Present");
        absentButton = new JRadioButton("Absent");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(presentButton);
        buttonGroup.add(absentButton);
        radiobuttonPanel.add(presentButton);
        radiobuttonPanel.add(absentButton);
        add(radiobuttonPanel);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        quitButton = new JButton("Quit");
        quitButton.addActionListener(this);
        buttonPanel.add(submitButton);
        buttonPanel.add(quitButton);
        add(buttonPanel);

        pack(); // Adjust frame size to fit components
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public static void main(String[] args) {
        new EmployeeAttendance();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            if (presentButton.isSelected()) {
                JOptionPane.showMessageDialog(this, "You are present");
            } else if (absentButton.isSelected()) {
                JOptionPane.showMessageDialog(this, "You are absent");
            } else {
                JOptionPane.showMessageDialog(this, "Please select attendance status");
            }
        } else if (e.getSource() == quitButton) {
            this.dispose();
            
        }
        
    }
}
