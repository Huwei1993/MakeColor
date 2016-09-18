package com.shayne.makecolor.Cantact;


import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.shayne.makecolor.Cantact.domain.ContactInfo;
import com.shayne.makecolor.Cantact.utils.ContactsInfoUtils;
import com.shayne.makecolor.R;

import java.util.List;

public class MainContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_contact);
    }

    //   读取联系人
    public void  readCantact(View view){
         List<ContactInfo>  infos =  ContactsInfoUtils .getAllContactsInfos(this);
        for (ContactInfo  info:infos){
            System.out.println(info.toString());
        }
    }







//       ContentResolver  resolver =   getContentResolver();
//        //  系统源码的清单文件（AndroidManifest.xml）中
//        /**
//         * -<provider
//         *          android:name="ContactsProvider2"
//         *          android:label="@string/provider_label"
//         *          android:writePermission="android.permission.WRITE_CONTACTS"
//         *          android:readPermission="android.permission.READ_CONTACTS"
//         *          android:multiprocess="false"
//         *          android:authorities="contacts;com.android.contacts">   //  contacts  或者  com.android.contacts都可以使用，兼容不同版本
//         *
//         */
//
//         Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");    //  分号表示用contacts 或者 com.android.contacts都可以
//        Uri dataUri = Uri.parse("content://com.android.contacts/data");
//        Cursor  cursor = resolver.query(uri,new String[]{"contact_id"},null,null,null);
//        while (cursor.moveToNext()){
//           String id  = cursor.getString(0);
//            System.out.println(id);
//            Toast.makeText(MainContactActivity.this,"查询成功！",Toast.LENGTH_SHORT).show();
//            //   根据id查询date表 ，读取联系人的数据
//            Cursor dateCursor =  resolver.query(dataUri,new String[]{"data1","mimetype"},"raw_contact_id = ?",new String[]{id},null);
//            for(String s :dateCursor.getColumnNames()){
//                System.out.println(s);
//            }
//              // System.out.println(dateCursor.getColumnNames().toString());  // 测 试   获取数据库表的列名称
//
//               while (dateCursor.moveToNext()){
//                String date1 = dateCursor.getString(0);
//                String mimetype_id = dateCursor.getString(1);
//                System.out.println("date1 = "+date1);
//                System.out.println("mimetype = "+mimetype_id);
//                Toast.makeText(MainContactActivity.this,date1,Toast.LENGTH_SHORT).show();
//                Toast.makeText(MainContactActivity.this,mimetype_id,Toast.LENGTH_SHORT).show();
//
//
//            }
//            dateCursor.close();
//        }
//        cursor.close();


      //  添加联系人点击事件
    public void  addContact(View view){
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");    //  分号表示用contacts 或者 com.android.contacts都可以
        Uri dataUri = Uri.parse("content://com.android.contacts/data");
        ContentResolver resolver = getContentResolver();
        // 在row_comtact表中添加一条新的id
         Cursor  cursor =   resolver.query(uri,new String[]{"_id"},null,null,"_id desc");   // 以id  为降序排列
         cursor.moveToFirst();
        int id =  cursor.getInt(0);
        //   计算出来新添加联系人条目的id
        int newId= id +1;

        ContentValues values = new ContentValues();
        values.put("contact_id",newId);
        resolver.insert(uri,values);
        System.out.println("id="+id);

        //  2. 在data表中添加新的数据
        ContentValues phoneValues = new ContentValues();
        phoneValues.put("raw_contact_id",newId);
        phoneValues.put("mimetype","vnd.android.cursor.item/phone_v2");
        phoneValues.put("data1","123456879");
        resolver.insert(dataUri,phoneValues);
        //    3. 在data表中添加新的姓名数据
        ContentValues nameValues = new ContentValues();
        nameValues.put("raw_contact_id",newId);
        nameValues.put("mimetype","vnd.android.cursor.item/name");
        nameValues.put("data1","小明");
        resolver.insert(dataUri,nameValues);
        //    4. 在data表中添加新的Email数据
        ContentValues emailValues = new ContentValues();
        emailValues.put("raw_contact_id",newId);
        emailValues.put("mimetype","vnd.android.cursor.item/email_v2");
        emailValues.put("data1","123456@qq.com");
        resolver.insert(dataUri,emailValues);

        Toast.makeText(MainContactActivity.this,"数据添加成功",Toast.LENGTH_SHORT).show();
    }

}
