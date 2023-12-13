package uk.ac.le.co2103.part2;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShoppingListViewHolder extends RecyclerView.ViewHolder {
    private final TextView shoppingListTextView;
    private  ImageView imageView;
    private ShoppingList currentShoppingList;

    private ShoppingListViewHolder(@NonNull View shoppingListView, ShoppingListAdapter.OnShoppingListClickListener listener) {
        super(shoppingListView);
        shoppingListTextView = shoppingListView.findViewById(R.id.textView);
        imageView = shoppingListView.findViewById(R.id.img_shopping_list);


        itemView.setOnClickListener(v -> {
            int position = getAdapterPosition();
            if (listener != null && position != RecyclerView.NO_POSITION) {
                listener.onShoppingListClick(currentShoppingList,"normal");
            }
        });
        itemView.setOnLongClickListener(v -> {
            int position = getAdapterPosition();
            if (listener != null && position != RecyclerView.NO_POSITION) {
                listener.onShoppingListClick(currentShoppingList,"Long");
            }
            return false;
        });

    }

    @SuppressLint("SuspiciousIndentation")
    public void bind(String text, String image) {
        shoppingListTextView.setText(text);
       // if (!image.equals(""))
       // imageView.setImageURI(Uri.parse(image));
    }

    static ShoppingListViewHolder create(ViewGroup parent, ShoppingListAdapter.OnShoppingListClickListener listener) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ShoppingListViewHolder(view, listener);
    }
}
