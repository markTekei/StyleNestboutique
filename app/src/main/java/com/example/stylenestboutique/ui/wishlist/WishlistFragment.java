package com.example.stylenestboutique.ui.wishlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import com.example.stylenestboutique.R;
import com.example.stylenestboutique.data.CartManager;
import com.example.stylenestboutique.data.WishlistManager;
import com.example.stylenestboutique.databinding.FragmentWishlistBinding;
import com.example.stylenestboutique.model.Product;
import com.example.stylenestboutique.ui.home.ProductAdapter;
import java.util.List;

public class WishlistFragment extends Fragment implements ProductAdapter.OnProductClickListener {

    private FragmentWishlistBinding binding;
    private ProductAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentWishlistBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        updateUI();
    }

    private void setupRecyclerView() {
        binding.wishlistRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    private void updateUI() {
        List<Product> items = WishlistManager.getInstance().getWishlistItems();
        if (items.isEmpty()) {
            binding.wishlistRecycler.setVisibility(View.GONE);
            binding.emptyWishlistView.setVisibility(View.VISIBLE);
        } else {
            binding.wishlistRecycler.setVisibility(View.VISIBLE);
            binding.emptyWishlistView.setVisibility(View.GONE);
            adapter = new ProductAdapter(items, this);
            binding.wishlistRecycler.setAdapter(adapter);
        }
    }

    @Override
    public void onProductClick(Product product) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("product", product);
        Navigation.findNavController(binding.getRoot()).navigate(R.id.productDetailFragment, bundle);
    }

    @Override
    public void onQuickAddClick(Product product) {
        CartManager.getInstance().addProduct(product);
        Toast.makeText(getContext(), product.getName() + " added to cart", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWishlistClick(Product product) {
        WishlistManager.getInstance().toggleWishlist(product);
        updateUI();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}