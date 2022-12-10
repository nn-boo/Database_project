package ru.nnboo.NNBOO.entity;

import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "customer")
public class Customer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;
    private String customerName;
    private String phoneNumber;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @OneToMany(mappedBy = "customer_id", fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders(boolean all) {
        if (all)
            return orders;
        else {
            List<Order> notActualOrders = new ArrayList<>();
            for (Order order: orders){
                if (order.getDone() != 0){
                    notActualOrders.add(order);
                }
            }
            return notActualOrders;
        }
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Customer(String customerName, String phoneNumber, String password, Set<Role> roles) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.roles = roles;
    }

    public Customer() {

    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Order getActualOrder(){
        for (Order order: orders) {
            if (Objects.equals(order.getDone(), 0)){
                return order;
            }
        }
        return null;
    }

    public int getDishCountByDishId(Long DishId){
        Order orderF = getActualOrder();
        if (orderF != null){
            for (OrderDishEnroll enroll: orderF.getOrderDishEnrolls()){
                if (Objects.equals(enroll.getDish().getDishId(), DishId))
                    return enroll.getCount();
            }
        }
        return 0;
    }

    public int getCountByEnroll(OrderDishEnroll enroll){
        return enroll.getCount();
    }

    public Dish getDishByEnroll(OrderDishEnroll enroll){
        return enroll.getDish();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String info(){
        String result = "";
        for(Role role: roles){
            result += role.getName();
        }
        return result;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
