package com.example.stylenestboutique.ui.categories;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.stylenestboutique.R;
import com.example.stylenestboutique.databinding.ItemCategoryBinding;
import com.example.stylenestboutique.model.Category;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> categories;
    private OnCategoryClickListener listener;

    public interface OnCategoryClickListener {
        void onCategoryClick(String categoryName);
    }

    public CategoryAdapter(List<Category> categories, OnCategoryClickListener listener) {
        this.categories = categories;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryBinding binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.binding.categoryName.setText(category.getName());
        
        Object imageSource = category.getImageUrl() != null ? category.getImageUrl() : category.getImageResource();
        
        Glide.with(holder.itemView.getContext())
                .load(imageSource)
                .placeholder(R.drawable.ic_logo) // Added placeholder
                .error(R.drawable.ic_launcher_background) // Added error fallback
                .centerCrop()
                .into(holder.binding.categoryImage);
                
        holder.itemView.setOnClickListener(v -> listener.onCategoryClick(category.getName()));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        ItemCategoryBinding binding;

        public CategoryViewHolder(ItemCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}