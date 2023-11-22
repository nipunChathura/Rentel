package rental.lk.ijse.layered.dao.custom.impl;

import rental.lk.ijse.layered.dao.custom.ItemDAO;
import rental.lk.ijse.layered.entity.Item;
import rental.lk.ijse.layered.util.CrudUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean add(Item entity) throws ClassNotFoundException, SQLException, IOException {
        System.out.println("entity = " + entity);
        return CrudUtil.executeUpdate("INSERT INTO item VALUE (?,?,?,?,?,?)",
                entity.getItemId(), entity.getName(), entity.getPreDayPrice(), entity.getExtraDayPrice(),
                entity.getCategoryId(), entity.getStatus());
    }

    @Override
    public boolean delete(Integer itemId) throws ClassNotFoundException, SQLException, IOException {
        return CrudUtil.executeUpdate("UPDATE item SET status = 'DELETED' WHERE item_id = ?", itemId);
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException, IOException {
        return CrudUtil.executeUpdate("UPDATE item SET name = ?, pre_day_price = ?, extra_day_price = ?, category_id = ?, status = ? WHERE item_id = ?",
                entity.getName(), entity.getPreDayPrice(), entity.getExtraDayPrice(), entity.getCategoryId(), entity.getStatus(), entity.getItemId());
    }

    @Override
    public Item getById(Integer id) throws SQLException, ClassNotFoundException, IOException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM item WHERE item_id = ?", id);
        if (resultSet.next()) {
            return getItemList(resultSet).get(0);
        }
        return null;
    }

    @Override
    public ArrayList<Item> searchItem(String searchValue, Long categoryId) throws SQLException, ClassNotFoundException, IOException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM item\n" +
                        "WHERE status not in ('DELETED')\n" +
                        "  AND (? is null or CONCAT(item_id, name, pre_day_price, extra_day_price, category_id) like LOWER(concat(concat('%', ?), '%')))\n" +
                        "  AND (? is null or category_id = ?)",
                searchValue, searchValue, categoryId, categoryId);
        return getItemList(resultSet);
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException, IOException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM item WHERE status not in ('DELETED')");
        return getItemList(resultSet);
    }

    private ArrayList<Item> getItemList(ResultSet resultSet) throws SQLException {
        ArrayList<Item> items = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            BigDecimal preDayPrice = resultSet.getBigDecimal(3);
            BigDecimal extraDayPrice = resultSet.getBigDecimal(4);
            int categoryId = resultSet.getInt(5);
            String status = resultSet.getString(6);
            Item item = new Item(id, name, preDayPrice, extraDayPrice, categoryId, status);
            items.add(item);
        }
        return items;
    }

    @Override
    public int getItemLastId() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT item_id FROM item ORDER BY item_id DESC LIMIT 1");
        if (resultSet.next()) {
            return resultSet.getInt(1) +1;
        }
        return 1;
    }

    @Override
    public ArrayList<Item> getFreeItemList() throws SQLException, IOException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM item WHERE status in ('FREE')");
        return getItemList(resultSet);
    }

    @Override
    public Item getItemByItemName(String itemName) throws SQLException, IOException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM item WHERE name = ?", itemName);
        if (resultSet.next()) {
            return getItemList(resultSet).get(0);
        }
        return null;
    }
}
