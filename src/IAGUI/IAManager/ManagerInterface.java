/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IAGUI.IAManager;

/**
 *
 * @author nguyenthanhlong
 */
import IAGUI.IAManager.ManagerWorkingProcess;
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ManagerInterface extends JFrame implements ActionListener {

    public static final Color BLUE_COLOR = new Color(35, 79, 30);
    public static final Font BIG_FONT = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 40);

    // Components
    private JPanel buttonPanel;
    private JLabel titleLabel;
    private JButton attendanceButton, feedbackButton,
      quitButton, notificationButton, workingButton, employeeList;
    private JMenuBar mainMenu;
    private JMenu subMenu;
    private JMenuItem About;
    private JMenuItem Roles;

    public ManagerInterface() {
        super("Employee Working Process");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(BLUE_COLOR);
        setLayout(new GridLayout(10, 1)); // Increased rows for better layout

        // Title Label
        titleLabel = new JLabel("Manager Working Process", SwingConstants.CENTER);
        titleLabel.setFont(BIG_FONT);
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel);
        
         JPanel spacePanel1 = new JPanel();
        spacePanel1.setPreferredSize(new Dimension(150, 50)); // Adjust height as needed
        add(spacePanel1);

        
        mainMenu = new JMenuBar();
        subMenu = new JMenu("Menu");
        About = new JMenuItem("About");
        About.addActionListener(this);
        Roles = new JMenuItem("Roles");
        Roles.addActionListener(this);
        
        mainMenu.add(subMenu);
        subMenu.add(About);
        subMenu.add(Roles);
        this.setJMenuBar(mainMenu);
        
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
        
        employeeList = new JButton("Employee List");
        employeeList.setPreferredSize(new Dimension(150, 50));
        employeeList.addActionListener(this);

        quitButton = new JButton("Quit");
        quitButton.setPreferredSize(new Dimension(150, 50));
        quitButton.addActionListener(this);

        // Button Panel
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(attendanceButton);
        buttonPanel.add(feedbackButton);
        buttonPanel.add(notificationButton);
        buttonPanel.add(employeeList);
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
        new ManagerInterface();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();
        if (sourceButton == attendanceButton) {
            
            new ManagerAttendance();
        } else if (sourceButton == feedbackButton) {
            new ManagerFeedback();
//            new ManagerFeedback(feedback);
        } else if (sourceButton == notificationButton) {
            // Handle notification button action
          new ManagerNotification();
        } else if (sourceButton == workingButton) {
            // Handle working process button action
            new ManagerWorkingProcess();
        } else if (sourceButton == quitButton) {
            // Handle quit button action
            dispose(); // Close the frame
        }
    }
}
