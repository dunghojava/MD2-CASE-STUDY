package controller;

import model.Food;
import service.food.FoodServiceIMPL;

import java.util.List;

public class FoodController {
    FoodServiceIMPL foodServiceIMPL = new FoodServiceIMPL();

    public List<Food> showListFood() {
        return foodServiceIMPL.findAll();
    }

    public void createFood(Food food) {
        foodServiceIMPL.save(food);
    }

    public Food findById(int id) {
        return foodServiceIMPL.findById(id);
    }

    public Food findByName(String name) {
        return foodServiceIMPL.findByName(name);
    }
}
