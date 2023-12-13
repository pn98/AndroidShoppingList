package uk.ac.le.co2103.part2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class ProductListAdapter extends ListAdapter<Product, ProductViewHolder> {
    private  ProductViewModel productViewModel;
    private  ProductList activity;

    public ProductListAdapter(@NonNull DiffUtil.ItemCallback<Product> diffCallback, ProductList activity) {



        super(diffCallback);

        productViewModel = new ViewModelProvider( activity).get(ProductViewModel.class);
        this.activity = activity;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ProductViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product current = getItem(position);
        holder.bind(current);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder( holder.itemView.getContext());

                builder.setMessage("What do want to do with this "+ current .getName()+" product ?");

                builder.setTitle("Product Dialog !");

                builder.setCancelable(true);

                builder.setPositiveButton("Edit", (DialogInterface.OnClickListener) (dialog, which) -> {
                    ProductList.currentProduct=current;
                    activity.startActivity(new Intent(activity,UpdateProductActivity
                            .class));

                });

                builder.setNegativeButton("Delete", (DialogInterface.OnClickListener) (dialog, which) -> {
                    productViewModel.delete(current);
                    Toast.makeText(holder.itemView.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                    ProductList.currentProducts.remove(current);
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
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
