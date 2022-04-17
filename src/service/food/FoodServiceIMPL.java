package service.food;

import config.ConfigReadAndWriteFile;
import model.Food;

import java.util.List;

public class FoodServiceIMPL implements IFoodService {

    public static String PATH = "D:\\CODEGYM-C0222I1\\Module 2\\Java Project\\CASE_STUDY\\src\\data\\food.txt";
    public static List<Food> foods = new ConfigReadAndWriteFile<Food>().readFromFile(PATH);


    @Override
    public List<Food> findAll() {
        new ConfigReadAndWriteFile<Food>().writeToFile(PATH, foods);
        return foods;
    }

    @Override
    public void save(Food food) {
        new ConfigReadAndWriteFile<Food>().writeToFile(PATH, foods);
        foods.add(food);
    }

    @Override
    public Food findById(int id) {
        for (int i = 0; i < foods.size(); i++) {
            if (id == foods.get(i).getId()) {
                return foods.get(i);
            }
        }
        return null;
    }

    public Food findByName(String name) {
        for (int i = 0; i < foods.size(); i++) {
            if (name.equalsIgnoreCase(foods.get(i).getName())) {
                return foods.get(i);
            }
        }
        return null;
    }
}
