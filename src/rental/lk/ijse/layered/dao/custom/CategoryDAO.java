package rental.lk.ijse.layered.dao.custom;

import rental.lk.ijse.layered.dao.CrudDAO;
import rental.lk.ijse.layered.entity.Category;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CategoryDAO extends CrudDAO<Category, Integer> {
    public ArrayList<Category> searchCategory(String searchValue) throws SQLException, ClassNotFoundException, IOException;
    public int getCategoryLastId() throws Exception;
    public ArrayList<String> getAllActiveCategoryNameList() throws SQLException, IOException, ClassNotFoundException;
    public int getCategoryIdByCategoryName(String categoryName) throws SQLException, IOException, ClassNotFoundException;
}
