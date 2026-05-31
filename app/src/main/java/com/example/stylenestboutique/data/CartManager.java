package com.example.stylenestboutique.data;

import com.example.stylenestboutique.model.Product;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<Product> cartItems;
    private OnCartChangedListener listener;

    public interface OnCartChangedListener {
        void onCartChanged(int newCount);
    }

    private CartManager() {
        cartItems = new ArrayList<>();
    }

    public static synchronized CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void setListener(OnCartChangedListener listener) {
        this.listener = listener;
    }

    public void addProduct(Product product) {
        cartItems.add(product);
        if (listener != null) {
            listener.onCartChanged(cartItems.size());
        }
    }

    public void removeProduct(Product product) {
        cartItems.remove(product);
        if (listener != null) {
            listener.onCartChanged(cartItems.size());
        }
    }

    public List<Product> getCartItems() {
        return cartItems;
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product product : cartItems) {
            total += product.getPrice();
        }
        return total;
    }

    public void clearCart() {
        cartItems.clear();
        if (listener != null) {
            listener.onCartChanged(0);
        }
    }
}
