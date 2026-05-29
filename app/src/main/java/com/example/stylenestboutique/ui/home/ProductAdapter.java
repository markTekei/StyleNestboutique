package com.example.stylenestboutique.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.stylenestboutique.R;
import com.example.stylenestboutique.data.WishlistManager;
import com.example.stylenestboutique.databinding.ItemProductBinding;
import com.example.stylenestboutique.model.Product;
import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> products;
    private OnProductClickListener listener;

    public interface OnProductClickListener {
        void onProductClick(Product product);
        void onQuickAddClick(Product product);
        default void onWishlistClick(Product product) {}
        default void onProductLongClick(Product product, View view) {}
    }

    public ProductAdapter(List<Product> products, OnProductClickListener listener) {
        this.products = products;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductBinding binding = ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.binding.productName.setText(product.getName());
        holder.binding.productPrice.setText("KES " + String.format(Locale.US, "%,.0f", product.getPrice()));
        
        // Wishlist state
        boolean inWishlist = WishlistManager.getInstance().isInWishlist(product);
        holder.binding.wishlistButton.setImageResource(inWishlist ? R.drawable.ic_dress : R.drawable.ic_dress);
        // Using same icon for now, but in a real app we'd use a filled/empty heart.
        // Let's change tint to indicate state if icons are the same.
        holder.binding.wishlistButton.setAlpha(inWishlist ? 1.0f : 0.3f);

        // Show/Hide sale tag
        if (product.isOnSale() && !product.isOutOfStock()) {
            holder.binding.saleTag.setVisibility(View.VISIBLE);
        } else {
            holder.binding.saleTag.setVisibility(View.GONE);
        }

        // Out of stock UI handling
        if (product.isOutOfStock()) {
            holder.binding.outOfStockOverlay.setVisibility(View.VISIBLE);
            holder.binding.outOfStockText.setVisibility(View.VISIBLE);
            holder.binding.addToCartQuick.setVisibility(View.GONE);
            holder.binding.productPrice.setAlpha(0.5f);
            holder.binding.productName.setAlpha(0.5f);
        } else {
            holder.binding.outOfStockOverlay.setVisibility(View.GONE);
            holder.binding.outOfStockText.setVisibility(View.GONE);
            holder.binding.addToCartQuick.setVisibility(View.VISIBLE);
            holder.binding.productPrice.setAlpha(1.0f);
            holder.binding.productName.setAlpha(1.0f);
        }
        
        Object imageSource = product.getImageUrl() != null ? product.getImageUrl() : product.getImageResource();
        
        Glide.with(holder.itemView.getContext())
                .load(imageSource)
                .placeholder(R.drawable.ic_logo)
                .error(R.drawable.ic_launcher_background)
                .centerCrop()
                .into(holder.binding.productImage);
        
        holder.itemView.setOnClickListener(v -> listener.onProductClick(product));
        
        holder.itemView.setOnLongClickListener(v -> {
            if (listener != null) {
                listener.onProductLongClick(product, v);
                return true;
            }
            return false;
        });
        
        holder.binding.addToCartQuick.setOnClickListener(v -> {
            if (listener != null && !product.isOutOfStock()) {
                listener.onQuickAddClick(product);
            }
        });

        holder.binding.wishlistButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onWishlistClick(product);
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ItemProductBinding binding;

        public ProductViewHolder(ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}