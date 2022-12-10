package ru.nnboo.NNBOO.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private Date time;
    private String comment;
    private Integer done;
    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer_id;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderDishEnroll> orderDishEnrolls = new ArrayList<>();

    public Order(Date time, String comment, Integer done, Customer customer_id) {
        this.time = time;
        this.comment = comment;
        this.done = done;
        this.customer_id = customer_id;
    }

    public List<OrderDishEnroll> getOrderDishEnrolls() {
        return orderDishEnrolls;
    }

    public void setOrderDishEnrolls(List<OrderDishEnroll> orderDishEnrolls) {
        this.orderDishEnrolls = orderDishEnrolls;
    }

    public Order() {

    }

    public OrderDishEnroll findEnrollByDishId(Long DishId){
        for(OrderDishEnroll enroll: orderDishEnrolls){
            if (Objects.equals(enroll.getDish().getDishId(), DishId) && Objects.equals(enroll.getOrder().getOrderId(), orderId))
                return enroll;
        }
        return null;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getDone() {
        return done;
    }

    public void setDone(Integer done) {
        this.done = done;
    }

    public Customer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Customer customer_id) {
        this.customer_id = customer_id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
