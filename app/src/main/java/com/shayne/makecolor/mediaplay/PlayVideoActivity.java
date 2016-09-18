package com.shayne.makecolor.mediaplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.VideoView;

import com.shayne.makecolor.MainActivity;
import com.shayne.makecolor.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by huwei1993 on 2016/5/5.
 */
public class PlayVideoActivity  extends AppCompatActivity{
   private VideoView Vv;
    private LinearLayout ll_controller;
    private SeekBar seekBar1;
    private Timer timer;   //计时器
    private TimerTask timerTask;  //任务

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playvideo);
        Vv = (VideoView)findViewById(R.id.Vv1);
        ll_controller = (LinearLayout)findViewById(R.id.ll_controller);
        seekBar1 = (SeekBar)findViewById(R.id.seekBar1);


        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Vv.seekTo( seekBar1.getProgress());    //定位到该位置
            }
        });

        String path = "http://192.168.1.107:8080/2.mp4";
        Vv.setVideoPath(path);
        Vv.start();


        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                int max = Vv.getDuration(); //总长度
                seekBar1.setMax(max);
             int progress = Vv.getCurrentPosition();
                seekBar1.setProgress(progress);
            }
        };
        timer.schedule(timerTask,0,500);  //任务  从0开始   没0.5S更新一次

    }

    @Override
    protected void onDestroy() {
        timer.cancel();
        timerTask.cancel();
        timer = null;
        timerTask = null;
        super.onDestroy();

    }

    @Override
    public boolean  onTouchEvent(MotionEvent event){
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            System.out.println("点击屏幕");
            if(ll_controller.getVisibility() == View.VISIBLE){     //判断是否已经出现了
                ll_controller.setVisibility(View.INVISIBLE);    //隐藏掉下方的按钮条
                /**
                 *为移动画
                 */
                TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0,
                        Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,10.f);
                translateAnimation.setDuration(200);
                ll_controller.startAnimation(translateAnimation);

            }else   if(ll_controller.getVisibility() ==View.INVISIBLE){
                ll_controller.setVisibility(View.VISIBLE);    //将下面的按钮条显示出来
                TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0,
                        Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0f);
                translateAnimation.setDuration(200);
                ll_controller.startAnimation(translateAnimation);
                new Thread(){
                    @Override
                    public void run() {
                        try {
                           Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                      //  ll_controller.setVisibility(View.INVISIBLE);    //将下面的按钮条隐藏   但是本语句不能再子线程里面
                    }
                }.start();

            }
        }

        return super.onTouchEvent(event);
    }

    public  void playVideos(View view){
       switch (view.getId()){
           case  R.id.palyv:
               Vv.start();
               break;
       }

    }
    public  void  pause(View view) {
        switch (view.getId()) {
            case R.id.pausev:
                Vv.pause();
                break;
        }
    }
    public  void  stop(View view) {
        switch (view.getId()) {
            case R.id.stopv:
                Vv.stopPlayback();
                break;
        }
    }

    public void return1(View view){
        Intent  intent  = new Intent(PlayVideoActivity.this, MainActivity.class);
        startActivity(intent);
        finish();    //关闭界面

    }
}
