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
