package com.example.stylenestboutique;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.example.stylenestboutique.data.CartManager;
import com.example.stylenestboutique.databinding.ActivityMainBinding;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.color.DynamicColors;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize Theme before super.onCreate to prevent flickering
        applySavedTheme();

        // Modern Feature: Material You Dynamic Colors
        DynamicColors.applyToActivitiesIfAvailable(this.getApplication());
        
        // Splash Screen
        SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupInsets();
        setupNavigation();
        
        // Reactive Cart Badge
        updateCartBadge();
        CartManager.getInstance().setListener(newCount -> updateCartBadge());
    }

    private void applySavedTheme() {
        SharedPreferences prefs = getSharedPreferences("theme_prefs", Context.MODE_PRIVATE);
        if (prefs.contains("is_dark_mode")) {
            boolean isDark = prefs.getBoolean("is_dark_mode", false);
            AppCompatDelegate.setDefaultNightMode(isDark ? 
                    AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public void updateCartBadge() {
        int count = CartManager.getInstance().getCartItems().size();
        BadgeDrawable badge = binding.navView.getOrCreateBadge(R.id.navigation_cart);
        if (count > 0) {
            badge.setVisible(true);
            badge.setNumber(count);
        } else {
            badge.setVisible(false);
        }
    }

    private void setupInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (view, windowInsets) -> {
            Insets systemBars = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(0, systemBars.top, 0, 0);
            return windowInsets;
        });
    }

    private void setupNavigation() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();
            NavigationUI.setupWithNavController(binding.navView, navController);

            navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
                if (destination.getLabel() != null) {
                    binding.toolbar.setTitle(destination.getLabel());
                }
            });
        }
    }
}
