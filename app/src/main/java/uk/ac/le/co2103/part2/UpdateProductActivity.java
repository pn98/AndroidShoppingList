package uk.ac.le.co2103.part2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import org.w3c.dom.Text;

public class UpdateProductActivity extends AppCompatActivity {

    private static final String TAG = UpdateProductActivity.class.getSimpleName();
    String[]spinnerList = { "Unit", "Liter","Kg"};
Spinner spinner;
    private ProductViewModel productViewModel;
String selectSpinner="Unit";
String shoppingListId;
Product product;
    public static final String EXTRA_REPLY = "uk.ac.le.co2103.part2.REPLY";

    private EditText quantity,id;
TextView name;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.activity_update_product);
        getSupportActionBar().setTitle("Update Product");
        name = findViewById(R.id.et_name);
        id = findViewById(R.id.et_id);
        quantity = findViewById(R.id.et_quantity);
        spinner = findViewById(R.id.spinner);


        product=ProductList.currentProduct;

       name.setText(product.getName()+"");
        id.setText(""+product.getId());
        quantity.setText(""+product.getQuantity());

        switch (product.getUnit())
        {
            case "Unit":
                spinner.setSelection(0);
                break;

            case "Liter":
                spinner.setSelection(1);

                break;
            case "Kg":
                spinner.setSelection(2);
                break;

        }
        selectSpinner=product.getUnit();

        findViewById(R.id.btn_increase).setOnClickListener(v -> {
          int q =    Integer.parseInt(quantity.getText().toString());
            quantity.setText(String.valueOf(++q));
        });

        findViewById(R.id.btn_mins).setOnClickListener(v -> {

            int q =    Integer.parseInt(quantity.getText().toString());

            if (q>0)
            quantity.setText(String.valueOf(--q));
        });
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,spinnerList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectSpinner= spinnerList[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        shoppingListId = getIntent().getStringExtra("shoppingListId");
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);






                findViewById(R.id.button_save).setOnClickListener(view -> {
                    

                           if (id.getText().equals(""))
                        Toast.makeText(this, "Please set id", Toast.LENGTH_SHORT).show();

                    else       if (quantity.getText().equals(""))
                        Toast.makeText(this, "Please set quantity", Toast.LENGTH_SHORT).show();

                    else
                    {

                        if (ProductList.currentProducts.size()>0)
                        {

                                    Product product = new Product( name.getText().toString(),
                                            Integer.parseInt(id.getText().toString()) ,Integer.parseInt( quantity.getText().toString()),
                                            selectSpinner,this.product.shoppingListId);
                                    productViewModel.update(product);
                                    Toast.makeText(this, "Product Updated", Toast.LENGTH_SHORT).show();


                                    finish();



                            }
                        }

                    

        });
    }
}