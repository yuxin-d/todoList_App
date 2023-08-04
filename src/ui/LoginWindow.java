package ui;

import model.Password;
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

public class LoginWindow extends JFrame implements ActionListener {

    JTextField username;
    JPasswordField userpasswords;

    public LoginWindow(){
        super("TodoList Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 350));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(null);
        try {
            BufferedImage myImage = ImageIO.read(new File("src/ui/images/3.png"));
            setContentPane(new ImagePanel(myImage));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel welcome = new JLabel("Welcome to the Todo List Application :D", JLabel.CENTER);
        welcome.setBounds(42,40, 280,20);
        add(welcome);
        welcome.setForeground(Color.white);

        JLabel enterYourUsername = new JLabel("Please enter your username", JLabel.CENTER);
        enterYourUsername.setBounds(40,70, 200,20);
        add(enterYourUsername);
        enterYourUsername.setForeground(Color.white);

        JLabel defaultName = new JLabel("(default:admin)(PW:0)", JLabel.CENTER);
        defaultName.setBounds(42,100, 200,20);
        add(defaultName);
        defaultName.setForeground(Color.white);

        username = new JTextField(10);
        username.setBounds(50,130, 100,20);
        add(username);

        JLabel enterYourPassword = new JLabel("Please enter your password (Numbers Only)");
        enterYourPassword.setBounds(53,180, 400,20);
        add(enterYourPassword);
        enterYourPassword.setForeground(Color.white);

        userpasswords = new JPasswordField();
        userpasswords.setBounds(50,210, 100,20);
        add(userpasswords);

        JButton btn = new JButton("Enter");
        btn.setBounds(220,270, 60,20);
        add(btn);
        btn.setBorder(new OptionWindow.RoundedBorder(10));
        btn.setForeground(Color.white);

        btn.setActionCommand("Enter");
        btn.addActionListener(this);


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
        if(e.getActionCommand().equals("Enter"))
        {
            String nm = username.getText();
            int pw = Integer.parseInt(String.valueOf(userpasswords.getPassword()));

            Boolean run = userSystem.accessVerification(pw, nm);
            if (run){
                this.dispose();
                new OptionWindow(new User(nm, new Password(pw)));
            }
            else{
                JOptionPane.showMessageDialog(null,"Invalid username or password");
            }
        }
    }

    public static void main(String[] args) {
        new LoginWindow();
    }
}
