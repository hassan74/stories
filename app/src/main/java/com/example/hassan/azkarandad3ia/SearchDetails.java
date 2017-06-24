package com.example.hassan.azkarandad3ia;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Locale;

public class SearchDetails extends AppCompatActivity {
    private TextView title ,description ;
    private ImageView next ,prev;
    public static int story_id ,cat_id;
    public static String tit,des;
    CheckBox star;
    int bookMark;
    public static int pos;
    public  ArrayList<Story> storyList_det;
    //public DBHelper mydb ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Locale locale = new Locale("ar_EG");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        SearchDetails.this.getApplicationContext().getResources().updateConfiguration(config, null);
        //get views from xml
        //----------------------ads----------
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        title = (TextView) findViewById(R.id.title_detail);
        description = (TextView) findViewById(R.id.description_detail);
        Typeface typeFace=Typeface.createFromAsset(getAssets(),"font/1.otf");
        description.setTypeface(typeFace);
        title.setTypeface(typeFace);
        next = (ImageView) findViewById(R.id.next);
        prev = (ImageView) findViewById(R.id.prev);
        next.setImageResource(0);
        prev.setImageResource(0);
        star=(CheckBox)findViewById(R.id.star);
        //get elements from intent and set it in vars
        DBHelper mydb =new DBHelper(SearchDetails.this);
        Intent intent = getIntent();
        pos = Integer.parseInt(intent.getStringExtra("pos"));
        final String query =intent.getStringExtra("query");
        // storyList = (ArrayList<Story>) intent.getSerializableExtra("list");
        //cat_id=Integer.parseInt(intent.getStringExtra("cat_id"));
        storyList_det=mydb.getSearchStory(query);
        Story story = (Story) storyList_det.get(pos);
        bookMark = story.getBookMark();
        tit=story.getTitle();
        setTitle(tit);
        des=story.getDesc();
        story_id=story.getId();
        setBoard();
        Button main_page =(Button)findViewById(R.id.main_page);
        typeFace=Typeface.createFromAsset(getAssets(),"font/2.otf");
        main_page.setTypeface(typeFace);

        main_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchDetails.this, CatActivity.class);
                startActivity(intent);
            }
        });
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper mydb =new DBHelper(SearchDetails.this);
                if(star.isChecked())
                {
                    mydb.flibFav(1 ,story_id);
                    storyList_det=mydb.getSearchStory(query);
                    // Toast.makeText(details.this,"true" ,Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mydb.flibFav(0 ,story_id);
                    storyList_det=mydb.getSearchStory(query);
                    //Toast.makeText(details.this,"false" ,Toast.LENGTH_SHORT).show();

                }
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
            SearchDetails.this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
    void setBoard()
    {
        //set board
        title.setText(tit);
        description.setText(des);
        if (bookMark == 1) {
            star.setChecked(true);
            //Toast.makeText(details.this,"bookmark 1" ,Toast.LENGTH_SHORT).show();
        } else
        {
            star.setChecked(false);
            //Toast.makeText(details.this,"bookmark "+bookMark ,Toast.LENGTH_SHORT).show();
        }
    }


}
