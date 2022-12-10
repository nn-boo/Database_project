package ru.nnboo.NNBOO.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.nnboo.NNBOO.entity.Customer;

@Controller
public class OrderController {


    @GetMapping("/orders")
    public String orders(Model model){
        Customer current_user = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("orders", current_user.getOrders(false));
        return "orders";
    }
}
