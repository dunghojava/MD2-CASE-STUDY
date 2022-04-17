package view;

import controller.FoodController;
import model.Food;
import model.Role;
import service.food.FoodServiceIMPL;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FoodView {
    Scanner scanner = new Scanner(System.in);
    FoodController foodController = new FoodController();
    List<Food> foodList = FoodServiceIMPL.foods;

    public Food formCreateFood(String username, int id, Role.RoleNameUser roleNameUser) {
        while (true) {
            int idFood;
            int idValue = 0;
            String nameFood = "";
            int count = 0;
            if (foodList.size() == 0) {
                idFood = 1;
            } else {
                idFood = foodList.get(foodList.size() - 1).getId() + 1;
            }
            do {
                System.out.println("ENTER THE NAME FOOD: ");
                nameFood = scanner.nextLine();
                System.out.println("check name food ===== " + nameFood);
                if (foodList.size() == 0) {
                    break;
                }
                if (foodList.size() != 0) {
                    for (int i = 0; i < foodList.size(); i++) {
                        if (nameFood.equalsIgnoreCase(foodList.get(i).getName())) {
                            idValue = i;
                            count++;
                        }
                    }
                    if (count != 0) {
                        String checkAnswer = "";
                        do {
                            System.out.println("FOOD IS EXISTED! DO YOU WANT CREATE AGAIN?!");
                            System.out.println("-YES/NO");
                            checkAnswer = scanner.nextLine();
                            if (checkAnswer.equalsIgnoreCase("no")) {
                                new Main(username, id, roleNameUser);
                            } else if (!checkAnswer.equalsIgnoreCase("yes")) {
                                System.out.println("SOMETHING WRONG!! TRY AGAIN!!");
                            } else {
                                new FoodView().formCreateFood(username, id, roleNameUser);
                            }
                        } while (!checkAnswer.equalsIgnoreCase("yes") && !checkAnswer.equalsIgnoreCase("no"));
                    }
                }
            } while (nameFood.equalsIgnoreCase(foodList.get(idValue).getName()));
            System.out.println("ENTER THE PRICE OF FOOD: ");
            int price = scanner.nextInt();
            scanner.nextLine();
            Food food = new Food(idFood, nameFood, price);
            foodController.createFood(food);
            System.out.println("YOU HAVE CREATE SUCCESSFULLY: " + food);
            System.out.println("=========================================");
            System.out.println("ENTER ANY KEY TO CONTINUE CREATE FOOD OR ENTER QUIT TO COMEBACK MENU: ");
            String backMenu = scanner.nextLine();
            if (backMenu.equalsIgnoreCase("quit")) {
                new Main(username, id, roleNameUser);
            }
        }
    }

    public void showListFood(String username, int id, Role.RoleNameUser role) {
        System.out.println(foodController.showListFood());
        System.out.println("== ENTER QUIT TO COME BACK MENU ==");
        String backMenu = scanner.nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            new Main(username, id, role);
        }
    }
}
