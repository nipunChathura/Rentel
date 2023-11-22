package rental.lk.ijse.layered.bo.custom.impl;

import rental.lk.ijse.layered.bo.custom.CategoryBO;
import rental.lk.ijse.layered.dao.DAOFactory;
import rental.lk.ijse.layered.dao.DAOFactoryType;
import rental.lk.ijse.layered.dao.custom.CategoryDAO;
import rental.lk.ijse.layered.dto.CategoryDTO;
import rental.lk.ijse.layered.entity.Category;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryBOImpl implements CategoryBO {

    CategoryDAO categoryDAO = (CategoryDAO) DAOFactory.getInstance().getDAO(DAOFactoryType.CATEGORY);

    @Override
    public boolean saveCategory(CategoryDTO categoryDTO) throws SQLException, IOException, ClassNotFoundException {
        return categoryDAO.add(categoryDTOToCategory(categoryDTO));
    }

    @Override
    public boolean updateCategory(CategoryDTO categoryDTO) throws SQLException, IOException, ClassNotFoundException {
        return categoryDAO.update(categoryDTOToCategory(categoryDTO));
    }

    @Override
    public boolean deleteCategory(int categoryId) throws SQLException, IOException, ClassNotFoundException {
        return categoryDAO.delete(categoryId);
    }

    @Override
    public CategoryDTO getCategoryById(int categoryId) throws SQLException, IOException, ClassNotFoundException {
        return categoryToCategoryDTO(categoryDAO.getById(categoryId));
    }

    @Override
    public List<CategoryDTO> getAllCategory() throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Category> categories = categoryDAO.getAll();
        if (!categories.isEmpty()) {
            ArrayList<CategoryDTO> categoryDTOS = new ArrayList<>();
            categories.forEach(category -> {
                categoryDTOS.add(categoryToCategoryDTO(category));
            });
            return categoryDTOS;
        }
        return null;
    }

    @Override
    public List<CategoryDTO> searchCategory(String value) throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Category> categories = categoryDAO.searchCategory(value);
        if (!categories.isEmpty()) {
            ArrayList<CategoryDTO> categoryDTOS = new ArrayList<>();
            categories.forEach(category -> {
                categoryDTOS.add(categoryToCategoryDTO(category));
            });
            return categoryDTOS;
        }
        return null;
    }

    @Override
    public int getCategoryLastId() throws Exception {
        return categoryDAO.getCategoryLastId();
    }

    @Override
    public ArrayList<String> getAllActiveCategoryNameList() throws Exception {
        return categoryDAO.getAllActiveCategoryNameList();
    }

    @Override
    public int getCategoryIdByCategoryName(String categoryName) throws SQLException, IOException, ClassNotFoundException {
        return categoryDAO.getCategoryIdByCategoryName(categoryName);
    }

    private CategoryDTO categoryToCategoryDTO(Category category) {
        return new CategoryDTO(
                category.getCategoryId(),
                category.getName(),
                category.getDescription(),
                category.getStatus()
        );
    }

    private Category categoryDTOToCategory(CategoryDTO categoryDTO) {
        return new Category(
                categoryDTO.getCategoryId(),
                categoryDTO.getName(),
                categoryDTO.getDescription(),
                categoryDTO.getStatus()
        );
    }
}
