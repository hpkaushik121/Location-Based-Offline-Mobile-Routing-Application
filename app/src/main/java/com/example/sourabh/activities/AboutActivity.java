package com.example.sourabh.activities;

import android.app.ActionBar;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.sourabh.R;

import java.util.ArrayList;
import java.util.List;

/**
 * This file is
 * Created by Sourabh kaushik on March 16, 2018.
 */
public class AboutActivity extends AppCompatActivity {
    SpannableString s;
    CollapsingToolbarLayout toolBarLayout;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        RecyclerView rcs= (RecyclerView) findViewById(R.id.recycle);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rcs.setLayoutManager(layoutManager);
        setSupportActionBar(toolbar);
        String images[] ={"goel","kaushik","singh","jain","dubey","chugh"};
        String names[] ={"Saurabh Goel","Sourabh Kaushik","Devender Singh","Samyak Jain","Shivam Dubey"," Twinkel Chugh"};
        String designaions[] = {"Team Leader","Team Member","Team Member","Team Member","Team Member","Team Member"};

        ArrayList<String> img = new ArrayList<>();
        ArrayList<String > nam = new ArrayList<>();
        ArrayList<String > des = new ArrayList<>();
        for(int i =0; i<images.length;i++){
            img.add(images[i]);
            nam.add(names[i]);
            des.add(designaions[i]);
        }


        rcs.setAdapter(new hi(this,img,nam,des));

        //         set status bar
//        new SetStatusBarColor().setSystemBarColor(findViewById(R.id.statusBarBackgroundSettings),
//                getResources().getColor(R.color.my_primary_dark), this);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
