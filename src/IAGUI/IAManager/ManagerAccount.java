package IAGUI.IAManager;

/**
 *
 */
import IAGUI.DeleteAccount;
import IAGUI.DeleteTaskList;
import IAGUI.DisplayTaskData;
import IAGUI.IAManager.ManagerInterface;
import IAGUI.InsertAccount;
import IAGUI.UpdateAccount;
import IAGUI.UpdateTaskList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.Image;
import javax.swing.ImageIcon;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ManagerAccount extends JFrame implements ActionListener
{
  
  public JLabel titleLabel;
  public JButton taskInsertButton;
  public JButton taskUpdateButton;
  public JButton taskDeleteButton;
  public JButton quitButton;
  public JButton showtable;
  public static final Color BLUE_COLOR = new Color(35, 79, 30);
  public static final Color BLACK_COLOR = new Color(0, 0, 0);
  public static final Font BIG_FONT = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40);
  
  public URL IMG_PATH = getClass().getResource("thanhlong.png");
  public ImageIcon companyImage;
  public JLabel imageLabel;
  public JPanel imagePanel;
  
  public ManagerAccount()
  {
    super("Manager Account");
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.getContentPane().setBackground(BLUE_COLOR);
    this.setBounds(100, 200, 1000, 400);
    this.setLayout(new BorderLayout());
    
    JLabel title = new JLabel("Manager Account");
    title.setFont(BIG_FONT);
    title.setForeground(Color.WHITE);
    
    JPanel titlePanel = new JPanel();
    titlePanel.add(title);
    
    titlePanel.setBackground(BLUE_COLOR);
    this.add(titlePanel, BorderLayout.NORTH);
    
    companyImage = new ImageIcon(new ImageIcon(IMG_PATH).getImage().getScaledInstance(400, 200, Image.SCALE_DEFAULT));
    imagePanel = new JPanel();
    imageLabel = new JLabel(companyImage);
    imagePanel.add(imageLabel);
    this.add(imagePanel, BorderLayout.CENTER);
    
    quitButton = new JButton("Quit");
    showtable = new JButton("Show the table");
    
    JPanel quitPanel = new JPanel();
    quitPanel.add(quitButton);
    quitPanel.add(showtable);
    add(quitPanel, BorderLayout.SOUTH);
    quitButton.addActionListener(this);
    showtable.addActionListener(this);
    
    setLocationRelativeTo(null);
    setVisible(true);
  }
  
  public static void main(String[] args)
  {
    new ManagerAccount();
  }
  
  @Override
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == taskInsertButton)
    {
      dispose();
      new InsertAccount();
    }
    else if (e.getSource() == taskUpdateButton)
    {
      dispose();
      new UpdateAccount();
    }
    else if (e.getSource() == taskDeleteButton)
    {
      dispose();
      new DeleteAccount();
    }
    else if (e.getSource() == quitButton)
    {
      new ManagerInterface();
      dispose();
     
      // Perform action for quitButton
    }
    else if (e.getSource() == showtable)
    {
    dispose();
    String dbName = "LIST";
    String tableName = "USERS";
    String[] columnHeaders =
    {
      "ID", "username", "password"
    };

    new AccountManagement(dbName, tableName, columnHeaders);
    }
  }
}
