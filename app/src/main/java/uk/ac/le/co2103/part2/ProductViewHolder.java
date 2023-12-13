package uk.ac.le.co2103.part2;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    private final TextView productTextView,productquantity,productunit,productID;

    private ProductViewHolder(View productView) {
        super(productView);
        productID = productView.findViewById(R.id.tv_id);
        productTextView = productView.findViewById(R.id.tv_name);
        productquantity = productView.findViewById(R.id.tv_quantity);
        productunit = productView.findViewById(R.id.tv_unit);
    }

    @SuppressLint("SetTextI18n")
    public void bind(Product product) {
        productID.setText("ID: "+product.getId());
        productTextView.setText("Name: "+product.getName());
        productquantity.setText("Quantity: "+product.getQuantity());
        productunit.setText("Unit: "+product.getUnit());
    }

    static ProductViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_product, parent, false);
        return new ProductViewHolder(view);
    }
}
