package Presentation;

import Business.DeliveryService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

public class Reports extends JFrame{
    private JPanel panel1;
    private JButton BACKButton;
    private JButton GENERATEButton;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField6;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private DeliveryService deliveryService;
    public Reports(DeliveryService ds){
        deliveryService=ds;
        setContentPane(panel1);
        setTitle("Reports");
        setSize(1000, 700);
        setLocation(400, 80);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        comboBox1.addItem("Monday");
        comboBox1.addItem("Tuesday");
        comboBox1.addItem("Wednesday");
        comboBox1.addItem("Thursday");
        comboBox1.addItem("Friday");
        comboBox1.addItem("Saturday");
        for (int i=0;i<24; i++){
            comboBox2.addItem(i);
            comboBox3.addItem(i);
        }
        GENERATEButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                if (!textField3.getText().equals("") && !textField4.getText().equals("") && !textField6.getText().equals("")) {
                    int zi = 0;
                    if (comboBox1.getSelectedItem().toString().equals("Monday")) zi = 1;
                    if (comboBox1.getSelectedItem().toString().equals("Tuesday")) zi = 2;
                    if (comboBox1.getSelectedItem().toString().equals("Wednesday")) zi = 3;
                    if (comboBox1.getSelectedItem().toString().equals("Thursday")) zi = 4;
                    if (comboBox1.getSelectedItem().toString().equals("Friday")) zi = 5;
                    if (comboBox1.getSelectedItem().toString().equals("Saturday")) zi = 6;
                    deliveryService.generateReport1(Integer.valueOf(comboBox2.getSelectedItem().toString()), Integer.valueOf(comboBox3.getSelectedItem().toString()));
                    try {
                        deliveryService.generateReport2(Integer.valueOf(textField3.getText()));
                        deliveryService.generateReport3(Integer.valueOf(textField4.getText()), Integer.valueOf(textField6.getText()));
                        deliveryService.generateReport4(zi);
                        JOptionPane.showMessageDialog(panel1, "All reports have been generated successfully!");
                    }catch (Exception exception){
                        JOptionPane.showMessageDialog(panel1, "Enter integer values!");
                    }

                }else   JOptionPane.showMessageDialog(panel1, "Complete all the fields!");
            }
        });
        BACKButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                dispose();
                new Administrator(deliveryService);
            }
        });
    }
}
