package ru.nnboo.NNBOO.entity;


import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.util.*;

@Entity
@Table(name = "dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dishId;
    private String dishName;
    private Integer cost;
    private Integer radius;
    private Float weight;
    private String receipt;

    @Lob
    private byte[] image;
    @OneToMany(mappedBy = "dish")
    private List<OrderDishEnroll> orderDishEnrolls = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Supply> supplies = new ArrayList<>();

    public Dish(String dishName, Integer cost, Integer radius, Float weight, String receipt, byte[] image) {
        this.dishName = dishName;
        this.cost = cost;
        this.radius = radius;
        this.weight = weight;
        this.receipt = receipt;
        this.image = image;
    }

    public Dish() {

    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public List<Supply> getSupplies() {
        return supplies;
    }

    public void setSupplies(List<Supply> supplies) {
        this.supplies = supplies;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }


    public List<OrderDishEnroll> getOrderDishEnrolls() {
        return orderDishEnrolls;
    }

    public void setOrderDishEnrolls(List<OrderDishEnroll> orderDishEnrolls) {
        this.orderDishEnrolls = orderDishEnrolls;
    }

    public String getImage() {
        return new String(image, StandardCharsets.UTF_8);
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
