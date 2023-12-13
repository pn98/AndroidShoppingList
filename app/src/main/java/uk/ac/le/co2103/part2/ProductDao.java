@Dao
public interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Product product);

    @Query("SELECT * FROM product ORDER BY name ASC")
    LiveData<List<Product>> getAllProducts();

    @Update
    void update(Product product);

    @Delete
    void delete(Product product);

    @Query("DELETE FROM product")
    void deleteAll();
}

