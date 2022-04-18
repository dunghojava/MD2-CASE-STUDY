package service.totalRevenue;

import model.Revenue;
import service.IGenericService;

public interface ITotalRevenue extends IGenericService {
    void save(Revenue revenue);
}
