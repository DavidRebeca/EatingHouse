package Business;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {

    private int idOrder;
    private int idClient;
    private Date orderDate;

    public Order(int idOrder, int idClient, Date orderDate) {
        this.idOrder = idOrder;
        this.idClient = idClient;
        this.orderDate=orderDate;
    }
    public Order(){

    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return idOrder == order.idOrder && idClient == order.idClient && Objects.equals(orderDate, order.orderDate);
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", idClient=" + idClient +
                ", orderDate=" + orderDate.toString() +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, idClient, orderDate);
    }
}
