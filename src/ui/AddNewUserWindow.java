package ui;

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

public class AddNewUserWindow extends JFrame implements ActionListener{

    JTextField username;
    JTextField userpassword;

    public AddNewUserWindow(){
        super("Add New User");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(500, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(null);


        JLabel welcome = new JLabel("Create a New User: ");
        welcome.setBounds(55,40, 300,20);
        add(welcome);
        welcome.setForeground(Color.DARK_GRAY);

        JLabel enterYourUsername = new JLabel("Please enter new username", JLabel.CENTER);
        enterYourUsername.setBounds(40,70, 200,20);
        add(enterYourUsername);
        enterYourUsername.setForeground(Color.DARK_GRAY);

        username = new JTextField(10);
        username.setBounds(50,100, 100,20);
        add(username);

        JLabel enterYourPassword = new JLabel("Please enter new password", JLabel.CENTER);
        enterYourPassword.setBounds(40,150, 200,20);
        add(enterYourPassword);
        enterYourPassword.setForeground(Color.DARK_GRAY);

        userpassword = new JTextField();
        userpassword.setBounds(50,180, 100,20);
        add(userpassword);

        JButton btn = new JButton("Enter");
        btn.setBounds(220,240, 60,20);
        add(btn);

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
        UserSystem userSystem = new UserSystem();
        try {
            userSystem.load("src/userfile");
        } catch (IOException excp) {
            System.out.println("File does not exist, please create the file before running the program!");
        }
        if(e.getActionCommand().equals("Enter")){
            String nm = username.getText();
            int pw =  Integer.parseInt(userpassword.getText());
            userSystem.addUser(nm,pw);
            try {
                userSystem.save("src/userfile");
            } catch (IOException ex) {
                System.out.println("This should never happen, I know this file exists");
            }
            JOptionPane.showMessageDialog(null, "New user has been added");
            dispose();
        }
    }
}
