package com.example.stylenestboutique.ui.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.stylenestboutique.databinding.ItemCategoryChipBinding;
import java.util.List;

public class CategoryChipAdapter extends RecyclerView.Adapter<CategoryChipAdapter.ChipViewHolder> {

    private final List<String> categories;
    private final OnCategoryChipClickListener listener;

    public interface OnCategoryChipClickListener {
        void onCategoryChipClick(String category);
    }

    public CategoryChipAdapter(List<String> categories, OnCategoryChipClickListener listener) {
        this.categories = categories;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ChipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryChipBinding binding = ItemCategoryChipBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ChipViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChipViewHolder holder, int position) {
        String category = categories.get(position);
        holder.binding.categoryName.setText(category);
        holder.itemView.setOnClickListener(v -> listener.onCategoryChipClick(category));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class ChipViewHolder extends RecyclerView.ViewHolder {
        ItemCategoryChipBinding binding;

        public ChipViewHolder(ItemCategoryChipBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}