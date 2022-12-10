package ru.nnboo.NNBOO.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nnboo.NNBOO.entity.Dish;
import ru.nnboo.NNBOO.repository.DishJPA;

import java.util.List;

@Service
public class DishService {
    @Autowired
    private DishJPA dishJPA;

    public List<Dish> getAllDishes(){
        return dishJPA.findAll();
    }

    public void saveDish(Dish dish){
        dishJPA.save(dish);
    }
}
