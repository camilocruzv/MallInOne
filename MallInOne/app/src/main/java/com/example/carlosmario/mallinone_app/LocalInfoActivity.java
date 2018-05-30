package com.example.carlosmario.mallinone_app;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.carlosmario.mallinone_app.fragments.Tab1Malls;
import com.example.carlosmario.mallinone_app.fragments.Tab2Searchs;
import com.example.carlosmario.mallinone_app.fragments.Tab3Map;
import com.example.carlosmario.mallinone_app.fragments.TabLocalSearch1;
import com.example.carlosmario.mallinone_app.fragments.TabLocalSearch2;

public class LocalInfoActivity extends AppCompatActivity {


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarLocalInfo);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.containerLocalInfo);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabsLocalInfo);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {
            //Toast.makeText(this, "You clicked: " + item.getTitle(), Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            String localId = getIntent().getStringExtra("LocalId");
            String localName = getIntent().getStringExtra("LocalName");
            String localImage = getIntent().getStringExtra("LocalImage");
            String localMapUrl = getIntent().getStringExtra("MapLocal");
            String mallId = getIntent().getStringExtra("MallId");
            String highlighted = getIntent().getStringExtra("highlighted");

            switch (position) {

                case 0:
                    TabLocalSearch1 tabLocalSearch1 = new TabLocalSearch1(localId, localName, localImage, localMapUrl, mallId);
                    return tabLocalSearch1;
                    //Tab1Malls tab1Malls = new Tab1Malls();
                    //return tab1Malls;
                case 1:
                    TabLocalSearch2 tabLocalSearch2 = new TabLocalSearch2(localId, localName, localImage, localMapUrl, mallId, highlighted);
                    return tabLocalSearch2;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }
}
