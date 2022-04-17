package service.computer;

import config.ConfigReadAndWriteFile;
import model.Computer;

import java.util.List;

public class ComputerServiceIMPL implements IComputerService {

    public static String PATH = "D:\\CODEGYM-C0222I1\\Module 2\\Java Project\\CASE_STUDY\\src\\data\\computer.txt";
    public static List<Computer> computers = new ConfigReadAndWriteFile<Computer>().readFromFile(PATH);

    @Override
    public List<Computer> findAll() {
        new ConfigReadAndWriteFile<Computer>().writeToFile(PATH, computers);
        return computers;
    }

    @Override
    public void save(Computer computer) {
        new ConfigReadAndWriteFile<Computer>().writeToFile(PATH, computers);
        computers.add(computer);
    }

    @Override
    public Computer findById(int id) {
        for (int i = 0; i < computers.size(); i++) {
            if (id == computers.get(i).getId()) {
                return computers.get(i);
            }
        }
        return null;
    }
}
