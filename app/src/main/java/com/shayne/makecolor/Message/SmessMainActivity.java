package com.shayne.makecolor.Message;

import android.content.Intent;
import android.os.Bundle;
import android.os.IInterface;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.shayne.makecolor.Cantact.MainContactActivity;
import com.shayne.makecolor.DateTime.DateTimePicker;
import com.shayne.makecolor.MainActivity;
import com.shayne.makecolor.Property.PropertyMainActivity;
import com.shayne.makecolor.R;
import com.shayne.makecolor.fragment.fragmentMainActivity;
import com.shayne.makecolor.notification.notificationMainActivity;

/**
 * Created by huwei1993 on 2016/5/7.
 */
public class SmessMainActivity extends AppCompatActivity {
    private EditText et_content;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendmessage);
        et_content = (EditText)findViewById(R.id.et_content);

        //直接打开的新界面
//       Intent intent =  getIntent();
//       String content =   intent.getStringExtra("content");   //获取从短信界面的传来的文字信息
//       et_content.setText(content);
    }

    public  void  SelectSMess(View view){
       Intent intent = new Intent(SmessMainActivity.this,SmessListActivity.class);   //显示意图
//        startActivity(intent);
        startActivityForResult(intent,0);    //意图     请求码（int）

    }
    //    返回上一层界面
    public  void  return3(View view){
        Intent intent = new Intent(SmessMainActivity.this,MainActivity.class);   //显示意图
        startActivity(intent);
        finish();    //   关闭当前页面

    }

    //    当我们开启的Activity关闭的时候  返回数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //判断是否为空
        if(data!=null){
            String content = data.getStringExtra("content");   //获取从短信界面的传来的文字信息
            System.out.println("content:123"+content);
            et_content.setText(content);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    //   跳转至短信操作界面  （备份 还原）
    public void  tomessage(View view){
        Intent intent = new Intent(SmessMainActivity.this,SmessupbackMainActivity.class);
        startActivity(intent);

    }

      //  跳转到获取联系人界面
    public void toContact(View  view){
        Intent intent = new Intent(SmessMainActivity.this, MainContactActivity.class);
        startActivity(intent);
    }
    //   跳转到属性动画界面
    public  void toProperty(View view){
        Intent intent = new Intent(SmessMainActivity.this, PropertyMainActivity.class);
        startActivity(intent);
        finish();
    }

    //  跳转到Fragment 界面
    public  void  toFragment(View view){
        Intent intent = new Intent(SmessMainActivity.this, fragmentMainActivity.class);
        startActivity(intent);
        finish();
    }
     //  跳转至通知栏信息
    public void toNotification(View view){
    Intent intent = new Intent(SmessMainActivity.this,notificationMainActivity.class);
        startActivity(intent);
        finish();

    }

    public void toDate(View view){
        Intent intent = new Intent(SmessMainActivity.this,DateTimePicker.class);
        startActivity(intent);

    }
}
