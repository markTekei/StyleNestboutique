package com.example.stylenestboutique.ui.categories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.stylenestboutique.R;
import com.example.stylenestboutique.data.ProductRepository;
import com.example.stylenestboutique.databinding.FragmentCategoriesBinding;

public class CategoriesFragment extends Fragment
        implements CategoryAdapter.OnCategoryClickListener {

    private FragmentCategoriesBinding binding;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {

        binding = FragmentCategoriesBinding.inflate(
                inflater,
                container,
                false
        );

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {

        super.onViewCreated(view, savedInstanceState);

        setupRecyclerView();
    }

    private void setupRecyclerView() {

        binding.categoriesRecycler.setLayoutManager(
                new LinearLayoutManager(requireContext())
        );

        // Updated to use the new Category model with images
        CategoryAdapter adapter = new CategoryAdapter(
                ProductRepository.getCategoriesWithImages(),
                this
        );

        binding.categoriesRecycler.setAdapter(adapter);
    }

    @Override
    public void onCategoryClick(String categoryName) {

        Bundle bundle = new Bundle();

        bundle.putString("category", categoryName);

        NavController navController =
                NavHostFragment.findNavController(this);

        navController.navigate(
                R.id.action_categories_to_list,
                bundle
        );
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();

        binding = null;
    }
}