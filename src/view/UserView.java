package view;

import controller.ComputerController;
import controller.UserController;
import model.Revenue;
import model.Role;
import model.User;
import service.totalRevenue.TotalRevenueIMPL;
import service.user.UserServiceIMPL;

import java.util.List;
import java.util.Scanner;

public class UserView {
    Scanner scanner = new Scanner(System.in);
    UserController userController = new UserController();
    List<User> userList = UserServiceIMPL.users;
    TotalRevenueIMPL totalRevenueIMPL = new TotalRevenueIMPL();
    ComputerController computerController = new ComputerController();
    UserServiceIMPL userServiceIMPL = new UserServiceIMPL();

    public void formCreateUser() {
        while (true) {
            int id;
            if (userList.size() == 0) {
                id = 1;
            } else {
                id = userList.get(userList.size() - 1).getId() + 1;
            }
            System.out.println("ENTER THE USERNAME: ");
            String name = scanner.nextLine();
            if (userList.size() != 0) {
                for (int i = 0; i < userList.size(); i++) {
                    if (name.equalsIgnoreCase(userList.get(i).getUsername())) {
                        System.out.println("USERNAME IS ALREADY!! TRY AGAIN!!");
                        new UserView().formCreateUser();
                    }
                }
            }
            System.out.println("ENTER THE PASSWORD: ");
            String password = scanner.nextLine();
            System.out.println("ENTER THE ROLE: ");
            System.out.println("1. ADMIN");
            System.out.println("2. USER");
            int chooseRole = scanner.nextInt();
            User user = new User();
            user.setUsername(name);
            user.setPassword(password);
            user.setId(id);
            switch (chooseRole) {
                case 1:
                    user.setRole(Role.RoleNameUser.ADMIN);
                    break;
                case 2:
                    user.setRole(Role.RoleNameUser.USER);
                    break;
            }
            userController.createUser(user);
            System.out.println("REGISTRATION SUCCESSFUL!!");
            System.out.println("YOUR ID IS: " + id);
            System.out.println("YOUR USERNAME IS: " + name);
            System.out.println("YOUR PASSWORD IS: " + password);
            new Main();
        }
    }

    public void changePassword(String username, int id, Role.RoleNameUser roleNameUser) {
        while (true) {
            System.out.println("ENTER NEW PASSWORD: ");
            String newPassword = scanner.nextLine();
            UserController userController = new UserController();
            userController.changePassword(id, newPassword);
            System.out.println("SUCCESSFULLY CHANGE PASSWORD!!!");
            System.out.println("NEW PASSWORD: " + userController.findById(id).getPassword());
            System.out.println("=========================================");
            System.out.println("ENTER ANY KEY TO CONTINUE CHANGE PASSWORD OR ENTER QUIT TO COMEBACK MENU: ");
            String backMenu = scanner.nextLine();
            if (backMenu.equalsIgnoreCase("quit")) {
                new Main(username, id, roleNameUser);
            }
        }
    }

    public void vaiThuLinhTinh(String username, int id, Role.RoleNameUser roleNameUser) {
        while (true) {
            System.out.println((char) 27 + "[34m");
            System.out.println("======= | V??I TH??? LINH TINH | =======");
            System.out.println("1. N???P TI???N");
            System.out.println("2. KI???M TRA TH??? H???NG T??I KHO???N");
            System.out.println("3. ROLE GAME");
            System.out.println("0. BACK");
            System.out.println("=========================================");
            String choose = scanner.nextLine();
            switch (choose) {
                case "1":
                    new UserView().napTien(username, id, roleNameUser);
                case "2":
                    new UserView().checkTaiKhoan(username, id, roleNameUser);
                case "3":
                    System.out.println((char) 27 + "[35m");
                    System.out.println("====== SOS ======");
                    System.out.println(" ??ANG B???O TR?? :))");
                    System.out.println("=================");
                case "0":
                    new UserView().accountManagement(username, id, roleNameUser);
            }


            System.out.println((char) 27 + "[39m");
        }
    }

