package uk.ac.le.co2103.part2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ShoppingListDao {
    @Insert
    void insert(ShoppingList shoppingList);

    @Query("SELECT * FROM shoppingList ORDER BY name ASC")
    LiveData<List<ShoppingList>> getShoppingLists();

    @Update
    void update(ShoppingList model);


    @Delete
    void delete(ShoppingList model);

    @Query("DELETE FROM shoppingList")
    void deleteAll();
}
