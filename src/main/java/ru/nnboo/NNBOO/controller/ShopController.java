package ru.nnboo.NNBOO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.nnboo.NNBOO.entity.Customer;
import ru.nnboo.NNBOO.service.CustomerService;

@Controller
public class ShopController {
    @Autowired
    private CustomerService customerService;


    @GetMapping("/adding_{number:\\d+}")
    public String addingItem(@PathVariable Long number){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        Customer customer = (Customer) auth.getPrincipal();
        customerService.addDishToCustomer(customer, number);
        return "redirect:/shop#item_" + number;
    }

    @GetMapping("/removing_{number:\\d+}")
    public String removingItem(@PathVariable Long number){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        Customer customer = (Customer) auth.getPrincipal();
        customerService.removeDishToCustomer(customer, number);
        return "redirect:/shop#item_" + number;
    }
}
