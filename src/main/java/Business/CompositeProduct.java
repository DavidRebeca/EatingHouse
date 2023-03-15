package Business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompositeProduct implements MenuItem, Serializable {

    private String name;
    List<MenuItem> compositeProducts=new ArrayList<>();

    public CompositeProduct(String name, List<MenuItem> compositeProducts) {
        this.name = name;
        this.compositeProducts = compositeProducts;
    }
    public CompositeProduct(){}

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuItem> getCompositeProducts() {
        return compositeProducts;
    }

    public void setCompositeProducts(List<MenuItem> compositeProducts) {
        this.compositeProducts = compositeProducts;
    }
    public void addMenuItem(MenuItem menuItem){
        compositeProducts.add(menuItem);
    }
    public void removeMenuItem(MenuItem menuItem){
        compositeProducts.remove(menuItem);
    }
    @Override
    public double computePrice() {
    double totalPrice=0;
    for(MenuItem m: compositeProducts)
        totalPrice=totalPrice+m.computePrice();
    return totalPrice;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public double getRating() {
        double totalRating=0;
        for(MenuItem m: compositeProducts)
            totalRating=totalRating+m.getRating();
        return totalRating;
    }

    @Override
    public int getCalories() {
        int c=0;
        for(MenuItem m: compositeProducts)
            c=c+m.getCalories();
        return c;
    }

    @Override
    public int getProtein() {
        int p=0;
        for(MenuItem m: compositeProducts)
            p=p+m.getProtein();
        return p;
    }

    @Override
    public int getFat() {
        int f=0;
        for(MenuItem m: compositeProducts)
            f=f+m.getFat();
        return f;
    }

    @Override
    public int getSodium() {
        int s=0;
        for(MenuItem m: compositeProducts)
            s=s+m.getSodium();
        return s;
    }



}
