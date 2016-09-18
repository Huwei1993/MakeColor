package com.shayne.makecolor.bank.db_provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.shayne.makecolor.bank.BankBDOpenHepler;
/**
 * Created by huwei1993 on 2016/5/8.
 */

/**
 *   银行内部的内线 内容提供者
 */
public class BankInfoProvider  extends ContentProvider {
  //  private BankDBDao dao;
    private  static final int ACCOUNT = 1;
    private  SQLiteOpenHelper  helper;    //  比使用dao方法更方便
    //  定义一个Uri的匹配器，识别器  如果不合法则返回 -1
    private static UriMatcher mUriMatcher  = new UriMatcher(UriMatcher.NO_MATCH) ;   //   UriMatcher.NO_MATCH = -1
    static {
        //  训练匹配器  注意是 清单文件中的 provider的 authorities
        //  银行行长的专属密令：com.shayne.makecolor.bank.db_provider
        mUriMatcher.addURI("com.shayne.makecolor.bank.db_provider","account",ACCOUNT);
        //  content://com.shayne.makecolor.bank.db_provider/account    访问account表里面的所有数据
        //  content://com.shayne.makecolor.bank.db_provider/account/8  访问account表里面的第8条数据   约定俗成的
    }
    @Override
    public boolean onCreate() {
//        dao = new BankDBDao(getContext());   //   拿到当前内容提供者的上下文（虚假的，模拟的）
      helper = new BankBDOpenHepler(getContext());
        return false;
    }

    /**
     *   暴露的查询方法
     * @param uri
     * @param columns   列数
     * @param selection   选择项
     * @param selectionArgs   选择条件
     * @param sortOrder     主机名  即口令
     * @return
     */
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] columns, String selection, String[] selectionArgs, String sortOrder) {
        int code = mUriMatcher.match(uri);
        System.out.println("银行行长查询----"+code);

        if (code == ACCOUNT) {
            System.out.println("银行行长查询----"+code);
            SQLiteDatabase db = helper.getWritableDatabase();
            Cursor cursor = db.query("account", columns, selection, selectionArgs, null, null, null);   //  查询
            return cursor;
        }else {
            throw new IllegalArgumentException("根据相关法律规定，您没有权限查看该数据。");
        }
    }

    @Nullable
    @Override
    public String getType(Uri uri) {

        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int code = mUriMatcher.match(uri);
        System.out.println("code ="+code);
            if (code == ACCOUNT) {
                SQLiteDatabase db = helper.getWritableDatabase();
                long id =  db.insert("account",null,values);
            db.close();
                //    通知数据库的变化
                getContext().getContentResolver().notifyChange(uri,null);
            return   Uri.parse("com.shayne.makecolor.bank.db_provider/account"+id);
        }else {
            throw new IllegalArgumentException("根据相关法律规定，您没有权限添加该数据。");
        }

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int code = mUriMatcher.match(uri);
        if (code == ACCOUNT) {
            SQLiteDatabase db = helper.getWritableDatabase();
            int result = db.delete("account",selection,selectionArgs);
            db.close();
            return  result;

        }else {
            throw new IllegalArgumentException("根据相关法律规定，您没有权限删除数据。");
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int code = mUriMatcher.match(uri);     //对Uri进行审计操作
        if (code == ACCOUNT) {
            SQLiteDatabase db = helper.getWritableDatabase();
            int result = db.update("account",values,selection,selectionArgs);
            db.close();
            return  result;

        }else {
            throw new IllegalArgumentException("根据相关法律规定，您没有权限修改数据。");
        }
    }
}
