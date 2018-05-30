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
import com.example.carlosmario.mallinone_app.fragments.TabProductSearch1;
import com.example.carlosmario.mallinone_app.fragments.TabProductSearch2;

public class ProductInfoActivity extends AppCompatActivity {


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
        setContentView(R.layout.activity_product_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarProductInfo);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.containerProductInfo);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabsProductInfo);

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

            String productId = getIntent().getStringExtra("productId");
            String productLocal = getIntent().getStringExtra("productLocal");
            String productName = getIntent().getStringExtra("productName");
            String productImage = getIntent().getStringExtra("productImage");
            String price = getIntent().getStringExtra("price");
            String characteristics = getIntent().getStringExtra("characteristics");

            switch (position) {

                case 0:
                    TabProductSearch1 tabLocalSearch1 = new TabProductSearch1(productId, productLocal, productName, productImage, price);
                    return tabLocalSearch1;
                case 1:
                    TabProductSearch2 tabProductSearch2 = new TabProductSearch2(productId, productLocal, productName, productImage, price, characteristics);
                    return  tabProductSearch2;
                    //TabLocalSearch2 tabLocalSearch2 = new TabLocalSearch2(productId, productLocal, productName, productImage, characteristics, price);
                    //return tabLocalSearch2;
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
