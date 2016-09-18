package com.shayne.makecolor.bank;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.shayne.makecolor.R;
import com.shayne.makecolor.bank.db_dao.BankDBDao;

import java.util.Random;

/**
 * Created by huwei1993 on 2016/5/8.
 */
public class BankMainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bank);
        BankDBDao dao = new BankDBDao(this);
        Random random = new Random();
        for (int i=0;i<20;i++) {



            dao.add("胡"+i,random.nextFloat()+random.nextInt(500));    //  0-500之间的，有小数的
        }
    }
    /**
     * 银行行长查询私有的数据库信息
     * @param view
     */
    public void queryBankdate(View view){
        //得到内容提供者的解析器
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://com.shayne.makecolor.bank.db_provider/account");
        Cursor cursor = resolver.query(uri, null, null, null, null);
        while(cursor.moveToNext()){
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String money = cursor.getString(2);
            System.out.println(id);
            System.out.println(name);
            System.out.println(money);
        }
        cursor.close();
    }
    /**
     * 通过内容提供者 ，向银行私有的数据库添加一条记录
     * @param view
     */
    public void addBankdate(View view){
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://com.shayne.makecolor.bank.db_provider/account");
        ContentValues values = new ContentValues();
        values.put("name", "王五");
        values.put("money", 1000000.23f);
        Uri result  = resolver.insert(uri, values);
        Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show();
    }

    public void deleteBankdate(View view){
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://com.shayne.makecolor.bank.db_provider/account");
        int result = resolver.delete(uri, "name=?", new String[]{"王五"});
        if(result>0){
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
        }
    }
    public void updateBankdate(View view){
        ContentResolver resolver = getContentResolver();  //com.shayne.makecolor.bank.db_provider
        Uri uri = Uri.parse("content://com.shayne.makecolor.bank.db_provider/account");
        ContentValues values = new ContentValues();
        values.put("money", 0.05f);
        int result = resolver.update(uri, values, "name=?", new String[]{"王五"});
        if(result>0){
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
        }
    }


}
