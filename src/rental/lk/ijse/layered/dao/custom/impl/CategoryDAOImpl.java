package rental.lk.ijse.layered.dao.custom.impl;

import rental.lk.ijse.layered.dao.custom.CategoryDAO;
import rental.lk.ijse.layered.entity.Category;
import rental.lk.ijse.layered.util.CrudUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAOImpl implements CategoryDAO {
    @Override
    public boolean add(Category entity) throws ClassNotFoundException, SQLException, IOException {
        return CrudUtil.executeUpdate("INSERT INTO category VALUE (?,?,?,?)",
                entity.getCategoryId(), entity.getName(), entity.getDescription(), entity.getStatus());
    }

    @Override
    public boolean delete(Integer categoryId) throws ClassNotFoundException, SQLException, IOException {
        return CrudUtil.executeUpdate("UPDATE category SET status = 'DELETED' WHERE category_id = ?", categoryId);
    }

    @Override
    public boolean update(Category entity) throws SQLException, ClassNotFoundException, IOException {
        return CrudUtil.executeUpdate("UPDATE category SET name = ?, description = ? WHERE category_id = ?",
                entity.getName(), entity.getDescription(), entity.getCategoryId());
    }

    @Override
    public Category getById(Integer id) throws SQLException, ClassNotFoundException, IOException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM category WHERE category_id = ?", id);
        if (resultSet.next()) {
            int categoryId = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String description = resultSet.getString(3);
            String status = resultSet.getString(4);

            return new Category(categoryId, name, description, status);
        }
        return null;
    }

    @Override
    public ArrayList<Category> searchCategory(String searchValue) throws SQLException, ClassNotFoundException, IOException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM category WHERE status not in ('DELETED') and CONCAT(name, description, category_id) like LOWER(concat(concat('%', ?), '%'))", searchValue);
        return getCategoryList(resultSet);
    }

    @Override
    public ArrayList<Category> getAll() throws SQLException, ClassNotFoundException, IOException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM category WHERE status not in ('DELETED')");
        return getCategoryList(resultSet);
    }

    private ArrayList<Category> getCategoryList(ResultSet resultSet) throws SQLException {
        ArrayList<Category> categories = new ArrayList<>();
        while (resultSet.next()) {
            int categoryId = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String description = resultSet.getString(3);
            String status = resultSet.getString(4);

            Category category = new Category(categoryId, name, description, status);
            categories.add(category);
        }

        return categories;
    }

    @Override
    public int getCategoryLastId() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT category_id FROM category ORDER BY category_id DESC LIMIT 1");
        int lastId = 0;
        if (resultSet.next()) {
            lastId = resultSet.getInt(1) +1;
        } else {
            lastId = 1;
        }
        return lastId;
    }

    @Override
    public ArrayList<String> getAllActiveCategoryNameList() throws SQLException, IOException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT name FROM category WHERE status in ('ACTIVE') ORDER BY name");
        ArrayList<String> categoryNameList = new ArrayList<>();
        while (resultSet.next()) {
            String categoryName = resultSet.getString(1);
            categoryNameList.add(categoryName);
        }
        return categoryNameList;
    }

    @Override
    public int getCategoryIdByCategoryName(String categoryName) throws SQLException, IOException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT category_id FROM category WHERE name = ?", categoryName);
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }
}
