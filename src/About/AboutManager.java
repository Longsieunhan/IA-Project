package About;
/**
 * This class displays an "About" window for the manager interface.
 */
import Manager.Interface.ManagerInterface;
import Manager.Interface.ManagerInterface;
import Model.User;
import java.awt.event.ActionEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AboutManager extends JFrame implements ActionListener
{
  // Constants

  public static final Color BLUE_COLOR = new Color(35, 79, 30);
  public static final Font BIG_FONT = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40);
  public static final Font SMALL_FONT = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20);
   // Path to the company logo image
  public final URL IMG1_PATH = getClass().getResource("thanhlong.png");
  // Components
  private JLabel titleLabel, descriptionLabel, imageLabel;
  public ImageIcon companyImage;
  User user;
 

  public AboutManager(User user)
  {
    super("About");
     // Set window properties
    this.setSize(700, 600); // Set the size
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.getContentPane().setBackground(BLUE_COLOR);
    this.setLayout(new GridLayout(4, 1));
    this.user = user;

     // Load and scale the company logo
    companyImage = new ImageIcon(new ImageIcon(IMG1_PATH).getImage().getScaledInstance(200, 100, Image.SCALE_DEFAULT));

    // Create and configure the title label
    titleLabel = new JLabel("About", SwingConstants.CENTER);
    titleLabel.setFont(BIG_FONT);
    titleLabel.setForeground(Color.WHITE);
    titleLabel.setPreferredSize(new Dimension(100, 100)); // Set preferred size
    add(titleLabel, BorderLayout.NORTH); // Add to the north of BorderLayout

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBackground(BLUE_COLOR);
    add(panel, BorderLayout.CENTER); // Add panel to the center

    imageLabel = new JLabel(companyImage);
    this.add(imageLabel, BorderLayout.NORTH);

    descriptionLabel = new JLabel("<html><div style='text-align: center;' >Thanh Long Group is a Vietnam's leading distributor system in the field of trading and distributing construction materials. The highlight is the tiles. Thanh Long with 18 member companies spread across 3 regions of Vietnam</div></html>");
    descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center alignment
    descriptionLabel.setFont(SMALL_FONT);
    descriptionLabel.setForeground(Color.WHITE);
    panel.add(descriptionLabel);

    // Create and configure the "Quit" button
    JButton quitButton = new JButton("Quit");
    quitButton.addActionListener(this);
    JPanel buttonPanel = new JPanel(); // Use BorderLayout
    buttonPanel.add(quitButton); // Add button to the south of BorderLayout

     
    this.add(buttonPanel, BorderLayout.SOUTH);

    // Center alignment
    setLocationRelativeTo(null); // Center the window on the screen
    setVisible(true);
  }


  @Override
  public void actionPerformed(ActionEvent e)
  {
   String command = e.getActionCommand();
   
   if(command.equals("Quit")) {
     dispose();
     new ManagerInterface(user);
   }
  }
}
