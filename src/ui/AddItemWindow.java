package ui;

import com.sun.tools.javac.comp.Todo;
import model.*;
import model.Exception.TodoListException;
import model.Exception.TooManyThingsException;
import model.Exception.TooManyUrgentItemException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class AddItemWindow extends JFrame implements ActionListener{

    JTextField itemText;
    JTextField itemDueDate;
    ViewListWindow vw;

    public AddItemWindow(ViewListWindow vw) {
        super("Add an Item");
        this.vw =vw;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(700, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(null);

        JLabel enterText = new JLabel("Enter the Item Text: ");
        enterText.setBounds(48, 40, 400, 20);
        add(enterText);
        enterText.setForeground(Color.darkGray);

        itemText = new JTextField(30);
        itemText.setBounds(50, 70, 300, 20);
        add(itemText);

        JLabel enterDueDate = new JLabel("Enter the Item Due Date " +
                "(Please Follow the Format of [MMMM d, yyyy]): ");
        enterDueDate.setBounds(50, 100, 600, 20);
        add(enterDueDate);
        enterDueDate.setForeground(Color.darkGray);

        itemDueDate = new JTextField(30);
        itemDueDate.setBounds(50, 130,300,20);
        add(itemDueDate);

        JLabel typeSelection = new JLabel("Please select the type of Item you wish to create: ");
        typeSelection.setBounds(48, 160, 600, 20);
        add(typeSelection);
        typeSelection.setForeground(Color.darkGray);

        JButton urgentItem = new JButton("Urgent");
        urgentItem.setBounds(70, 210, 100, 20);
        add(urgentItem);
        urgentItem.setActionCommand("Urgent");
        urgentItem.addActionListener(this);
        urgentItem.setBorder(new OptionWindow.RoundedBorder(10));
        urgentItem.setForeground(Color.darkGray);

        JButton regularItem = new JButton("Regular");
        regularItem.setBounds(190, 210,100,20);
        add(regularItem);
        regularItem.setActionCommand("Regular");
        regularItem.addActionListener(this);
        regularItem.setBorder(new OptionWindow.RoundedBorder(10));
        regularItem.setForeground(Color.darkGray);


        JButton businessItem = new JButton("Business");
        businessItem.setBounds(310,210,100,20);
        add(businessItem);
        businessItem.setActionCommand("Business");
        businessItem.addActionListener(this);
        businessItem.setBorder(new OptionWindow.RoundedBorder(10));
        businessItem.setForeground(Color.darkGray);

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
        String t = itemText.getText();
        String due = itemDueDate.getText();
        if(e.getActionCommand().equals("Urgent")){
            try {
                Item item = new UrgentItem(t,due);
                todoList.addItem(item);
                JOptionPane.showMessageDialog(null,"Your Item has been successfully added.");
            } catch (ParseException pe){
                JOptionPane.showMessageDialog(null,"Incorrect date format, try again later");
            }
            catch (TodoListException ex) {
                JOptionPane.showMessageDialog(null,"Item over limit, try again later");
            }
            try {
                todoList.save("src/savefile.txt");
            } catch (IOException ex) {
                System.out.println("This should never happen, I know this file exists");
            }
        }
        if(e.getActionCommand().equals("Regular")){
            try {
                Item item = new RegularItem(t, due);
                todoList.addItem(item);
                JOptionPane.showMessageDialog(null,"Your Item has been successfully added.");
            } catch (ParseException pe){
                JOptionPane.showMessageDialog(null,"Incorrect date format, try again later");
            }
            catch (TodoListException ex) {
                JOptionPane.showMessageDialog(null,"Item over limit, try again later");
            }
            try {
                todoList.save("src/savefile.txt");
            } catch (IOException ex) {
                System.out.println("This should never happen, I know this file exists");
            }
        }
        if(e.getActionCommand().equals("Business")){
            try {
                Item item = new BusinessItem(t,due);
                todoList.addItem(item);
                JOptionPane.showMessageDialog(null,"Your Item has been successfully added.");
            } catch (ParseException pe){
                JOptionPane.showMessageDialog(null,"Incorrect date format, try again later");
            }
            catch (TodoListException ex) {
                JOptionPane.showMessageDialog(null,"Item over limit, try again later");
            }
            try {
                todoList.save("src/savefile.txt");
            } catch (IOException ex) {
                System.out.println("This should never happen, I know this file exists");
            }
        }
        vw.dispose();
        new ViewListWindow();
        dispose();
    }
}
