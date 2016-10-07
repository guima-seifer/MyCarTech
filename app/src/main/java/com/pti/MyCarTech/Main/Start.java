package com.pti.MyCarTech.Main;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pti.MyCarTech.Main.Acquire.Session_capture;
import com.pti.MyCarTech.R;

public class Start extends Fragment {
    public Start() {
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
        View root = inflater.inflate(R.layout.activity_start, container, false);
        final Intent intent = new Intent(getActivity(), Session_capture.class);


        final Button start = (Button) root.findViewById(R.id.b_start);

        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        return root;
    }


}
