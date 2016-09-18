package com.shayne.makecolor.Message;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Xml;
import android.view.View;
import android.widget.Toast;

import com.shayne.makecolor.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by huwei1993 on 2016/5/8.
 */
public class SmessupbackMainActivity  extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mess_reset_activity);


    }
      //   返回到上一层界面
    public  void  return4(View view){
        Intent intent = new Intent(SmessupbackMainActivity.this,SmessMainActivity.class);
        startActivity(intent);
        finish();
    }

      //     备份短信
    public  void  backupsmess(View view) {
        try {
            //   利用激光内容提供者获取数据
            ContentResolver resolver = getContentResolver();
            //   XML的序列化器
            XmlSerializer xmlSerializer = Xml.newSerializer();
            File file = new File(Environment.getExternalStorageDirectory(), "backSmess.xml");
            FileOutputStream os = new FileOutputStream(file);
            xmlSerializer.setOutput(os, "UTF-8");
            //  写xml文件的头
            xmlSerializer.startDocument("utf-8", true);

            Uri uri = Uri.parse("content://sms/");
            Cursor cursor = resolver.query(uri, new String[]{"address", "date", "type", "body"}, null, null, null);
            xmlSerializer.startTag(null,"root");   //   开始标签
            while (cursor.moveToNext()) {
                xmlSerializer.startTag(null,"sms");   //   开始节点
                xmlSerializer.startTag(null,"address");
                String address = cursor.getString(0);
                xmlSerializer.text(address);
                xmlSerializer.endTag(null,"address");
                xmlSerializer.startTag(null,"date");
                String date = cursor.getString(1);
                xmlSerializer.text(date);
                xmlSerializer.endTag(null,"date");
                xmlSerializer.startTag(null,"type");
                String type = cursor.getString(2);
                xmlSerializer.text(type);
                xmlSerializer.endTag(null,"type");
                xmlSerializer.startTag(null,"body");
                String body = cursor.getString(3);
                xmlSerializer.text(body);
                xmlSerializer.endTag(null,"body");
                System.out.println("address:---" + address);

                System.out.println("date:---" + date);
                System.out.println("type:---" + type);
                System.out.println("body:---" + body);
                //  存在xml 文件，跨平台。


                xmlSerializer.endTag(null,"sms");   // 结束标签

            }
            cursor.close();
            xmlSerializer.endTag(null,"root");   //  结束标签

            xmlSerializer.endDocument();
            os.close();
            Toast.makeText(SmessupbackMainActivity.this,"备份成功",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(SmessupbackMainActivity.this,"备份失败",Toast.LENGTH_SHORT).show();
        }
    }

    //    短信还原
    public  void resetmess(View view){
        File file = new File(Environment.getExternalStorageDirectory(),"backSmess.xml");
       // file.lastModified();   //    获取文件上一次修改（备份）的时间
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("温馨提示！");
        builder.setMessage("是否清除原有的短信？");
        builder.setPositiveButton("确认清除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Uri uri =  Uri.parse("content://sms/");
                getContentResolver().delete(uri,null,null);
                restore();
            }
        });
        builder.setNegativeButton("取消清除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            restore();
            }
        });
        builder.show();
    }

    /**
     * 还原所有备份的短信
     */
    private void restore() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "backSmess.xml");


            FileInputStream fis = new FileInputStream(file);
            XmlPullParser parser = Xml.newPullParser();
            //    输入流
            parser.setInput(fis, "utf-8");
            int type = parser.getEventType();
            String address = null;
            String  date = null;
            String smstype = null;
            String body = null;
            while (type!=XmlPullParser.END_DOCUMENT){
                switch (type){
                    case  XmlPullParser.START_TAG:
                        if("type".equals(parser.getName())){
                            //  短信开始
                            smstype = parser.nextText();
                        }else if ("body".equals(parser.getName())){
                        body = parser.nextText();
                    }else if ("date".equals(parser.getName())){
                            date = parser.nextText();
                        }else if ("address".equals(parser.getName())){
                            address   = parser.nextText();
                        }
                    break;
                    case XmlPullParser.END_TAG:
                        if("sms".equals(parser.getName())){
                            //  一条短信数据结束  把它放入到系统短信数据库
                            ContentResolver resolver =  getContentResolver();
                             Uri uri =  Uri.parse("content://sms/");
                            ContentValues values = new ContentValues();
                            values.put("address",address);
                            values.put("date",body);
                            values.put("type",smstype);
                            values.put("date",date);

                            resolver.insert(uri,values);

                        }
                        break;
                }
            }
            Toast.makeText(SmessupbackMainActivity.this,"还原短信成功",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(SmessupbackMainActivity.this,"还原短信失败",Toast.LENGTH_SHORT).show();
        }
    }

}
