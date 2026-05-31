package com.example.stylenestboutique.ui.productlist;

import android.os.Bundle;
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
import com.example.stylenestboutique.R;
import com.example.stylenestboutique.data.CartManager;
import com.example.stylenestboutique.data.ProductRepository;
import com.example.stylenestboutique.databinding.FragmentProductListBinding;
import com.example.stylenestboutique.model.Product;
import com.example.stylenestboutique.ui.home.ProductAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductListFragment extends Fragment implements ProductAdapter.OnProductClickListener {

    private FragmentProductListBinding binding;
    private List<Product> productList = new ArrayList<>(); // Initialize to avoid null
    private List<Product> originalList = new ArrayList<>();
    private ProductAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProductListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupProductList();
        setupSorting();
    }

    private void setupProductList() {
        String category = null;
        boolean onlySale = false;
        if (getArguments() != null) {
            category = getArguments().getString("category");
            onlySale = getArguments().getBoolean("only_sale", false);
            if (category != null) {
                binding.categoryTitle.setText(category);
            } else if (onlySale) {
                binding.categoryTitle.setText(getString(R.string.seasonal_sale));
            }
        }

        refreshData(category, onlySale);

        binding.productRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new ProductAdapter(productList, this);
        binding.productRecycler.setAdapter(adapter);
    }

    private void refreshData(String category, boolean onlySale) {
        List<Product> newData;
        if (onlySale) {
            newData = ProductRepository.getSaleProducts();
        } else if (category != null) {
            newData = ProductRepository.getProductsByCategory(category);
        } else {
            newData = ProductRepository.getAllProducts();
        }
        
        originalList.clear();
        originalList.addAll(newData);
        
        productList.clear();
        productList.addAll(originalList);
        
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    private void setupSorting() {
        binding.sortChipGroup.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (checkedIds.isEmpty()) return;
            int checkedId = checkedIds.get(0);
            
            if (checkedId == R.id.chip_default) {
                productList.clear();
                productList.addAll(originalList);
                adapter.notifyDataSetChanged();
            } else if (checkedId == R.id.chip_price_low) {
                sortProducts((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
            } else if (checkedId == R.id.chip_price_high) {
                sortProducts((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
            } else if (checkedId == R.id.chip_size) {
                sortProducts((p1, p2) -> p1.getSize().compareTo(p2.getSize()));
            }
        });

        binding.sortFab.setOnClickListener(this::showSortMenu);
    }

    private void showSortMenu(View view) {
        PopupMenu popup = new PopupMenu(getContext(), view);
        popup.getMenu().add("Price: Lowest to Highest");
        popup.getMenu().add("Price: Highest to Lowest");
        popup.getMenu().add("Sort by Size");

        popup.setOnMenuItemClickListener(item -> {
            String title = item.getTitle().toString();
            if (title.contains("Lowest")) {
                binding.chipPriceLow.setChecked(true);
            } else if (title.contains("Highest")) {
                binding.chipPriceHigh.setChecked(true);
            } else if (title.contains("Size")) {
                binding.chipSize.setChecked(true);
            }
            return true;
        });
        popup.show();
    }

    private void sortProducts(Comparator<Product> comparator) {
        Collections.sort(productList, comparator);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onProductClick(Product product) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("product", product);
        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_list_to_detail, bundle);
    }

    @Override
    public void onQuickAddClick(Product product) {
        if (product.isOutOfStock()) {
            Toast.makeText(getContext(), "Sorry, this item is out of stock", Toast.LENGTH_SHORT).show();
            return;
        }
        CartManager.getInstance().addProduct(product);
        Toast.makeText(getContext(), product.getName() + " added to cart", Toast.LENGTH_SHORT).show();
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
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(), product.getName() + " updated", Toast.LENGTH_SHORT).show();
            }
            return true;
        });
        adminMenu.show();
    }

    private void showDeleteConfirmation(Product product) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Delete Product")
                .setMessage("Are you sure you want to remove " + product.getName() + " from the catalog?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    ProductRepository.deleteProduct(product);
                    productList.remove(product);
                    originalList.remove(product);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Product removed", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
