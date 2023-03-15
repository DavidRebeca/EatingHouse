package Business;

import java.util.List;

public interface IDeliveryServiceProcessing {
    List<MenuItem> importMenuItems();
    void addMenuItem(BaseProduct baseProduct);
    void addCompositeItem(CompositeProduct compositeProduct);
    void deleteMenuItems(MenuItem menuItem);
    void updateMenuItems(MenuItem menuItem, MenuItem newMenuItem);
    List<MenuItem> searchMenuItemsByKeyword(String keyWord);
    List<MenuItem> searchMenuItemsByRating(double rating);
    List<MenuItem> searchMenuItemsByCalories(int calories);
    List<MenuItem> searchMenuItemsByFat(int fat);
    List<MenuItem> searchMenuItemsByProtein(int protein);
    List<MenuItem> searchMenuItemsBySodium(int sodium);
    List<MenuItem> searchMenuItemsByPrice(double price);
    void addOrder(Order order,  List<MenuItem> menuItems);
    void generateBill(Order order, User u);
    void generateReport1(int start, int end);
    void generateReport2(int nrOfTimes);
    void generateReport3(int nrOfTimes, int amount);
    void generateReport4(int day);

}
