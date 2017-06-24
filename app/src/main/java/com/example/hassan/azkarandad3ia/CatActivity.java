package com.example.hassan.azkarandad3ia;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CatActivity extends AppCompatActivity {

    private GridView gridView;
    private List catList;
    private DBHelper mydb ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Locale locale = new Locale("ar_EG");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        CatActivity.this.getApplicationContext().getResources().updateConfiguration(config, null);
        //----------------------ads----------
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mydb =new DBHelper(this);
        catList=new ArrayList();
        catList =mydb.getCategory(CatActivity.this);
        Button fav =(Button)findViewById(R.id.get_fav);
        Typeface typeFace= Typeface.createFromAsset(getAssets(),"font/2.otf");
        fav.setTypeface(typeFace);
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(CatActivity.this ,Favourite.class));
            }
        });
        gridView = (GridView) findViewById(R.id.gridView1);
        gridView.setAdapter(new CatAdapter(CatActivity.this ,catList));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(CatActivity.this ,StoryActivity.class);
                Cat cat = (Cat) catList.get(position);
                int cat_id=cat.getId();
                intent.putExtra("cat_id",cat_id+"");
                intent.putExtra("cat_name",cat.getName());
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        // Assumes current activity is the searchable activity
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search1).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
               // Log.d(TAG, "onQueryTextSubmit ");
                   Intent intent =new Intent(CatActivity.this ,SearchActivity.class);
                    intent.putExtra("query",s);
                    startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }

        });
        return true;
    }

}
