package com.example.myapplication.bottomnavigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class FragMenu extends Fragment {
    private View view;
    private ListView listView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        view = inflater.inflate(R.layout.frag_menu,container,false);

        //원래는 extends AppCompatActivity 해야 하는 데, 상속은 1개만 되니까

        //원코드 ListView listView = findViewById(R.id.listview); 안 먹혔음
        listView = (ListView) view.findViewById(R.id.listview);

        List<String> list = new ArrayList<>();

        list.add("    식량작물");
        list.add("    채소류");
        list.add("    특용작물");
        list.add("    과일류");
        list.add("    수산물");


        // 원래는 매개변수 getApplicationContext()인데, extends AppCompatActivity 못해서 안 먹힘
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,list);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position ==0){
                    //clicked 식량작물
                }else if(position==1){
                    //clicked 채소류
                }else if(position==2){
                    //clicked 특용작물
                }else if(position==3){
                    //clicked 과일류
                    Intent intent = new Intent(getActivity(), FruitMain.class);
                    startActivity(intent);
                }else{
                    //clicked 수산물
                }
            }
        });








        return view;
    }
}

