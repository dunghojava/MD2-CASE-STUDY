package view;

import model.Role;

import java.util.Scanner;

public class Main {
    Scanner scanner = new Scanner(System.in);

    public Main() {
        String chooseMenu = "";
        boolean check = true;
        while (check) {
            System.out.println("========= QUÁN NET =========");
            System.out.println("1. REGISTER");
            System.out.println("============================");
            System.out.println("2. LOGIN");
            System.out.println("============================");
            chooseMenu = scanner.nextLine();
            switch (chooseMenu) {
                case "1":
                    new UserView().formCreateUser();
                case "2":
                    new UserView().formLoginUser();
            }
            System.out.println("SOMETHING WRONG!! TRY AGAIN!!");
        }
    }

    public Main(String username, int id, Role.RoleNameUser role) {
        System.out.println("======== MENU NET ========");
        System.out.println("| USER_NAME | : | " + username + " |");
        System.out.println("| ID | : | " + id + " |");
        System.out.println("| ROLE | : | " + role + " |");
        System.out.println("1. SHOW LIST COMPUTER");
        System.out.println("2. ADD COMPUTER |ADMIN|");
        System.out.println("3. EDIT INFORMATION COMPUTER |ADMIN|");
        System.out.println("4. REMOVE COMPUTER |ADMIN|");
        System.out.println("5. ADD SERVICE |ADMIN|");
        System.out.println("6. ADD SERVICE FOR COMPUTER");
        System.out.println("7. CHECK BILL |ADMIN|");
        System.out.println("8. ACCOUNT MANAGEMENT |ADMIN|");
        System.out.println("9. TURNOVER |ADMIN|");
        System.out.println("10. TURN ON/OFF COMPUTER");
        System.out.println("11. SHOW LIST FOOD");
        System.out.println("12. NEW CHECK TOTAL BILL");
        System.out.println("0. LOGOUT");
        System.out.println("===============================");
        int chooseMenu = Integer.parseInt(scanner.nextLine());
        switch (chooseMenu) {
            case 1:
                new ComputerView().showListComputer(username, id, role);
            case 2:
                if (role.equals(Role.RoleNameUser.ADMIN)) {
                    new ComputerView().formCreateComputer(username, id, role);
                } else {
                    System.err.println("CHƯA LÊN ADMIN THÌ CHƯA CÓ TUỔI!!");
                    new Main(username, id, role);
                }
            case 3:
                if (role.equals(Role.RoleNameUser.ADMIN)) {
                    new ComputerView().changeInformationComputer(username, id, role);
                } else {
                    System.err.println("CHƯA LÊN ADMIN THÌ CHƯA CÓ TUỔI!!");
                    new Main(username, id, role);
                }
            case 4:
                if (role.equals(Role.RoleNameUser.ADMIN)) {
                    new ComputerView().removeComputer(username, id, role);
                } else {
                    System.err.println("CHƯA LÊN ADMIN THÌ CHƯA CÓ TUỔI!!");
                    new Main(username, id, role);
                }
            case 5:
                if (role.equals(Role.RoleNameUser.ADMIN)) {
                    new FoodView().formCreateFood(username, id, role);
                } else {
                    System.err.println("CHƯA LÊN ADMIN THÌ CHƯA CÓ TUỔI!!");
                    new Main(username, id, role);
                }
            case 6:
                new ComputerView().addServiceComputer(username, id, role);
            case 7:
                new ComputerView().checkBill(username, id, role);
            case 8:
                if (role.equals(Role.RoleNameUser.ADMIN)) {
                    new UserView().accountManagement(username, id, role);
                } else {
                    System.err.println("CHƯA LÊN ADMIN THÌ CHƯA CÓ TUỔI!!");
                    new Main(username, id, role);
                }
            case 9:
                if (role.equals(Role.RoleNameUser.ADMIN)) {
                    new ComputerView().checkTotalBill(username, id, role);
                } else {
                    System.err.println("CHƯA LÊN ADMIN THÌ CHƯA CÓ TUỔI!!");
                    new Main(username, id, role);
                }
            case 10:
                new ComputerView().onOffComputer(username, id, role);
            case 11:
                new FoodView().showListFood(username, id, role);
            case 12:
                if (role.equals(Role.RoleNameUser.ADMIN)) {
                    new ComputerView().newCheckTotalBill(username, id, role);
                } else {
                    System.err.println("CHƯA LÊN ADMIN THÌ CHƯA CÓ TUỔI!!");
                    new Main(username, id, role);
                }
            case 0:
                new Main();
        }
    }

    public static void main(String[] args) {
        new Main();
    }

}

