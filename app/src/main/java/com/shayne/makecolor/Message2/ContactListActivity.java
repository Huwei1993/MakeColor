package com.shayne.makecolor.Message2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.shayne.makecolor.R;
import com.shayne.makecolor.Activity.ErWeiMa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *   模拟显示所有的联系人
 * Created by huwei1993 on 2016/5/7.
 */
public class ContactListActivity   extends AppCompatActivity {
    private ListView lv_contact;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactlist);    //   找到联系人列表=
        lv_contact = (ListView)findViewById(R.id.lv_contact);
        List<Map<String,String>>   data= new ArrayList<Map<String,String>>();
        for (int i =1;i<=10;i++){
            Map<String ,String> map = new HashMap<String, String>();
            map.put("name","张三"+i);
            map.put("phone","12306"+i);
            map.put("address","江西理工大学"+i);
            data.add(map);
        }


        lv_contact.setAdapter(new SimpleAdapter(this,data,R.layout.item_contact,new String[]{"name","phone","address"},
                new int[] {R.id.tv_name,R.id.tv_phone,R.id.tv_address}));    //
            lv_contact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String number = "12306"+position;
                    Intent data = new Intent();
                    data.putExtra("number",number);
                    setResult(0,data);
                    finish();
                }
            });
    }

    /**
     *  打开二维码操作
     */
    public void openErWeiMa(View view){
        Intent intent = new Intent(ContactListActivity.this, ErWeiMa.class);
        startActivity(intent);
    }
}
