package Presentation;

import Business.DeliveryService;
import Business.User;

import javax.jws.soap.SOAPBinding;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LogIn extends JFrame{
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton LOGINButton;
    private JButton REGISTERButton;
    private JLabel label1;
    private JPasswordField passwordField1;
    private DeliveryService deliveryService;

    public LogIn() {
        setContentPane(panel1);
        setTitle("LogIn");
        setSize(450, 300);
        setLocation(700, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        deliveryService = new DeliveryService();
       LOGINButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
              User u=deliveryService.findUser(textField1.getText());
              if(u!=null){
                if(u.getPassword().equals(passwordField1.getText()))     {
                    if(u.getType().equals("ADMINISTRATOR")) {
                        new Administrator(deliveryService);
                        dispose();
                    }
                    else {
                        new Client(deliveryService, u);
                        dispose();
                    }
                }
                else
                    label1.setText("Try again!");
              }
              else  label1.setText("User not found!");

           }
       });
        REGISTERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User u=new User(deliveryService.getUsersSize(), textField1.getText(), passwordField1.getText(),"CLIENT");
                deliveryService.addUser(u);


            }
        });
    }
}