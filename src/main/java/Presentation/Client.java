package Presentation;

import Business.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Client extends JFrame {
    private JPanel panel1;
    private JTable table1;
    private JTable table2;
    private JButton ADDButton;
    private JButton FINISHORDERButton;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JButton SEARCHButton;
    private JButton STARTCOMANDAButton;
    private JButton DOWNLOADBILLButton;
    private JButton MENUButton;
    private JButton signOutButton;
    private DeliveryService deliveryService;
    private User user;
    public Client(DeliveryService ds, User u) {
        deliveryService = ds;
        user=u;
        setContentPane(panel1);
        setTitle("CLIENT");
        setSize(1200, 800);
        setLocation(400, 80);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        String[] coloane = {"TITLE", "RATING", "CALORIES", "PROTEIN", "FAT", "SODIUM", "PRICE"};

        DefaultTableModel model = new DefaultTableModel();
        DefaultTableModel model2 = new DefaultTableModel();
        for (int i = 0; i < 7; i++) {
            model.addColumn(coloane[i]);
            model2.addColumn(coloane[i]);
        }
        model.setRowCount(0);
        for (MenuItem m : deliveryService.getMenu()) {
            String[] linii = new String[7];
            linii[0] = m.getTitle();
            linii[1] = Double.toString(m.getRating());
            linii[2] = Integer.toString(m.getCalories());
            linii[3] = Integer.toString(m.getProtein());
            linii[4] = Integer.toString(m.getFat());
            linii[5] = Integer.toString(m.getSodium());
            linii[6] = Double.toString(m.computePrice());
            model.addRow(linii);

        }
        table2.setModel(model);
        for(int i=0; i<7; i++)
            comboBox1.addItem(coloane[i]);
        SEARCHButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                if (!textField1.getText().equals("")) {
                    int rowCount = model.getRowCount();
                    for (int i = rowCount - 1; i >= 0; i--) {
                        model.removeRow(i);
                    }
                    String sel = comboBox1.getSelectedItem().toString();
                    List<MenuItem> l = new ArrayList<>();
                    if (sel.equals("TITLE"))
                        l = deliveryService.searchMenuItemsByKeyword(textField1.getText());
                    else {
                        if (sel.equals("RATING") && isNumeric(textField1.getText()))
                            l = deliveryService.searchMenuItemsByRating(Double.valueOf(textField1.getText()));
                        else if (!isNumeric(textField1.getText())) textField1.setText("Please enter a numeric value!");
                        if (sel.equals("PRICE") && isNumeric(textField1.getText()))
                            l = deliveryService.searchMenuItemsByPrice(Double.valueOf(textField1.getText()));
                        else if (!isNumeric(textField1.getText())) textField1.setText("Please enter a numeric value!");
                        if (sel.equals("CALORIES") && isNumeric(textField1.getText()))
                            l = deliveryService.searchMenuItemsByCalories(Integer.valueOf(textField1.getText()));
                        else if (!isNumeric(textField1.getText())) textField1.setText("Please enter a numeric value!");
                        if (sel.equals("FAT") && isNumeric(textField1.getText()))
                            l = deliveryService.searchMenuItemsByFat(Integer.valueOf(textField1.getText()));
                        else if (!isNumeric(textField1.getText())) textField1.setText("Please enter a numeric value!");
                        if (sel.equals("PROTEIN") && isNumeric(textField1.getText()))
                            l = deliveryService.searchMenuItemsByProtein(Integer.valueOf(textField1.getText()));
                        else if (!isNumeric(textField1.getText())) textField1.setText("Please enter a numeric value!");
                        if (sel.equals("SODIUM") && isNumeric(textField1.getText()))
                            l = deliveryService.searchMenuItemsBySodium(Integer.valueOf(textField1.getText()));
                        else if (!isNumeric(textField1.getText())) textField1.setText("Please enter a numeric value!");
                    }
                    for (MenuItem m : l) {
                        String[] linii = new String[7];
                        linii[0] = m.getTitle();
                        linii[1] = Double.toString(m.getRating());
                        linii[2] = Integer.toString(m.getCalories());
                        linii[3] = Integer.toString(m.getProtein());
                        linii[4] = Integer.toString(m.getFat());
                        linii[5] = Integer.toString(m.getSodium());
                        linii[6] = Double.toString(m.computePrice());
                        model.addRow(linii);
                    }
                    table2.setModel(model);//

                }
                else textField1.setText("Enter data here!");
            }
        });

        STARTCOMANDAButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
             List<MenuItem> menuItemList=new ArrayList<>();
             Order comandaActuala=new Order(deliveryService.getOrdersSize()+1, u.getIdUser(), new Date());
                ADDButton.addActionListener(new ActionListener() {
                    @Override public void actionPerformed(ActionEvent e) {
                        if(!table2.getSelectionModel().isSelectionEmpty()){
                        int row = table2.getSelectedRow();
                        String[] col= new String[7];
                        for(int column=0; column<7; column++){
                            col[column]= (String) table2.getValueAt(row, column);
                        }

                        MenuItem m=new BaseProduct(col[0], Double.valueOf(col[1]), Integer.valueOf(col[2]),Integer.valueOf(col[3]),
                                Integer.valueOf(col[4]), Integer.valueOf(col[5]),Double.valueOf(col[6]));
                        menuItemList.add(m);
                        String[] linii = new String[7];
                        linii[0] = m.getTitle();
                        linii[1] = Double.toString(m.getRating());
                        linii[2] = Integer.toString(m.getCalories());
                        linii[3] = Integer.toString(m.getProtein());
                        linii[4] = Integer.toString(m.getFat());
                        linii[5] = Integer.toString(m.getSodium());
                        linii[6] = Double.toString(m.computePrice());
                        model2.addRow(linii);
                        table1.setModel(model2);

                    }}
                });
                FINISHORDERButton.addActionListener(new ActionListener() {
                    @Override public void actionPerformed(ActionEvent e) {
                        deliveryService.addOrder(comandaActuala, menuItemList);
                        int rowCount = model2.getRowCount();
                        for (int i = rowCount - 1; i >= 0; i--) {
                            model2.removeRow(i);
                    }  table1.setModel(model2);
                        deliveryService.finishOrder();

                }});
                DOWNLOADBILLButton.addActionListener(new ActionListener() {
                    @Override public void actionPerformed(ActionEvent e) {
                        deliveryService.generateBill(comandaActuala, u);
                    }
                });
            }

        });
        MENUButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = new DefaultTableModel();
                DefaultTableModel model2 = new DefaultTableModel();
                for (int i = 0; i < 7; i++) {
                    model.addColumn(coloane[i]);
                    model2.addColumn(coloane[i]);
                }
                model.setRowCount(0);
                for (MenuItem m : deliveryService.getMenu()) {
                    String[] linii = new String[7];
                    linii[0] = m.getTitle();
                    linii[1] = Double.toString(m.getRating());
                    linii[2] = Integer.toString(m.getCalories());
                    linii[3] = Integer.toString(m.getProtein());
                    linii[4] = Integer.toString(m.getFat());
                    linii[5] = Integer.toString(m.getSodium());
                    linii[6] = Double.toString(m.computePrice());
                    model.addRow(linii);

                }
                table2.setModel(model);
            }
        });
        signOutButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                new LogIn();
                dispose();
            }
        });


    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
