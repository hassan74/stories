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
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StoryActivity extends AppCompatActivity {
      private ArrayList<Story>storyList=new ArrayList<Story>();
      public static int cat_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Locale locale = new Locale("ar_EG");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        StoryActivity.this.getApplicationContext().getResources().updateConfiguration(config, null);
        Intent intent=getIntent();
         cat_id =Integer.parseInt(intent.getStringExtra("cat_id")) ;
        final String name =intent.getStringExtra("cat_name");
        setTitle(name);
        setContentView(R.layout.activity_story);
        //----------------------ads----------
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //------------
        DBHelper mydb =new DBHelper(StoryActivity.this);
        storyList=mydb.getStory(cat_id);
        GridView gridView =(GridView)findViewById(R.id.storyGridview);
        gridView.setAdapter(new StoryAdapter(StoryActivity.this ,storyList));
        Button fav =(Button)findViewById(R.id.get_fav2);
        Button main =(Button)findViewById(R.id.main_page_story);
        Typeface typeFace= Typeface.createFromAsset(getAssets(),"font/2.otf");
        main.setTypeface(typeFace);
        fav.setTypeface(typeFace);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StoryActivity.this ,CatActivity.class));
            }
        });
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(StoryActivity.this ,Favourite.class));
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Story story =(Story) storyList.get(position);
                Intent intent1=new Intent(StoryActivity.this ,details.class);
                intent1.putExtra("pos",position+"");
                intent1.putExtra("cat_id",cat_id+"");
                intent1.putExtra("cat_name",name);
                startActivity(intent1);
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
            StoryActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
