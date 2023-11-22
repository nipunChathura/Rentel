package rental.lk.ijse.layered.bo.custom;

import rental.lk.ijse.layered.bo.SuperBO;
import rental.lk.ijse.layered.dto.ItemDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface ItemBO extends SuperBO {
    public boolean saveItem(ItemDTO itemDTO) throws SQLException, IOException, ClassNotFoundException;
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, IOException, ClassNotFoundException;
    public boolean deleteItem(int itemId) throws SQLException, IOException, ClassNotFoundException;
    public ItemDTO getItemById(int itemId) throws SQLException, IOException, ClassNotFoundException;
    public ItemDTO getItemByName(String itemName) throws SQLException, IOException, ClassNotFoundException;
    public List<ItemDTO> getAllItem() throws SQLException, IOException, ClassNotFoundException;
    public List<ItemDTO> searchItem(String value, Long categoryId) throws SQLException, IOException, ClassNotFoundException;
    public int getItemLastId() throws Exception;
    public HashMap<String, ItemDTO> getFreeItemList() throws SQLException, IOException, ClassNotFoundException;
}
