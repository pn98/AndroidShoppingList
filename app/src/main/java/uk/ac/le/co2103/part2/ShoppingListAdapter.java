package uk.ac.le.co2103.part2;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class ShoppingListAdapter extends ListAdapter<ShoppingList, ShoppingListViewHolder> {
    private OnShoppingListClickListener shoppingListClickListener;

    public interface OnShoppingListClickListener {
        void onShoppingListClick(ShoppingList shoppingList,String typePress);
    }

    public void setOnShoppingListClickListener(OnShoppingListClickListener listener) {
        this.shoppingListClickListener = listener;
    }

    public ShoppingListAdapter(@NonNull DiffUtil.ItemCallback<ShoppingList> diffCallback) {
        super(diffCallback);
    }

    @Override
    public ShoppingListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ShoppingListViewHolder.create(parent, shoppingListClickListener);
    }

    @Override
    public void onBindViewHolder(ShoppingListViewHolder holder, int position) {
        ShoppingList current = getItem(position);
        holder.bind(current.getShoppingList(),current.getImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shoppingListClickListener != null) {
                    shoppingListClickListener.onShoppingListClick(current,"normal");
                }
            }
        });


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (shoppingListClickListener != null) {
                    shoppingListClickListener.onShoppingListClick(current,"long");
                }

                return false;
            }
        });


    }

    static class ItemDiff extends DiffUtil.ItemCallback<ShoppingList> {

        @Override
        public boolean areItemsTheSame(@NonNull ShoppingList oldShoppingList, @NonNull ShoppingList newShoppingList) {
            return oldShoppingList == newShoppingList;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ShoppingList oldShoppingList, @NonNull ShoppingList newShoppingList) {
            return oldShoppingList.getShoppingList().equals((newShoppingList.getShoppingList()));
        }
    }
}