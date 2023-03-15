package Presentation;

import Business.BaseProduct;
import Business.DeliveryService;
import Business.MenuItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUpdate extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JButton UPDATEButton;
    private JButton BACKButton;
    private JButton ADDButton;
    private JPanel panel1;
    private DeliveryService deliveryService;
    private MenuItem menuItem;
    public AddUpdate(DeliveryService ds, MenuItem mi){
        deliveryService=ds;
        menuItem=mi;
        setContentPane(panel1);
        setTitle("ADD/UPDATE");
        setSize(500, 500);
        setLocation(700, 200);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        textField1.setText(mi.getTitle());
        textField2.setText(String.valueOf(mi.getRating()));
        textField3.setText(String.valueOf(mi.getCalories()));
        textField4.setText(String.valueOf(mi.getProtein()));
        textField5.setText(String.valueOf(mi.getFat()));
        textField6.setText(String.valueOf(mi.getSodium()));
        textField7.setText(String.valueOf(mi.computePrice()));
        ADDButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                BaseProduct bp=new BaseProduct(textField1.getText(), Double.valueOf(textField2.getText()), Integer.valueOf(textField3.getText()),
                        Integer.valueOf(textField4.getText()), Integer.valueOf(textField5.getText()), Integer.valueOf(textField6.getText()),
                        Double.valueOf(textField7.getText()));
               deliveryService.addMenuItem(bp);
            }
        });
        UPDATEButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                BaseProduct bp=new BaseProduct(textField1.getText(), Double.valueOf(textField2.getText()), Integer.valueOf(textField3.getText()),
                        Integer.valueOf(textField4.getText()), Integer.valueOf(textField5.getText()), Integer.valueOf(textField6.getText()),
                        Double.valueOf(textField7.getText()));

                deliveryService.updateMenuItems(mi, bp);
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
