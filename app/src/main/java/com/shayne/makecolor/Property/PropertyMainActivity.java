package com.shayne.makecolor.Property;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.shayne.makecolor.Message.SmessMainActivity;
import com.shayne.makecolor.R;

public class PropertyMainActivity extends AppCompatActivity {

    private ImageView lv2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_main);
        lv2 = (ImageView)findViewById(R.id.lv2);
        lv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PropertyMainActivity.this,"得到Love",Toast.LENGTH_SHORT).show();
            }
        });


        //   只有实现了get set 方法才是属性
//        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)lv2.getLayoutParams();
//        params.leftMargin = 50;
//        params.topMargin = 50;
//        lv2.setLayoutParams(params);   以上这些都不属于属性动画
        /**
         *  以下属性动画
         */
          //  透明度属性：  lv2.setAlpha();
          //    位移属性：  lv2.setTranslationX();

    }

    //   返回上一界面
    public void returnToSendmessage(View view){
        Intent intent = new Intent(PropertyMainActivity.this, SmessMainActivity.class);
        startActivity(intent);
        finish();

    }

    //  alphaClick  方法   透明度渐变的属性动画
    public  void  alphaClick(View  view){
        //   操作的对象，对象属性 变化率
        ObjectAnimator animator = ObjectAnimator.ofFloat(lv2,"alpha",0.0f,0.1f,0.2f,0.3f,0.5f,0.9f,1.0f);
        animator.setDuration(5000);
        animator.setRepeatCount(2);   //   重复两次
        animator.setRepeatMode(ObjectAnimator.REVERSE);    // 倒叙重新开始
        animator.start();
    }

    // trans  方法   位移的属性动画
    public  void  trans(View  view){
        //   操作的对象，对象属性 变化率
        ObjectAnimator animator = ObjectAnimator.ofFloat(lv2,"translationX",0,10,20,30,50,90,160,280,140,70,35,15,0);
        animator.setDuration(5000);
        animator.setRepeatCount(2);   //   重复两次
        animator.setRepeatMode(ObjectAnimator.REVERSE);    // 倒叙重新开始
        animator.start();
    }

    // Scale  方法  缩放的属性动画
    public  void  Scale(View  view){
        //   操作的对象，对象属性 倍数变化率
        ObjectAnimator animator = ObjectAnimator.ofFloat(lv2,"scaleX",1.0f,0.0f,0.4f,0.6f,0.3f,1.0f,1.5f,2.0f,1.0f);
        animator.setDuration(5000);
        animator.setRepeatCount(2);   //   重复两次
        animator.setRepeatMode(ObjectAnimator.REVERSE);    // 倒叙重新开始
        animator.start();
    }


    // spin  方法   旋转的属性动画
    public  void  rotate(View  view){
        //   操作的对象，对象属性 旋转角度变化
        ObjectAnimator animator = ObjectAnimator.ofFloat(lv2,"rotationY",0,10,20,30,50,90,160,280,140,70,35,15,0);
        animator.setDuration(5000);
        animator.setRepeatCount(2);   //   重复两次
        animator.setRepeatMode(ObjectAnimator.REVERSE);    // 倒叙重新开始
        animator.start();
    }

    //   setAll  设置所有属性一起变化   动画集合
    public  void setAll(View view){
        AnimatorSet set = new AnimatorSet();
        //  旋转
       ObjectAnimator animator1 = ObjectAnimator.ofFloat(lv2,"rotationY",0,10,20,30,50,90,160,280,140,70,35,15,0);
        animator1.setDuration(2000);
        animator1.setRepeatCount(2);   //   重复两次
        animator1.setRepeatMode(ObjectAnimator.REVERSE);    // 倒叙重新开始

            //  缩放
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(lv2,"scaleX",1.0f,0.0f,0.4f,0.6f,0.3f,1.0f,1.5f,2.0f,1.0f);
        animator2.setDuration(2000);
        animator2.setRepeatCount(2);   //   重复两次
        animator2.setRepeatMode(ObjectAnimator.REVERSE);    // 倒叙重新开始
            //  位移
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(lv2,"translationX",0,10,20,30,50,90,160,280,140,70,35,15,0);
        animator3.setDuration(2000);
        animator3.setRepeatCount(2);   //   重复两次
        animator3.setRepeatMode(ObjectAnimator.REVERSE);    // 倒叙重新开始

         //   透明度
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(lv2,"alpha",0.0f,0.1f,0.2f,0.3f,0.5f,0.9f,1.0f);
        animator4.setDuration(2000);
        animator4.setRepeatCount(2);   //   重复两次
        animator4.setRepeatMode(ObjectAnimator.REVERSE);    // 倒叙重新开始
//        set.playSequentially(animator1,animator2,animator3,animator4);     //  按顺序播放
        set.playTogether(animator1,animator2,animator3,animator4);      //  所有动画混合一起播放
        set.start();
    }


}
