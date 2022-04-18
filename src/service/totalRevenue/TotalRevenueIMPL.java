package service.totalRevenue;

import config.ConfigReadAndWriteFile;
import model.Revenue;

import java.util.List;

public class TotalRevenueIMPL implements ITotalRevenue{

    public static String PATH = "D:\\CODEGYM-C0222I1\\Module 2\\Java Project\\CASE_STUDY\\src\\data\\totalRevenue.txt";
    public static List<Revenue> revenues = new ConfigReadAndWriteFile<Revenue>().readFromFile(PATH);

    @Override
    public List findAll() {
        new ConfigReadAndWriteFile<Revenue>().writeToFile(PATH, revenues);
        return revenues;
    }

    @Override
    public void save(Object o) {
    }

    public void getTotalPrice() {

    }

    @Override
    public void save(Revenue revenue) {
        new ConfigReadAndWriteFile<Revenue>().writeToFile(PATH, revenues);
        revenues.add(revenue);
    }

    @Override
    public Object findById(int id) {
        for (int i = 0; i < revenues.size(); i++) {
            if (id == revenues.get(i).getId()) {
                return revenues.get(i);
            }
        }
        return null;
    }
}
