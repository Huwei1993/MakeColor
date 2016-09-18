package com.shayne.makecolor.bank;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by huwei1993 on 2016/5/8.
 */
public class BankBDOpenHepler extends SQLiteOpenHelper {


    public BankBDOpenHepler(Context context) {
        super(context, "bank.db",  null, 1);      //   上下文  数据库名称    游标工厂   版本号
    }

    //  数据库第一次使用调用的方法
    @Override
    public void onCreate(SQLiteDatabase db) {
        //    表名  account   账户_id 主键    用户名  name  存款  money
        db.execSQL("create table account (_id integer primary key autoincrement,name varchar(20),money  varchar(2))");
    }
    //  数据库再次使用调用的方法
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
