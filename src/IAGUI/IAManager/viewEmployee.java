/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IAGUI.IAManager;

/**
 *
 * @author nguyenthanhlong
 */
import IAGUI.DisplayFeedback;
import static IAGUI.Login.LoginPage.BLUE_COLOR;
import static IAGUI.InsertEmployeeList.companyImage;
import static IAGUI.Login.LoginPage.BIG_FONT;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class viewEmployee extends JFrame implements ActionListener
{

  JLabel titleLabel;
  JButton quitButton;
  JPanel buttonPanel;
  JButton viewButton;
  public static final Color BLUE_COLOR = new Color(35, 79, 30);
  public final URL IMG2_PATH = getClass().getResource("thanhlong.png");
  public ImageIcon companyImage;
  JLabel imageLabel;
  JPanel imagePanel;

  public viewEmployee()
  {
    super("Employee view");
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.getContentPane().setBackground(BLUE_COLOR);
    this.setBounds(100, 200, 600, 400);
    this.setLayout(new BorderLayout());

    companyImage = new ImageIcon(new ImageIcon(IMG2_PATH).getImage().getScaledInstance(400, 200, Image.SCALE_DEFAULT));

    titleLabel = new JLabel("Employee view", JLabel.CENTER);
    titleLabel.setFont(BIG_FONT);
    titleLabel.setForeground(Color.WHITE);
    this.add(titleLabel, BorderLayout.NORTH);

    imagePanel = new JPanel();
    imageLabel = new JLabel(companyImage);
    imagePanel.add(imageLabel);
    this.add(imagePanel, BorderLayout.CENTER);

    viewButton = new JButton("View");
    viewButton.setPreferredSize(new Dimension(150, 50));
    viewButton.addActionListener(this);

    quitButton = new JButton("Quit");
    quitButton.setPreferredSize(new Dimension(150, 50));
    quitButton.addActionListener(this);

    buttonPanel = new JPanel();
    buttonPanel.add(viewButton);
    buttonPanel.add(quitButton);
    add(buttonPanel, BorderLayout.SOUTH);

    setLocationRelativeTo(null);
    setVisible(true);
  }

  // Other existing code
  public static void main(String[] args)
  {
    new viewEmployee();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {

    if (e.getSource() == quitButton)
    {
      new ManagerInterface();
      dispose();
    }

    if (e.getSource() == viewButton)
    {
      dispose();
    String dbName = "LIST";
    String tableName = "EMPLOYEE";
    String[] columnHeaders =
    {
      "ID", "Name", "Age", "Status"
    };

    new EmployeeList(dbName, tableName, columnHeaders);
    }
  }
}
