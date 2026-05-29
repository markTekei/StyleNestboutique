package com.example.stylenestboutique.data;

import com.example.stylenestboutique.model.Product;
import java.util.ArrayList;
import java.util.List;

public class WishlistManager {
    private static WishlistManager instance;
    private final List<Product> wishlistItems;

    private WishlistManager() {
        wishlistItems = new ArrayList<>();
    }

    public static synchronized WishlistManager getInstance() {
        if (instance == null) {
            instance = new WishlistManager();
        }
        return instance;
    }

    public void toggleWishlist(Product product) {
        if (isInWishlist(product)) {
            wishlistItems.removeIf(p -> p.getName().equals(product.getName()));
        } else {
            wishlistItems.add(product);
        }
    }

    public boolean isInWishlist(Product product) {
        for (Product p : wishlistItems) {
            if (p.getName().equals(product.getName())) {
                return true;
            }
        }
        return false;
    }

    public List<Product> getWishlistItems() {
        return new ArrayList<>(wishlistItems);
    }

    public void clearWishlist() {
        wishlistItems.clear();
    }
}