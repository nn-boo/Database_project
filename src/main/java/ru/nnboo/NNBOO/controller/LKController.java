package ru.nnboo.NNBOO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.nnboo.NNBOO.entity.Customer;
import ru.nnboo.NNBOO.service.CustomerService;

@Controller
public class LKController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/lk")
    public String lk(){
        return "lk";
    }

    public Model addInfoAboutSession(Model model){
        model.addAttribute("infoSetting", true);
        model.addAttribute("infoMessage", "После смены данных вы выйдете из своего личного кабинета");
        return model;
    }

    @GetMapping("/lk-username")
    public String chUser(Model model){
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("changeUsername", true);
        model = addInfoAboutSession(model);
        return "lk";
    }

    @PostMapping("/lk-username")
    public String confUser(@ModelAttribute("username") String phoneNumber, Model model){
        if (phoneNumber.length() < 5){
            model.addAttribute("errorSetting", true);
            model.addAttribute("message", "Имя пользователя должно быть не менее 5 символов");
            return "lk";
        }
        else if (customerService.findCustomerByPhoneNumber(phoneNumber) != null){
            model.addAttribute("errorSetting", true);
            model.addAttribute("message", "Пользователь с таким ником уже существует");
            return "lk";
        }
        Customer changed_user = customerService.findCustomerByPhoneNumber(SecurityContextHolder.getContext().getAuthentication().getName());
        changed_user.setPhoneNumber(phoneNumber);
        customerService.rootReSave(changed_user, false);
        return "redirect:/logout";
    }

    @GetMapping("/lk-password")
    public String chPass(Model model){
        model.addAttribute("changePassword", true);
        model = addInfoAboutSession(model);
        return "lk";
    }

    @PostMapping("/lk-password")
    public String confPass(@ModelAttribute("newPassword") String newPassword, Model model){
        Customer current_user = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (newPassword.length() < 5){
            model.addAttribute("errorSetting", true);
            model.addAttribute("message", "Новый пароль должен быть не менее 5 символов");
            return "lk";
        }
        current_user.setPassword(newPassword);
        customerService.rootReSave(current_user, true);
        return "redirect:/logout";
    }

    @GetMapping("/lk-email")
    public String chEmail(Model model){
        Customer current_user = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("changeEmail", true);
        model.addAttribute("email", current_user.getCustomerName());
        model = addInfoAboutSession(model);
        return "lk";
    }

    @PostMapping("/lk-email")
    public String chEmail(@ModelAttribute("email") String email, Model model){
        Customer current_user = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        current_user.setCustomerName(email);
        customerService.rootReSave(current_user, false);
        return "redirect:/logout";
    }
}
