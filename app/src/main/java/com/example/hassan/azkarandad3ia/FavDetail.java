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

public class FavDetail extends AppCompatActivity {
    private TextView title ,description ;
    private ImageView next ,prev;
    public static int story_id ,cat_id;
    public static String tit,des;
    CheckBox star;
    int bookMark;
    public static int pos;
    public ArrayList<Story> storyList;
    //public DBHelper mydb ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Locale locale = new Locale("ar_EG");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        FavDetail.this.getApplicationContext().getResources().updateConfiguration(config, null);
        setTitle("القصص المفضله");
        setContentView(R.layout.activity_details);
        //----------------------ads----------
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //get views from xml
        title = (TextView) findViewById(R.id.title_detail);
        description = (TextView) findViewById(R.id.description_detail);
        next = (ImageView) findViewById(R.id.next);
        prev = (ImageView) findViewById(R.id.prev);
        star=(CheckBox)findViewById(R.id.star);
        Button main =(Button)findViewById(R.id.main_page);
        Typeface typeFace=Typeface.createFromAsset(getAssets(),"font/1.otf");
        description.setTypeface(typeFace);
        title.setTypeface(typeFace);
        typeFace= Typeface.createFromAsset(getAssets(),"font/2.otf");
        main.setTypeface(typeFace);
        //get elements from intent and set it in vars
        DBHelper mydb =new DBHelper(FavDetail.this);
        Intent intent = getIntent();
        pos = Integer.parseInt(intent.getStringExtra("pos"));
        storyList=mydb.getFavStory();
        Story story = (Story) storyList.get(pos);
        bookMark = story.getBookMark();
        tit=story.getTitle();
        des=story.getDesc();
        story_id=story.getId();
        setBoard();
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FavDetail.this, CatActivity.class);
                startActivity(intent);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos < storyList.size() - 1)
                {
                    pos++ ;
                    changeStory(pos);
                }
                else pos=0;

            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos > 0) {
                    pos--;
                    changeStory(pos);

                } else
                {
                    pos=storyList.size() - 1;
                }
            }
        });
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper mydb =new DBHelper(FavDetail.this);
                if(star.isChecked())
                {
                    mydb.flibFav(1 ,story_id);
                    storyList=mydb.getFavStory();
                    // Toast.makeText(details.this,"true" ,Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mydb.flibFav(0 ,story_id);
                    storyList=mydb.getFavStory();
                    Favourite.listFav=mydb.getFavStory();
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
            FavDetail.this.finish();
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
           // Toast.makeText(FavDetail.this,"bookmark 1" ,Toast.LENGTH_SHORT).show();
        } else
        {
            star.setChecked(false);
           // Toast.makeText(FavDetail.this,"bookmark "+bookMark ,Toast.LENGTH_SHORT).show();
        }
    }
    void changeStory(int pos)
    {
        Story story =(Story) storyList.get(pos);
        bookMark=story.getBookMark();
        tit=story.getTitle();
        des=story.getDesc();
        story_id=story.getId();
        title.setText(tit);
        description.setText(des);

        if (bookMark == 1) {
            star.setChecked(true);
        } else
        {
            star.setChecked(false);
        }
    }
}
