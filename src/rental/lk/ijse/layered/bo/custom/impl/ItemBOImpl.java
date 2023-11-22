package rental.lk.ijse.layered.bo.custom.impl;

import rental.lk.ijse.layered.bo.custom.ItemBO;
import rental.lk.ijse.layered.dao.DAOFactory;
import rental.lk.ijse.layered.dao.DAOFactoryType;
import rental.lk.ijse.layered.dao.custom.CategoryDAO;
import rental.lk.ijse.layered.dao.custom.ItemDAO;
import rental.lk.ijse.layered.dto.ItemDTO;
import rental.lk.ijse.layered.entity.Category;
import rental.lk.ijse.layered.entity.Item;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactoryType.ITEM);
    CategoryDAO categoryDAO = (CategoryDAO) DAOFactory.getInstance().getDAO(DAOFactoryType.CATEGORY);

    @Override
    public boolean saveItem(ItemDTO itemDTO) throws SQLException, IOException, ClassNotFoundException {
        return itemDAO.add(itemDTOToItem(itemDTO));
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, IOException, ClassNotFoundException {
        return itemDAO.update(itemDTOToItem(itemDTO));
    }

    @Override
    public boolean deleteItem(int itemId) throws SQLException, IOException, ClassNotFoundException {
        return itemDAO.delete(itemId);
    }

    @Override
    public ItemDTO getItemById(int itemId) throws SQLException, IOException, ClassNotFoundException {
        return itemToItemDTO(itemDAO.getById(itemId));
    }

    @Override
    public ItemDTO getItemByName(String itemName) throws SQLException, IOException, ClassNotFoundException {
        return itemToItemDTO(itemDAO.getItemByItemName(itemName));
    }

    @Override
    public List<ItemDTO> getAllItem() throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Item> items = itemDAO.getAll();
        if (!items.isEmpty()) {
            ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
            items.forEach(item -> {
                ItemDTO itemDTO = new ItemDTO();
                String categoryName = null;
                try {
                    Category category = categoryDAO.getById(item.getCategoryId());
                    categoryName = category.getName();
                } catch (SQLException | ClassNotFoundException | IOException throwables) {
                    throwables.printStackTrace();
                }
                itemDTO = itemToItemDTO(item);
                itemDTO.setCategoryName(categoryName);
                itemDTOS.add(itemDTO);
            });
            return itemDTOS;
        }
        return null;
    }

    @Override
    public List<ItemDTO> searchItem(String value, Long categoryId) throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Item> items = itemDAO.searchItem(value, categoryId);
        if (!items.isEmpty()) {
            ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
            items.forEach(item -> {
                ItemDTO itemDTO = new ItemDTO();
                String categoryName = null;
                try {
                    Category category = categoryDAO.getById(item.getCategoryId());
                    categoryName = category.getName();
                } catch (SQLException | ClassNotFoundException | IOException throwables) {
                    throwables.printStackTrace();
                }
                itemDTO = itemToItemDTO(item);
                itemDTO.setCategoryName(categoryName);
                itemDTOS.add(itemDTO);
            });
            return itemDTOS;
        }
        return null;
    }

    @Override
    public int getItemLastId() throws Exception {
        return itemDAO.getItemLastId();
    }

    @Override
    public HashMap<String, ItemDTO> getFreeItemList() throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Item> freeItemList = itemDAO.getFreeItemList();
        System.out.println("freeItemList = " + freeItemList);
        HashMap<String, ItemDTO> itemHashMap = new HashMap<>();
        if (!freeItemList.isEmpty()) {
            freeItemList.forEach(item -> {
                itemHashMap.put(item.getName(), itemToItemDTO(item));
            });
        }
        return itemHashMap;
    }

    private Item itemDTOToItem(ItemDTO itemDTO) {
        return new Item(
                itemDTO.getItemId(),
                itemDTO.getName(),
                BigDecimal.valueOf(itemDTO.getPreDayPrice()),
                BigDecimal.valueOf(itemDTO.getExtraDayPrice()),
                itemDTO.getCategoryId(),
                itemDTO.getStatus()
        );
    }

    private ItemDTO itemToItemDTO(Item item) {
        return new ItemDTO(
                item.getItemId(),
                item.getName(),
                item.getPreDayPrice().doubleValue(),
                item.getExtraDayPrice().doubleValue(),
                null,
                item.getCategoryId(),
                item.getStatus()
        );
    }
}
