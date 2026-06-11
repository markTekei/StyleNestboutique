package com.example.stylenestboutique.ui.checkout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.example.stylenestboutique.data.CartManager;
import com.example.stylenestboutique.data.UserManager;
import com.example.stylenestboutique.databinding.FragmentCheckoutBinding;
import com.example.stylenestboutique.model.User;

public class CheckoutFragment extends Fragment {

    private FragmentCheckoutBinding binding;
    private UserManager userManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCheckoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userManager = UserManager.getInstance(requireContext());
        
        setupPaymentSelection();
        prefillDetails();

        binding.placeOrderButton.setOnClickListener(v -> {
            CartManager.getInstance().clearCart();
            Toast.makeText(getContext(), "Order placed successfully! Thank you for shopping with STYLENEST.", Toast.LENGTH_LONG).show();
            Navigation.findNavController(view).popBackStack();
        });
    }

    private void setupPaymentSelection() {
        binding.paymentRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == com.example.stylenestboutique.R.id.radio_card) {
                binding.cardDetailsContainer.setVisibility(View.VISIBLE);
            } else {
                binding.cardDetailsContainer.setVisibility(View.GONE);
            }
        });
    }

    private void prefillDetails() {
        User user = userManager.getUser();
        if (user.getName() != null) binding.nameEditText.setText(user.getName());
        if (user.getAddress() != null) binding.addressEditText.setText(user.getAddress());
        if (user.getPhoneNumber() != null) binding.phoneEditText.setText(user.getPhoneNumber());
        
        if (user.getCardNumber() != null && !user.getCardNumber().isEmpty()) {
            binding.radioCard.setChecked(true);
            binding.cardDetailsContainer.setVisibility(View.VISIBLE);
            binding.cardNumberEditText.setText(user.getCardNumber());
            binding.expiryEditText.setText(user.getCardExpiry());
            binding.cvvEditText.setText(user.getCardCvv());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
