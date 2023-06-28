package com.example.myapplication.bottomnavigation;

import androidx.fragment.app.Fragment;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import android.view.View;

import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private FragHome fragHome;
    private FragRecipe fragRecipe;
    private FragMenu fragMenu;
    private FragSearch fragSearch;
    private FragFavorite fragFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_activity);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.home) {
                    setFrag(0);
                } else if (menuItem.getItemId() == R.id.recipe) {
                    setFrag(1);
                } else if (menuItem.getItemId() == R.id.menu) {
                    setFrag(2);
                } else if (menuItem.getItemId() == R.id.search) {
                    setFrag(3);
                } else if (menuItem.getItemId() == R.id.favorite) {
                    setFrag(4);
                }
                return true;

            }
        });

        fragHome = new FragHome();
        fragRecipe = new FragRecipe();
        fragMenu = new FragMenu();
        fragSearch = new FragSearch();
        fragFavorite = new FragFavorite();
        setFrag(0); //첫 프래그먼트 화면을 무엇으로 지정해줄 것인지 선택

    }

    //프레그먼트 교체가 일어나는 실행문이다.
    private void setFrag(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction(); //실제적인 프레그먼트 교체에서 사용
        switch (n) {
            case 0:
                ft.replace(R.id.main_frame,fragHome);
                ft.commit(); //저장의미
                break;
            case 1:
                ft.replace(R.id.main_frame, fragRecipe);
                ft.commit(); //저장의미
                break;
            case 2:
                ft.replace(R.id.main_frame,fragMenu);
                ft.commit(); //저장의미
                break;
            case 3:
                ft.replace(R.id.main_frame, fragSearch);
                ft.commit(); //저장의미
                break;
            case 4:
                ft.replace(R.id.main_frame, fragFavorite);
                ft.commit(); //저장의미
                break;
        }
    }
}
