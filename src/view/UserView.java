package view;

import controller.UserController;
import model.Role;
import model.User;
import service.user.UserServiceIMPL;
import java.util.List;
import java.util.Scanner;

public class UserView {
    Scanner scanner = new Scanner(System.in);
    UserController userController = new UserController();
    List<User> userList = UserServiceIMPL.users;

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

    public void rechagreAccount(String username, int id, Role.RoleNameUser roleNameUser) {
        System.err.println("CHƯA LÀM!!!");
        new Main();
    }

    public void accountManagement(String username, int id, Role.RoleNameUser roleNameUser) {
        while (true) {
            System.out.println("======== ACCOUNT MANAGEMENT ========");
            System.out.println("| USER_NAME | : | " + username + " |");
            System.out.println("| ID | : | " + id + " |");
            System.out.println("| ROLE | : | " + roleNameUser + " |");
            System.out.println("1. CHANGE PASSWORD");
            System.out.println("2. RECHARGE ACCOUNT");
            String choose = scanner.nextLine();
            switch (choose) {
                case "1":
                    new UserView().changePassword(username, id, roleNameUser);
                    break;
                case "2":
                    new UserView().rechagreAccount(username, id, roleNameUser);
                    break;
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
