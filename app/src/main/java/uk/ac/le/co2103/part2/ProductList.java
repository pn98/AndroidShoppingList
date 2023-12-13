package uk.ac.le.co2103.part2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProductList extends AppCompatActivity {

    private static final String TAG = ProductList.class.getSimpleName();
    public static final int ADD_PRODUCT_LIST_REQUEST_CODE = 112;
    private ProductViewModel productViewModel;
    private ProductListAdapter adapter;

    public static ArrayList<Product> currentProducts = new ArrayList<>();
    public static Product currentProduct = new Product();
    String shoppingListId;

    @SuppressLint("SuspiciousIndentation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        shoppingListId = getIntent().getStringExtra("shoppingListId");

        RecyclerView recyclerView = findViewById(R.id.recyclerview_product);
        adapter = new ProductListAdapter(new ProductListAdapter.ProductDiff(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        final FloatingActionButton button = findViewById(R.id.fab_product_list);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(ProductList.this, AddProductActivity.class);
            intent.putExtra("shoppingListId", shoppingListId);
            startActivity(intent);
            Log.d(TAG, "Floating action button clicked. PL.JAVA");
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        productViewModel.getAllProducts().observe(this, products -> {
            currentProducts.clear();
            currentProducts.addAll(products);
            adapter.submitList(currentProducts);
        });
    }
}
