package com.tedu.pdapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cgb on 12/26/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {

        super(context, "pd.db", null, 1);
    }
   //创建数据库，数据库保存在手机端
    @Override
    public void onCreate(SQLiteDatabase db) {
//SQLiteDatabase 执行sql语句
        String sql="CREATE TABLE USER"+"("+"username VARCHAR(50)"+")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
