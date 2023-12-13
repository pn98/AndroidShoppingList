package uk.ac.le.co2103.part2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ProductListAdapter extends ListAdapter<Product, ProductViewHolder> {
    private final ProductViewModel productViewModel;
    private final ProductList activity;

    public ProductListAdapter(@NonNull DiffUtil.ItemCallback<Product> diffCallback, ProductList activity) {
        super(diffCallback);
        productViewModel = new ViewModelProvider(activity).get(ProductViewModel.class);
        this.activity = activity;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ProductViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product current = getItem(position);
        holder.bind(current);

        holder.itemView.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());

            builder.setMessage("What do you want to do with this " + current.getName() + " product?");

            builder.setTitle("Product Dialog!");

            builder.setCancelable(true);

            builder.setPositiveButton("Edit", (dialog, which) -> {
                ProductList.currentProduct = current;
                activity.startActivity(new Intent(activity, UpdateProductActivity.class));
            });

            builder.setNegativeButton("Delete", (dialog, which) -> {
                productViewModel.delete(current);
                Toast.makeText(holder.itemView.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
                ProductList.currentProducts.remove(current);
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
    }

    static class ProductDiff extends DiffUtil.ItemCallback<Product> {
        @Override
        public boolean areItemsTheSame(@NonNull Product oldProduct, @NonNull Product newProduct) {
            return oldProduct.name.equals(newProduct.name);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldProduct, @NonNull Product newProduct) {
            return oldProduct.getProduct().equals(newProduct.getProduct());
        }
    }
}
