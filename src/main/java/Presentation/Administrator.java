package Presentation;

import Business.BaseProduct;
import Business.DeliveryService;
import Business.MenuItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Administrator extends JFrame{

    private JButton ADDUPDATEButton;
    private JButton DELETEButton;
    private JButton CREATEMENUButton;
    private JTable table1;
    private JButton REPORTSButton;
    private JPanel panel1;
    private JButton signOutButton;
    private DeliveryService deliveryService;
    public Administrator(DeliveryService ds){
        deliveryService=ds;
        setContentPane(panel1);
        setTitle("ADMINISTRATOR");
        setSize(1200, 800);
        setLocation(400, 80);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        String[] coloane={"TITLE", "RATING", "CALORIES", "PROTEIN", "FAT", "SODIUM", "PRICE"};

        DefaultTableModel model=new DefaultTableModel();

        for (int i=0; i<7; i++)
            model.addColumn(coloane[i]);
        model.setRowCount(0);
        for(MenuItem m: deliveryService.getMenu()) {
            String[] linii=new String[7];
            linii[0]=m.getTitle();
            linii[1]=Double.toString(m.getRating());
            linii[2]=Integer.toString(m.getCalories());
            linii[3]=Integer.toString(m.getProtein());
            linii[4]=Integer.toString(m.getFat());
            linii[5]=Integer.toString(m.getSodium());
            linii[6]=Double.toString(m.computePrice());
            model.addRow(linii);

        }
        table1.setModel(model);
        ADDUPDATEButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
              if(!table1.getSelectionModel().isSelectionEmpty()){
                int row = table1.getSelectedRow();
                String[] col= new String[7];
                for(int column=0; column<7; column++){
                    col[column]= (String) table1.getValueAt(row, column);
                }
                MenuItem m=new BaseProduct(col[0], Double.valueOf(col[1]), Integer.valueOf(col[2]),Integer.valueOf(col[3]),
                        Integer.valueOf(col[4]), Integer.valueOf(col[5]),Double.valueOf(col[6]));

                new AddUpdate(deliveryService, m);
                dispose();
            }}
        });
        DELETEButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                if(!table1.getSelectionModel().isSelectionEmpty()){
                int row = table1.getSelectedRow();
                String[] col= new String[7];
                for(int column=0; column<7; column++){
                    col[column]= (String) table1.getValueAt(row, column);
                }
                MenuItem m=new BaseProduct(col[0], Double.valueOf(col[1]), Integer.valueOf(col[2]),Integer.valueOf(col[3]),
                        Integer.valueOf(col[4]), Integer.valueOf(col[5]),Double.valueOf(col[6]));
                deliveryService.deleteMenuItems(m);

                new Administrator(deliveryService);
                dispose();
            }}
        });
        REPORTSButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
             new Reports(deliveryService);
             dispose();
            }
        });
        CREATEMENUButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                new CreateMenu(deliveryService);
                dispose();
            }
        });
        signOutButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                new LogIn();
                dispose();
            }
        });



    }
}
