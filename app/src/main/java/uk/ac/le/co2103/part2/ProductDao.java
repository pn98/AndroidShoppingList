package uk.ac.le.co2103.part2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    void insert(Product product);

    @Query("SELECT * FROM product ORDER BY name ASC")
    LiveData<List<Product>> getAllProducts();

    @Update
    void update(Product product);

    @Delete
    void delete(Product product);

    @Query("DELETE FROM product")
    void deleteAll();
}
