package com.pti.MyCarTech.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;


import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GithubAuthProvider;
import com.pti.MyCarTech.Login.GitHubAuthentication.LoginService;
import com.pti.MyCarTech.Login.GitHubAuthentication.ServiceGenerator;
import com.pti.MyCarTech.Login.models.AppCredentials;
import com.pti.MyCarTech.Login.models.Email;
import com.pti.MyCarTech.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by fernando on 22-11-2016.
 *
 * falta guardar os tokens e codes em memoria
 * */

public class GitHubLoginActivity extends BaseActivity{
    private static final String TAG = "GitHubLoginActivity";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    Intent intent;
    // you should either define client id and secret as constants or in string resources
    private final String clientId = "3e9734f11940351b4c5a";
    private final String clientSecret = "5dc689169c9be05ec4975fe12623944396c13824";
    private final String redirectUri = "https://mycartech-dda53.firebaseapp.com/__/auth/handler";
    private EditText mEmailField;
    private EditText mPasswordField;
    private LinearLayout linearlayout;
    private boolean test = true;
    private boolean aux;
    private String mEmail;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_github_login);

        linearlayout = (LinearLayout) findViewById(R.id.linearLayout);

        // Views
        mEmailField = (EditText) findViewById(R.id.input_email);
        mPasswordField = (EditText) findViewById(R.id.input_password);


        Button loginButton = (Button) findViewById(R.id.bLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                    if (validateForm()) {
                    LoginService service = ServiceGenerator.createService(LoginService.class, mEmailField.getText().toString().trim(), mPasswordField.getText().toString().trim());

                    AppCredentials appCredentials = new AppCredentials(clientId, clientSecret, "admin_script");
                    Call<AppCredentials> requestLogin = service.getAccessToken(appCredentials);
                    requestLogin.enqueue(new Callback<AppCredentials>() {
                        @Override
                        public void onResponse(Call<AppCredentials> call, Response<AppCredentials> response) {
                            if (!response.isSuccessful()) {
                                Log.d(TAG, "Erro: " + response.code() + " " + response.message());
                                Log.d(TAG, "Erro: " + response.raw());

                            } else {
                                //Success
                                AppCredentials app = response.body();
                                Log.d(TAG, "Code: " + response.code() + " Success " + app.toString());
                                handleGitHubAccessToken(app.getToken());
                            }
                        }

                        @Override
                        public void onFailure(Call<AppCredentials> call, Throwable t) {
                            Log.d(TAG, "Error: " + t.getMessage());
                        }
                    });
                }else{
                    Log.d(TAG," Invalid Form!!");
                }
            }
        });
        mAuth = FirebaseAuth.getInstance();





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
                // ...
            }
        };
        //
    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }



    private void handleGitHubAccessToken(String token) {
        Snackbar snackbar;
        // [START_EXCLUDE silent]
        showProgressDialog();
        // [END_EXCLUDE]
        AuthCredential credential = GithubAuthProvider.getCredential(token);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            hideProgressDialog();
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Snackbar snackbar = Snackbar
                                    .make(linearlayout, "GitHub Sign In failed", Snackbar.LENGTH_SHORT)
                                    .setAction("CLOSE", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                        }
                                    });
                            snackbar.setActionTextColor(getResources().getColor(R.color.colorAccent));
                            snackbar.show();
                            startActivity(new Intent(getApplication(), GitHubLoginActivity.class));
                            GitHubLoginActivity.this.finish();
                        }if (task.isSuccessful()) {
                            Log.w(TAG, "task email: " + task.getResult().getUser().getEmail());

                            /**
                             * Now we have to get User info from GitHub and update it on FirebaseAuth
                             * */
                        String taskEmail = task.getResult().getUser().getEmail();

                        if(taskEmail == null) {
                            aux = false;
                            LoginService service = ServiceGenerator.createService(LoginService.class, mEmailField.getText().toString().trim(), mPasswordField.getText().toString().trim());

                            Call<List<Email>> requestLogin = service.fetchData();
                            requestLogin.enqueue(new Callback<List<Email>>() {
                                @Override
                                public void onResponse(Call<List<Email>> call, Response<List<Email>> response) {
                                    Snackbar snackbar;
                                    if (!response.isSuccessful()) {
                                        Log.d(TAG, "Erro UserData Response: " + response.code() + " " + response.message());
                                    } else {
                                        //Success
                                        List<Email> data = response.body();
                                        for (int i = 0; i < data.size(); i++) {
                                            Log.d(TAG, "UserData Response Code: " + response.code() + " Success " + data.get(i).toString());
                                            if (data.get(i).getPrimary().equals("true")) {
                                                Log.d(TAG, "Primary email: " + data.get(i).getEmail());
                                                mEmail = data.get(i).getEmail();
                                            }
                                        }
                                        //Updates firebase user authentication with a not null email
                                        FirebaseUser fireuser = FirebaseAuth.getInstance().getCurrentUser();
                                        fireuser.updateEmail(mEmail)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Snackbar snackbar;
                                                        if (task.isSuccessful()) {
                                                            hideProgressDialog();
                                                            aux = true;
                                                            Log.d(TAG, "Success --- User email address updated.");
                                                            Toast.makeText(getApplicationContext(), "User registered successfully ",
                                                                    Toast.LENGTH_LONG).show();
                                                            startActivity(new Intent(getApplication(), com.pti.MyCarTech.Main.MainActivity.class));
                                                            GitHubLoginActivity.this.finish();

                                                        } else {
                                                            hideProgressDialog();
                                                            aux = false;
                                                            snackbar = Snackbar
                                                                    .make(linearlayout, "Primary email already registered, try with other provider", Snackbar.LENGTH_LONG)
                                                                    .setAction("CLOSE", new View.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(View view) {
                                                                        }
                                                                    });
                                                            snackbar.setActionTextColor(getResources().getColor(R.color.colorAccent));
                                                            snackbar.show();
                                                            Log.d(TAG, "No success --- User email address not updated.");

                                                        }
                                                    }
                                                });

                                    }
                                }

                                @Override
                                public void onFailure(Call<List<Email>> call, Throwable t) {
                                    Log.d(TAG, "Error UserData Response: " + t.getMessage());
                                }
                            });
                            //in case there is a available email in task.getResult().getUser().getEmail()
                        }else{
                            aux = true;
                        }
                            if(aux) {
                                Toast.makeText(getApplicationContext(), "Welcome " + task.getResult().getUser().getDisplayName(),
                                        Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplication(), com.pti.MyCarTech.Main.MainActivity.class));
                                GitHubLoginActivity.this.finish();
                            }
                        }

                        // ...
                    }
                });
    }
    private boolean validateForm() {
        boolean valid = true;
        Snackbar snackbar;
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();


        if(TextUtils.isEmpty(password) && TextUtils.isEmpty(email)){
            snackbar = Snackbar
                    .make(linearlayout, "Please fill the form", Snackbar.LENGTH_LONG)
                    .setAction("CLOSE", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    });
            snackbar.setActionTextColor(getResources().getColor(R.color.colorAccent));
            snackbar.show();
            valid = false;
            return valid;
        }


        if (TextUtils.isEmpty(email)) {
            snackbar = Snackbar
                    .make(linearlayout, "Insert valid email/username", Snackbar.LENGTH_LONG)
                    .setAction("CLOSE", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    });
            snackbar.setActionTextColor(getResources().getColor(R.color.colorAccent));
            snackbar.show();
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        if (TextUtils.isEmpty(password)) {
            snackbar = Snackbar
                    .make(linearlayout, "Insert a valid password", Snackbar.LENGTH_LONG)
                    .setAction("CLOSE", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    });
            snackbar.setActionTextColor(getResources().getColor(R.color.colorAccent));
            snackbar.show();
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }


}