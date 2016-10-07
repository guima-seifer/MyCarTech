package com.pti.MyCarTech.Main.Acquire;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.pti.MyCarTech.Login.Login;
import com.pti.MyCarTech.Main.MainActivity;
import com.pti.MyCarTech.R;

/**
 * Created by fernando on 03-10-2016.
 */

public class Session_capture extends AppCompatActivity {

    private Button mButton;
    private Button exitButton;
    private ViewPager mViewPager;

    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_capture);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        exitButton = (Button) findViewById(R.id.b_exit) ;
        mCardAdapter = new CardPagerAdapter();

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
        mCardShadowTransformer.enableScaling(true);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder =  new AlertDialog.Builder(Session_capture.this);
                builder.setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("End Session")
                        .setMessage("Are you sure you want to terminate this session?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Session Terminated",
                                        Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplication(), MainActivity.class));
                                Session_capture.this.finish();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
    }
}
