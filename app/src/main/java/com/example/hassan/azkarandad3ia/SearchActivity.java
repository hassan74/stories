package com.example.hassan.azkarandad3ia;

import android.app.SearchManager;
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
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {
    private ArrayList<Story> storyList_search=new ArrayList<Story>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Locale locale = new Locale("ar_EG");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        SearchActivity.this.getApplicationContext().getResources().updateConfiguration(config, null);
        setContentView(R.layout.activity_search);
        setTitle("نتائج البحث");
        //----------------------ads----------
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        Intent inten=getIntent();
        Button fav =(Button)findViewById(R.id.get_fav_search);
        Button main =(Button)findViewById(R.id.main_page_search);
        Typeface typeFace= Typeface.createFromAsset(getAssets(),"font/2.otf");
        main.setTypeface(typeFace);
        fav.setTypeface(typeFace);
        Intent intent=getIntent();
        final String query = intent.getStringExtra("query");
        //Toast.makeText(SearchActivity.this,"nta2g el bahs  "+query,Toast.LENGTH_SHORT).show();
        //use the query to search your data somehow
        DBHelper mydb =new DBHelper(SearchActivity.this);
        storyList_search=mydb.getSearchStory(query);
        GridView gridView =(GridView)findViewById(R.id.searchGridview);
        gridView.setAdapter(new SearchAdapter(SearchActivity.this ,storyList_search));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Story story =(Story) storyList_search.get(position);
                Intent intent1=new Intent(SearchActivity.this ,SearchDetails.class);
                intent1.putExtra("pos",position+"");
                intent1.putExtra("query",query);
                startActivity(intent1);
            }
        });
        main.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(SearchActivity.this ,CatActivity.class));
        }
    });
        fav.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            startActivity(new Intent(SearchActivity.this ,Favourite.class));
        }
    });


}
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();
        if(id==R.id.back)
        {
            SearchActivity.this.finish();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu ,menu);

        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
    }
}
