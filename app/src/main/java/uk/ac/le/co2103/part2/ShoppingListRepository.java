package uk.ac.le.co2103.part2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ShoppingListRepository {
    private ShoppingListDao shoppingListDao;
    private LiveData<List<ShoppingList>> allShoppingLists;

    ShoppingListRepository(Application application) {
        ShoppingCartDB db = ShoppingCartDB.getDatabase(application);
        shoppingListDao = db.shoppingListDao();
        allShoppingLists = shoppingListDao.getShoppingLists();
    }

    LiveData<List<ShoppingList>> getAllShoppingLists() {
        return allShoppingLists;
    }

    void insert(ShoppingList shoppingList) {
        ShoppingCartDB.databaseWriteExecutor.execute(() -> {
            shoppingListDao.insert(shoppingList);
        });
    }

    void delete(ShoppingList shoppingList) {
        ShoppingCartDB.databaseWriteExecutor.execute(() -> {
            shoppingListDao.delete(shoppingList);
        });
    }

    void update(ShoppingList shoppingList) {
        ShoppingCartDB.databaseWriteExecutor.execute(() -> {
            shoppingListDao.update(shoppingList);
        });
    }
}
