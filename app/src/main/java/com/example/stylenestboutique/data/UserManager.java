package com.example.stylenestboutique.data;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.stylenestboutique.model.User;
import com.google.gson.Gson;

public class UserManager {
    private static final String PREF_NAME = "user_prefs";
    private static final String KEY_USER = "current_user";
    private static UserManager instance;
    private final SharedPreferences sharedPreferences;
    private final Gson gson;

    private UserManager(Context context) {
        sharedPreferences = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public static synchronized UserManager getInstance(Context context) {
        if (instance == null) {
            instance = new UserManager(context);
        }
        return instance;
    }

    public void saveUser(User user) {
        String userJson = gson.toJson(user);
        sharedPreferences.edit().putString(KEY_USER, userJson).apply();
    }

    public User getUser() {
        String userJson = sharedPreferences.getString(KEY_USER, null);
        if (userJson == null) {
            return new User();
        }
        return gson.fromJson(userJson, User.class);
    }

    public void logout() {
        sharedPreferences.edit().remove(KEY_USER).apply();
    }
    
    public boolean isLoggedIn() {
        return sharedPreferences.contains(KEY_USER);
    }
}
