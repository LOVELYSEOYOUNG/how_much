package com.example.myapplication.bottomnavigation;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FragFavorite extends Fragment {
    private View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.frag_favorite,container,false);

        DBHelper DB = new DBHelper(getContext());

//        List<Integer> drawables = Arrays.asList(R.drawable.apple, R.drawable.pear,
//                R.drawable.mandarin, R.drawable.banana, R.drawable.kiwi, R.drawable.pineapple,
//                R.drawable.orange, R.drawable.lemon, R.drawable.cherry, R.drawable.mango);

        ArrayList<DataModel> favDataSet = new ArrayList<>();

        Cursor res = DB.getdata();

        while(res.moveToNext()) {
            String title = res.getString(0);
            String dpr1 = res.getString(1);
            String dpr3 = res.getString(2);
            Integer image = res.getInt(3);
            favDataSet.add(new DataModel(title, image, dpr1, dpr3));
        }

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context) this);
        recyclerView.setLayoutManager(linearLayoutManager);  // LayoutManager 설정

        CustomAdapter customAdapter = new CustomAdapter(favDataSet, DB);
        recyclerView.setAdapter(customAdapter); // 어댑터 설정


        return view;
    }
}
