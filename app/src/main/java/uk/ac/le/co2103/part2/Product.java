package uk.ac.le.co2103.part2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product")
public class Product {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "quantity")
    public int quantity;
    @ColumnInfo(name = "unit")
    public String unit;
    @ColumnInfo(name = "shoppingListId")
    public String shoppingListId;


    public Product(@NonNull String name, int id, int quantity, String unit, String shoppingListId) {
        this.name = name;
        this.id = id;
        this.quantity = quantity;
        this.unit = unit;
        this.shoppingListId = shoppingListId;
    }

    public Product() {

    }

    public String getProduct() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(String shoppingListId) {
        this.shoppingListId = shoppingListId;
    }
}
