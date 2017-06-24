package com.example.hassan.azkarandad3ia;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Locale;

public class Favourite extends AppCompatActivity {
    Button fav ;
    GridView gridView;
    public static ArrayList<Story>listFav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Locale locale = new Locale("ar_EG");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        Favourite.this.getApplicationContext().getResources().updateConfiguration(config, null);
        setTitle("القصص المفضله");
        setContentView(R.layout.activity_favourite);
        //----------------------ads----------
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        DBHelper db =new DBHelper(Favourite.this);
        listFav=db.getFavStory();
        Button main_fav =(Button )findViewById(R.id.main_fav);
        Typeface typeFace= Typeface.createFromAsset(getAssets(),"font/2.otf");
        main_fav.setTypeface(typeFace);
        main_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Favourite.this ,CatActivity.class);
                startActivity(intent);
            }
        });
        gridView =(GridView)findViewById(R.id.favGridview);
        gridView.setAdapter(new StoryAdapter(Favourite.this ,listFav));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(Favourite.this ,FavDetail.class);
                intent.putExtra("pos" ,position+"");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        gridView.setAdapter(new StoryAdapter(Favourite.this ,listFav));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(Favourite.this ,FavDetail.class);
                intent.putExtra("pos" ,position+"");
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu ,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();
        if(id==R.id.back)
        {
            Favourite.this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
