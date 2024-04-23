package IAGUI.IAManager;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author nguyenthanhlong
 */
import IAGUI.IAManager.ManagerInterface;
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

public class ManagerNotification extends JFrame implements ActionListener {

    private JPanel buttonPanel;
    private JLabel titleLabel;
    private JTextArea NotificationText;
    private JButton submitButton, quitButton;
    public static final Color BLUE_COLOR = new Color(35, 79, 30);
    public static final Font BIG_FONT = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 40);

    public ManagerNotification() {
        super("Employee Feedback");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.BLUE);
        this.setBounds(100, 200, 600, 400);
        this.setLayout(new BorderLayout());

        titleLabel = new JLabel("Employee Feedback", JLabel.CENTER);
        titleLabel.setFont(BIG_FONT);
        titleLabel.setForeground(Color.WHITE);
        this.add(titleLabel, BorderLayout.NORTH);

        NotificationText = new JTextArea("Feedback enter here");
        NotificationText.setPreferredSize(new Dimension(400, 100));
        
        JPanel feedbackPanel = new JPanel();
        feedbackPanel.add(NotificationText);
        this.add(feedbackPanel, BorderLayout.CENTER);

        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(150, 50));
        submitButton.addActionListener(this);

        quitButton = new JButton("Quit");
        quitButton.setPreferredSize(new Dimension(150, 50));
        quitButton.addActionListener(this);

        buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    public static void main(String[] args) {
        new ManagerNotification();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            JOptionPane.showMessageDialog(this, "Notification is submitted");
            String Notification = NotificationText.getText();
            // Process the feedback
        } else if (e.getSource() == quitButton) {
            new ManagerInterface();
            dispose(); 
        }
    }
}

