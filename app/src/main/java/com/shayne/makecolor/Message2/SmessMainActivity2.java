package com.shayne.makecolor.Message2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.shayne.makecolor.MainActivity;
import com.shayne.makecolor.Message.SmessListActivity;
import com.shayne.makecolor.Message.SmessMainActivity;
import com.shayne.makecolor.R;

/**
 * Created by huwei1993 on 2016/5/7.
 */
public class SmessMainActivity2 extends AppCompatActivity {
    private EditText et_content;
    private   static   int GET_SMESS =1;  //  获取短信内容
    private   static   int GET_CONTACT =2; //  获取联系人
    private EditText et_phone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendmessage2);
        et_content = (EditText) findViewById(R.id.et_content);
        et_phone = (EditText)findViewById(R.id.et_phone);
        //直接打开的新界面
//       Intent intent =  getIntent();
//       String content =   intent.getStringExtra("content");   //获取从短信界面的传来的文字信息
//       et_content.setText(content);
    }

    /**
     * 选择短信内容
     * @param view
     */
    public  void  SelectSMess(View view){
       Intent intent = new Intent(SmessMainActivity2.this,SmessListActivity2.class);   //显示意图
//        startActivity(intent);
        startActivityForResult(intent,GET_SMESS);    //意图     请求码（int）

    }

    /**
     * 选择联系人
     * @param view
     */

    public void  SelectContacts(View view){
        Intent intent = new Intent(SmessMainActivity2.this,ContactListActivity.class);
        startActivityForResult(intent,GET_CONTACT);
    }


    //    返回上一层界面
    public  void  return3(View view){
        Intent intent = new Intent(SmessMainActivity2.this,MainActivity.class);   //显示意图
        startActivity(intent);
        finish();    //   关闭当前页面

    }

    //    当我们开启的Activity关闭的时候  返回数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {   //请求吗  结果码  数据
        //判断是否为空
        if(data!=null){
            if (requestCode == GET_SMESS){
                System.out.println("请求的是短信的内容数据");
                String content = data.getStringExtra("content");   //获取从短信界面的传来的文字信息
                System.out.println("content:123"+content);          //  测试短信是否取到
                et_content.setText(content);
            }else if (requestCode == GET_CONTACT){
                System.out.println("请求的是联系人的内容数据");
                String number = data.getStringExtra("number");   //获取从短信界面的传来的文字信息
                System.out.println("number2:"+number);          //  测试短信是否取到
               et_phone.setText(number);
            }


        }
        super.onActivityResult(requestCode, resultCode, data);


    }


}
