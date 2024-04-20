/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IAGUI.IAManager;

/**
 *
 * @author nguyenthanhlong
 */


import IAGUI.IAManager.ManagerInterface;
import static IAGUI.Login.LoginPage.BIG_FONT;
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

public class ManagerFeedback extends JFrame implements ActionListener {
   JLabel titleLabel;
   JTextArea feedbackText;
   JButton quitButton;
   JPanel buttonPanel;
   JButton viewButton;

    public ManagerFeedback() {
        super("Manager Feedback");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.BLUE);
        this.setBounds(100, 200, 600, 400);
        this.setLayout(new BorderLayout());

        titleLabel = new JLabel("Manager Feedback", JLabel.CENTER);
        titleLabel.setFont(BIG_FONT);
        titleLabel.setForeground(Color.WHITE);
        this.add(titleLabel, BorderLayout.NORTH);

        feedbackText = new JTextArea();
        feedbackText.setPreferredSize(new Dimension(400, 100));

        JPanel feedbackPanel = new JPanel();
        feedbackPanel.add(feedbackText);
        this.add(feedbackPanel, BorderLayout.CENTER);

        viewButton = new JButton("View");
        viewButton.setPreferredSize(new Dimension(150, 50));
        viewButton.addActionListener(this);
        
        quitButton = new JButton("Quit");
        quitButton.setPreferredSize(new Dimension(150, 50));
        quitButton.addActionListener(this);
        

        buttonPanel = new JPanel();
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Other existing code


   public static void main(String[] args) {
        new ManagerFeedback();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            
        if (e.getSource() == quitButton) {
           new ManagerInterface();
            dispose(); 
        }
        
         if (e.getSource() == viewButton) {
           
        }
    }
}
