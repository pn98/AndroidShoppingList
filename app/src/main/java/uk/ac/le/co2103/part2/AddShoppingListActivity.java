package uk.ac.le.co2103.part2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class AddShoppingListActivity extends AppCompatActivity {

    private static final String TAG = AddShoppingListActivity.class.getSimpleName();

    public static final String EXTRA_REPLY = "uk.ac.le.co2103.part2.REPLY";
    public static final String EXTRA_Image = "uk.ac.le.co2103.part2.Image";

    private EditText editTextShoppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() ASLA");

        setContentView(R.layout.activity_add_shopping_list);

        editTextShoppingList = findViewById(R.id.edit_new_shopping_list);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(editTextShoppingList.getText())) {
                Log.i(TAG, "Empty text field could be controlled in UI (Save Button Disabled");
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                Log.i(TAG, "Adding Shopping List");
                String shoppingList = editTextShoppingList.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY, shoppingList);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}