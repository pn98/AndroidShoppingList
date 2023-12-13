package uk.ac.le.co2103.part2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class CreateListActivity extends AppCompatActivity {
    private static final String TAG = CreateListActivity.class.getSimpleName();
    public static final String EXTRA_Image = "uk.ac.le.co2103.part2.Image";
    public static final String EXTRA_REPLY = "uk.ac.le.co2103.part2.REPLY";
    public static final int SELECT_Image = 100;
    private EditText editTextProduct;
    private ImageView imgList;
    private String imageURL="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);




        editTextProduct = findViewById(R.id.edit_new_product);
        imgList = findViewById(R.id.img_shopping);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(editTextProduct.getText())) {
                Log.i(TAG, "Empty text field could be controlled in UI (Save Button Disabled");
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                Log.i(TAG, "Adding Item to List");
                String nameList = editTextProduct.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY, nameList);
                replyIntent.putExtra(EXTRA_Image, imageURL);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });


        imgList.setOnClickListener(view -> {pickFromGallery();});
    }

    private void pickFromGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_Image);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //For android 11 and above

            if (resultCode == RESULT_OK) {
                if (requestCode == SELECT_Image) {
                    //handle image
                    Uri selectedImageUri = data.getData();


                    Log.d("comeOn_comeOn", "image");

                    imgList.setImageURI(selectedImageUri);

                    imageURL = String.valueOf(selectedImageUri);


                }

            }

        }
    }
}