package ru.nnboo.NNBOO.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "supply")
public class Supply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long supId;
    private String supName;
    private Integer quantity;
    private Integer cost;
    private String type;
    @ManyToMany(mappedBy = "supplies", fetch = FetchType.EAGER)
    private Set<Dish> dishes = new HashSet<>();
    @ManyToMany(mappedBy = "supplies", fetch = FetchType.EAGER)
    private Set<Plan> plans = new HashSet<>();

    public Supply(String supName, Integer quantity, Integer cost, String type, Set<Dish> dishes, Set<Plan> plans) {
        this.supName = supName;
        this.quantity = quantity;
        this.cost = cost;
        this.type = type;
        this.dishes = dishes;
        this.plans = plans;
    }

    public Supply() {

    }

    public Long getSupId() {
        return supId;
    }

    public void setSupId(Long supId) {
        this.supId = supId;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public Set<Plan> getPlans() {
        return plans;
    }

    public void setPlans(Set<Plan> plans) {
        this.plans = plans;
    }
}
