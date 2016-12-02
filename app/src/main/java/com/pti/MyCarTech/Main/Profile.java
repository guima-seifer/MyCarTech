package com.pti.MyCarTech.Main;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.pti.MyCarTech.Login.LoginActivity;
import com.pti.MyCarTech.Main.Sessions.Sessions;
import com.pti.MyCarTech.R;

public class Profile extends Fragment {

    public Profile() {
        // Required empty public constructor
    }
    GoogleApiClient mGoogleApiClient;

    private static final String TAG = "Profile";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final Sessions sessions = new Sessions();
        final Intent intent = new Intent(getActivity(), LoginActivity.class);

        View root = inflater.inflate(R.layout.activity_profile, container, false);
        Switch swt_wifi = (Switch) root.findViewById(R.id.switch1);


        Button mButtonExit = (Button) root.findViewById(R.id.bLogout);

        //Logout
        mButtonExit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                logout();
                startActivity(intent);
                getActivity().finish();
            }
        });

        swt_wifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    WifiManager wifiManager = (WifiManager)getActivity().getSystemService(Context.WIFI_SERVICE);
                    wifiManager.setWifiEnabled(true);
                }else{
                    WifiManager wifiManager = (WifiManager)getActivity().getSystemService(Context.WIFI_SERVICE);
                    wifiManager.setWifiEnabled(false);
                }

            }
        });

        return root;
    }

    //terminates user authentication session
    public void logout(){
        FirebaseAuth.getInstance().signOut();
        //Closes authenticated user session
        startActivity(new Intent(getActivity(), LoginActivity.class));


    }
}