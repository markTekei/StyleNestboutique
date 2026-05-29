package com.example.stylenestboutique.ui.productdetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.example.stylenestboutique.R;
import com.example.stylenestboutique.data.CartManager;
import com.example.stylenestboutique.data.WishlistManager;
import com.example.stylenestboutique.databinding.FragmentProductDetailBinding;
import com.example.stylenestboutique.model.Product;
import com.google.android.material.chip.Chip;
import java.util.Locale;

public class ProductDetailFragment extends Fragment {

    private FragmentProductDetailBinding binding;
    private Product product;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            product = (Product) getArguments().getSerializable("product");
            setupUI();
        }
    }

    private void setupUI() {
        if (product == null) return;

        binding.productName.setText(product.getName());
        binding.productPrice.setText("KES " + String.format(Locale.US, "%,.0f", product.getPrice()));
        binding.productDescription.setText(product.getDescription());
        
        Object imageSource = product.getImageUrl() != null ? product.getImageUrl() : product.getImageResource();
        
        Glide.with(this)
                .load(imageSource)
                .placeholder(R.drawable.ic_logo)
                .error(R.drawable.ic_launcher_background)
                .centerCrop()
                .into(binding.productImage);

        updateWishlistFab();

        binding.wishlistFab.setOnClickListener(v -> {
            WishlistManager.getInstance().toggleWishlist(product);
            updateWishlistFab();
            String msg = WishlistManager.getInstance().isInWishlist(product) ? "Added to wishlist" : "Removed from wishlist";
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        });

        binding.shareButton.setOnClickListener(v -> shareProduct());

        binding.addToCartButton.setOnClickListener(v -> {
            int checkedId = binding.sizeChipGroup.getCheckedChipId();
            if (checkedId != View.NO_ID) {
                Chip chip = binding.sizeChipGroup.findViewById(checkedId);
                product.setSize(chip.getText().toString());
            }
            
            CartManager.getInstance().addProduct(product);
            Toast.makeText(getContext(), product.getName() + " (" + product.getSize() + ") added to cart", Toast.LENGTH_SHORT).show();
        });
    }

    private void updateWishlistFab() {
        boolean inWishlist = WishlistManager.getInstance().isInWishlist(product);
        binding.wishlistFab.setImageResource(inWishlist ? R.drawable.ic_dress : R.drawable.ic_dress);
        // Using alpha/tint to differentiate since we don't have a heart icon
        binding.wishlistFab.setSupportImageTintList(ContextCompat.getColorStateList(requireContext(), 
                inWishlist ? R.color.brand_gold : R.color.brand_gold_light));
        binding.wishlistFab.setAlpha(inWishlist ? 1.0f : 0.6f);
    }

    private void shareProduct() {
        String shareText = "Check out this " + product.getName() + " on STYLENEST! Only KES " + 
                String.format(Locale.US, "%,.0f", product.getPrice());
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, shareText);
        startActivity(Intent.createChooser(intent, "Share via"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
