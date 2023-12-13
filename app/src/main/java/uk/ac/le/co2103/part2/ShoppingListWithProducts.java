package uk.ac.le.co2103.part2;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ShoppingListWithProducts {
    @Embedded
    public ShoppingList shoppingList;

    @Relation(parentColumn = "listId", entityColumn = "name", entity = Product.class)
    public List<Product> products;
}
