package com.shayne.makecolor.Cantact.utils;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.widget.Toast;

import com.shayne.makecolor.Cantact.MainContactActivity;
import com.shayne.makecolor.Cantact.domain.ContactInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huwei1993 on 2016/5/9.
 */
public class ContactsInfoUtils {
    /**
     * 获取联系人信息的工具类
     * 获取系统所有联系人信息
     * @return
     * @Context   上下文
     */

    public static    List<ContactInfo> getAllContactsInfos(Context context){
        List<ContactInfo>  infos  =  new ArrayList<ContactInfo>();    // 联系人集合


        ContentResolver resolver =  context.getContentResolver();
        //  系统源码的清单文件（AndroidManifest.xml）中
        /**
         * -<provider
         *          android:name="ContactsProvider2"
         *          android:label="@string/provider_label"
         *          android:writePermission="android.permission.WRITE_CONTACTS"
         *          android:readPermission="android.permission.READ_CONTACTS"
         *          android:multiprocess="false"
         *          android:authorities="contacts;com.android.contacts">   //  contacts  或者  com.android.contacts都可以使用，兼容不同版本
         *
         */

        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");    //  分号表示用contacts 或者 com.android.contacts都可以
        Uri dataUri = Uri.parse("content://com.android.contacts/data");
        Cursor cursor = resolver.query(uri,new String[]{"contact_id"},null,null,null);
        while (cursor.moveToNext()){
            String id  = cursor.getString(0);
            ContactInfo  info = new ContactInfo();
            info.setId(id);
            System.out.println(id);
          //  Toast.makeText(MainContactActivity.this,"查询成功！",Toast.LENGTH_SHORT).show();
            //   根据id查询date表 ，读取联系人的数据
            Cursor dateCursor =  resolver.query(dataUri,new String[]{"data1","mimetype"},"raw_contact_id = ?",new String[]{id},null);

            // System.out.println(dateCursor.getColumnNames().toString());  // 测 试   获取数据库表的列名称

            while (dateCursor.moveToNext()){
                String date1 = dateCursor.getString(0);
                String mimetype = dateCursor.getString(1);
                System.out.println("date1 = "+date1);
                System.out.println("mimetype = "+mimetype);
                if("vnd.android.cursor.item/email_v2".equals(mimetype)){   //  Email 信息
                    info.setEmail(date1);
                }else if ("vnd.android.cursor.item/im".equals(mimetype)){    //  QQ 信息
                    info.setQq(date1);
                }else if ("vnd.android.cursor.item/name".equals(mimetype)){  // 姓名
                    info.setName(date1);
                }else if ("vnd.android.cursor.item/phone_v2".equals(mimetype)){   //  电话 号码
                    info.setPhone(date1);
                }


            }
            infos.add(info);
            dateCursor.close();
        }
        cursor.close();
        return  infos;
    }
}
