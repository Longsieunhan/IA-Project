package IAGUI.IAManager;

import IAGUI.DisplayTaskData;
import static IAGUI.Login.LoginPage.BLUE_COLOR;
import IAGUI.IAEmployee.EmployeeFeedback;
import IAGUI.IAEmployee.EmployeeAttendance;
import java.awt.BorderLayout;
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

public class ManagerInterface extends JFrame implements ActionListener
{

  public static final Color BLUE_COLOR = new Color(35, 79, 30);
  public static final Font BIG_FONT = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40);

  // Components
  private JPanel buttonPanel, quitPanel;
  private JLabel titleLabel;
  private JButton attendanceButton, feedbackButton, notificationButton, workingButton, accountButton, employeeButton;
  JMenu mainMenu;
  JMenuItem about;
  JMenuItem quit;
  JMenuBar menuBar;

  public ManagerInterface()
  {
    super("Manager Interface");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    getContentPane().setBackground(BLUE_COLOR);
    this.setBounds(100, 200, 800, 600);
    setLayout(new GridLayout(5, 1)); // Increased rows for better layout

    // Title Label
    titleLabel = new JLabel("Manager Interface", SwingConstants.CENTER);
    titleLabel.setFont(BIG_FONT);
    titleLabel.setForeground(Color.WHITE);
    add(titleLabel);

    mainMenu = new JMenu("MainMenu");
    about = new JMenuItem("About");
    about.addActionListener(this);

    quit = new JMenuItem("Logout");
    quit.addActionListener(this);

    mainMenu.add(about);
    mainMenu.add(quit);

    menuBar = new JMenuBar();
    this.setJMenuBar(menuBar);
    menuBar.add(mainMenu);

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

    accountButton = new JButton("Account Management");
    accountButton.setPreferredSize(new Dimension(150, 50));
    accountButton.addActionListener(this);
    
    employeeButton = new JButton("Employee list");
    employeeButton.setPreferredSize(new Dimension(150, 50));
    employeeButton.addActionListener(this);
    
    
    
    // Button Panel
    buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(attendanceButton);
    buttonPanel.add(feedbackButton);
    buttonPanel.add(notificationButton);
    buttonPanel.add(workingButton);
    buttonPanel.add(accountButton);
    buttonPanel.add(employeeButton);

    add(buttonPanel);

    JPanel spacePanel2 = new JPanel();
    spacePanel2.setPreferredSize(new Dimension(150, 50)); // Adjust height as needed
    add(spacePanel2);

    pack(); // Adjust frame size to fit components
    setLocationRelativeTo(null); // Center the frame on the screen
    setVisible(true);
  }

  public static void main(String[] args)
  {
    new ManagerInterface();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    
    String command = e.getActionCommand();
 
    if (command.equals("Attendance"))
    {
      this.dispose();
      new viewAttendance();

    }
    else if (command.equals("Feedback"))
    {
      this.dispose();
      new ManagerFeedback();

    }
    else if (command.equals("Notification"))
    {

      this.dispose();
      new ManagerNotification();
    }
    else if (command.equals("Working Process"))
    {
      this.dispose();
      new ManagerWorkingProcess();

    }

    else if (command.equals("Account Management"))
    {
      new ManagerAccount();
      dispose();
    }
    
    else if(command.equals("About")) {
      dispose();
      new AboutManager();
    }
    
    else if(command.equals("Logout")) {
      dispose();
    }
    
    else if(command.equals("Employee list")) {
      dispose();
      new viewEmployee();
    }
  }
}
