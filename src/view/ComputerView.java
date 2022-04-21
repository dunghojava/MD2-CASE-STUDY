package view;

import controller.ComputerController;

import controller.TotalRevenueController;
import model.Computer;
import model.Food;
import model.Revenue;
import model.Role;
import service.computer.ComputerServiceIMPL;
import service.food.FoodServiceIMPL;
import service.totalRevenue.TotalRevenueIMPL;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ComputerView {
    Scanner scanner = new Scanner(System.in);
    ComputerController computerController = new ComputerController();
    List<Computer> computerList = ComputerServiceIMPL.computers;
    List<Food> foodList = FoodServiceIMPL.foods;
    TotalRevenueIMPL totalRevenueIMPL = new TotalRevenueIMPL();

    public void formCreateComputer(String username, int id, Role.RoleNameUser role) {
        while (true) {
            int idComputer;
            if (computerList.size() == 0) {
                idComputer = 1;
            } else {
                idComputer = computerList.get(computerList.size() - 1).getId() + 1;
            }
            System.out.println("ENTER THE NAME COMPUTER: ");
            String name = scanner.nextLine();
            boolean checkRole = true;
            Role.RoleNameComputer roleNameComputer = null;
            while (checkRole) {
                System.out.println("ENTER THE ROLE COMPUTER: ");
                System.out.println("NORMAL / VIP");
                System.out.println("==============");
                String nameRole = scanner.nextLine();
                if (nameRole.equalsIgnoreCase("normal")) {
                    roleNameComputer = Role.RoleNameComputer.NORMAL;
                    checkRole = false;
                } else if (nameRole.equalsIgnoreCase("vip")) {
                    roleNameComputer = Role.RoleNameComputer.VIP;
                    checkRole = false;
                } else {
                    System.err.println("PLEASE CHOOSE ROLE FOR COMPUTER");
                    System.err.println("===============================");
                }
            }
            Computer computer = new Computer(idComputer, name, roleNameComputer);
            computerController.createComputer(computer);
            System.out.println("YOU HAVE CREATE SUCCESSFULLY " + computer);
            System.out.println("=========================================");
            System.out.println("ENTER ANY KEY TO CONTINUE CREATE COMPUTER OR ENTER QUIT TO COMEBACK MENU: ");
            String backMenu = scanner.nextLine();
            if (backMenu.equalsIgnoreCase("quit")) {
                new Main(username, id, role);
            }
        }
    }

    public void onOffComputer(String username, int id, Role.RoleNameUser roleNameUser) {
        while (true) {
            System.out.println("ENTER COMPUTER'S ID WANT TO SET STATUS");
            int idComputer = scanner.nextInt();
            scanner.nextLine();
            if (computerController.findById(idComputer) == null) {
                System.out.println("NO ID IN THE LIST");
                new Main(username, id, roleNameUser);
            } else {
                String setStatus = "";
                do {
                    System.out.println("SET STATUS: | ON / OFF |");
                    setStatus = scanner.nextLine();
                    if (setStatus.equalsIgnoreCase("on") && computerController.findById(idComputer).getStatus() == false) {
                        computerController.findById(idComputer).setStatus(true);
                    } else if (setStatus.equalsIgnoreCase("off") && computerController.findById(idComputer).getStatus() == true) {
                        computerController.findById(idComputer).setStatus(false);
                    } else {
                        System.err.println("SOMETHING WRONG!! TRY AGAIN!!");
                    }
                } while (!setStatus.equalsIgnoreCase("on") && !setStatus.equalsIgnoreCase("off"));
            }
            System.out.println("=========================================");
            System.out.println("ENTER ANY KEY TO CONTINUE SET STATUS COMPUTER OR ENTER QUIT TO COMEBACK MENU: ");
            String backMenu = scanner.nextLine();
            if (backMenu.equalsIgnoreCase("quit")) {
                new Main(username, id, roleNameUser);
            }
        }
    }

    public void checkBill(String username, int id, Role.RoleNameUser roleNameUser) {
        while (true) {
            System.out.println("ENTER COMPUTER'S ID YOU WANT TO CHECK");
            int idComputer = 0;
            try {
                idComputer = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("SOMETHING WRONG!! TRY AGAIN!!");
                checkBill(username, id, roleNameUser);
            }
            if (computerController.findById(idComputer) == null) {
                System.err.println("NO ID IN THE LIST");
                new Main(username, id, roleNameUser);
            } else {
                double bill = computerController.findById(idComputer).checkTotalPrice();
                double time = 0;
                double totalPriceComputer = 0;
                if (computerController.findById(idComputer).getStatus() == true) {
                    System.out.println("SERVICES BILL " + computerController.findById(idComputer).getName().toUpperCase() + " : " + bill + "VND");
                    computerController.findById(idComputer).setStatus(false);
                    time = (computerController.findById(idComputer).getEndTime() - computerController.findById(idComputer).getStartTime()) / Math.pow(10, 9);
                    totalPriceComputer = Math.ceil(bill + time * computerController.findById(idComputer).getRole().getRoleNameComputer() / 3600 * roleNameUser.getRoleNameUser());
                    System.out.println("TOTAL BILL = " + totalPriceComputer + "VND");
                    System.out.println("USED TIME: " + Math.ceil(time) + "s ");
                } else if (computerController.findById(idComputer).getEndTime() > computerController.findById(idComputer).getStartTime()) {
                    time = (computerController.findById(idComputer).getEndTime() - computerController.findById(idComputer).getStartTime()) / Math.pow(10, 9);
                    totalPriceComputer = Math.ceil(bill + time * computerController.findById(idComputer).getRole().getRoleNameComputer() / 3600 * roleNameUser.getRoleNameUser());
                    System.out.println("TOTAL BILL = " + totalPriceComputer + "VND");
                }
                computerController.findById(idComputer).setFoodList(null);
                computerController.findById(idComputer).setStartTime(0);
                computerController.findById(idComputer).setEndTime(0);
                Revenue revenue = new Revenue(totalPriceComputer, computerController.findById(idComputer).getName());
                totalRevenueIMPL.save(revenue);
            }
            System.out.println("=========================================");
            System.out.println("ENTER ANY KEY TO CONTINUE CHECK BILL OR ENTER QUIT TO COMEBACK MENU: ");
            String backMenu = scanner.nextLine();
            if (backMenu.equalsIgnoreCase("quit")) {
                new Main(username, id, roleNameUser);
            }
        }
    }

    public void newCheckTotalBill(String username, int id, Role.RoleNameUser roleNameUser) {
        while (true) {
            TotalRevenueController totalRevenueController = new TotalRevenueController();
            double sum = 0;
            double total = 0;
            int phanNguyen = totalRevenueIMPL.findAll().size() / 3;
            int phanDu = totalRevenueIMPL.findAll().size() % 3;
            int k = 0;
            int count = 1;
            if (phanNguyen > 0) {
                for (int i = 0; i < phanNguyen; i++) {
                    for (int j = 0; j < 3; j++) {
                        System.out.println(totalRevenueIMPL.findAll().get(k));
                        sum += totalRevenueController.showListRevenue().get(k).getPrice();
                        k++;
                    }
                    System.out.println("BILL " + count + " = " + sum + "VND");
                    System.out.println("----------------------");
                    count++;
                    total += sum;
                    sum = 0;
                }
            }
            if (phanDu > 0) {
                for (int i = 0; i < phanDu; i++) {
                    System.out.println(totalRevenueIMPL.findAll().get(k));
                    sum += totalRevenueController.showListRevenue().get(k).getPrice();
                    k++;
                }
                System.out.println("BILL " + count + " = " + sum + "VND");
                System.out.println("----------------------");
            }
            total += sum;
            System.out.println("TOTAL REVENUE = " + total + "VND");

            System.out.println("=========================================");
            System.out.println("ENTER ANY KEY TO CONTINUE OR ENTER QUIT TO COMEBACK MENU: ");
            String backMenu = scanner.nextLine();
            if (backMenu.equalsIgnoreCase("quit")) {
                new Main(username, id, roleNameUser);
            }
        }
    }

    public void showListComputer(String username, int id, Role.RoleNameUser role) {
        System.out.println(computerController.showListComputer());
        System.out.println("== ENTER QUIT TO COME BACK MENU ==");
        String backMenu = scanner.nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            new Main(username, id, role);
        }
    }

    public void addServiceComputer(String username, int id, Role.RoleNameUser roleNameUser) {
        while (true) {
            System.out.println("ENTER THE ID COMPUTER TO ADD SERVICE");
            int idComputer = scanner.nextInt();
            scanner.nextLine();
            int idValue = 0;
            int countIdComputer = 0;
            int count = 0;
            System.out.println("=====================");
            for (int i = 0; i < computerList.size(); i++) {
                if (computerList.get(i).getId() == idComputer) {
                    idValue = i;
                    countIdComputer++;
                }
            }
            if (countIdComputer == 0) {
                System.err.println("NO ID IN THE LIST");
                new ComputerView().addServiceComputer(username, id, roleNameUser);
            } else if (computerController.findById(idComputer).getStatus()) {
                String checkAnswer = "";
                String checkAnswer2 = "";
                System.out.println("ENTER THE NAME FOOD TO ADD SERVICE TO: " + computerList.get(idValue).toString());
                System.out.println("=================================");
                String nameAddService = scanner.nextLine();
                System.out.println("==================");
                if (foodList.size() == 0) {
                    System.out.println("CURRENTLY UNAVAILABLE FOOD IN THE LIST");
                    do {
                        System.out.println("DO YOU WANT TO CREATE A NEW ONE?!");
                        System.out.println("| YES / NO |");
                        checkAnswer2 = scanner.nextLine();
                        if (checkAnswer2.equalsIgnoreCase("yes")) {
                            if (roleNameUser.equals(Role.RoleNameUser.ADMIN)) {
                                new FoodView().formCreateFood(username, id, roleNameUser);
                            } else {
                                System.err.println("CHƯA LÊN ADMIN THÌ CHƯA CÓ TUỔI!!");
                                new Main(username, id, roleNameUser);
                            }
                        }
                        if (checkAnswer2.equalsIgnoreCase("no")) {
                            new Main(username, id, roleNameUser);
                        } else {
                            System.err.println("SOMETHING WRONG!! TRY AGAIN!!");
                        }
                    } while (!checkAnswer2.equalsIgnoreCase("yes") || !checkAnswer2.equalsIgnoreCase("no"));
                } else {
                    List<Food> foods = new ArrayList<>();
                    if (computerList.get(idValue).getFoodList() != null) {
                        foods = computerList.get(idValue).getFoodList();
                    }
                    for (int i = 0; i < foodList.size(); i++) {
                        if (nameAddService.equalsIgnoreCase(foodList.get(i).getName())) {
                            System.out.println("SUCCESSFULLY ADD FOOD TO COMPUTER!!");
                            System.out.println("==================");
                            System.out.println(foodList.get(i));
                            System.out.println("==================");
                            foods.add(foodList.get(i));
                            computerController.findById(idComputer).setFoodList(foods);
                            System.out.println("check COMPUTER add food ===========> " + computerList.get(idValue));
                            count++;
                        }

                    }
                    if (count == 0) {
                        do {
                            System.out.println("THIS ITEM IS NOT IN THE LIST, DO YOU WANT TO CREATE A NEW ONE?!");
                            System.out.println("| YES / NO |");
                            checkAnswer = scanner.nextLine();
                            if (checkAnswer.equalsIgnoreCase("yes")) {
                                new FoodView().formCreateFood(username, id, roleNameUser);
                            }
                            if (checkAnswer.equalsIgnoreCase("no")) {
                                new Main(username, id, roleNameUser);
                            } else {
                                System.err.println("SOMETHING WRONG!! TRY AGAIN!!");
                                System.err.println("=============================");
                            }
                        } while (!checkAnswer.equalsIgnoreCase("yes") || !checkAnswer.equalsIgnoreCase("no"));
                    }
                }
            } else {
                System.err.println("COMPUTER IS OFF!! PLEASE TURN ON COMPUTER!!");
                new ComputerView().onOffComputer(username, id, roleNameUser);
            }
            System.out.println("=========================================");
            System.out.println("ENTER ANY KEY TO CONTINUE ADD SERVICE OR ENTER QUIT TO COMEBACK MENU: ");
            System.out.println("============================");
            String backMenu = scanner.nextLine();
            if (backMenu.equalsIgnoreCase("quit")) {
                new Main(username, id, roleNameUser);
            }
        }
    }

    public void removeComputer(String username, int id, Role.RoleNameUser role) {
        while (true) {
            System.out.println("== ENTER THE ID TO REMOVE ==");
            int idRemove = scanner.nextInt();
            scanner.nextLine();
            int idValue = 0;
            System.out.println("====================");
            for (int i = 0; i < computerList.size(); i++) {
                if (computerList.get(i).getId() == idRemove) {
                    idValue = i;
                }
            }
            if (idValue == 0) {
                System.out.println("NO ID IN THE LIST");
            } else {
                System.out.println("YOU HAVE SUCCESSFULLY DELETED " + computerList.get(idValue));
                computerList.remove(idValue);
                computerController.showListComputer();
            }
            System.out.println("=========================================");
            System.out.println("ENTER ANY KEY TO CONTINUE CREATE COMPUTER OR ENTER QUIT TO COMEBACK MENU: ");
            String backMenu = scanner.nextLine();
            if (backMenu.equalsIgnoreCase("quit")) {
                new Main(username, id, role);
            }
        }
    }

    public void changeInformationComputer(String username, int id, Role.RoleNameUser role) {
        while (true) {
            System.out.println("== ENTER THE ID TO CHANGE ==");
            String nameRole = "";
            Role.RoleNameComputer roleNameComputer = null;
            int idChange = scanner.nextInt();
            scanner.nextLine();
            System.out.println("===============");
            if (computerController.findById(idChange) == null) {
                System.out.println("NO ID IN THE LIST");
            } else {
                Computer oldComputer = new Computer(computerController.findById(idChange).getId(), computerController.findById(idChange).getName(), computerController.findById(idChange).getRole());
                System.out.println("CHECK OLD COMPUTER =============> " + oldComputer);
                System.out.println("ENTER THE NAME TO CHANGE: ");
                computerController.findById(idChange).setName(scanner.nextLine());
                scanner.nextLine();
                boolean changeRole = true;
                while (changeRole) {
                    System.out.println("CHANGE ROLE NAME COMPUTER: ");
                    System.out.println("NORMAL / VIP");
                    System.out.println("==============");
                    nameRole = scanner.nextLine();
                    if (nameRole.equalsIgnoreCase("normal")) {
                        roleNameComputer = Role.RoleNameComputer.NORMAL;
                        changeRole = false;
                    } else if (nameRole.equalsIgnoreCase("vip")) {
                        roleNameComputer = Role.RoleNameComputer.VIP;
                        changeRole = false;
                    } else {
                        System.out.println("PLEASE CHOOSE ROLE FOR COMPUTER");
                        System.out.println("===============================");
                    }
                }
                computerController.findById(idChange).setRole(roleNameComputer);
                System.out.println("===== SUCCESSFULLY CHANGE ROLE =====");
                System.out.println(oldComputer.toString() + " ====> " + computerController.findById(idChange));
                System.out.println(" ================================= ");
            }
            System.out.println("=========================================");
            System.out.println("ENTER ANY KEY TO CONTINUE CREATE COMPUTER OR ENTER QUIT TO COMEBACK MENU: ");
            String backMenu = scanner.nextLine();
            if (backMenu.equalsIgnoreCase("quit")) {
                new Main(username, id, role);
            }
        }
    }
}
