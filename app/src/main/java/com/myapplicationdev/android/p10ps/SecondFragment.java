package com.myapplicationdev.android.p10ps;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class SecondFragment extends Fragment {

    Button btnChangeColor;

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View secondFragment = inflater.inflate(R.layout.fragment_second, container, false);
        btnChangeColor = secondFragment.findViewById(R.id.btnChangeColor);

        btnChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                secondFragment.setBackgroundColor(Color.RED);

            }
        });

        return secondFragment;
    }

}
