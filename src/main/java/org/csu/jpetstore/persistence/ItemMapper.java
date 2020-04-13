package org.csu.jpetstore.persistence;

import org.csu.jpetstore.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface ItemMapper {
    void updateInventoryQuantity(Map<String,Object> param);

    int getInventoryQuantity(String itemId);

    List<Item> getItemListByProduct(String productId);

    Item getItem(String itemId);

    void addItem(Item item);
    void addItemInventory(Item item);

    void updateItem(Item item);
    void updateItemInventory(Item item);

    void deleteItem(String itemId);
    void deleteItemInventory(String itemId);

    List<Item> getAllItem();

//    List<Item> searchItemList(String keywords);
}
