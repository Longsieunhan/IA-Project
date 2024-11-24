/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package About;
/**
 * This class implements a GUI window to display information about the company.
 */
import Employee.Interface.EmployeeInterface;
import Model.User;
import java.awt.event.ActionEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class AboutEmployee extends JFrame implements ActionListener
{
  
  public static final Color BLUE_COLOR = new Color(35, 79, 30);
  public static final Font BIG_FONT = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40);
  public static final Font SMALL_FONT = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20);
  public final URL IMG1_PATH = getClass().getResource("thanhlong.png");
  
  // Components
  private JLabel titleLabel, descriptionLabel, imageLabel;
  public ImageIcon companyImage;
  User user;

  public AboutEmployee(User user)
  {
    super("About");
    this.setSize(700, 600); // Set the size
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.getContentPane().setBackground(BLUE_COLOR);
    this.setLayout(new GridLayout(4, 1));
    this.user = user;

    companyImage = new ImageIcon(new ImageIcon(IMG1_PATH).getImage().getScaledInstance(200, 100, Image.SCALE_DEFAULT));

      // Create title label with formatting
    titleLabel = new JLabel("About", SwingConstants.CENTER);
    titleLabel.setFont(BIG_FONT);
    titleLabel.setForeground(Color.WHITE);
    titleLabel.setPreferredSize(new Dimension(100, 100)); // Set preferred size
    add(titleLabel, BorderLayout.NORTH); // Add to the north of BorderLayout

     // Create panel for description and center it vertically
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBackground(BLUE_COLOR);
    add(panel, BorderLayout.CENTER); // Add panel to the center

    imageLabel = new JLabel(companyImage);
    this.add(imageLabel, BorderLayout.NORTH);

     // Create description label with HTML formatting and center alignment
    descriptionLabel = new JLabel("<html><div style='text-align: center;' >Thanh Long Group is a Vietnam's leading distributor system in the field of trading and distributing construction materials. The highlight is the tiles. Thanh Long with 18 member companies spread across 3 regions of Vietnam</div></html>");
    descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center alignment
    descriptionLabel.setFont(SMALL_FONT);
    descriptionLabel.setForeground(Color.WHITE);
    panel.add(descriptionLabel);

    
     // Create quit button and add it to the bottom of the frame
    JButton quitButton = new JButton("Quit");
    quitButton.addActionListener(this);
    JPanel buttonPanel = new JPanel(); // Use BorderLayout
    buttonPanel.add(quitButton); // Add button to the south of BorderLayout

     
    this.add(buttonPanel, BorderLayout.SOUTH);

    // Center the window on the screen
    setLocationRelativeTo(null); // Center the window on the screen
    setVisible(true);
  }

//  public static void main(String[] args)
//  {
//    new AboutEmployee();
//  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
   String command = e.getActionCommand();
   
   if(command.equals("Quit")) {
     dispose();
     new EmployeeInterface(user);
   }
  }
}