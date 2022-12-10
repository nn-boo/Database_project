package ru.nnboo.NNBOO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.nnboo.NNBOO.entity.Dish;
import ru.nnboo.NNBOO.entity.Worker;
import ru.nnboo.NNBOO.service.DishService;
import ru.nnboo.NNBOO.service.WorkerService;

import java.io.IOException;
import java.util.Base64;

@Controller
public class AdminController {
    @Autowired
    private DishService dishService;
    @Autowired
    private WorkerService workerService;

    @GetMapping("/admin")
    public String getAdminPanel(Model model){
        return "admin";
    }

    @GetMapping("/admin-dish")
    public String dish(Model model){
        model.addAttribute("addDish", true);
        return "admin";
    }

    @PostMapping("/admin-dish")
    public String getDish(Model model,
                          @ModelAttribute("name") String name,
                          @ModelAttribute("radius") Integer radius,
                          @ModelAttribute("receipt") String receipt,
                          @ModelAttribute("weight") Float weight,
                          @ModelAttribute("cost") Integer cost,
                          @RequestParam("image")MultipartFile file) throws IOException {
        byte[] image = Base64.getEncoder().encode(file.getBytes());
        Dish dish = new Dish(name, cost, radius, weight, receipt, image);
        dishService.saveDish(dish);
        model.addAttribute("errorSetting", "true");
        model.addAttribute("message", "Блюдо сохранено");
        return "admin";
    }

    @GetMapping("/admin-emp")
    public String emp(Model model){
        model.addAttribute("addEmp", true);
        return "admin";
    }

    @PostMapping("/admin-emp")
    public String getEmp(Model model,
                         @ModelAttribute("nameE") String name,
                         @ModelAttribute("post") String post,
                         @ModelAttribute("salary") Integer salary){
        Worker worker = new Worker(name, post, salary);
        workerService.saveEmployer(worker);
        model.addAttribute("errorSetting", "true");
        model.addAttribute("message", "Сотрудник сохранен");
        return "admin";
    }

}
