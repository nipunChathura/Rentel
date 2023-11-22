package rental.lk.ijse.layered.dao.custom;

import rental.lk.ijse.layered.dao.CrudDAO;
import rental.lk.ijse.layered.entity.Item;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<Item, Integer> {
    public ArrayList<Item> searchItem(String searchValue, Long categoryId) throws SQLException, ClassNotFoundException, IOException;
    public int getItemLastId() throws Exception;
    public ArrayList<Item> getFreeItemList() throws SQLException, IOException, ClassNotFoundException;
    public Item getItemByItemName(String itemName) throws SQLException, IOException, ClassNotFoundException;
}
