package ru.nnboo.NNBOO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.nnboo.NNBOO.entity.Customer;
import ru.nnboo.NNBOO.service.DishService;
import ru.nnboo.NNBOO.service.WorkerService;

@Controller
public class PageController {
    @Autowired
    private DishService dishService;
    @Autowired
    private WorkerService workerService;

    @GetMapping({"/","/index"})
    public String mainPage(){
        return "index";
}

    @GetMapping("/contacts")
    public String contacts(){
        return "contacts";
    }

    @GetMapping("/masters")
    public String masters(Model model){
        model.addAttribute("employers", workerService.getAllEmployers());
        return "masters";
    }

    @GetMapping("/shop")
    public String shop(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            Customer customer = (Customer) auth.getPrincipal();
            model.addAttribute("user", customer);
        }
        model.addAttribute("dishes", dishService.getAllDishes());
        return "shop_unregistered";
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
