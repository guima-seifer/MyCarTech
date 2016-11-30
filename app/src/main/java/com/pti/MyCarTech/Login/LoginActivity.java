package com.pti.MyCarTech.Login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pti.MyCarTech.Main.MainActivity;
import com.pti.MyCarTech.R;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";
    private EditText mEmailField, mPasswordField;
    private LinearLayout linearlayout;
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]
    // [START declare_auth_listener]
    private FirebaseAuth.AuthStateListener mAuthListener;
    // [END declare_auth_listener]
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        linearlayout = (LinearLayout) findViewById(R.id.linearLayout);

        //Views
        mEmailField = (EditText) findViewById(R.id.input_email);
        mPasswordField = (EditText) findViewById(R.id.input_password);

        //Buttons
        findViewById(R.id.button_facebook).setOnClickListener(this);
        findViewById(R.id.github_button).setOnClickListener(this);
        findViewById(R.id.sign_in_button).setOnClickListener(this);
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




    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressDialog();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());


                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            snackbar = Snackbar
                                    .make(linearlayout, R.string.auth_failed, Snackbar.LENGTH_SHORT)
                                    .setAction("CLOSE", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                        }
                                    });
                            snackbar.setActionTextColor(getResources().getColor(R.color.colorAccent));
                            snackbar.show();
                        }

                        // [START_EXCLUDE]
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Welcome " + task.getResult().getUser().getDisplayName(),
                                    Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplication(), MainActivity.class));
                            LoginActivity.this.finish();
                            //signOut();
                        }
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });

        // [END sign_in_with_email]
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
            startActivity(new Intent(getApplication(), RegisterActivity.class));
            LoginActivity.this.finish();
        } else if (i == R.id.bLogin) {
            signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
        } else if (i == R.id.sign_in_button) {
            startActivity(new Intent(getApplication(), GoogleSignInActivity.class));
            LoginActivity.this.finish();
        } else if (i == R.id.github_button) {
            Intent intent = new Intent(LoginActivity.this, GitHubLoginActivity.class);
            startActivity(intent);
            LoginActivity.this.finish();
        }else if (i == R.id.button_facebook) {
            startActivity(new Intent(getApplication(), FacebookLoginActivity.class));
            LoginActivity.this.finish();
        }
    }

}