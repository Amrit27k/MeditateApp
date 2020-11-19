package com.example.yoga;

import android.content.Context;
import android.content.SharedPreferences;

public class CurrentUser {
    private String username, pass;
    Context context;
    SharedPreferences sharedPreferences;

    public CurrentUser(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("login_details", Context.MODE_PRIVATE);
    }

    public String getUsername() {
        username = sharedPreferences.getString("username", "");
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        sharedPreferences.edit().putString("username", username).commit();
    }

    public String getPass() {
        pass = sharedPreferences.getString("pass", "");
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
        sharedPreferences.edit().putString("pass", pass).commit();
    }

    public void removeUser() {
        sharedPreferences.edit().clear().commit();

    }


}
