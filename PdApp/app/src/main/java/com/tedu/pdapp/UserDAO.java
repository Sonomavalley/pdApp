package com.tedu.pdapp;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by cgb on 12/26/2018.
 */
//用来操作user表
public class UserDAO {
    public int query(Activity activity){
        int row=0;
        DatabaseHelper databaseHelper=new DatabaseHelper(activity);
        SQLiteDatabase db=databaseHelper.getReadableDatabase();
        String sql="select * from user";
        String[] args=null;
        Cursor cursor=db.rawQuery(sql,args);
        if(cursor!=null){
            row=cursor.getCount();
        }
       db.close();
        databaseHelper.close();
        return row;
    };
    public long insert(Activity activity,String username) {
        long row=0;
        //得到连接
        //contentValues是key:value结构
        DatabaseHelper databaseHelper=new DatabaseHelper(activity);
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
       row= sqLiteDatabase.insert("user",null,contentValues);

        sqLiteDatabase.close();
        databaseHelper.close();

        return row;

    }
}
