package uk.ac.le.co2103.part2;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ListConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Product> fromJson(String json) {
        Type listType = new TypeToken<List<Product>>() {}.getType();
        return gson.fromJson(json, listType);
    }

    @TypeConverter
    public static String toJson(List<Product> products) {
        return gson.toJson(products);
    }

}
