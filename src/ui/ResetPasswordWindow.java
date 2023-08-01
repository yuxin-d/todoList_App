package ui;

import model.User;
import model.UserSystem;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResetPasswordWindow extends JFrame implements ActionListener {
    private User u;
    private JTextField passwordEntered;

    public ResetPasswordWindow(User u){
        super("Reset User Password");
        this.u=u;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(500, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(null);

        JLabel enterPassword = new JLabel("Please Enter New Passwords (Numbers Only): ");
        add(enterPassword);
        enterPassword.setBounds(50, 40, 300,20);
        enterPassword.setForeground(Color.DARK_GRAY);

        passwordEntered = new JTextField(20);
        passwordEntered.setBounds(50,80,100,20);
        add(passwordEntered);

        JButton btn = new JButton("Enter");
        add(btn);
        btn.setBounds(210,120,80,20);
        btn.setActionCommand("Enter");
        btn.addActionListener(this);
        btn.setBorder(new OptionWindow.RoundedBorder(10));
        btn.setForeground(Color.darkGray);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Enter")) {
            UserSystem userSystem = new UserSystem();
            try {
                userSystem.load("src/userfile");
            } catch (IOException ex) {
                System.out.println("File does not exist, please create the file before running the program!");
            }
            int pw = Integer.parseInt(passwordEntered.getText());

            userSystem.resetPasswords(u, pw);

            try {
                userSystem.save("src/userfile");
            } catch (IOException ex) {
                System.out.println("This should never happen, I know this file exists");
            }

            JOptionPane.showMessageDialog(null, "User password has been reset");
            dispose();
        }

    }
}
