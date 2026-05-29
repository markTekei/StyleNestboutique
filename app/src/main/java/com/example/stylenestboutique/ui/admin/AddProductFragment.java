package com.example.stylenestboutique.ui.admin;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.bumptech.glide.Glide;
import com.example.stylenestboutique.R;
import com.example.stylenestboutique.data.ProductRepository;
import com.example.stylenestboutique.databinding.FragmentAddProductBinding;
import com.example.stylenestboutique.model.Product;

public class AddProductFragment extends Fragment {

    private FragmentAddProductBinding binding;
    private Uri selectedImageUri;

    private final ActivityResultLauncher<String> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    selectedImageUri = uri;
                    binding.selectedProductImage.setAlpha(1.0f);
                    binding.imagePlaceholder.setVisibility(View.GONE);
                    Glide.with(this).load(uri).centerCrop().into(binding.selectedProductImage);
                }
            }
    );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddProductBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupCategoryDropdown();
        
        binding.imagePickerCard.setOnClickListener(v -> imagePickerLauncher.launch("image/*"));
        
        binding.saveButton.setOnClickListener(v -> saveProduct());
    }

    private void setupCategoryDropdown() {
        String[] categories = {
            ProductRepository.CATEGORY_MEN,
            ProductRepository.CATEGORY_WOMEN,
            ProductRepository.CATEGORY_KIDS,
            ProductRepository.CATEGORY_SHOES,
            ProductRepository.CATEGORY_ACCESSORIES
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, categories);
        binding.categoryDropdown.setAdapter(adapter);
    }

    private void saveProduct() {
        String name = binding.nameEditText.getText().toString().trim();
        String category = binding.categoryDropdown.getText().toString();
        String priceStr = binding.priceEditText.getText().toString().trim();
        String description = binding.descriptionEditText.getText().toString().trim();

        if (name.isEmpty() || category.isEmpty() || priceStr.isEmpty() || description.isEmpty()) {
            Toast.makeText(getContext(), "Please fill all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedImageUri == null) {
            Toast.makeText(getContext(), "Please select a product photo", Toast.LENGTH_SHORT).show();
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Invalid price format", Toast.LENGTH_SHORT).show();
            return;
        }

        // Store URI string in the imageUrl field
        Product newProduct = new Product(name, category, price, description, selectedImageUri.toString(), false, "M");

        ProductRepository.addProduct(newProduct);
        Toast.makeText(getContext(), "Product added successfully!", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(requireView()).popBackStack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
