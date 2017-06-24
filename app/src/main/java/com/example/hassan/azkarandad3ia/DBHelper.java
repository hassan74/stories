package com.example.hassan.azkarandad3ia;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hassan on 15/03/2017.
 */

public class DBHelper extends SQLiteAssetHelper {
    public static final String DATABASE_NAME = "story.db";
//    public static final String first_TABLE_NAME = "first";
//    public static final String first_COLUMN_ID = "id";
//    public static final String first_COLUMN_NAME = "name";
//    public static final String second_TABLE_NAME = "second";
//    public static final String second_COLUMN_ID = "did";
//    public static final String second_COLUMN_NAME = "description";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // db.execSQL("DROP TABLE IF EXISTS first");
        //onCreate(db);
    }
    public void setData(int id)
    {
       String sql= "INSERT INTO first VALUES("+id+",'ali');";
        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("id", id);
//        contentValues.put("text", "mohamed");
//        db.insert("first", null, contentValues);

    }
    public ArrayList<Cat> getCategory (Context c){
        String name ;
        int id ;
        ArrayList<Cat> cats=new ArrayList<Cat>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from cat",null);
        if (res.moveToFirst()) {
            do {
                id =res.getInt(0);
                name=res.getString(1);
               //Toast.makeText(c ,name+" "+id ,Toast.LENGTH_LONG).show();
                Cat cat =new Cat(id ,name);
                cats.add(cat);
            } while (res.moveToNext());
        }
        res.close();
        return cats ;
    }
    public ArrayList<Story> getSearchStory(String query)
    {
        int storyId ,bookMark ,catId;
        String title ,desc ;
        ArrayList<Story>stories=new ArrayList<Story>();
        SQLiteDatabase db=this.getReadableDatabase();
        String[] args = new String[1];
        args[0] = "%"+query+"%";
        Cursor res = db.rawQuery("SELECT * FROM story WHERE title like ?", args);
        //Cursor res=db.rawQuery("select * from story where title like ? %'"+query+"'%",null);
        if (res.moveToFirst()) {
            do {
                storyId=res.getInt(0);
                catId=res.getInt(1);
                desc=res.getString(2);
                title=res.getString(3);
                bookMark=res.getInt(4);
                Story story=new Story(storyId,title,desc ,bookMark,catId);
                stories.add(story);
            } while (res.moveToNext());
        }
        res.close();
        return stories;
    }
    public ArrayList<Story> getStory(int cat_id)
    {
        int storyId ,bookMark ,catId;
        String title ,desc ;
        ArrayList<Story>stories=new ArrayList<Story>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from story where cat_id ="+cat_id,null);
        if (res.moveToFirst()) {
            do {
                storyId=res.getInt(0);
                cat_id=res.getInt(1);
                desc=res.getString(2);
                title=res.getString(3);
                bookMark=res.getInt(4);
                Story story=new Story(storyId,title,desc ,bookMark,cat_id);
                stories.add(story);
            } while (res.moveToNext());
        }
        res.close();
        return stories;
    }
    public ArrayList<Story> getFavStory()
    {
        int storyId ,bookMark ,catId;
        String title ,desc ;
        ArrayList<Story>stories=new ArrayList<Story>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from story where bookmark =1",null);
        if (res.moveToFirst()) {
            do {
                storyId=res.getInt(0);
                catId=res.getInt(1);
                desc=res.getString(2);
                title=res.getString(3);
                bookMark=res.getInt(4);
                Story story=new Story(storyId,title,desc ,bookMark,catId);
                stories.add(story);
            } while (res.moveToNext());
        }
        res.close();
        return stories;
    }

    public void flibFav(int i ,int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String sql="update story set bookmark ="+i+" where id= "+id;
        Cursor c=  db.rawQuery(sql ,null);
        c.moveToFirst();
        c.close();
    }
}
