package ru.nnboo.NNBOO.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nnboo.NNBOO.entity.*;
import ru.nnboo.NNBOO.repository.CustomerJPA;
import ru.nnboo.NNBOO.repository.DishJPA;
import ru.nnboo.NNBOO.repository.OrderJPA;
import ru.nnboo.NNBOO.repository.RoleJPA;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CustomerService implements UserDetailsService {

    @Autowired
    private CustomerJPA customerJPA;
    @Autowired
    private RoleJPA roleJPA;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DishJPA dishJPA;

    @Autowired
    private OrderJPA orderJPA;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerJPA.findCustomerByPhoneNumber(username);

        if (customer == null){
            throw new UsernameNotFoundException("Customer not found");
        }

        return customer;
    }

    @Transactional
    public Customer findCustomerByPhoneNumber(String phone){
        return customerJPA.findCustomerByPhoneNumber(phone);
    }

    @Transactional
    public void saveCustomer(Customer customer){
        Customer userFromDB = customerJPA.findCustomerByPhoneNumber(customer.getUsername());
        if (userFromDB != null){
            return;
        }

        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        Role role = roleJPA.findRoleByName("ROLE_USER");
        Order order = new Order();
        order.setDone(0);
        order.setCustomer_id(customer);
        customer.getRoles().add(role);
        customer.getOrders(true).add(order);
        customerJPA.save(customer);
    }

    @Transactional
    public void addDishToCustomer(Customer user, Long dishId){
        int count = user.getDishCountByDishId(dishId);
        Order actual = user.getActualOrder();
        Dish dish = dishJPA.findDishByDishId(dishId);
        List<OrderDishEnroll> enrolls = actual.getOrderDishEnrolls();
        if (count == 0){
            OrderDishEnroll newEnroll = new OrderDishEnroll();
            newEnroll.setDish(dish);
            newEnroll.setCount(1);
            newEnroll.setOrder(actual);
            enrolls.add(newEnroll);
            customerJPA.createEnroll(dishId, actual.getOrderId());
        } else {
            OrderDishEnroll currentEnroll = actual.findEnrollByDishId(dishId);
            currentEnroll.setCount(count + 1);
            customerJPA.updateEnroll(dishId, actual.getOrderId(), count + 1);
        }
        actual.setOrderDishEnrolls(enrolls);
        dish.setOrderDishEnrolls(enrolls);
        customerJPA.save(user);
    }

    @Transactional
    public void removeDishToCustomer(Customer user, Long dishId){
        int count = user.getDishCountByDishId(dishId);
        Order actual = user.getActualOrder();
        Dish dish = dishJPA.findDishByDishId(dishId);
        List<OrderDishEnroll> enrolls = actual.getOrderDishEnrolls();
        if (count == 0){
            return;
        }
        OrderDishEnroll currentEnroll = actual.findEnrollByDishId(dishId);
        if (count == 1){
            enrolls.remove(currentEnroll);
            customerJPA.deleteEnroll(dishId, actual.getOrderId());
        } else {
            currentEnroll.setCount(currentEnroll.getCount() - 1);
            customerJPA.updateEnroll(dishId, actual.getOrderId(), count - 1);
        }
        actual.setOrderDishEnrolls(enrolls);
        dish.setOrderDishEnrolls(enrolls);
        customerJPA.save(user);
    }

    @Transactional
    public void rootReSave(Customer user, boolean chpass){
        if (chpass){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        customerJPA.save(user);
    }

    @Transactional
    public List<Dish> getCustomerDishes(Customer user){
        Order actual = user.getActualOrder();
        List<Dish> dishes = new ArrayList<>();
        for(OrderDishEnroll enroll: actual.getOrderDishEnrolls()){
            dishes.add(enroll.getDish());
        }
        return dishes;
    }

    @Transactional
    public double getTotalCost(Customer user){
        double result = 0;
        Order actual = user.getActualOrder();
        for(OrderDishEnroll enroll: actual.getOrderDishEnrolls()){
            result += enroll.getDish().getCost() * enroll.getCount();
        }
        return result;
    }

    @Transactional
    public void removeDishes(Customer user){
        Order deletingOrder = user.getActualOrder();
        deletingOrder.getOrderDishEnrolls().clear();
        customerJPA.save(user);
    }

    @Transactional
    public void makeOrder(Customer user, String comment){
        Order makingOrder = user.getActualOrder();
        makingOrder.setDone(1);
        makingOrder.setComment(comment);
        makingOrder.setTime(new Date());
        Order order = new Order();
        order.setDone(0);
        order.setCustomer_id(user);
        order = orderJPA.save(order);
        user.getOrders(true).add(order);
        customerJPA.save(user);
    }
}
