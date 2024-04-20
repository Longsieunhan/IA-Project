package IAGUI.IAEmployee;

import IAGUI.IAEmployee.EmployeeFeedback;
import IAGUI.IAEmployee.EmployeeAttendance;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class EmployeeInterface extends JFrame implements ActionListener {

    public static final Color BLUE_COLOR = new Color(0, 200, 250);
    public static final Font BIG_FONT = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 40);

    // Components
    private JPanel buttonPanel;
    private JLabel titleLabel;
    private JButton attendanceButton, feedbackButton, quitButton, notificationButton, workingButton;

    public EmployeeInterface() {
        super("Employee Working Process");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(BLUE_COLOR);
        this.setBounds(100, 200, 600, 400);
        setLayout(new GridLayout(5, 1)); // Increased rows for better layout

        // Title Label
        titleLabel = new JLabel("Employee Working Process", SwingConstants.CENTER);
        titleLabel.setFont(BIG_FONT);
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel);
        
         JPanel spacePanel1 = new JPanel();
        spacePanel1.setPreferredSize(new Dimension(150, 50)); // Adjust height as needed
        add(spacePanel1);

        // Buttons
        attendanceButton = new JButton("Attendance");
        attendanceButton.setPreferredSize(new Dimension(150, 50));
        attendanceButton.addActionListener(this);

        feedbackButton = new JButton("Feedback");
        feedbackButton.setPreferredSize(new Dimension(150, 50));
        feedbackButton.addActionListener(this);

        notificationButton = new JButton("Notification");
        notificationButton.setPreferredSize(new Dimension(150, 50));
        notificationButton.addActionListener(this);

        workingButton = new JButton("Working Process");
        workingButton.setPreferredSize(new Dimension(150, 50));
        workingButton.addActionListener(this);

        quitButton = new JButton("Quit");
        quitButton.setPreferredSize(new Dimension(150, 50));
        quitButton.addActionListener(this);

        // Button Panel
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(attendanceButton);
        buttonPanel.add(feedbackButton);
        buttonPanel.add(notificationButton);
        buttonPanel.add(workingButton);
        buttonPanel.add(quitButton);
        add(buttonPanel);
        
        JPanel spacePanel2 = new JPanel();
        spacePanel2.setPreferredSize(new Dimension(150, 50)); // Adjust height as needed
        add(spacePanel2);


        pack(); // Adjust frame size to fit components
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public static void main(String[] args) {
        new EmployeeInterface();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();
        if (sourceButton == attendanceButton) {
            new EmployeeAttendance();
            
        } else if (sourceButton == feedbackButton) {
            
            this.dispose();
            new EmployeeFeedback();
            
        } else if (sourceButton == notificationButton) {
          
          this.dispose();
  
            new EmployeeNotification();
        } else if (sourceButton == workingButton) {
            this.dispose();
            new EmployeeWorkingProcess();
            
        } else if (sourceButton == quitButton) {
            dispose(); // Close the frame
        }
    }
}
