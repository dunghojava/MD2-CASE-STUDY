package model;

import java.io.Serializable;
import java.util.List;

public class Computer implements Serializable {
    private int id;
    private String name;
    private Boolean status = false;
    private int time;
    private List<Food> foodList = null;
    private Role.RoleNameComputer role = Role.RoleNameComputer.NORMAL;
    private double startTime = 0;
    private double endTime = 0.0;

    public Computer() {
    }

    public Computer(int id, String name, Role.RoleNameComputer role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public Computer(int id, String name, Boolean status, int time, List<Food> foodList, Role.RoleNameComputer role) {
        this.name = name;
        this.status = status;
        this.time = time;
        this.foodList = foodList;
        this.role = role;
        this.id = id;
    }

    public double getEndTime() {
        return endTime;
    }

    public double getStartTime() {
        return startTime;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(Boolean status) {
        if (status) {
            this.startTime = System.nanoTime();
        } else {
            this.endTime = System.nanoTime();
        }
        this.status = status;

    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public Role.RoleNameComputer getRole() {
        return role;
    }

    public void setRole(Role.RoleNameComputer role) {
        this.role = role;
    }

    public double checkPriceTime() {
        if (this.startTime == 0 || this.endTime == 0) {
            return 0;
        } else {
            return 0;
        }
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    public double checkTotalPrice() {
        double sum = 0;
        if (this.foodList == null && checkPriceTime() == 0) {
            return 0;
        } else {
            for (Food foodListPrice : foodList) {
                sum += foodListPrice.getPrice();
            }
            return sum + checkPriceTime();
        }
    }

    @Override
    public String toString() {
        return '\n' + "Computer {" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", status = " + status +
                ", foodList = " + foodList +
                ", role = " + role +
                '}';
    }

    public static void main(String[] args) {
        System.out.println("===============");
        System.out.println(new Computer().checkPriceTime());
        System.out.println("===============");
        System.out.println(new Computer().getRole().getRoleNameComputer());
        System.out.println("===============");

    }

}
