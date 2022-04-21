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
            System.out.println("======= | VÀI THỨ LINH TINH | =======");
            System.out.println("1. NẠP TIỀN");
            System.out.println("2. KIỂM TRA THỨ HẠNG TÀI KHOẢN");
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
                    System.out.println(" ĐANG BẢO TRÌ :))");
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
                System.out.println((char) 27 + "[39m" + "SỐ TIỀN HIỆN TẠI LÀ: " + tongNap + "vnđ");
                System.out.println((char) 27 + "[39m" + "-------------------------------");
                System.out.println((char) 27 + "[39m" + " RANK CÙI BẮP CHỨ CÒN GÌ NỮA!!!");
                System.out.println((char) 27 + "[39m" + "-------------------------------");
            } else if (tongNap >= 100000 && tongNap < 200000) {
                System.out.println((char) 27 + "[33m" + "SỐ TIỀN HIỆN TẠI LÀ: " + tongNap + "vnđ");
                System.out.println((char) 27 + "[33m" + "-------------------------------");
                System.out.println((char) 27 + "[33m" + " RANK HIỆN TẠI CỦA BẠN LÀ VÀNG");
                System.out.println((char) 27 + "[33m" + "-------------------------------");
            } else if (tongNap >= 200000 && tongNap < 500000) {
                System.out.println((char) 27 + "[32m" + "SỐ TIỀN HIỆN TẠI LÀ: " + tongNap + "vnđ");
                System.out.println((char) 27 + "[32m" + "----------------------------------");
                System.out.println((char) 27 + "[32m" + " RANK HIỆN TẠI CỦA BẠN LÀ LỤC BẢO");
                System.out.println((char) 27 + "[32m" + "----------------------------------");
            } else if (tongNap >= 500000) {
                System.out.println((char) 27 + "[36m" + "SỐ TIỀN HIỆN TẠI LÀ: " + tongNap + "vnđ");
                System.out.println((char) 27 + "[36m" + "---------------------------------------------------------");
                System.out.println((char) 27 + "[36m" + "           RANK HIỆN TẠI CỦA BẠN LÀ KIM CƯƠNG");
                System.out.println((char) 27 + "=== BẠN LÀ 1 TRONG SỐ NHỮNG KHÁCH HÀNG VIP NHẤT Ở ĐÊY!!! ===");
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
                    System.out.println("TIỀN DỊCH VỤ: " + billService + "vnđ");
                    System.out.println("TIỀN GIỜ CHƠI: " + priceComputer + "vnđ");
                    System.out.println("THỜI GIAN SỬ DỤNG MÁY: " + time + "s");
                    System.out.println("TỔNG TIỀN CẦN THANH TOÁN: " + totalPrice + "vnđ");
                    System.out.println("--------------------------------------");
                    System.out.println("SỐ TIỀN HIỆN CÓ TRONG TÀI KHOẢN: " + userController.findById(id).getTienNap() + "vnđ");
                    System.out.println("--------------------------------------");
                    System.out.println("1. THANH TOÁN");
                    System.out.println("2. NẠP TIỀN");
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
                                System.out.println("BẠN ĐÃ THANH TOÁN THÀNH CÔNG!!!");
                                System.out.println("SỐ TIỀN CÒN LẠI TRONG TÀI KHOẢN LÀ: " + userController.findById(id).getTienNap() + "vnđ");
                                System.out.println("--------------------------------");
                                new UserView().setRoleRankUser(id);
                            } else if (userController.findById(id).getTienNap() < totalPrice) {
                                System.out.println("--------------------------------");
                                System.err.println("TÀI KHOẢN CỦA BẠN KHÔNG ĐỦ ĐỂ THANH TOÁN!! VUI LÒNG NẠP THÊM!!");
                                System.out.println("--------------------------------");
                            }
                            break;
                        case 2:
                            new UserView().napTien(username, id, roleNameUser);
                        case 0:
                            new Main(username, id, roleNameUser);
                    }
                } else if (userController.findById(id).getTienNap() == 0) {
                    System.err.println("TÀI KHOẢN ĐANG TRỐNG TRƠN!! NẠP LẦN ĐẦU ĐI BẠN!!");
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
            System.err.println("CHƯA NẠP TIỀN ĐÃ ĐÒI THANH TOÁN ˣ‿ˣ");
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
            System.out.println((char) 27 + "[39m" + "RANK CÙI BẮP (SỐ DƯ DƯỚI 100K)");
            System.out.println((char) 27 + "[33m" + "RANK VÀNG (SỐ DƯ TỪ 100K)");
            System.out.println((char) 27 + "[32m" + "RANK LỤC BẢO (SỐ DƯ TỪ 200K)");
            System.out.println((char) 27 + "[36m" + "RANK KIM CƯƠNG (SỐ DƯ TỪ 500K)");
            System.out.println((char) 27 + "[34m" + "----------------------------------");
            double tongNap = userController.findById(id).getTienNap();
            boolean answer = true;
            do {
                System.out.println("NHẬP SỐ TIỀN MUỐN NẠP: ");
                double tienNap = Integer.parseInt(scanner.nextLine());
                Revenue revenue = new Revenue(tienNap, username);
                totalRevenueIMPL.save(revenue);
                tongNap += tienNap;
                userController.findById(id).setTienNap(tongNap);
                userController.showListUser();
                String choose = "";
                do {
                    System.out.println("BẠN CÓ MUỐN NẠP THÊM KHÔNG?!");
                    System.out.println("YES / NO");
                    choose = scanner.nextLine();
                    if (choose.equalsIgnoreCase("yes")) {
                        answer = true;
                    } else if (choose.equalsIgnoreCase("no")) {
                        answer = false;
                    } else {
                        System.err.println("CÓ GÌ ĐÓ SAI SAI!! VUI LÒNG NHẬP LẠI!!");
                    }
                } while (!choose.equalsIgnoreCase("yes") && !choose.equalsIgnoreCase("no"));
            } while (answer);
            if (tongNap < 100000) {
                userController.findById(id).setRoleRankUser(Role.RoleRankUser.SILVER);
                System.out.println((char) 27 + "[39m" + "-------------------------------");
                System.out.println((char) 27 + "[39m" + " RANK CÙI BẮP CHỨ CÒN GÌ NỮA!!!");
                System.out.println((char) 27 + "[39m" + "-------------------------------");
            } else if (tongNap >= 100000 && tongNap < 200000) {
                userController.findById(id).setRoleRankUser(Role.RoleRankUser.GOLD);
                System.out.println((char) 27 + "[33m" + "-------------------------------");
                System.out.println((char) 27 + "[33m" + " RANK HIỆN TẠI CỦA BẠN LÀ VÀNG");
                System.out.println((char) 27 + "[33m" + "-------------------------------");
            } else if (tongNap >= 200000 && tongNap < 500000) {
                userController.findById(id).setRoleRankUser(Role.RoleRankUser.EMERALD);
                System.out.println((char) 27 + "[32m" + "----------------------------------");
                System.out.println((char) 27 + "[32m" + " RANK HIỆN TẠI CỦA BẠN LÀ LỤC BẢO");
                System.out.println((char) 27 + "[32m" + "----------------------------------");
            } else if (tongNap >= 500000) {
                userController.findById(id).setRoleRankUser(Role.RoleRankUser.DIAMOND);
                System.out.println((char) 27 + "[36m" + "---------------------------------------------------------");
                System.out.println((char) 27 + "[36m" + "           RANK HIỆN TẠI CỦA BẠN LÀ KIM CƯƠNG");
                System.out.println((char) 27 + "=== BẠN LÀ 1 TRONG SỐ NHỮNG KHÁCH HÀNG VIP NHẤT Ở ĐÊY!!! ===");
                System.out.println((char) 27 + "[36m" + "---------------------------------------------------------");
            }
            new UserView().vaiThuLinhTinh(username, id, roleNameUser);
        }
    }

    public void checkTotalBill(String username, int id, Role.RoleNameUser role) {
        String answer = "";
        System.out.println("BẠN MUỐN THANH TOÁN QUA TÀI KHOẢN HAY TIỀN MẶT?!");
        System.out.println("1. QUA TÀI KHOẢN");
        System.out.println("2. TIỀN MẶT");
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
            System.out.println("2. VÀI THỨ LINH TINH");
            System.out.println("3. THÔNG TIN TÀI KHOẢN");
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
                        System.out.println("---------- THÔNG TIN ----------");
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
