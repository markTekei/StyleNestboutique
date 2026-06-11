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
import com.example.stylenestboutique.data.UserManager;
import com.example.stylenestboutique.databinding.FragmentCartBinding;
import com.example.stylenestboutique.model.User;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;
    private UserManager userManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userManager = UserManager.getInstance(requireContext());
        
        binding.cartRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        CartAdapter adapter = new CartAdapter(CartManager.getInstance().getCartItems());
        binding.cartRecycler.setAdapter(adapter);

        binding.totalPrice.setText("KES " + CartManager.getInstance().getTotalPrice());

        updateDeliveryAddress();

        binding.changeAddressButton.setOnClickListener(v -> 
            Navigation.findNavController(v).navigate(R.id.action_cart_to_savedDetails));

        binding.checkoutButton.setOnClickListener(v -> {
            if (CartManager.getInstance().getCartItems().isEmpty()) {
                return;
            }
            Navigation.findNavController(v).navigate(R.id.action_cart_to_checkout);
        });
    }

    private void updateDeliveryAddress() {
        User user = userManager.getUser();
        if (user.getAddress() != null && !user.getAddress().isEmpty()) {
            binding.deliveryAddressText.setText(user.getAddress());
        } else {
            binding.deliveryAddressText.setText("No address saved. Click to add.");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateDeliveryAddress();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
