package com.pti.MyCarTech.Main.Sessions;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pti.MyCarTech.R;
import com.pti.MyCarTech.models.session_models.Session_model;

import java.util.ArrayList;

/**
 * Created by fernando on 02-12-2016.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder>{
    private ArrayList<Session_model> sessionsList = new ArrayList<>();
    private String TAG = "RecyclerAdapter";

    public RecyclerAdapter(FirebaseDatabase fireDatabase ,FirebaseUser fireUser) {
        DatabaseReference ref = fireDatabase.getInstance().getReference();

        ref.child("sessions").child(fireUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("Got snapshot with "+snapshot.getChildrenCount()+" children");
                sessionsList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Session_model session = postSnapshot.getValue(Session_model.class);
                    session.setSession_id(postSnapshot.getKey());
                    sessionsList.add(session);
                    Log.d(TAG, "session: " + session.toString());
                }
                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "The read failed: " + databaseError.getMessage());
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Session_model item = sessionsList.get(position);
        holder.getItemTitle().setText(item.getSession_id());
        holder.getItemDate().setText(item.getData().toString());
        holder.getItemConsumo().setText(item.getCons_medio().toString());
        holder.getItemDistance().setText(item.getDist_percorrida().toString());
    }

    @Override
    public int getItemCount() {
        return sessionsList.size();
    }
}