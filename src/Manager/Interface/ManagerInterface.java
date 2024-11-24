package Manager.Interface;

import Login.LoginPage;
import Model.User;
import About.AboutManager;
import Manager.Attendance.ManagerAttendance;
import Manager.Account.AccountManagement;
import Manager.Employee.EmployeeList;
import Manager.Feedback.DisplayFeedback;
import Manager.Task.DisplayTaskData;
import Manager.Notification.ManagerNotification;
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
  User user;

  public ManagerInterface(User user)
  {
    super("Manager Interface");
    this.user = user;
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

    JButton salaryButton = new JButton("Salary");
    salaryButton.setPreferredSize(new Dimension(150, 50));
    salaryButton.addActionListener(this);

    // Button Panel
    buttonPanel = new JPanel(new GridLayout(2, 1));
    
    JPanel buttonRow1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonRow1.add(attendanceButton);
    buttonRow1.add(feedbackButton);
    buttonRow1.add(notificationButton);
    buttonPanel.add(buttonRow1);
    
    JPanel buttonRow2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonRow2.add(workingButton);
    buttonRow2.add(accountButton);
    buttonRow2.add(employeeButton);
    buttonRow2.add(salaryButton);
    buttonPanel.add(buttonRow2);

    add(buttonPanel);

    JPanel spacePanel2 = new JPanel();
    spacePanel2.setPreferredSize(new Dimension(150, 50)); // Adjust height as needed
    add(spacePanel2);

    pack(); // Adjust frame size to fit components
    setLocationRelativeTo(null); // Center the frame on the screen
    setVisible(true);
  }

//  public static void main(String[] args)
//  {
//    new ManagerInterface();
//  }
  @Override
  public void actionPerformed(ActionEvent e)
  {

    String command = e.getActionCommand();

    if (command.equals("Attendance"))
    {
      this.dispose();
      dispose();
      String dbName = "LIST";
      String tableName = "ATTENDANCE";
      String[] columnHeaders =
      {
        "ID", "Name", "Attendance", "Reason", "timestamp"
      };

      new ManagerAttendance(dbName, tableName, columnHeaders, user);

    }
    else if (command.equals("Feedback"))
    {
      this.dispose();
      String dbName = "LIST";
      String tableName = "FEEDBACK";
      String[] columnHeaders =
      {
        "ID", "name", "feedback"
      };

      new DisplayFeedback(dbName, tableName, columnHeaders, user);;

    }
    else if (command.equals("Notification"))
    {

      this.dispose();
      new ManagerNotification(user);
    }
    else if (command.equals("Working Process"))
    {
      this.dispose();
      String dbName = "LIST";
      String tableName = "TASK";
      String[] columnHeaders =
      {
        "ID", "Taskname", "Taskdescription", "Taskdeadline", "Employees"
      };
      new DisplayTaskData(dbName, tableName, columnHeaders, user);

    }

    else if (command.equals("Account Management"))
    {
      dispose();
      String dbName = "LIST";
      String tableName = "USERS";
      String[] columnHeaders =
      {
        "ID", "username", "password", "role"
      };

      new AccountManagement(columnHeaders, user);
    }

    else if (command.equals("About"))
    {
      dispose();
      new AboutManager(user);
    }

    else if (command.equals("Salary"))
    {
      dispose();
      new Salary();
    }

    else if (command.equals("Logout"))
    {
      dispose();
      new LoginPage();
    }

    else if (command.equals("Employee list"))
    {
      dispose();
      String dbName = "LIST";
      String tableName = "EMPLOYEE";
      String[] columnHeaders =
      {
        "ID", "name", "age", "status", "gender"
      };

      new EmployeeList(dbName, tableName, columnHeaders);
    }
  }
}
