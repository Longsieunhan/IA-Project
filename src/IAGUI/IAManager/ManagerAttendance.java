/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IAGUI.IAManager;

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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author nguyenthanhlong
 */
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
public class ManagerAttendance extends JFrame implements ActionListener {

    private JPanel buttonPanel;
    private JLabel titleLabel;
    private JTextArea EmployeeText;
    private JTextArea AttendanceText;
    private JButton  quitButton;
    private JPanel AttendancePanel;
    public static final Color BLUE_COLOR = new Color(35, 79, 30);
    public static final Font BIG_FONT = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 40);

    public ManagerAttendance() {
        super("Manager Attendance");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.BLUE);
        this.setBounds(200, 400, 600, 500);
        this.setLayout(new BorderLayout());
        

        titleLabel = new JLabel("Manager Attendance", JLabel.CENTER);
        titleLabel.setFont(BIG_FONT);
        titleLabel.setForeground(Color.WHITE);
        this.add(titleLabel, BorderLayout.NORTH);
        

        EmployeeText = new JTextArea("Employees");
        EmployeeText.setPreferredSize(new Dimension(200, 400));
        EmployeeText.setLineWrap(true); 
        EmployeeText.setWrapStyleWord(true);
        
        
        AttendanceText = new JTextArea("Attendance");
        AttendanceText.setPreferredSize(new Dimension(200, 400));
        AttendanceText.setLineWrap(true); 
        AttendanceText.setWrapStyleWord(true);
        
        
        JPanel employeePanel = new JPanel();
        employeePanel.add(EmployeeText);
        this.add(employeePanel, BorderLayout.WEST);
        
        AttendancePanel = new JPanel();
        AttendancePanel.add(AttendanceText);
        this.add(AttendancePanel, BorderLayout.EAST);
        

        quitButton = new JButton("Quit");
        quitButton.setPreferredSize(new Dimension(150, 50));
        quitButton.addActionListener(this);

        buttonPanel = new JPanel();
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    public static void main(String[] args) {
        new ManagerAttendance();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            String notification = EmployeeText.getText();
            // Process the feedback
         if (e.getSource() == quitButton) {
            // Handle quit button action
            new EmployeeInterface();
            this.dispose(); // Close the frame
        }
    }
}
