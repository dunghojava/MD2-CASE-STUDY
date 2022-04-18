package controller;

import model.Revenue;
import service.totalRevenue.TotalRevenueIMPL;

import java.util.List;

public class TotalRevenueController {
    TotalRevenueIMPL totalRevenueIMPL = new TotalRevenueIMPL();

    public List<Revenue> showListRevenue() {
        return totalRevenueIMPL.findAll();
    }
}
