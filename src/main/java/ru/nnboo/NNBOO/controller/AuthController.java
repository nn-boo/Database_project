package ru.nnboo.NNBOO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.nnboo.NNBOO.entity.Customer;
import ru.nnboo.NNBOO.service.CustomerService;

import javax.validation.Valid;

@Controller
public class AuthController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/register")
    public String getForm(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/";
        }

        Customer user = new Customer();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String registerSave(@ModelAttribute("user") @Valid Customer user,
                               Model model){

        if (user.getUsername().length() < 5){
            model.addAttribute("errorLenUsername", true);
            return "register";
        }
        if (user.getPassword().length() < 5){
            model.addAttribute("errorLenPassword", true);
            return "register";
        }
        if (customerService.findCustomerByPhoneNumber(user.getUsername()) != null){
            model.addAttribute("errorAlreadyExistsUsername", true);
            return "register";
        }
        try{
            customerService.saveCustomer(user);
            return "redirect:/login";
        } catch (Exception e){
            model.addAttribute("errorAnomaly", true);
            return "register";
        }
    }
}
