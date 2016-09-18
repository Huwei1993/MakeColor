package com.shayne.makecolor.bank.db_dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.shayne.makecolor.bank.BankBDOpenHepler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**  数据的增删改查类  数据库：bank.db
 * Created by huwei1993 on 2016/5/8.
 */
public class BankDBDao {
    private BankBDOpenHepler hepler;
    public  BankDBDao(Context  context){
        hepler = new BankBDOpenHepler(context);

    }
    /**
     * 添加一条账户信息]
     * name   用户名称
     * money  存的钱
     * return   代表添加在数据库的id 如果返回-1 则表示添加失败
     */
    public  long   add(String name,float money){
        if(isUserExist(name)){              //判断用户名是否已经存在
            return  -1;
        }
        SQLiteDatabase  db = hepler.getWritableDatabase();   //  获取到可写的数据库
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("money",money);
        long rowID = db.insert("account",null,contentValues);   //  添加到某一行_id
        db.close();
        return  rowID;

    }

    /**
     *     删除7一条数据库信息
     * @param name
     * @return
     */
    public  boolean delete(String name){
        SQLiteDatabase db = hepler.getWritableDatabase();
        int result = db.delete("account","name = ?",new String[] {name});
        db.close();
        if (result>0){
            System.out.println("删除成功！！"+name);
            return  true;
        }else {
            System.out.println("删除失败！！"+name);
            return  false;
        }

    }

    /**
     * 修改用户的账户信息
     * @param name   要修改的储户名
     * @param money   要修改的存款
     * @return   是否修改成功  true 成功   false  失败
     */
    public  boolean update(String name,float money){
        SQLiteDatabase db = hepler.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("money",money);
        int  resdult =  db.update("account",contentValues,"name=?",new String[]{name});
        db.close();
        if (resdult>0){
            System.out.println("修改成功！！"+name);
            return  true;
        }else {
            System.out.println("修改失败！！，请重试。。"+name);
            return  false;
        }
    }

    /**
     *   查询某个用户有多少money
     * @param name  用户名
     * @return   返回money
     */
    public  float getUserMoney(String name){
        float money =0;

        SQLiteDatabase db = hepler.getWritableDatabase();
        // 查询的表名   哪列   选择条件   选择条件的参数  是否排序  是否分组
        Cursor  cursor =  db.query("account",new String[]{"money"},"name=?",new String[]{name},null,null,null);  //结果集合
        if(cursor.moveToNext()){     //  如果结果集合可以移动到下一列 ，则说明查询成功
           money =  cursor.getFloat(0);

        }cursor.close();
        db.close();
        return money;
    }

    /**
     *   查询某个用户是否存在
     * @param name  用户名
     * @return   返回true  表示存在，反之则不存在
     */
    public  boolean isUserExist(String name){
       boolean  result = false;
        SQLiteDatabase db = hepler.getWritableDatabase();
        // 查询的表名   哪列   选择条件   选择条件的参数  是否排序  是否分组
        Cursor  cursor =  db.query("account",null,"name=?",new String[]{name},null,null,null);  //结果集合
        if(cursor.moveToNext()){     //  如果结果集合可以移动到下一列 ，则说明查询成功
           result =true;
        }cursor.close();
        db.close();
        return result;
    }


    /**
     *   查询所有用户的信息 id   name  money
     * @return
     */
    public List<Map<String, Object>>   findAllUser(){
        List<Map<String,Object>> allUsers = new ArrayList<Map<String, Object>>();
        SQLiteDatabase db = hepler.getWritableDatabase();
        Cursor cursor = db.query("account",new String[]{"_id","name","money"},null,null,null,null,null);
        while (cursor.moveToNext()){
            Map<String,Object> user = new HashMap<String, Object>();
            user.put("_id",cursor.getInt(0));
            user.put("name",cursor.getString(1));
            user.put("money",cursor.getFloat(2));
            allUsers.add(user);
        }
        cursor.close();
        db.close();
        return allUsers;
    }
}
