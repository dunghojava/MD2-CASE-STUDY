package controller;

import model.User;
import service.user.UserServiceIMPL;

import java.util.List;

public class UserController {
    UserServiceIMPL userServiceIMPL = new UserServiceIMPL();

    public List<User> showListUser(){
        return userServiceIMPL.findAll();
    }

    public User changePassword(int id, String password) {
        userServiceIMPL.findById(id).setPassword(password);
        return  userServiceIMPL.findById(id);
    }

    public void createUser(User user){
        userServiceIMPL.save(user);
    }

    public User findById(int id) {
        return userServiceIMPL.findById(id);
    }

}
