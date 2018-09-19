package com.example.main;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.example.login.R;
import com.example.main.adapter.ViewAdapter;
import com.example.main.view.ContactFragment;
import com.example.main.view.RecodeFragment;
import com.example.main.view.SetFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;

    private ViewPager viewPager;

    private ViewAdapter adapter;

    private List<Fragment> fragmentList = new ArrayList<>();

    private MenuItem item;

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.recode:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.contact:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.setting:
                    viewPager.setCurrentItem(2);
                    return true;
                default:
                    break;
            }
            return false;
        }
    };

    private ViewPager.OnPageChangeListener viewPagerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            if (item != null){
                item.setChecked(false);
            } else {
                bottomNav.getMenu().getItem(0).setChecked(false);
            }
            item = bottomNav.getMenu().getItem(i);
            item.setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        addListView();

        adapter = new ViewAdapter(getSupportFragmentManager(),fragmentList,this);
        viewPager.setAdapter(adapter);
    }

    private void initView(){
        bottomNav = findViewById(R.id.bottom_nav);
        viewPager = findViewById(R.id.view_pager);

        viewPager.addOnPageChangeListener(viewPagerListener);

        bottomNav.setOnNavigationItemSelectedListener(bottomNavListener);
    }

    private void addListView(){
        fragmentList.add(RecodeFragment.newInstance());
        fragmentList.add(ContactFragment.newInstance());
        fragmentList.add(SetFragment.newInstance());
    }
}
