package ui;

import model.TodoList;
import model.User;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class OptionWindow extends JFrame implements ActionListener {
    private int xPosition = 100;
    private int buttonWidth = 300;
    private int buttonHeight = 20;
    Clip clip;
    private User currentUser;


    public OptionWindow(User currentUser){
        super("TodoList Application");
        this.currentUser = currentUser;
        setPreferredSize(new Dimension(500, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(null);
        try {
            BufferedImage myImage = ImageIO.read(new File("src/ui/images/3.png"));
            setContentPane(new ImagePanel(myImage));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel optionMessage = new JLabel("Please select an option: ", JLabel.CENTER);
        optionMessage.setBounds(26, 10, 300, 20);
        add(optionMessage);
        optionMessage.setForeground(Color.white);

        JButton viewList = new JButton("View Current Todolist");
        viewList.setBounds(xPosition, 40, buttonWidth, buttonHeight );
        add(viewList);
        viewList.setActionCommand("view");
        viewList.addActionListener(this);
        viewList.setBorder(new RoundedBorder(10));
        viewList.setForeground(Color.WHITE);

        JButton empty = new JButton("Empty Todolist");
        empty.setBounds(xPosition, 80, buttonWidth, buttonHeight );
        add(empty);
        empty.setActionCommand("empty");
        empty.addActionListener(this);
        empty.setBorder(new RoundedBorder(10));
        empty.setForeground(Color.white);

        this.currentUser = currentUser;
        JButton resetPassword = new JButton("Reset Passwords");
        resetPassword.setBounds(xPosition, 120, buttonWidth, buttonHeight );
        add(resetPassword);
        resetPassword.setActionCommand("resetPassword");
        resetPassword.addActionListener(this);
        resetPassword.setBorder(new RoundedBorder(10));
        resetPassword.setForeground(Color.white);


        JButton newUser = new JButton("Add A New User to the System");
        newUser.setBounds(xPosition, 160, buttonWidth, buttonHeight );
        add(newUser);
        newUser.setActionCommand("newUser");
        newUser.addActionListener(this);
        newUser.setBorder(new RoundedBorder(10));
        newUser.setForeground(Color.white);

        JButton restart = new JButton("Restart TodoList Application");
        restart.setBounds(xPosition, 200, buttonWidth, buttonHeight );
        add(restart);
        restart.setActionCommand("restart");
        restart.addActionListener(this);
        restart.setBorder(new RoundedBorder(10));
        restart.setForeground(Color.white);

        JButton close = new JButton("Close TodoList Application");
        close.setBounds(xPosition, 240, buttonWidth, buttonHeight );
        add(close);
        close.setActionCommand("close");
        close.addActionListener(this);
        close.setBorder(new RoundedBorder(10));
        close.setForeground(Color.white);

        try {
            // Open an audio input stream.
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("src/ui/music.wav"));
            // Get a sound clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("view")){
            new ViewListWindow();
        }
        if(e.getActionCommand().equals("resetPassword")){
            new ResetPasswordWindow(currentUser);
        }
        if(e.getActionCommand().equals("empty")){
            TodoList todoList = new TodoList();
                try {
                    todoList.emptyFile("src/savefile.txt");
                } catch (IOException ex) {
                    System.out.println("This should never happen, I know this file exists");
                }
            JOptionPane.showMessageDialog(null,"Your TodoList has been emptied!");
        }
        if(e.getActionCommand().equals("newUser")){
            new AddNewUserWindow();
        }
        if(e.getActionCommand().equals("restart")){
            clip.close();
            new LoginWindow();
            dispose();
        }
        if(e.getActionCommand().equals("close")){
            clip.close();
            dispose();
        }
    }

    protected static class RoundedBorder implements Border {
        private int radius;
        RoundedBorder(int radius) {
            this.radius = radius;
        }
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }
        public boolean isBorderOpaque() {
            return true;
        }
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }
}
