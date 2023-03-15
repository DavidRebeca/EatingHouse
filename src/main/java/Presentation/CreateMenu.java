package Presentation;

import Business.BaseProduct;
import Business.CompositeProduct;
import Business.DeliveryService;
import Business.MenuItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CreateMenu extends JFrame{
    private JPanel panel1;
    private JButton ADDButton;
    private JTextField textField1;
    private JButton FINISHButton;
    private JButton BACKButton;
    private JTable table1;
    private DeliveryService deliveryService;
    public CreateMenu(DeliveryService ds){
        deliveryService=ds;
        setContentPane(panel1);
        setTitle("Create Menu");
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
        List<MenuItem> menuItemList=new ArrayList<>();
        ADDButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                if(!table1.getSelectionModel().isSelectionEmpty()){
                    int row = table1.getSelectedRow();
                    String[] col= new String[7];
                    for(int column=0; column<7; column++){
                        col[column]= (String) table1.getValueAt(row, column);
                    }
                    MenuItem m=new BaseProduct(col[0], Double.valueOf(col[1]), Integer.valueOf(col[2]),Integer.valueOf(col[3]),
                            Integer.valueOf(col[4]), Integer.valueOf(col[5]),Double.valueOf(col[6]));
                    menuItemList.add(m);

            }
            }
        });
        FINISHButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                String s=" (";
                for (MenuItem m: menuItemList)
                    s+=m.getTitle()+",";
                s+=")";
                CompositeProduct cp=new CompositeProduct(textField1.getText()+s, menuItemList);
                deliveryService.addCompositeItem(cp);

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
