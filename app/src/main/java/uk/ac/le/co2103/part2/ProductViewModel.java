package uk.ac.le.co2103.part2;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private ProductRepository repo;

    private final LiveData<List<Product>> allProducts;

    public ProductViewModel (Application application) {
        super(application);
        repo = new ProductRepository(application);
        allProducts = repo.getAllProducts();
    }

    LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }

    public void insert(Product product) {
        repo.insert(product);
    }
    public void update(Product product) {
        repo.update(product);
    }

    public void delete(Product product) {
        repo.delete(product);
    }
}
