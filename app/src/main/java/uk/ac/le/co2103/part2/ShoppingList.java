package uk.ac.le.co2103.part2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.List;

@Entity(tableName = "shoppingList")
@TypeConverters(ListConverter.class)
public class ShoppingList {

    @PrimaryKey(autoGenerate = true)
    public int listId;

    @NonNull
    @ColumnInfo(name = "name")
    public String name;
    @NonNull
    @ColumnInfo(name = "image")
    public String image="";

    public List<Product> products;

    public ShoppingList(@NonNull String name) {
        this.name = name;
    }

    public String getShoppingList() {
        return name;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
