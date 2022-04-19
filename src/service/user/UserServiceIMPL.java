package service.user;

import config.ConfigReadAndWriteFile;
import model.User;

import java.util.List;

public class UserServiceIMPL implements IUserService {

    public static String PATH = "D:\\CODEGYM-C0222I1\\Module 2\\Java Project\\CASE_STUDY\\src\\data\\user.txt";
    public static List<User> users = new ConfigReadAndWriteFile<User>().readFromFile(PATH);

    @Override
    public List findAll() {
        new ConfigReadAndWriteFile<User>().writeToFile(PATH, users);
        return users;
    }

    @Override
    public void save(User user) {
        new ConfigReadAndWriteFile<User>().writeToFile(PATH, users);
        users.add(user);

    }

    @Override
    public User findById(int id) {
        for (int i = 0; i < users.size(); i++) {
            if (id == users.get(i).getId()) {
                return users.get(i);
            }
        }
        return null;
    }
}
