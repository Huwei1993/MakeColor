package com.shayne.makecolor.phoneWiretap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.shayne.makecolor.R;

/**
 * Created by huwei1993 on 2016/5/7.
 */
public class MainWiretapActivity  extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiretap);
    }
    /**
     * 开启服务
     */

    public  void  startWiretap(View view){
        Intent intent = new Intent(MainWiretapActivity.this,PhoneService.class);
        Toast.makeText(this,"服务开启",Toast.LENGTH_SHORT).show();
        startService(intent);

    }
    /**
     * 停止服务
     */
    public  void stopWiretap(View view){
         Intent intent = new Intent(MainWiretapActivity.this,PhoneService.class);
        Toast.makeText(this,"停止服务",Toast.LENGTH_SHORT).show();
        startService(intent);
    }


}
