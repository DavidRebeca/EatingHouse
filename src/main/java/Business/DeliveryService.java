package Business;

import Data.Serializator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeliveryService implements IDeliveryServiceProcessing{

   List<MenuItem> menu=new ArrayList<>();
   List<User> users=new ArrayList<>();
   Map<Order, List<MenuItem>> orders=new HashMap<>();
   Serializator serializator=new Serializator();

    public DeliveryService() {
        List<MenuItem> menuItemList=serializator.deserializeMenu();
        if(menuItemList==null){
            menuItemList=importMenuItems();
            this.menu=menuItemList;
            serializator.serializeMenuItems(menu);
        }
        else  this.menu=menuItemList;
        List<User> usersList=serializator.deserializeUsers();
        if(usersList==null){
            usersList=importUsers();
            this.users= usersList;
            serializator.serializeUsers(users);
        }
        else this.users= usersList;
        orders=serializator.deserializeOrders();


    }

    public List<MenuItem> getMenu() {
        return menu;
    }

    public User findUser(String s){
        for(User u: users)
            if(u.getUserName().equals(s))
                return u;
        return null;
    }
    void eliminateMenuDuplicate(List<MenuItem> m){
        for(MenuItem mi:m){
            int fv=0;
            for(MenuItem mia: m) {
                if (isEqual(mia, mi))
                    fv++;
                if(fv>1) m.remove(mia);
            }

        }
    }
    public List<User> importUsers(){
        Path path = Paths.get("user.txt");

        try (Stream<String> lines = Files.lines(path)){

            Set<User> us = lines.map(line -> {
                String[] col = line.split(",");
                return new User(Integer. valueOf(col[0]),col[1],col[2],col[3]);
            }).collect(Collectors.toSet());

            List<User> userRez=new ArrayList<>(us);

            return userRez;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<MenuItem> importMenuItems() {
     Path path = Paths.get("products.csv");

     try (Stream<String> lines = Files.lines(path)){

       Set<MenuItem> menuProducts = lines.skip(1).map(line -> {
       String[] col = line.split(",");
       return new BaseProduct( col[0], Double. valueOf(col[1]),Integer. valueOf(col[2]),Integer. valueOf(col[3]),
               Integer. valueOf(col[4]),Integer. valueOf(col[5]),  Double. valueOf(col[6]));
      }).collect(Collectors.toSet());

      List<MenuItem> menuRez=new ArrayList<MenuItem>(menuProducts);
      eliminateMenuDuplicate(menuRez);
      return menuRez;

     } catch (IOException e) {
      e.printStackTrace();
      return null;
     }

    }

    public int getOrdersSize() {
        return orders.size();
    }
    public int getUsersSize(){return users.size();}
    @Override
    public void addMenuItem(BaseProduct baseProduct) {
      menu.add(baseProduct);
      serializator.serializeMenuItems(menu);
    }

    @Override
    public void addCompositeItem(CompositeProduct compositeProduct) {
    menu.add(compositeProduct);
    serializator.serializeMenuItems(menu);
    }

    @Override
    public void deleteMenuItems(MenuItem menuItem) {

        for(MenuItem m: menu){
            if(isEqual(menuItem, m)){
                menu.remove(m);
                break;
            }
        }

    serializator.serializeMenuItems(menu);
    }
    boolean isEqual(MenuItem m1, MenuItem m2){
        if(m1.getTitle().equals(m2.getTitle())==true&&Double.compare(m1.getRating(), m2.getRating())==0&&m1.getProtein()==m2.getProtein()&&
        m1.getCalories()== m2.getCalories()&& m1.getFat()== m2.getFat()&&Double.compare(m1.computePrice(), m2.computePrice())==0&&
        m1.getSodium()==m2.getSodium())
            return true;
        return false;
    }
    @Override
    public void updateMenuItems(MenuItem menuItem, MenuItem newMenuItem) {
        int i=0;
        for(MenuItem m: menu){
            if(isEqual(menuItem, m))
                break;
            i++;
        }
      menu.set(i, newMenuItem);
      serializator.serializeMenuItems(menu);
    }

    @Override
    public List<MenuItem> searchMenuItemsByKeyword(String keyWord) {
         List<MenuItem> list=menu.stream().filter(f->f.getTitle().contains(keyWord)).collect(Collectors.toList());
         List<MenuItem> rezultat=new ArrayList<>(list);
         return rezultat;
    }

    @Override
    public List<MenuItem> searchMenuItemsByRating(double rating) {
        List<MenuItem> list=menu.stream().filter(f->Double.compare(f.getRating(),rating)==0).collect(Collectors.toList());
        List<MenuItem> rezultat=new ArrayList<>(list);
        return rezultat;
    }

    @Override
    public List<MenuItem> searchMenuItemsByCalories(int calories) {
        List<MenuItem> list=menu.stream().filter(f->f.getCalories()==calories).collect(Collectors.toList());
        List<MenuItem> rezultat=new ArrayList<>(list);
        return rezultat;
    }

    @Override
    public List<MenuItem> searchMenuItemsByFat(int fat) {
        List<MenuItem> list=menu.stream().filter(f->f.getFat()==fat).collect(Collectors.toList());
        List<MenuItem> rezultat=new ArrayList<>(list);
        return rezultat;
    }

    @Override
    public List<MenuItem> searchMenuItemsByProtein(int protein) {
        List<MenuItem> list=menu.stream().filter(f->f.getProtein()==protein).collect(Collectors.toList());
        List<MenuItem> rezultat=new ArrayList<>(list);
        return rezultat;
    }

    @Override
    public List<MenuItem> searchMenuItemsBySodium(int sodium) {
        List<MenuItem> list=menu.stream().filter(f->f.getSodium()==sodium).collect(Collectors.toList());
        List<MenuItem> rezultat=new ArrayList<>(list);
        return rezultat;
    }

    @Override
    public List<MenuItem> searchMenuItemsByPrice(double price) {
        List<MenuItem> list=menu.stream().filter(f->Double.compare(f.computePrice(), price) == 0).collect(Collectors.toList());
        List<MenuItem> rezultat=new ArrayList<>(list);
        return rezultat;
    }

    @Override
    public void addOrder(Order order,  List<MenuItem> menuItems) {
        orders.put(order, menuItems);
    }
    public void finishOrder(){
        serializator.serializeOrders(orders);
    }
    public void addUser(User u) {
        users.add(u);
        serializator.serializeUsers(users);
    }

    @Override
    public void generateBill(Order order, User u) {

        try {
            File file = new File("Bill.txt");
            FileWriter writer = new FileWriter(file);
            writer.write("FACTURA NR. "+order.getIdOrder()+"\n");
            writer.write("DATA: "+order.getOrderDate().toString()+"\n");
            writer.write("CLIENT: "+u.getUserName()+"\n\n\n");
            writer.write("PRODUSE COMANDATE: "+"\n");
            List<MenuItem> orderItems=orders.get(order);
            double total=0;
            for(MenuItem o: orderItems) {
                writer.write(o.getTitle() + " " + o.computePrice() + "\n\n\n");
                total+=o.computePrice();
            }
            writer.write("TOTAL: "+total);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void generateReport1(int start, int end) {
        try {
            File file = new File("RAPORT1.txt");
            FileWriter writer = new FileWriter(file);
            writer.write("RAPORT 1 "+"\n");
            writer.write("Comenzile plasate intre orele "+start+" si "+end+"\n");
            List<Order> list= orders.entrySet()
                    .stream()
                    .filter( o -> o.getKey().getOrderDate().getHours()>=start&& o.getKey().getOrderDate().getHours()<=end)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
            for(Order o: list)
                writer.write(o.toString()+"\n");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void generateReport2(int nrOfTimes) {
        try {
            File file = new File("RAPORT2.txt");
            FileWriter writer = new FileWriter(file);
            writer.write("RAPORT 2 "+"\n");
            writer.write("Produsele comandate mai mult de "+nrOfTimes+" ori"+"\n");
            Map<String, Integer> frecventa=new HashMap<String, Integer>();

            for(MenuItem item: menu){
                Integer frecv=0;
                for (Map.Entry<Order, List<MenuItem>> m : orders.entrySet()) {
                    for(MenuItem mi: m.getValue()){
                        if (item.getTitle().equals(mi.getTitle()))
                           frecv++;
                    }
                }

                frecventa.put(item.getTitle(), frecv);
            }

            List<String> list= frecventa.entrySet()
                    .stream()
                    .filter( fr ->fr.getValue()>=nrOfTimes)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
            List<String> lista=new ArrayList<>(list);
            for(String s: lista)
                writer.write(s+"\n");
            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public double calculateOrderPrice(List <MenuItem> comanda){
        double pr=0;
        for(MenuItem m: comanda)
            pr+= m.computePrice();
        return pr;
    }
    @Override
    public void generateReport3(int nrOfTimes, int amount) {
        try {
            File file = new File("RAPORT3.txt");
            FileWriter writer = new FileWriter(file);
            writer.write("RAPORT 3 "+"\n");
            writer.write("Clientii care au comandat de mai mult de "+nrOfTimes+" ori si valoarea comenziilor mai mare de "+amount+"\n");
            Map<User, Integer> frecventa=new HashMap<User, Integer>();
            for(User u: users){
                Integer frecv=0;
                for (Map.Entry<Order, List<MenuItem>> m : orders.entrySet()) {
                        if (m.getKey().getIdClient()==u.getIdUser())
                            frecv++;
                }
                frecventa.put(u, frecv);
            }
            Set<User> list= frecventa.entrySet()
                    .stream()
                    .filter( fr ->fr.getValue()>=nrOfTimes)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toSet());
            Set<User> lista=new HashSet<>(list);
            for (Map.Entry<Order, List<MenuItem>> m : orders.entrySet())
                for(User u: lista)
                    if(u.getIdUser()==m.getKey().getIdClient()&&calculateOrderPrice(m.getValue())>=amount)
                        writer.write(u.getUserName()+"\n");

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generateReport4(int day) {
        try {
            File file = new File("RAPORT4.txt");
            FileWriter writer = new FileWriter(file);
            writer.write("RAPORT 4 "+"\n");
            writer.write("Produsele comandate in ziua "+day+"\n");

            List<Order> list= orders.entrySet()
                    .stream()
                    .filter(f-> f.getKey().getOrderDate().getDay()==day)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
            for(Order o: list)
                for (Map.Entry<Order, List<MenuItem>> m : orders.entrySet()) {
                    if(o.getIdOrder()==m.getKey().getIdOrder())
                        for(MenuItem p: m.getValue())
                            writer.write(p.getTitle()+"\n");
                }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
