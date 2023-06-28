package com.example.myapplication.bottomnavigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ViewFlipper;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;


public class FragHome extends Fragment {
    private View view;

    ViewFlipper v_filpper;
    Button button1;
    Button button2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.frag_home,container,false);

        int images[] = {R.drawable.slide1, R.drawable.slide2, R.drawable.slide3};
        v_filpper = view.findViewById(R.id.v_flipper);

        for (int image : images) {
            flipperImages(image);
        }












        button1 = view.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReportMain.class);
                startActivity(intent);

            }
        });


        button2 = view.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RecommandMain.class);
                startActivity(intent);

            }
        });


        return view;
    }


    public void flipperImages(int image) {
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(image);

        v_filpper.addView(imageView);
        v_filpper.setFlipInterval(2000);
        v_filpper.setAutoStart(true);

        v_filpper.setInAnimation(getContext(), android.R.anim.slide_in_left);
        v_filpper.setOutAnimation(getContext(), android.R.anim.slide_out_right);
    }

}




