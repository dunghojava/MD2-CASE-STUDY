package controller;

import model.Computer;
import service.computer.ComputerServiceIMPL;

import java.util.List;

public class ComputerController {
    ComputerServiceIMPL computerServiceIMPL = new ComputerServiceIMPL();

    public List<Computer> showListComputer() {
        return computerServiceIMPL.findAll();
    }

    public void createComputer(Computer computer) {
        computerServiceIMPL.save(computer);
    }

    public Computer findById(int id) {
        return computerServiceIMPL.findById(id);
    }
}
