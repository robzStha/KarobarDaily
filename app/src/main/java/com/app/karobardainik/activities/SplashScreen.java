package com.app.karobardainik.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.app.karobardainik.models.Categories;
import com.app.karobardainik.models.Category;
import com.app.karobardainik.server_protocols.ApiCalls;
import com.app.karobardainik.server_protocols.RetrofitSingleton;
import com.app.karobardainik.static_vars.StaticVars;
import com.app.karobardainik.utils.ConnectionMngr;
import com.app.karobardainik.utils.Opener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {

    ApiCalls apiCalls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiCalls = RetrofitSingleton.getApiCalls();
        if(ConnectionMngr.hasConnection(SplashScreen.this)) {
            getCategories();
        }
    }

    private void getCategories() {

        Call<Categories> categoriesCall = apiCalls.getMenuCategories();
        categoriesCall.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                if (response.isSuccessful()) {
                    StaticVars.categoryList = new ArrayList<>();
                    StaticVars.categoryList = response.body().getCategory();
                    Opener.BaseActivity(SplashScreen.this);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                Log.e("API Error from Retrofit", t.getMessage());
            }
        });
    }
}
