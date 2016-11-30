package com.pti.MyCarTech.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pti.MyCarTech.Main.*;
import com.pti.MyCarTech.R;

/**
 * Created by fernando on 15-11-2016.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "RegisterActivity";

    private EditText mEmailField, mPasswordField;
    private LinearLayout linearlayout;
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    // [START declare_auth_listener]
    private FirebaseAuth.AuthStateListener mAuthListener;
    // [END declare_auth_listener]


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerlayout);

        linearlayout = (LinearLayout) findViewById(R.id.linearLayout);

        //Views
        mEmailField = (EditText) findViewById(R.id.input_email);
        mPasswordField = (EditText) findViewById(R.id.input_password);

        //Buttons
        findViewById(R.id.bLogin).setOnClickListener(this);
        findViewById(R.id.bRegister).setOnClickListener(this);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        // [START auth_state_listener]
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // [START_EXCLUDE]
                //updateUI(user);
                // [END_EXCLUDE]
            }

        };


        // [END auth_state_listener]



}


    // [START on_start_add_listener]
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    // [END on_start_add_listener]

    // [START on_stop_remove_listener]
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    // [END on_stop_remove_listener]

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressDialog();

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Snackbar snackbar = Snackbar
                                    .make(linearlayout, R.string.auth_failed, Snackbar.LENGTH_SHORT)
                                    .setAction("CLOSE", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                        }
                                    });
                            snackbar.setActionTextColor(getResources().getColor(R.color.colorAccent));
                            snackbar.show();
                        }
                        else {
                            if (task.isSuccessful()) {
                                Snackbar snackbar = Snackbar
                                        .make(linearlayout, "Success!", Snackbar.LENGTH_SHORT)
                                        .setAction("CLOSE", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                            }
                                        });
                                snackbar.setActionTextColor(getResources().getColor(R.color.colorAccent));
                                snackbar.show();
                            }
                            signOut();
                        }
                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }
    private void signOut() {
        mAuth.signOut();
    }

    private boolean validateForm() {

        Snackbar snackbar;
        // Changing action button text color


        boolean valid = true;

        String email = mEmailField.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            snackbar = Snackbar
                    .make(linearlayout, "Insert Valid Email", Snackbar.LENGTH_SHORT)
                    .setAction("CLOSE", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    });
            snackbar.setActionTextColor(getResources().getColor(R.color.colorAccent));
            snackbar.show();
            valid = false;
        }
        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            snackbar = Snackbar
                    .make(linearlayout, "Insert Valid Password", Snackbar.LENGTH_SHORT)
                    .setAction("CLOSE", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    });
            snackbar.setActionTextColor(getResources().getColor(R.color.colorAccent));
            snackbar.show();
            valid = false;
        }
        return valid;
    }
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.bRegister) {
            createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
        } else if (i == R.id.bLogin) {
            startActivity(new Intent(getApplication(), LoginActivity.class));
            RegisterActivity.this.finish();
        }
    }
}