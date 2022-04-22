package view;

import model.Role;

import java.util.Scanner;

public class Main {
    Scanner scanner = new Scanner(System.in);

    public Main() {
        String chooseMenu = "";
        boolean check = true;
        while (check) {
            System.out.println("========= NET CỎ CỦA DŨNG =========");
            System.out.println("1. ĐĂNG KÝ");
            System.out.println("============================");
            System.out.println("2. ĐĂNG NHẬP");
            System.out.println("============================");
            chooseMenu = scanner.nextLine();
            switch (chooseMenu) {
                case "1":
                    new UserView().formCreateUser();
                case "2":
                    new UserView().formLoginUser();
            }
            System.err.println("SOMETHING WRONG!! TRY AGAIN!!");
        }
    }

    public Main(String username, int id, Role.RoleNameUser role) {
        System.out.println("======== MENU NET ========");
        System.out.println("| USER_NAME | : | " + username + " |");
        System.out.println("| ID | : | " + id + " |");
        System.out.println("| ROLE | : | " + role + " |");
        System.out.println("1. DANH SÁCH COMPUTER");
        System.out.println("2. THÊM COMPUTER |ADMIN|");
        System.out.println("3. THAY ĐỔI THÔNG TIN COMPUTER |ADMIN|");
        System.out.println("4. XÓA COMPUTER |ADMIN|");
        System.out.println("5. THÊM DỊCH VỤ |ADMIN|");
        System.out.println("6. THÊM DỊCH VỤ CHO COMPUTER");
        System.out.println("7. HÓA ĐƠN");
        System.out.println("8. QUẢN LÝ TÀI KHOẢN");
        System.out.println("9. DOANH THU |ADMIN|");
        System.out.println("10. BẬT / TẮT COMPUTER");
        System.out.println("11. DANH SÁCH DỊCH VỤ");
        System.out.println("0. ĐĂNG XUẤT");
        System.out.println("===============================");
        String chooseMenu = scanner.nextLine();
        switch (chooseMenu) {
            case "1":
                new ComputerView().showListComputer(username, id, role);
            case "2":
                if (role.equals(Role.RoleNameUser.ADMIN)) {
                    new ComputerView().formCreateComputer(username, id, role);
                } else {
                    System.err.println("CHƯA LÊN ADMIN THÌ CHƯA CÓ TUỔI!!");
                    new Main(username, id, role);
                }
            case "3":
                if (role.equals(Role.RoleNameUser.ADMIN)) {
                    new ComputerView().changeInformationComputer(username, id, role);
                } else {
                    System.err.println("CHƯA LÊN ADMIN THÌ CHƯA CÓ TUỔI!!");
                    new Main(username, id, role);
                }
            case "4":
                if (role.equals(Role.RoleNameUser.ADMIN)) {
                    new ComputerView().removeComputer(username, id, role);
                } else {
                    System.err.println("CHƯA LÊN ADMIN THÌ CHƯA CÓ TUỔI!!");
                    new Main(username, id, role);
                }
            case "5":
                if (role.equals(Role.RoleNameUser.ADMIN)) {
                    new FoodView().formCreateFood(username, id, role);
                } else {
                    System.err.println("CHƯA LÊN ADMIN THÌ CHƯA CÓ TUỔI!!");
                    new Main(username, id, role);
                }
            case "6":
                new ComputerView().addServiceComputer(username, id, role);
            case "7":
                new UserView().checkTotalBill(username, id, role);
            case "8":
                new UserView().accountManagement(username, id, role);
            case "9":
                if (role.equals(Role.RoleNameUser.ADMIN)) {
                    new ComputerView().newCheckTotalBill(username, id, role);
                } else {
                    System.err.println("CHƯA LÊN ADMIN THÌ CHƯA CÓ TUỔI!!");
                    new Main(username, id, role);
                }
            case "10":
                new ComputerView().onOffComputer(username, id, role);
            case "11":
                new FoodView().showListFood(username, id, role);
            case "0":
                new Main();
        }
    }


    public static void main(String[] args) {
        new Main();
    }

}

