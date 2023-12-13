package uk.ac.le.co2103.part2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductRepository {
    private final ProductDao productDao;
    private final LiveData<List<Product>> allProducts;

    ProductRepository(Application application) {
        ProductDB db = ProductDB.getDatabase(application);
        productDao = db.productDao();
        allProducts = productDao.getAllProducts();
    }

    LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }

    void insertAsync(Product product) {
        ProductDB.databaseWriteExecutor.execute(() -> {
            try {
                productDao.insert(product);
            } catch (Exception e) {
                // Handle error
                e.printStackTrace();
            }
        });
    }

    void updateAsync(Product product) {
        ProductDB.databaseWriteExecutor.execute(() -> {
            try {
                productDao.update(product);
            } catch (Exception e) {
                // Handle error
                e.printStackTrace();
            }
        });
    }

    void deleteAsync(Product product) {
        ProductDB.databaseWriteExecutor.execute(() -> {
            try {
                productDao.delete(product);
            } catch (Exception e) {
                // Handle error
                e.printStackTrace();
            }
        });
    }
}
