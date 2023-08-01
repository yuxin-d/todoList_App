package ui;

import model.Item;
import model.TodoList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ResetDueWindow extends JFrame implements ActionListener {

    JTextField dateEntered;
    int index;
    ViewListWindow vw;
    Date currentdate;

    public ResetDueWindow(int index, ViewListWindow vw){
        super("Reset Item Due Date");
        this.index = index;
        this.vw = vw;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(500, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(null);

        JLabel enterDate = new JLabel("Please enter new due date for item selected.");
        add(enterDate);
        enterDate.setBounds(100, 30, 300, 20);

        dateEntered = new JTextField(20);
        add(dateEntered);
        dateEntered.setBounds(175,80,150,20);

        JButton btn = new JButton("Enter");
        add(btn);
        btn.setBounds(210, 150,80,20);
        btn.setBorder(new OptionWindow.RoundedBorder(10));
        btn.setForeground(Color.BLACK);


        btn.setActionCommand("Enter");
        btn.addActionListener(this);


        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TodoList todoList = new TodoList();
        try {
            todoList.load("src/savefile.txt");
        } catch (IOException ex) {
            System.out.println("File does not exist, please create the file before running the program!");
        }

        Item i = todoList.getTodoList().get(index);

        if(e.getActionCommand().equals("Enter")){
            String Date = dateEntered.getText();
            try {
                i.setDueDate(Date);
                JOptionPane.showMessageDialog(null,"Item due date has been reset");
                vw.dispose();
                dispose();
                try {
                    todoList.save("src/savefile.txt");
                } catch (IOException ex) {
                    System.out.println("This should never happen, I know this file exists");
                }
            } catch (ParseException e1) {
                JOptionPane.showMessageDialog(null,"Incorrect date format, try again later");
            }
            new ViewListWindow();
        }

    }
}
