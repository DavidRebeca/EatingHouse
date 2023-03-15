package Business;

import java.io.Serializable;

public interface MenuItem extends Serializable {
    double computePrice();
    String toString();
    String getTitle();
    double getRating();
    int getCalories();
    int getProtein();
    int getFat();
    int getSodium();

}
