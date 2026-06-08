package com.example.stylenestboutique.data;

import com.example.stylenestboutique.R;
import com.example.stylenestboutique.model.Category;
import com.example.stylenestboutique.model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    public static final String CATEGORY_MEN = "Men";
    public static final String CATEGORY_WOMEN = "Women";
    public static final String CATEGORY_KIDS = "Kids";
    public static final String CATEGORY_SHOES = "Shoes";
    public static final String CATEGORY_ACCESSORIES = "Accessories";

    private static final List<Product> products = new ArrayList<>();

    static {
        initializeProducts();
    }

    private static void initializeProducts() {
        if (!products.isEmpty()) return;

        // =========================
        // FEATURED / SALE TOP ITEM (Matches Banner)
        // =========================
        products.add(new Product("Classic Leather Sneakers", CATEGORY_SHOES, 4500, "Timeless white sneakers with premium finish.", R.drawable.shoes, true, "40"));

        // =========================
        // MEN'S WEAR
        // =========================
        products.add(new Product("Packers Varsity Jacket", CATEGORY_MEN, 7200, "Classic blue and white Packers varsity jacket.", R.drawable.wood1, false, "L"));
        products.add(new Product("Angels Varsity Jacket", CATEGORY_MEN, 6800, "Stylish green and white Angels varsity jacket.", R.drawable.wood2, true, "M"));
        products.add(new Product("Luxury Patterned Hoodie", CATEGORY_MEN, 5500, "Premium brown hoodie with designer patterns.", R.drawable.wood3, false, "XL"));
        products.add(new Product("Slim Fit Stretch Denim", CATEGORY_MEN, 4200, "High-quality indigo denim.", R.drawable.denim1, true, "32"));
        products.add(new Product("Classic Straight Cut Jeans", CATEGORY_MEN, 3800, "Timeless straight cut denim.", R.drawable.denim2, false, "34"));
        products.add(new Product("Oxford White Shirt", CATEGORY_MEN, 2500, "Essential white cotton shirt.", R.drawable.shirt1, false, "M"));
        products.add(new Product("Classic Blue Button-Down", CATEGORY_MEN, 2800, "Versatile blue shirt.", R.drawable.shirt2, true, "S"));
        products.add(new Product("Performance Sports Tracksuit", CATEGORY_MEN, 7500, "Breathable training suit.", R.drawable.tracksuit1, false, "M"));
        products.add(new Product("Urban Street Tracksuit", CATEGORY_MEN, 7000, "Modern urban look tracksuit.", R.drawable.tracksuit2, true, "L"));
        products.add(new Product("Tech-Fleece Hoodie", CATEGORY_MEN, 4800, "Warm and lightweight fleece hoodie.", R.drawable.track1, true, "S"));
        products.add(new Product("Athletic Jogger Pants", CATEGORY_MEN, 3200, "Comfortable joggers for training.", R.drawable.track2, false, "M"));
        products.add(new Product("Premium Crew Sweatshirt", CATEGORY_MEN, 3500, "Soft cotton sweatshirt.", R.drawable.sweatshirt1, false, "XL"));
        
        products.add(new Product("Minimalist Wool Sweater", CATEGORY_MEN, 3800, "Warm cream knit sweater.", R.drawable.sweatshirt2, false, "L"));

        // =========================
        // WOMEN'S WEAR
        // =========================
        products.add(new Product("Premium Business Blazer", CATEGORY_WOMEN, 6500, "Sharp executive blazer.", R.drawable.b, false, "M"));
        products.add(new Product("Executive Navy Blazer", CATEGORY_WOMEN, 6800, "Classic navy blue blazer.", R.drawable.b1, true, "S"));
        products.add(new Product("Slim Fit Charcoal Blazer", CATEGORY_WOMEN, 6200, "Modern charcoal blazer.", R.drawable.b2, false, "L"));
        products.add(new Product("Vintage Tweed Blazer", CATEGORY_WOMEN, 5800, "Classic tweed blazer.", R.drawable.b3, false, "M"));
        products.add(new Product("Modern Utility Jacket", CATEGORY_WOMEN, 5200, "Functional jacket for all seasons.", R.drawable.j1, true, "M"));
        products.add(new Product("Summer Floral Dress", CATEGORY_WOMEN, 4500, "Lightweight floral dress.", R.drawable.summerfloral, true, "S"));
        products.add(new Product("Vibrant Floral Summer Dress", CATEGORY_WOMEN, 4800, "Colorful floral pattern.", R.drawable.summerfloral1, false, "M"));
        products.add(new Product("Evening Silk Gown", CATEGORY_WOMEN, 7500, "Elegant silk gown.", R.drawable.evesilk, false, "L"));
        products.add(new Product("Midnight Silk Evening Gown", CATEGORY_WOMEN, 7500, "Stunning midnight black silk gown.", R.drawable.evesilk1, true, "S"));

        // =========================
        // SHOES
        // =========================
        products.add(new Product("Elite Runner Sneakers", CATEGORY_SHOES, 6200, "High-performance sneakers.", R.drawable.s1, true, "42"));
        products.add(new Product("Urban High-Tops", CATEGORY_SHOES, 5500, "Street-style high-tops.", R.drawable.shoes2, true, "43"));
        products.add(new Product("Chelsea Suede Boots", CATEGORY_SHOES, 7200, "Classic suede boots.", R.drawable.boot1, true, "41"));
        products.add(new Product("Premium Leather Boots", CATEGORY_SHOES, 7500, "High-quality leather boots.", R.drawable.boot2, false, "44"));
        products.add(new Product("Rugged Trail Boots", CATEGORY_SHOES, 6800, "Durable boots for adventures.", R.drawable.boot3, true, "42"));
        products.add(new Product("Pro Court Basketball Shoes", CATEGORY_SHOES, 6500, "Maximum support for the court.", R.drawable.s2, false, "45"));
        products.add(new Product("Lightweight Trail Runners", CATEGORY_SHOES, 5200, "Durable off-road shoes.", R.drawable.s3, false, "40"));
        products.add(new Product("Junior Pro Sneakers", CATEGORY_SHOES, 3500, "Stylish sneakers for little feet.", "https://images.unsplash.com/photo-1514989940723-e8e51635b782?q=80&w=800", true, "32"));

        // =========================
        // ACCESSORIES
        // =========================
        products.add(new Product("Chrono Silver Watch", CATEGORY_ACCESSORIES, 4500, "Luxury stainless steel timepiece.", R.drawable.watch1, false, "One Size"));
        products.add(new Product("Midnight Edition Watch", CATEGORY_ACCESSORIES, 4800, "Sleek all-black designer watch.", R.drawable.watch2, true, "One Size"));
        products.add(new Product("Executive Gold Watch", CATEGORY_ACCESSORIES, 5500, "Premium gold-plated timepiece.", R.drawable.watch4, false, "One Size"));
        products.add(new Product("Modern Smart Watch", CATEGORY_ACCESSORIES, 7500, "Latest tech in a stylish frame.", R.drawable.watch5, true, "One Size"));
        products.add(new Product("Heritage Mechanical Watch", CATEGORY_ACCESSORIES, 6800, "Classic hand-wound watch.", R.drawable.watch6, false, "One Size"));
        products.add(new Product("Designer Leather Handbag", CATEGORY_ACCESSORIES, 5200, "Elegant tan leather bag.", R.drawable.bag1, false, "Medium"));
        products.add(new Product("Quilted Evening Bag", CATEGORY_ACCESSORIES, 4200, "Stylish black quilted bag.", R.drawable.bag2, true, "Small"));
        products.add(new Product("Chic Crossbody Bag", CATEGORY_ACCESSORIES, 3500, "Perfect daily carry bag.", R.drawable.bag3, true, "Small"));
        products.add(new Product("Urban Leather Backpack", CATEGORY_ACCESSORIES, 4800, "Minimalist backpack.", R.drawable.bag4, false, "Large"));
        products.add(new Product("Luxury Tote Bag", CATEGORY_ACCESSORIES, 5800, "Spacious leather tote.", R.drawable.bag5, false, "Large"));
        products.add(new Product("Polarized Sun-Shades", CATEGORY_ACCESSORIES, 1800, "Premium UV protection eyewear.", R.drawable.sunglass1, true, "Standard"));
        products.add(new Product("Aviator Style Sunglasses", CATEGORY_ACCESSORIES, 2200, "Classic aviators.", R.drawable.sunglass2, false, "Standard"));

        // =========================
        // KIDS' WEAR
        // =========================
        products.add(new Product("Denim Adventure Jacket", CATEGORY_KIDS, 3200, "Durable denim for kids.", "https://images.unsplash.com/photo-1519238263530-99bdd11df2ea?q=80&w=800", false, "6Y"));
    }

    public static List<String> getCategories() {
        List<String> categories = new ArrayList<>();
        categories.add(CATEGORY_MEN);
        categories.add(CATEGORY_WOMEN);
        categories.add(CATEGORY_KIDS);
        categories.add(CATEGORY_SHOES);
        categories.add(CATEGORY_ACCESSORIES);
        return categories;
    }

    public static List<Category> getCategoriesWithImages() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(CATEGORY_MEN, R.drawable.wood1));
        categories.add(new Category(CATEGORY_WOMEN, R.drawable.b));
        categories.add(new Category(CATEGORY_KIDS, "https://images.unsplash.com/photo-1519238263530-99bdd11df2ea?q=80&w=800"));
        categories.add(new Category(CATEGORY_SHOES, R.drawable.s1));
        categories.add(new Category(CATEGORY_ACCESSORIES, R.drawable.watch1));
        return categories;
    }

    public static List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public static void addProduct(Product product) {
        products.add(0, product);
    }

    public static void deleteProduct(Product product) {
        products.remove(product);
    }

    public static List<Product> getFeaturedProducts() {
        return getAllProducts();
    }

    public static List<Product> getSaleProducts() {
        List<Product> saleProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.isOnSale()) {
                saleProducts.add(product);
            }
        }
        return saleProducts;
    }

    public static List<Product> getProductsByCategory(String category) {
        List<Product> filtered = new ArrayList<>();
        for (Product p : products) {
            if (p.getCategory().equalsIgnoreCase(category)) filtered.add(p);
        }
        return filtered;
    }

    public static List<Product> searchProducts(String query) {
        List<Product> results = new ArrayList<>();
        String q = query.toLowerCase();
        for (Product p : products) {
            if (p.getName().toLowerCase().contains(q)) results.add(p);
        }
        return results;
    }

    public static Product getProductByName(String name) {
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) return p;
        }
        return null;
    }
}
