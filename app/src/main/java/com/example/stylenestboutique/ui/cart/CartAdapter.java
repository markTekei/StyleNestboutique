package com.example.stylenestboutique.ui.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.stylenestboutique.R;
import com.example.stylenestboutique.data.CartManager;
import com.example.stylenestboutique.databinding.ItemProductBinding;
import com.example.stylenestboutique.model.Product;
import java.util.List;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Product> products;

    public CartAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductBinding binding = ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = products.get(position);
        holder.binding.productName.setText(product.getName());
        holder.binding.productPrice.setText("KES " + String.format(Locale.US, "%,.0f", product.getPrice()));
        
        holder.binding.saleTag.setVisibility(View.GONE);
        holder.binding.wishlistButton.setVisibility(View.GONE);
        
        Object imageSource = product.getImageUrl() != null ? product.getImageUrl() : product.getImageResource();
        
        Glide.with(holder.itemView.getContext())
                .load(imageSource)
                .placeholder(R.drawable.ic_logo)
                .error(R.drawable.ic_launcher_background)
                .centerCrop()
                .into(holder.binding.productImage);
        
        if (holder.binding.addToCartQuick != null) {
            holder.binding.addToCartQuick.setImageResource(android.R.drawable.ic_menu_delete);
            holder.binding.addToCartQuick.setAlpha(0.6f);
            holder.binding.addToCartQuick.setOnClickListener(v -> {
                CartManager.getInstance().removeProduct(product);
                notifyDataSetChanged();
            });
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        ItemProductBinding binding;

        public CartViewHolder(ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
