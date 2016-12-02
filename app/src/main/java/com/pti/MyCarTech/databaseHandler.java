package com.pti.MyCarTech;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pti.MyCarTech.models.User;
import com.pti.MyCarTech.models.session_models.Session_model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fernando on 01-12-2016.
 */

public class databaseHandler {
    private DatabaseReference mDatabase;
    private String TAG ="databaseHandler";
    private User user = new User();

    public List<Session_model> getSessionsList() {
        return sessionsList;
    }

    public void setSessionsList(List<Session_model> sessionsList) {
        this.sessionsList = sessionsList;
    }

    private List<Session_model> sessionsList = new ArrayList<>();

    public databaseHandler(FirebaseUser firebaseUser){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        user = fetchUserData(firebaseUser);
    }

    public void WriteUser(){
        //myRef = database.getReference(mRef);
        //myRef.setValue("Hello, World!");
        //Writes to Firebase Database User model
        mDatabase.child("users").child(user.getId()).setValue(user);

    }
    public void ReadSessions(){
        final List<Session_model> sessionsList = new ArrayList<Session_model>();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("sessions").child(user.getId()).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sessionsList.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Session_model session = postSnapshot.getValue(Session_model.class);
                    session.setSession_id(postSnapshot.getKey());
                    sessionsList.add(session);
                }
                Log.d(TAG,"session: " +sessionsList.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void Read(){
        // to update data in realtime, you should add a ValueEventListener to the reference created
        //The onDataChange() method in this class is triggered once when the listener is attached and again every time the data changes, including the children.
        // Read from the database
        mDatabase.child("users").child(user.getId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                User user = dataSnapshot.getValue(User.class);
                Log.d(TAG, "User data is: " + user.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private User fetchUserData(FirebaseUser firebaseUser){
        User user = new User();
        user.setEmail(firebaseUser.getEmail());
        user.setId(firebaseUser.getUid());
        user.setPhoto(firebaseUser.getPhotoUrl().toString());
        user.setName(firebaseUser.getDisplayName());
        Log.d(TAG, "fetched data: " + user.toString());

        return user;
    }
}