    public void checkTaiKhoan(String username, int id, Role.RoleNameUser roleNameUser) {
        while (true) {
            System.out.println((char) 27 + "[34m");
            double tongNap = userController.findById(id).getTienNap();
            if (tongNap < 100000) {
                System.out.println((char) 27 + "[39m" + "S??? TI???N HI???N T???I L??: " + tongNap + "vn??");
                System.out.println((char) 27 + "[39m" + "-------------------------------");
                System.out.println((char) 27 + "[39m" + " RANK C??I B???P CH??? C??N G?? N???A!!!");
                System.out.println((char) 27 + "[39m" + "-------------------------------");
            } else if (tongNap >= 100000 && tongNap < 200000) {
                System.out.println((char) 27 + "[33m" + "S??? TI???N HI???N T???I L??: " + tongNap + "vn??");
                System.out.println((char) 27 + "[33m" + "-------------------------------");
                System.out.println((char) 27 + "[33m" + " RANK HI???N T???I C???A B???N L?? V??NG");
                System.out.println((char) 27 + "[33m" + "-------------------------------");
            } else if (tongNap >= 200000 && tongNap < 500000) {
                System.out.println((char) 27 + "[32m" + "S??? TI???N HI???N T???I L??: " + tongNap + "vn??");
                System.out.println((char) 27 + "[32m" + "----------------------------------");
                System.out.println((char) 27 + "[32m" + " RANK HI???N T???I C???A B???N L?? L???C B???O");
                System.out.println((char) 27 + "[32m" + "----------------------------------");
            } else if (tongNap >= 500000) {
                System.out.println((char) 27 + "[36m" + "S??? TI???N HI???N T???I L??: " + tongNap + "vn??");
                System.out.println((char) 27 + "[36m" + "---------------------------------------------------------");
                System.out.println((char) 27 + "[36m" + "           RANK HI???N T???I C???A B???N L?? KIM C????NG");
                System.out.println((char) 27 + "=== B???N L?? 1 TRONG S??? NH???NG KH??CH H??NG VIP NH???T ??? ????Y!!! ===");
                System.out.println((char) 27 + "[36m" + "---------------------------------------------------------");
            }
            System.out.println("=========================================");
            System.out.println("ENTER QUIT TO COMEBACK: ");
            String backMenu = scanner.nextLine();
            if (backMenu.equalsIgnoreCase("quit")) {
                new UserView().vaiThuLinhTinh(username, id, roleNameUser);
            }
        }
    }

