package com.shayne.makecolor.fragment;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.shayne.makecolor.R;

/**
 * fragment  碎片，片段
 * 不需要在清单文件manifest中注册
 */
public class fragmentMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_main);
        //    默认进入的时候界面是蓝牙设置界面
        BlueToothFragment  btf  = new BlueToothFragment();
        // 开启事务 即右边框
        android.app.FragmentTransaction ftaction =   getFragmentManager().beginTransaction();
        ftaction.replace(R.id.fl_container,btf);
        ftaction.commit();   //  提交事务
    }

    // 蓝牙点击事件

    public void blueToothSet(View view){
        BlueToothFragment  btf  = new BlueToothFragment();
        // 开启事务 即右边框
       android.app.FragmentTransaction ftaction =   getFragmentManager().beginTransaction();
        ftaction.replace(R.id.fl_container,btf);
        ftaction.commit();   //  提交事务
    }

    // 声音点击事件


    public void voiceSet(View view){
        VoiceSetFragment  vsf  = new VoiceSetFragment();
        // 开启事务 即右边框
        android.app.FragmentTransaction ftaction =   getFragmentManager().beginTransaction();
        ftaction.replace(R.id.fl_container,vsf);
        ftaction.commit();   //  提交事务

    }

    // 存储点击事件

    public void StroageSet(View view){
        StorageSetFragment  sf  = new StorageSetFragment();
        // 开启事务 即右边框
        android.app.FragmentTransaction ftaction =   getFragmentManager().beginTransaction();
        ftaction.replace(R.id.fl_container,sf);
        ftaction.commit();   //  提交事务
    }




}
