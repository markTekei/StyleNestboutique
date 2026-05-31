package com.example.stylenestboutique.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bumptech.glide.Glide;
import com.example.stylenestboutique.R;
import com.example.stylenestboutique.data.CartManager;
import com.example.stylenestboutique.data.ProductRepository;
import com.example.stylenestboutique.data.WishlistManager;
import com.example.stylenestboutique.databinding.FragmentHomeBinding;
import com.example.stylenestboutique.model.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment implements 
        ProductAdapter.OnProductClickListener, 
        CategoryChipAdapter.OnCategoryChipClickListener {

    private FragmentHomeBinding binding;
    private ProductAdapter productAdapter;
    private List<Product> allProducts;
    private String currentCategory = "All";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupCategories();
        loadProducts();
        setupSearch();
        setupBanner();

        binding.sortButton.setOnClickListener(this::showSortMenu);
    }

    private void setupBanner() {
        List<Product> saleProducts = ProductRepository.getSaleProducts();
        if (!saleProducts.isEmpty()) {
            Product featured = saleProducts.get(0);
            
            // Sync banner text and image with the first sale product
            binding.bannerTitle.setText(featured.getName().toUpperCase().replace(" ", "\n"));
            
            Object imageSource = featured.getImageUrl() != null ? featured.getImageUrl() : featured.getImageResource();
            Glide.with(this)
                    .load(imageSource)
                    .centerCrop()
                    .into(binding.bannerProductImage);

            // Clicking "Shop Now" or the banner itself opens this specific product
            View.OnClickListener featuredClick = v -> {
                Bundle bundle = new Bundle();
                bundle.putSerializable("product", featured);
                Navigation.findNavController(v).navigate(R.id.action_home_to_detail, bundle);
            };

            binding.shopNowButton.setOnClickListener(featuredClick);
            binding.bannerCard.setOnClickListener(featuredClick);
        } else {
            binding.shopNowButton.setOnClickListener(v -> {
                Bundle args = new Bundle();
                args.putBoolean("only_sale", true);
                Navigation.findNavController(v).navigate(R.id.action_home_to_list, args);
            });
        }
    }

    private void loadProducts() {
        allProducts = ProductRepository.getFeaturedProducts();
        binding.featuredRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        updateProductAdapter(new ArrayList<>(allProducts));
    }

    private void setupCategories() {
        List<String> categories = new ArrayList<>();
        categories.add("All");
        categories.addAll(ProductRepository.getCategories());

        CategoryChipAdapter categoryAdapter = new CategoryChipAdapter(categories, this);
        binding.categoriesChipRecycler.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.categoriesChipRecycler.setAdapter(categoryAdapter);
    }

    private void setupSearch() {
        binding.searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                applyFilters(currentCategory, s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void applyFilters(String category, String query) {
        List<Product> filteredList = new ArrayList<>();
        for (Product product : allProducts) {
            boolean matchesCategory = category.equals("All") || product.getCategory().equalsIgnoreCase(category);
            boolean matchesQuery = query.isEmpty() || product.getName().toLowerCase().contains(query.toLowerCase());
            
            if (matchesCategory && matchesQuery) {
                filteredList.add(product);
            }
        }
        updateProductAdapter(filteredList);
    }

    private void updateProductAdapter(List<Product> newList) {
        productAdapter = new ProductAdapter(newList, this);
        binding.featuredRecycler.setAdapter(productAdapter);
    }

    @Override
    public void onCategoryChipClick(String category) {
        currentCategory = category;
        applyFilters(category, binding.searchEditText.getText().toString());
    }

    @Override
    public void onProductClick(Product product) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("product", product);
        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_home_to_detail, bundle);
    }

    @Override
    public void onQuickAddClick(Product product) {
        if (product.isOutOfStock()) {
            Toast.makeText(getContext(), "Item is out of stock", Toast.LENGTH_SHORT).show();
            return;
        }
        CartManager.getInstance().addProduct(product);
        Toast.makeText(getContext(), product.getName() + " added to cart", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWishlistClick(Product product) {
        WishlistManager.getInstance().toggleWishlist(product);
        boolean isInWishlist = WishlistManager.getInstance().isInWishlist(product);
        String message = isInWishlist ? " added to wishlist" : " removed from wishlist";
        Toast.makeText(getContext(), product.getName() + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProductLongClick(Product product, View view) {
        PopupMenu adminMenu = new PopupMenu(getContext(), view);
        String stockAction = product.isOutOfStock() ? "Mark as In Stock" : "Mark as Out of Stock";
        adminMenu.getMenu().add(stockAction);
        adminMenu.getMenu().add("Remove Product");

        adminMenu.setOnMenuItemClickListener(item -> {
            if (item.getTitle().equals("Remove Product")) {
                showDeleteConfirmation(product);
            } else {
                product.setOutOfStock(!product.isOutOfStock());
                productAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), product.getName() + " updated", Toast.LENGTH_SHORT).show();
            }
            return true;
        });
        adminMenu.show();
    }

    private void showDeleteConfirmation(Product product) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Delete Product")
                .setMessage("Remove " + product.getName() + " from catalog?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    ProductRepository.deleteProduct(product);
                    allProducts.remove(product);
                    applyFilters(currentCategory, binding.searchEditText.getText().toString());
                    Toast.makeText(getContext(), "Product removed", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showSortMenu(View view) {
        PopupMenu popup = new PopupMenu(getContext(), view);
        popup.getMenuInflater().inflate(R.menu.sort_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            if (id == R.id.sort_price_low_high) {
                sortProducts(true);
                return true;
            } else if (id == R.id.sort_price_high_low) {
                sortProducts(false);
                return true;
            }
            return false;
        });
        popup.show();
    }

    private void sortProducts(boolean lowToHigh) {
        if (lowToHigh) {
            Collections.sort(allProducts, (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
        } else {
            Collections.sort(allProducts, (p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
        }
        applyFilters(currentCategory, binding.searchEditText.getText().toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
