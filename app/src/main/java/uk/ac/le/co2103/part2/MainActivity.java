package uk.ac.le.co2103.part2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements ShoppingListAdapter.OnShoppingListClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ShoppingListViewModel shoppingListViewModel;

    public static final int ADD_PRODUCT_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate()");

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ShoppingListAdapter adapter = new ShoppingListAdapter(new ShoppingListAdapter.ItemDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setOnShoppingListClickListener(this);


        shoppingListViewModel = new ViewModelProvider(this).get(ShoppingListViewModel.class);

        shoppingListViewModel.getAllShoppingLists().observe(this, shoppingLists -> {
            adapter.submitList(shoppingLists);
        });

        final FloatingActionButton button = findViewById(R.id.fab_main);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CreateListActivity.class);
            startActivityForResult(intent, ADD_PRODUCT_ACTIVITY_REQUEST_CODE);
            Log.d(TAG, "Floating action button clicked.");
        });
    }

    public void onShoppingListClick(ShoppingList shoppingList , String type) {
        if (type.equals("normal"))
        {
            Intent intent = new Intent(MainActivity.this, ProductList.class);
            intent.putExtra("shoppingListId", String.valueOf(shoppingList.getListId()));
            startActivity(intent);
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            builder.setMessage("Do you want delete Shopping List ?");

            builder.setTitle("Alert !");

            builder.setCancelable(false);

            builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                shoppingListViewModel.delete(shoppingList);
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
            });

            builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_PRODUCT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            ShoppingList shoppingList = new ShoppingList(data.getStringExtra(AddShoppingListActivity.EXTRA_REPLY));
            shoppingList.setImage(data.getStringExtra(AddShoppingListActivity.EXTRA_Image));

            shoppingListViewModel.insert(shoppingList);
            Toast.makeText(this, "Shopping List saved", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}