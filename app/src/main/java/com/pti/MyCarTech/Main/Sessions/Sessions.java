package com.pti.MyCarTech.Main.Sessions;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pti.MyCarTech.R;
import com.pti.MyCarTech.databaseHandler;
import com.pti.MyCarTech.models.session_models.Session_model;

import java.util.List;

public class Sessions extends Fragment {
    private String TAG = "Sessions";
    RecyclerView mRecyclerView;
    FirebaseDatabase mDatabase;
    FirebaseUser mUser;

    public Sessions() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.activity_session, container, false);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());

        //Initializing our Recyclerview
        mRecyclerView = (RecyclerView) root.findViewById(R.id.cardList);
        if (mRecyclerView != null) {
            //to enable optimization of recyclerview
            mRecyclerView.setHasFixedSize(true);
        }
        mRecyclerView.setLayoutManager(llm);

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();

        RecyclerAdapter adapter = new RecyclerAdapter(mDatabase, mUser);


        mRecyclerView.setAdapter(adapter);


        return root;
    }
}
