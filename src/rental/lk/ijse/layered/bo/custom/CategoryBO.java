package rental.lk.ijse.layered.bo.custom;

import rental.lk.ijse.layered.bo.SuperBO;
import rental.lk.ijse.layered.dto.CategoryDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CategoryBO extends SuperBO {
    public boolean saveCategory(CategoryDTO categoryDTO) throws SQLException, IOException, ClassNotFoundException;
    public boolean updateCategory(CategoryDTO categoryDTO) throws SQLException, IOException, ClassNotFoundException;
    public boolean deleteCategory(int categoryId) throws SQLException, IOException, ClassNotFoundException;
    public CategoryDTO getCategoryById(int categoryId) throws SQLException, IOException, ClassNotFoundException;
    public List<CategoryDTO> getAllCategory() throws SQLException, IOException, ClassNotFoundException;
    public List<CategoryDTO> searchCategory(String value) throws SQLException, IOException, ClassNotFoundException;
    public int getCategoryLastId() throws Exception;
    public ArrayList<String> getAllActiveCategoryNameList() throws Exception;
    public int getCategoryIdByCategoryName(String categoryName) throws SQLException, IOException, ClassNotFoundException;
}
