package com.example.order.Activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.order.Adapter.ViewpagerAdapter;
import com.example.order.Fragment.CostFragment;
import com.example.order.Fragment.ManagerFragment;
import com.example.order.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

public class ManageActivity extends AppCompatActivity {
    private BottomBar bottomBar;
    private ViewPager viewPager;
    private CostFragment costFragment;
    private ManagerFragment managerFragment;
    private List<Fragment> fragmentList;
    private ViewpagerAdapter viewpagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        init();


        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(viewpagerAdapter);
        viewPager.setCurrentItem(0);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch (tabId){
                    case R.id.cost:
                        viewPager.setCurrentItem(0);
                    break;
                    case R.id.manager:
                        viewPager.setCurrentItem(1);
                        break;
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomBar.selectTabAtPosition(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void init() {
        bottomBar=findViewById(R.id.bottombar);
        viewPager=findViewById(R.id.viewpager);
        costFragment=new CostFragment();
        managerFragment=new ManagerFragment();
        fragmentList=new ArrayList<>();
        fragmentList.add(costFragment);
        fragmentList.add(managerFragment);
        viewpagerAdapter=new ViewpagerAdapter(getSupportFragmentManager(),fragmentList);
    }
}
