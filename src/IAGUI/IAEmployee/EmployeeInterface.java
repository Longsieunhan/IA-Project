package IAGUI.IAEmployee;

import IAGUI.IAManager.AboutManager;
import IAGUI.DisplayTaskData;
import IAGUI.EmployeeWorkingProcess;
import static IAGUI.Login.LoginPage.BLUE_COLOR;
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

public class EmployeeInterface extends JFrame implements ActionListener
{

  public static final Color BLUE_COLOR = new Color(35, 79, 30);
  public static final Font BIG_FONT = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40);

  // Components
  private JPanel buttonPanel;
  private JLabel titleLabel;
  private JButton attendanceButton, feedbackButton, notificationButton, workingButton;
  JMenu mainMenu;
  JMenuItem about;
  JMenuItem quit;
  JMenuBar menuBar;

  public EmployeeInterface()
  {
    super("Employee Interface");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    getContentPane().setBackground(BLUE_COLOR);
    this.setBounds(100, 200, 800, 600);
    setLayout(new GridLayout(5, 1)); // Increased rows for better layout

    // Title Label
    titleLabel = new JLabel("Employee Interface", SwingConstants.CENTER);
    titleLabel.setFont(BIG_FONT);
    titleLabel.setForeground(Color.WHITE);
    add(titleLabel);

    mainMenu = new JMenu("MainMenu");
    about = new JMenuItem("About");
    about.addActionListener(this);
    quit = new JMenuItem("Log out");
    quit.addActionListener(this);
    mainMenu.add(about);
    mainMenu.add(quit);
    menuBar = new JMenuBar();
    menuBar.add(mainMenu);
    this.setJMenuBar(menuBar);

 
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

    // Button Panel
    buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(attendanceButton);
    buttonPanel.add(feedbackButton);
    buttonPanel.add(notificationButton);
    buttonPanel.add(workingButton);
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
    new EmployeeInterface();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();

    if (command.equals("Attendance"))
    {
      this.dispose();
      new EmployeeAttendance();

    }
    else if (command.equals("Feedback"))
    {
      this.dispose();
      new EmployeeFeedback();

    }
    else if (command.equals("Working Process"))
    {
      this.dispose();
      
      String dbName = "LIST";
      String tableName = "TASK";
      String[] columnHeaders =
      {
        "Taskname", "Taskdescription", "Taskdeadline", "Employees"
      };
      new EmployeeWorkingProcess(dbName, tableName, columnHeaders);

      
    }
    else if (command.equals("Notification"))
    {
      this.dispose();
      
      String dbName = "LIST";
      String tableName = "NOTIFICATION";
      String[] columnHeaders =
      {
        "ID", "notification"
      };

      new EmployeeNotification(dbName, tableName, columnHeaders);

    }
    else if (command.equals("Log out"))
    {
      dispose(); // Close the frame
    }

    else if (command.equals("About"))
    {
      new AboutEmployee();
      dispose();
    }
  }
}
