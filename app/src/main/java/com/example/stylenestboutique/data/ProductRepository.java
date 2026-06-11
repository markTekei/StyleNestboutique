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
        // FEATURED / SALE TOP ITEM
        // =========================
        products.add(new Product("Classic Leather Sneakers", CATEGORY_SHOES, 4500, "Timeless white sneakers with premium finish.", R.drawable.shoes, true, "42"));

        // =========================
            // MEN'S WEAR - Cleaned to remove style repetition
        // =========================
        products.add(new Product("Green Bay Varsity Jacket", CATEGORY_MEN, 7200, "Classic green and yellow varsity jacket with premium wool.", R.drawable.wood1, false, "L"));
        products.add(new Product("Urban Monogram Hoodie", CATEGORY_MEN, 5500, "Premium brown hoodie with designer monogram patterns.", R.drawable.wood3, false, "XL"));
        products.add(new Product("Vintage Indigo Slim Jeans", CATEGORY_MEN, 4200, "High-quality stretch denim with a vintage wash.", R.drawable.denim1, true, "32"));
        products.add(new Product("Oxford Essential White Shirt", CATEGORY_MEN, 2500, "Essential white cotton shirt for every wardrobe.", R.drawable.shirt1, false, "M"));
        products.add(new Product("Pro-Fit Athletic Tracksuit", CATEGORY_MEN, 7500, "Full breathable training suit for peak performance.", R.drawable.tracksuit1, false, "M"));
        products.add(new Product("Zip-Up Tech Fleece Hoodie", CATEGORY_MEN, 4800, "Warm and lightweight tech fleece hoodie.", R.drawable.track1, true, "S"));
        products.add(new Product("Signature Athletic Joggers", CATEGORY_MEN, 3200, "Comfortable tapered joggers for training.", R.drawable.track2, false, "M"));
        products.add(new Product("All-Weather Running Jacket", CATEGORY_MEN, 4500, "Water-resistant windbreaker for outdoor activities.", R.drawable.track4, true, "M"));
        products.add(new Product("Premium Heather Grey Crew", CATEGORY_MEN, 3500, "Extra soft cotton crewneck sweatshirt.", R.drawable.sweatshirt1, false, "XL"));
        products.add(new Product("Minimalist Oatmeal Sweater", CATEGORY_MEN, 3800, "Warm cream-colored knit sweater for layering.", R.drawable.sweatshirt2, false, "L"));

        // =========================
        // WOMEN'S WEAR
        // =========================
        products.add(new Product("Professional Navy Blazer", CATEGORY_WOMEN, 6500, "Sharp executive blazer for business meetings.", R.drawable.b, false, "M"));
        products.add(new Product("Tailored Midnight Blazer", CATEGORY_WOMEN, 6800, "Classic fitted navy blue blazer.", R.drawable.b1, true, "S"));
        products.add(new Product("Slim Fit Charcoal Jacket", CATEGORY_WOMEN, 6200, "Modern charcoal grey blazer with a sleek cut.", R.drawable.b2, false, "L"));
        products.add(new Product("Heritage Tweed Blazer", CATEGORY_WOMEN, 5800, "Classic textured tweed blazer for a timeless look.", R.drawable.b3, false, "M"));
        products.add(new Product("Urban Utility Bomber Jacket", CATEGORY_WOMEN, 5200, "Functional black jacket for everyday style.", R.drawable.j1, true, "M"));
        products.add(new Product("Azure Floral Summer Dress", CATEGORY_WOMEN, 4500, "Lightweight floral dress for sunny days.", R.drawable.summerfloral, true, "S"));
        products.add(new Product("Peony Garden Midi Dress", CATEGORY_WOMEN, 4800, "Vibrant colorful floral pattern on soft fabric.", R.drawable.summerfloral1, false, "M"));
        products.add(new Product("Wildflower Meadow Maxi", CATEGORY_WOMEN, 5200, "Flowy floral maxi dress with elegant drape.", R.drawable.summerfloral2, true, "L"));
        products.add(new Product("Golden Hour Sun Dress", CATEGORY_WOMEN, 4200, "Soft yellow floral tones for a bright look.", R.drawable.summerfloral3, false, "S"));
        products.add(new Product("Tropical Paradise Gown", CATEGORY_WOMEN, 6000, "Vibrant tropical patterns for a bohemian style.", R.drawable.summerfloral4, false, "M"));
        products.add(new Product("Rose Garden Cocktail Dress", CATEGORY_WOMEN, 5500, "Elegant red rose print for special occasions.", R.drawable.summerfloral6, true, "S"));
        products.add(new Product("Black Silk Evening Gown", CATEGORY_WOMEN, 8500, "Luxurious pure silk gown for formal events.", R.drawable.evesilk, false, "L"));
        products.add(new Product("Starlight Navy Silk Gown", CATEGORY_WOMEN, 8500, "Stunning navy blue silk evening dress.", R.drawable.evesilk1, true, "S"));

        // =========================
        // SHOES
        // =========================
        products.add(new Product("Ultra-Light Runner Pro", CATEGORY_SHOES, 6200, "High-performance running sneakers.", R.drawable.s1, true, "42"));
        products.add(new Product("City-Style High-Top Sneaker", CATEGORY_SHOES, 5500, "Modern street-style high-tops in black.", R.drawable.shoes2, true, "43"));
        products.add(new Product("Ash Suede Chelsea Boots", CATEGORY_SHOES, 7200, "Classic suede Chelsea boots with pull tabs.", R.drawable.boot1, true, "41"));
        products.add(new Product("Heritage Tan Leather Boots", CATEGORY_SHOES, 7500, "Premium hand-crafted leather boots.", R.drawable.boot2, false, "44"));
        products.add(new Product("Dark Mocha Combat Boots", CATEGORY_SHOES, 6800, "Durable rugged boots for all terrains.", R.drawable.boot3, true, "42"));
        products.add(new Product("Apex Court Basketball Shoes", CATEGORY_SHOES, 6500, "Professional basketball shoes with ankle support.", R.drawable.s2, false, "45"));
        products.add(new Product("Lightweight Trail Cross-Trainers", CATEGORY_SHOES, 5200, "Versatile shoes for gym and light trails.", R.drawable.s3, false, "40"));

        // =========================
        // ACCESSORIES
        // =========================
        products.add(new Product("Sterling Chronograph Watch", CATEGORY_ACCESSORIES, 4500, "Luxury stainless steel timepiece with date function.", R.drawable.watch1, false, "One Size"));
        products.add(new Product("Noir Edition Designer Watch", CATEGORY_ACCESSORIES, 4800, "Sleek all-black designer watch with leather strap.", R.drawable.watch2, true, "One Size"));
        products.add(new Product("Majestic Gold Dress Watch", CATEGORY_ACCESSORIES, 5500, "Premium gold-plated timepiece for formal wear.", R.drawable.watch4, false, "One Size"));
        products.add(new Product("Titanium Hybrid Smartwatch", CATEGORY_ACCESSORIES, 7500, "Advanced technology in a classic watch frame.", R.drawable.watch5, true, "One Size"));
        products.add(new Product("Classic Mechanical Skeleton", CATEGORY_ACCESSORIES, 6800, "Exquisite hand-wound watch showing internal gears.", R.drawable.watch6, false, "One Size"));
        products.add(new Product("Tuscany Leather Handbag", CATEGORY_ACCESSORIES, 5200, "Elegant tan Italian leather handbag.", R.drawable.bag1, false, "Medium"));
        products.add(new Product("Quilted Midnight Clutch", CATEGORY_ACCESSORIES, 4200, "Sophisticated black quilted evening bag.", R.drawable.bag2, true, "Small"));
        products.add(new Product("Chic Crimson Crossbody", CATEGORY_ACCESSORIES, 3500, "Compact and stylish daily crossbody bag.", R.drawable.bag3, true, "Small"));
        products.add(new Product("Metropolitan Leather Backpack", CATEGORY_ACCESSORIES, 4800, "Minimalist leather backpack for professionals.", R.drawable.bag4, false, "Large"));
        products.add(new Product("Grande Saffiano Tote", CATEGORY_ACCESSORIES, 5800, "Spacious textured leather tote bag.", R.drawable.bag5, false, "Large"));
        products.add(new Product("Weekender Leather Duffel", CATEGORY_ACCESSORIES, 6500, "Spacious and stylish duffel for short trips.", R.drawable.bag7, false, "Large"));
        products.add(new Product("Onyx Polarized Aviators", CATEGORY_ACCESSORIES, 1800, "Premium UV protection with polarized lenses.", R.drawable.sunglass1, true, "Standard"));
        products.add(new Product("Classic Gold Rim Aviators", CATEGORY_ACCESSORIES, 2200, "Timeless aviator design with gold-tone frames.", R.drawable.sunglass2, false, "Standard"));
        products.add(new Product("Amber Retro Wayfarers", CATEGORY_ACCESSORIES, 2000, "Iconic retro design with tortoise shell finish.", R.drawable.sunglass3, true, "Standard"));

        // =========================
        // KIDS' WEAR
        // =========================
        // --- BOYS ---
        products.add(new Product("Boy's Urban Graphic Tee", CATEGORY_KIDS, 1500, "Comfortable cotton tee with a cool urban print.", R.drawable.boy, false, "8Y"));
        products.add(new Product("Boy's Nautical Stripe Sweater", CATEGORY_KIDS, 2500, "Soft knit sweater with classic nautical stripes.", R.drawable.boy1, true, "10Y"));
        products.add(new Product("Boy's Adventure Parka", CATEGORY_KIDS, 5500, "Heavy-duty insulated parka for winter adventures.", R.drawable.boy2, false, "12Y"));
        products.add(new Product("Boy's Rugged Denim Jacket", CATEGORY_KIDS, 3200, "Timeless denim jacket with a rugged finish.", R.drawable.boy3, false, "10Y"));
        products.add(new Product("Boy's Summer Chino Shorts", CATEGORY_KIDS, 1800, "Lightweight chino shorts for warm weather play.", R.drawable.boy4, true, "8Y"));
        products.add(new Product("Boy's Streetstyle Hoodie", CATEGORY_KIDS, 2800, "Trendy fleece hoodie with a modern streetwear look.", R.drawable.boy5, false, "14Y"));
        products.add(new Product("Boy's Athletic Polo", CATEGORY_KIDS, 2000, "Breathable cotton polo for active boys.", R.drawable.boy6, false, "10Y"));
        products.add(new Product("Boy's Cargo Utility Pants", CATEGORY_KIDS, 2600, "Durable pants with multiple pockets for exploring.", R.drawable.boy7, false, "12Y"));
        products.add(new Product("Boy's Winter Beanie", CATEGORY_KIDS, 1200, "Warm wool-blend beanie with a cozy fit.", R.drawable.boy8, true, "One Size"));
        products.add(new Product("Boy's Casual Canvas Slip-Ons", CATEGORY_KIDS, 3000, "Easy-wear canvas shoes for everyday activities.", R.drawable.boy9, false, "34"));

        // --- GIRLS ---
        products.add(new Product("Girl's Polka Dot Party Dress", CATEGORY_KIDS, 2800, "Sweet polka dot dress with a playful flare.", R.drawable.girl, true, "6Y"));
        products.add(new Product("Girl's Ballerina Tutu Skirt", CATEGORY_KIDS, 2500, "Layered tulle skirt for little dancers.", R.drawable.girl1, false, "4Y"));
        products.add(new Product("Girl's Ruffled Floral Blouse", CATEGORY_KIDS, 2200, "Charming floral blouse with elegant ruffle details.", R.drawable.girl2, false, "8Y"));
        products.add(new Product("Girl's Classic Denim Skirt", CATEGORY_KIDS, 1800, "Versatile denim skirt that pairs well with anything.", R.drawable.girl3, true, "10Y"));
        products.add(new Product("Girl's Royal Velvet Gown", CATEGORY_KIDS, 6500, "Stunning velvet gown for special formal occasions.", R.drawable.girl4, false, "12Y"));
        products.add(new Product("Girl's Soft Knit Cardigan", CATEGORY_KIDS, 2600, "Cozy cardigan for layering over dresses and tops.", R.drawable.girl5, false, "8Y"));
        products.add(new Product("Girl's Butterfly Embroidered Top", CATEGORY_KIDS, 1900, "Pretty top featuring colorful butterfly embroidery.", R.drawable.girl6, true, "6Y"));
        products.add(new Product("Girl's Sparkling Sequin Dress", CATEGORY_KIDS, 4800, "Eye-catching dress with shimmering sequins.", R.drawable.girl7, false, "10Y"));
        products.add(new Product("Girl's Rainbow Stripe Leggings", CATEGORY_KIDS, 1500, "Stretchy and vibrant leggings for all-day comfort.", R.drawable.girl8, false, "8Y"));
        products.add(new Product("Girl's Summer Straw Hat", CATEGORY_KIDS, 1400, "Stylish straw hat for sun protection.", R.drawable.girl9, true, "One Size"));
        products.add(new Product("Girl's Elegant Lace Headband", CATEGORY_KIDS, 800, "Beautiful lace accessory for any hairstyle.", R.drawable.girl10, false, "One Size"));

        products.add(new Product("Kids' Classic Indigo Denim", CATEGORY_KIDS, 2500, "Durable and stylish denim for growing kids.", R.drawable.denim1, false, "6Y"));
        products.add(new Product("Little Star Sparkle Sneakers", CATEGORY_KIDS, 2800, "Comfy and stylish sneakers for little feet.", R.drawable.s2, true, "30"));
        products.add(new Product("Boys' Casual Oxford Shirt", CATEGORY_KIDS, 2200, "Smart casual white shirt for special occasions.", R.drawable.shirt1, false, "10Y"));
        products.add(new Product("Girls' Floral Meadow Gown", CATEGORY_KIDS, 4200, "Elegant long dress with beautiful floral patterns.", R.drawable.summerfloral, false, "12Y"));
        products.add(new Product("Junior Pro Running Shoes", CATEGORY_KIDS, 3500, "High-performance running shoes for juniors.", R.drawable.s1, true, "34"));
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
        categories.add(new Category(CATEGORY_KIDS, R.drawable.boy));
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
