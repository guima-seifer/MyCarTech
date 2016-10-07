package com.pti.MyCarTech.Main;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pti.MyCarTech.R;

public class Profile extends Fragment {

    public Profile() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final Sessions sessions = new Sessions();
        View root = inflater.inflate(R.layout.activity_profile, container, false);

        return root;
    }
}