    public void checkBill(String username, int id, Role.RoleNameUser roleNameUser) {
        try {
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
                double billService = Math.ceil(computerController.findById(idComputer).checkTotalPrice());
                double time = 0;
                double totalPrice = 0;
                if (computerController.findById(idComputer).getStatus() == true && userController.findById(id).getTienNap() != 0) {
                    computerController.findById(idComputer).setStatus(false);
                    time = Math.ceil((computerController.findById(idComputer).getEndTime() - computerController.findById(idComputer).getStartTime()) / Math.pow(10, 9));
                    double priceComputer = Math.ceil(time * computerController.findById(idComputer).getRole().getRoleNameComputer() * userController.findById(id).getRole().getRoleNameUser() * userController.findById(id).getRoleRankUser().getRoleRankUser() / 3600);
                    totalPrice = Math.ceil(billService + priceComputer);
                    System.out.println("--------------------------------------");
                    System.out.println("TI???N D???CH V???: " + billService + "vn??");
                    System.out.println("TI???N GI??? CH??I: " + priceComputer + "vn??");
                    System.out.println("TH???I GIAN S??? D???NG M??Y: " + time + "s");
                    System.out.println("T???NG TI???N C???N THANH TO??N: " + totalPrice + "vn??");
                    System.out.println("--------------------------------------");
                    System.out.println("S??? TI???N HI???N C?? TRONG T??I KHO???N: " + userController.findById(id).getTienNap() + "vn??");
                    System.out.println("--------------------------------------");
                    System.out.println("1. THANH TO??N");
                    System.out.println("2. N???P TI???N");
                    System.out.println("0. BACK");
                    int choose = Integer.parseInt(scanner.nextLine());
                    switch (choose) {
                        case 1:
                            if (userController.findById(id).getTienNap() >= totalPrice) {
                                double ceil = userController.findById(id).getTienNap() - totalPrice;
                                userController.findById(id).setTienNap(ceil);
                                userController.showListUser();
                                computerController.findById(idComputer).setFoodList(null);
                                computerController.showListComputer();
                                System.out.println("--------------------------------");
                                System.out.println("B???N ???? THANH TO??N TH??NH C??NG!!!");
                                System.out.println("S??? TI???N C??N L???I TRONG T??I KHO???N L??: " + userController.findById(id).getTienNap() + "vn??");
                                System.out.println("--------------------------------");
                                new UserView().setRoleRankUser(id);
                            } else if (userController.findById(id).getTienNap() < totalPrice) {
                                System.out.println("--------------------------------");
                                System.err.println("T??I KHO???N C???A B???N KH??NG ????? ????? THANH TO??N!! VUI L??NG N???P TH??M!!");
                                System.out.println("--------------------------------");
                            }
                            break;
                        case 2:
                            new UserView().napTien(username, id, roleNameUser);
                        case 0:
                            new Main(username, id, roleNameUser);
                    }
                } else if (userController.findById(id).getTienNap() == 0) {
                    System.err.println("T??I KHO???N ??ANG TR???NG TR??N!! N???P L???N ?????U ??I B???N!!");
                } else {
                    System.err.println("SOMETHING WRONG!! TRY AGAIN!!");
                }
                String backMenu = "";
                do {
                    System.out.println("=========================");
                    System.out.println("ENTER QUIT TO BACK MENU");
                    backMenu = scanner.nextLine();
                    if (backMenu.equalsIgnoreCase("quit")) {
                        new Main(username, id, roleNameUser);
                    }
                } while (!backMenu.equalsIgnoreCase("quit"));
            }
        } catch (NullPointerException e) {
            System.err.println("--------------------------------------");
            System.err.println("CH??A N???P TI???N ???? ????I THANH TO??N ???????");
            System.err.println("--------------------------------------");
            new UserView().checkTotalBill(username, id, roleNameUser);
        }
    }

    public void setRoleRankUser(int id) {
        if (userController.findById(id).getTienNap()<100000) {
            userController.findById(id).setRoleRankUser(Role.RoleRankUser.SILVER);
        } else if (userController.findById(id).getTienNap()<200000) {
            userController.findById(id).setRoleRankUser(Role.RoleRankUser.GOLD);
        } else if (userController.findById(id).getTienNap()<500000) {
            userController.findById(id).setRoleRankUser(Role.RoleRankUser.EMERALD);
        } else if (userController.findById(id).getTienNap()>=500000){
            userController.findById(id).setRoleRankUser(Role.RoleRankUser.DIAMOND);
        }
    }

    public void napTien(String username, int id, Role.RoleNameUser roleNameUser) {
        while (true) {
            System.out.println((char) 27 + "[34m" + "----------------------------------");
            System.out.println((char) 27 + "[39m" + "RANK C??I B???P (S??? D?? D?????I 100K)");
            System.out.println((char) 27 + "[33m" + "RANK V??NG (S??? D?? T??? 100K)");
            System.out.println((char) 27 + "[32m" + "RANK L???C B???O (S??? D?? T??? 200K)");
            System.out.println((char) 27 + "[36m" + "RANK KIM C????NG (S??? D?? T??? 500K)");
            System.out.println((char) 27 + "[34m" + "----------------------------------");
            double tongNap = userController.findById(id).getTienNap();
            boolean answer = true;
            do {
                System.out.println("NH???P S??? TI???N MU???N N???P: ");
                double tienNap = Integer.parseInt(scanner.nextLine());
                Revenue revenue = new Revenue(tienNap, username);
                totalRevenueIMPL.save(revenue);
                tongNap += tienNap;
                userController.findById(id).setTienNap(tongNap);
                userController.showListUser();
                String choose = "";
                do {
                    System.out.println("B???N C?? MU???N N???P TH??M KH??NG?!");
                    System.out.println("YES / NO");
                    choose = scanner.nextLine();
                    if (choose.equalsIgnoreCase("yes")) {
                        answer = true;
                    } else if (choose.equalsIgnoreCase("no")) {
                        answer = false;
                    } else {
                        System.err.println("C?? G?? ???? SAI SAI!! VUI L??NG NH???P L???I!!");
                    }
                } while (!choose.equalsIgnoreCase("yes") && !choose.equalsIgnoreCase("no"));
            } while (answer);
            if (tongNap < 100000) {
                userController.findById(id).setRoleRankUser(Role.RoleRankUser.SILVER);
                System.out.println((char) 27 + "[39m" + "-------------------------------");
                System.out.println((char) 27 + "[39m" + " RANK C??I B???P CH??? C??N G?? N???A!!!");
                System.out.println((char) 27 + "[39m" + "-------------------------------");
            } else if (tongNap >= 100000 && tongNap < 200000) {
                userController.findById(id).setRoleRankUser(Role.RoleRankUser.GOLD);
                System.out.println((char) 27 + "[33m" + "-------------------------------");
                System.out.println((char) 27 + "[33m" + " RANK HI???N T???I C???A B???N L?? V??NG");
                System.out.println((char) 27 + "[33m" + "-------------------------------");
            } else if (tongNap >= 200000 && tongNap < 500000) {
                userController.findById(id).setRoleRankUser(Role.RoleRankUser.EMERALD);
                System.out.println((char) 27 + "[32m" + "----------------------------------");
                System.out.println((char) 27 + "[32m" + " RANK HI???N T???I C???A B???N L?? L???C B???O");
                System.out.println((char) 27 + "[32m" + "----------------------------------");
            } else if (tongNap >= 500000) {
                userController.findById(id).setRoleRankUser(Role.RoleRankUser.DIAMOND);
                System.out.println((char) 27 + "[36m" + "---------------------------------------------------------");
                System.out.println((char) 27 + "[36m" + "           RANK HI???N T???I C???A B???N L?? KIM C????NG");
                System.out.println((char) 27 + "=== B???N L?? 1 TRONG S??? NH???NG KH??CH H??NG VIP NH???T ??? ????Y!!! ===");
                System.out.println((char) 27 + "[36m" + "---------------------------------------------------------");
            }
            new UserView().vaiThuLinhTinh(username, id, roleNameUser);
        }
    }

    public void checkTotalBill(String username, int id, Role.RoleNameUser role) {
        String answer = "";
        System.out.println("B???N MU???N THANH TO??N QUA T??I KHO???N HAY TI???N M???T?!");
        System.out.println("1. QUA T??I KHO???N");
        System.out.println("2. TI???N M???T");
        System.out.println("0. BACK");
        do {
            answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("1")) {
                new UserView().checkBill(username, id, role);
            } else if (answer.equalsIgnoreCase("2")) {
                new ComputerView().checkBill(username, id, role);
            } else if (answer.equalsIgnoreCase("0")) {
                new Main(username, id, role);
            } else {
                System.out.println("SOMETHING WRONG!!! TRY AGAIN!!");
            }
        } while (!answer.equalsIgnoreCase("1") && !answer.equalsIgnoreCase("2"));
    }

    public void accountManagement(String username, int id, Role.RoleNameUser roleNameUser) {
        while (true) {
            System.out.println((char) 27 + "[39m");
            System.out.println("======== ACCOUNT MANAGEMENT ========");
            System.out.println("| USER_NAME | : | " + username + " |");
            System.out.println("| ID | : | " + id + " |");
            System.out.println("| ROLE | : | " + roleNameUser + " |");
            System.out.println("1. CHANGE PASSWORD");
            System.out.println("2. V??I TH??? LINH TINH");
            System.out.println("3. TH??NG TIN T??I KHO???N");
            System.out.println("0. BACK");
            String choose = scanner.nextLine();
            switch (choose) {
                case "1":
                    new UserView().changePassword(username, id, roleNameUser);
                    break;
                case "2":
                    new UserView().vaiThuLinhTinh(username, id, roleNameUser);
                    break;
                case "3":
                    while (true) {
                        System.out.println("---------- TH??NG TIN ----------");
                        System.out.println(userController.findById(id).toString());
                        System.out.println("-------------------------------");
                        System.out.println("ENTER QUIT TO BACK MENU");
                        String back = scanner.nextLine();
                        if (back.equalsIgnoreCase("quit")) {
                            new UserView().accountManagement(username, id, roleNameUser);
                        }
                    }
                case "0":
                    new Main(username, id, roleNameUser);
            }
        }
    }

    public void formLoginUser() {
        System.out.println("ENTER YOUR USERNAME: ");
        String name = scanner.nextLine();
        System.out.println("ENTER YOUR PASSWORD: ");
        String password = scanner.nextLine();
        int count = 0;
        if (userController.showListUser().size() == 0) {
            System.out.println("PLEASE REGISTER!!");
            new Main();
        } else {
            for (int i = 0; i < userController.showListUser().size(); i++) {
                if (name.equalsIgnoreCase(userController.showListUser().get(i).getUsername()) && password.equalsIgnoreCase(userController.showListUser().get(i).getPassword())) {
                    System.out.println("SUCCESSFUL LOGIN!!");
                    count++;
                    new Main(userController.showListUser().get(i).getUsername(), userController.showListUser().get(i).getId(), userController.showListUser().get(i).getRole());
                } else if (count == 0 && i == userController.showListUser().size() - 1) {
                    System.out.println("PLEASE LOGIN AGAIN!!");
                    new Main();
                }
            }
        }
    }

}
