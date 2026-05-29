package com.example.stylenestboutique.ui.profile;

import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.example.stylenestboutique.R;
import com.example.stylenestboutique.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupClickListeners();
    }

    private void setupClickListeners() {
        // Feature 1: Functional Wishlist Navigation
        binding.wishlistButton.setOnClickListener(v -> 
            Navigation.findNavController(v).navigate(R.id.action_profile_to_wishlist));

        binding.virtualTryOnButton.setOnClickListener(v -> 
            showComingSoon("Virtual Try-On Closet", "Use AR to try clothes from your home."));

        binding.sizeProfileButton.setOnClickListener(v -> 
            showComingSoon("Size Profile", "Personalized sizing based on your measurements."));

        binding.orderHistoryButton.setOnClickListener(v -> 
            Navigation.findNavController(v).navigate(R.id.action_profile_to_orderHistory));

        binding.styleRewardsButton.setOnClickListener(v -> 
            showComingSoon("Style Rewards", "Earn points for every purchase."));

        // Feature 2: Interactive Style Quiz Feature
        binding.styleQuizButton.setOnClickListener(v -> startStyleQuiz());

        binding.paymentMethodsButton.setOnClickListener(v -> 
            showComingSoon("Payment Methods", "Securely manage your credit cards and mobile money."));

        binding.adminDashboardButton.setOnClickListener(v -> 
            Navigation.findNavController(v).navigate(R.id.action_profile_to_admin));

        binding.logoutButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Logged out of STYLENEST", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(v).navigate(R.id.action_profile_to_login);
        });

        // Feature 3: Modern Haptic Feedback for Badges
        binding.badgeEco.setOnClickListener(v -> {
            v.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
            showFeatureInfo("Eco-Warrior", "Unlocked: You prefer sustainable and organic fabrics!");
        });
        binding.badgeTrend.setOnClickListener(v -> {
            v.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
            showFeatureInfo("Trendsetter", "Unlocked: You're among the first 1% to buy new arrivals!");
        });
        binding.badgeLoyal.setOnClickListener(v -> {
            v.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
            showFeatureInfo("Gold Member", "Unlocked: Enjoy free shipping on all your orders!");
        });
    }

    private void startStyleQuiz() {
        String[] styles = {"Urban Streetwear", "Classic Elegance", "Minimalist", "Bohemian"};
        new AlertDialog.Builder(requireContext())
                .setTitle("What's your vibe today?")
                .setItems(styles, (dialog, which) -> {
                    String selected = styles[which];
                    Toast.makeText(getContext(), "Profile Updated: Your style is " + selected + "!", Toast.LENGTH_LONG).show();
                })
                .show();
    }

    private void showComingSoon(String feature, String description) {
        new AlertDialog.Builder(requireContext())
                .setTitle(feature)
                .setMessage(description + "\n\nThis feature is currently in development for the next update.")
                .setPositiveButton("Notify Me", (dialog, which) -> 
                    Toast.makeText(getContext(), "We'll let you know!", Toast.LENGTH_SHORT).show())
                .setNegativeButton("Close", null)
                .show();
    }

    private void showFeatureInfo(String title, String description) {
        Toast.makeText(getContext(), title + ": " + description, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
