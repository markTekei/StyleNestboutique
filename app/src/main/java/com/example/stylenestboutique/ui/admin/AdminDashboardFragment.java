package com.example.stylenestboutique.ui.admin;

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
import com.example.stylenestboutique.databinding.FragmentAdminDashboardBinding;

public class AdminDashboardFragment extends Fragment {

    private FragmentAdminDashboardBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAdminDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupClickListeners();
    }

    private void setupClickListeners() {
        binding.addProductButton.setOnClickListener(v -> 
            Navigation.findNavController(v).navigate(R.id.action_admin_to_addProduct));

        binding.manageInventoryButton.setOnClickListener(v -> 
            Toast.makeText(getContext(), "Inventory Management coming soon!", Toast.LENGTH_SHORT).show());

        binding.pendingOrdersButton.setOnClickListener(v -> 
            Toast.makeText(getContext(), "Order Management coming soon!", Toast.LENGTH_SHORT).show());

        binding.salesReportButton.setOnClickListener(v -> 
            Toast.makeText(getContext(), "Report Generation coming soon!", Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
