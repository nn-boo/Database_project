package ru.nnboo.NNBOO.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dishes_orders")
public class OrderDishEnroll implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "dish_id", referencedColumnName = "dishId")
    private Dish dish;

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "orderId")
    private Order order;

    @JoinColumn
    private int count;

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
