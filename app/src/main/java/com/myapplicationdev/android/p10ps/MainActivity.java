package com.myapplicationdev.android.p10ps;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    Button btnRead;
    ArrayList<Fragment> al;
    MyFragmentPagerAdapter adapter;
    AlarmManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        btnRead = findViewById(R.id.btnRead);

        FragmentManager fm = getSupportFragmentManager();
        al = new ArrayList<Fragment>();
        al.add(new FirstFragment());
        al.add(new SecondFragment());
        al.add(new ThirdFragment());

        adapter = new MyFragmentPagerAdapter(fm, al);
        viewPager.setAdapter(adapter);

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.MINUTE, 5);

                Intent intent = new Intent(MainActivity.this,
                        MainActivity.class);

                int reqCode = 12345;

                PendingIntent pendingIntent =
                        PendingIntent.getActivity(MainActivity.this,
                                reqCode, intent,
                                PendingIntent.FLAG_CANCEL_CURRENT);

                am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);

                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_next:
                nextFragment();
                return true;

            case R.id.action_previous:
                previousFragment();
                return true;

            case R.id.action_random:
                randomFragment();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void nextFragment(){
        int max = viewPager.getChildCount();
        if (viewPager.getCurrentItem() < max - 1){
            int nextPage = viewPager.getCurrentItem() + 1;
            viewPager.setCurrentItem(nextPage,true);
        }
    }

    public void previousFragment(){
        if (viewPager.getCurrentItem() > 0){
            int previousPage = viewPager.getCurrentItem() - 1;
            viewPager.setCurrentItem(previousPage, true);
        }
    }

    public void randomFragment(){
        int max = viewPager.getChildCount() -1;
        int min = 0;
        int random = new Random().nextInt((max - min) + 1) + min;
        viewPager.setCurrentItem(random,true);
    }


}


