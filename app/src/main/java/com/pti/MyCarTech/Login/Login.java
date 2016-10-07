package com.pti.MyCarTech.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pti.MyCarTech.Main.MainActivity;
import com.pti.MyCarTech.R;


public class Login extends AppCompatActivity {

    private Button bLogin, bRegister;
    private EditText inputEmail, inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        bLogin = (Button) findViewById(R.id.B_login);
        bRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        // Login button Click Event
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reply = "";
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                // Check for empty data in the form
                if (!email.isEmpty() && !password.isEmpty()) {
                    // Form Validation
                    if (email.matches("") && password.matches("")) {
                        Toast.makeText(getApplicationContext(),
                                "Please insert Username and Password", Toast.LENGTH_LONG).show();
                        return;
                    } else if (email.matches("")) {
                        Toast.makeText(getApplicationContext(), "Insert Valid Username",
                                Toast.LENGTH_LONG).show();
                        return;
                    } else if (password.matches("")) {
                        Toast.makeText(getApplicationContext(), "Insert Valid Password",
                                Toast.LENGTH_LONG).show();
                        return;
                    }
                    Toast.makeText(getApplicationContext(), "Welcome",
                            Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplication(), MainActivity.class));
                    Login.this.finish();
                }
            }
        });

    }
}