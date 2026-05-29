package com.example.stylenestboutique.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.stylenestboutique.R;
import com.example.stylenestboutique.data.CartManager;
import com.example.stylenestboutique.databinding.FragmentCartBinding;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        binding.cartRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        CartAdapter adapter = new CartAdapter(CartManager.getInstance().getCartItems());
        binding.cartRecycler.setAdapter(adapter);

        binding.totalPrice.setText("KES " + CartManager.getInstance().getTotalPrice());

        binding.checkoutButton.setOnClickListener(v -> {
            if (CartManager.getInstance().getCartItems().isEmpty()) {
                return;
            }
            Navigation.findNavController(v).navigate(R.id.action_cart_to_checkout);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}