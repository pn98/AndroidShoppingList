package uk.ac.le.co2103.part2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddProductActivity extends AppCompatActivity {

    private static final String TAG = AddProductActivity.class.getSimpleName();
    String[] spinnerList = {"Unit", "Liter", "Kg"};
    Spinner spinner;
    private ProductViewModel productViewModel;
    String selectSpinner = "Unit";
    String shoppingListId;
    public static final String EXTRA_REPLY = "uk.ac.le.co2103.part2.REPLY";

    private EditText name, quantity, id;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");

        setContentView(R.layout.activity_add_product);
        shoppingListId = getIntent().getStringExtra("shoppingListId");
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectSpinner = spinnerList[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        name = findViewById(R.id.et_name);
        id = findViewById(R.id.et_id);
        quantity = findViewById(R.id.et_quantity);

        findViewById(R.id.button_save).setOnClickListener(view -> {
            if (TextUtils.isEmpty(name.getText().toString())) {
                Toast.makeText(this, "Please set name", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(id.getText().toString())) {
                Toast.makeText(this, "Please set id", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(quantity.getText().toString())) {
                Toast.makeText(this, "Please set quantity", Toast.LENGTH_SHORT).show();
            } else {
                boolean productExists = false;

                for (Product existingProduct : ProductList.currentProducts) {
                    if (existingProduct.getName().equals(name.getText().toString())) {
                        productExists = true;
                        break;
                    }
                }

                if (productExists) {
                    Toast.makeText(this, "Product already exists with the same name", Toast.LENGTH_SHORT).show();
                } else {
                    Product product = new Product(
                            name.getText().toString(),
                            Integer.parseInt(id.getText().toString()),
                            Integer.parseInt(quantity.getText().toString()),
                            selectSpinner,
                            shoppingListId
                    );

                    productViewModel.insert(product);
                    Toast.makeText(this, "Product saved", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
