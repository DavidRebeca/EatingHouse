package Data;

import Business.MenuItem;
import Business.Order;
import Business.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Serializator {
    public void serializeUsers(List<User> users){
        String fileName = "userSer.txt";
        try
        {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream o = new ObjectOutputStream(file);
            o.writeObject(users);
            o.close();
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void serializeMenuItems(List<MenuItem> menuItems){
        String fileName = "menuSer.txt";
        try
        {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream o = new ObjectOutputStream(file);
            o.writeObject(menuItems);
            o.close();
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void serializeOrders(Map<Order, List<MenuItem>> orderListMap){
        String fileName = "ordersSer.txt";
        try
        {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream o = new ObjectOutputStream(file);
            o.writeObject(orderListMap);
            o.close();
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
   public List<User> deserializeUsers(){
        List<User> users=new ArrayList<>();
        try
        {
            String fileName = "userSer.txt";
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);

            users = ( List<User>)in.readObject();

            in.close();
            file.close();

            return users;
        } catch (FileNotFoundException e) {
            e.printStackTrace(); return null;
        } catch (IOException e) {
            e.printStackTrace(); return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); return null;
        }

    }
   public List<MenuItem> deserializeMenu(){
        List<MenuItem> menuItemList=new ArrayList<>();
        try
        {
            String fileName = "menuSer.txt";
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);

            menuItemList = (  List<MenuItem>)in.readObject();

            in.close();
            file.close();

            return menuItemList;
        } catch (FileNotFoundException e) {
            e.printStackTrace(); return null;
        } catch (IOException e) {
            e.printStackTrace(); return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); return null;
        }

    }
   public Map<Order, List<MenuItem>> deserializeOrders(){
        Map<Order, List<MenuItem>> orders;
        try
        {
            String fileName = "ordersSer.txt";
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);

            orders = ( Map<Order, List<MenuItem>> )in.readObject();

            in.close();
            file.close();

            return orders;
        } catch (FileNotFoundException e) {
            e.printStackTrace(); return null;
        } catch (IOException e) {
            e.printStackTrace(); return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); return null;
        }

    }
}
