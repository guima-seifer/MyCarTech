package com.pti.MyCarTech.Main;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.rubensousa.tablayoutadapter.TabLayoutAdapter;
import com.pti.MyCarTech.Main.Acquire.Start;
import com.pti.MyCarTech.Main.Sessions.Sessions;
import com.pti.MyCarTech.R;

public class MainActivity extends AppCompatActivity {

    private TabLayoutAdapter mTabLayoutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        /*Keeps fragment pages activesfrom 2 to right and left, Only 3 frags in this app*/
        viewPager.setOffscreenPageLimit(2);
        mTabLayoutAdapter = new TabLayoutAdapter(getSupportFragmentManager(), tabLayout, viewPager);
        mTabLayoutAdapter.addItem(new Start(), "Drive", R.drawable.ic_directions_car_white_24dp);
        mTabLayoutAdapter.addItem(new Sessions(), "Sessions", R.drawable.ic_event_note_white_24dp);
        mTabLayoutAdapter.addItem(new Profile(), "Profile", R.drawable.ic_build_white_24dp);
        mTabLayoutAdapter.createTabs();

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mTabLayoutAdapter.saveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mTabLayoutAdapter.restoreInstanceState(savedInstanceState);
    }
}
