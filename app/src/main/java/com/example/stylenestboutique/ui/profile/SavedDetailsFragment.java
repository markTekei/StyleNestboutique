package com.example.stylenestboutique.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.example.stylenestboutique.R;
import com.example.stylenestboutique.data.UserManager;
import com.example.stylenestboutique.databinding.FragmentSavedDetailsBinding;
import com.example.stylenestboutique.model.User;

public class SavedDetailsFragment extends Fragment {

    private FragmentSavedDetailsBinding binding;
    private UserManager userManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSavedDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userManager = UserManager.getInstance(requireContext());
        loadUserData();

        binding.saveButton.setOnClickListener(v -> {
            saveUserData();
            Toast.makeText(getContext(), "Details saved successfully!", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(v).popBackStack();
        });

        binding.selectLocationButton.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_savedDetails_to_map);
        });
    }

    private void loadUserData() {
        User user = userManager.getUser();
        binding.nameEditText.setText(user.getName());
        binding.phoneEditText.setText(user.getPhoneNumber());
        binding.addressEditText.setText(user.getAddress());
        binding.cardNumberEditText.setText(user.getCardNumber());
        binding.expiryEditText.setText(user.getCardExpiry());
        binding.cvvEditText.setText(user.getCardCvv());
    }

    private void saveUserData() {
        User user = userManager.getUser();
        user.setName(binding.nameEditText.getText().toString());
        user.setPhoneNumber(binding.phoneEditText.getText().toString());
        user.setAddress(binding.addressEditText.getText().toString());
        user.setCardNumber(binding.cardNumberEditText.getText().toString());
        user.setCardExpiry(binding.expiryEditText.getText().toString());
        user.setCardCvv(binding.cvvEditText.getText().toString());
        userManager.saveUser(user);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
