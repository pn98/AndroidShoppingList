package uk.ac.le.co2103.part2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductRepository {
    private ProductDao productDao;
    private LiveData<List<Product>> allProducts;

    ProductRepository(Application application) {
        ProductDB db = ProductDB.getDatabase(application);
        productDao = db.productDao();
        allProducts = productDao.getAllProducts();
    }

    LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }

    void insert(Product product) {
        ProductDB.databaseWriteExecutor.execute(() -> {

            productDao.insert(product);
        });
    }
    void update(Product product) {
        ProductDB.databaseWriteExecutor.execute(() -> {

            productDao.update(product);
        });
    }

    void delete(Product product) {
        ProductDB.databaseWriteExecutor.execute(() -> {

            productDao.delete(product);
        });
    }
}
