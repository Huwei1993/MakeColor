package com.shayne.makecolor.mediaplay;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.shayne.makecolor.MainActivity;
import com.shayne.makecolor.R;
import com.shayne.makecolor.bank.BankMainActivity;

import java.io.IOException;

/**
 * Created by huwei1993 on 2016/5/5.
 */
public class MediaPlayerActivity  extends AppCompatActivity {
    private ProgressBar pb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mediaplayer);
        pb = (ProgressBar)findViewById(R.id.pb);
    }
    /**
     * 播放网络音乐的按钮
     */
    public  void play(View view){
        try {
        final MediaPlayer mediaPlayer = new MediaPlayer();
            pb.setVisibility(View.VISIBLE);
        mediaPlayer.reset();
        String path = "http://192.168.1.107:8080/eren.mp3";

        mediaPlayer.setDataSource(path);

//        mediaPlayer.prepare(); //同步的准备，如果没有准备还要就会一直等待，直到准备好。  主线程
//        mediaPlayer.start();

            //为了避免因为没有准备好而一直等待，所以需要开启子线程
            mediaPlayer.prepareAsync();      //异步的准备   ，开子线程准备
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                    pb.setVisibility(View.INVISIBLE);    //隐藏
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }







    /**
     * 返回主界面的点击事件
     * @param view
     */
    public void Onclick(View view) {
        Intent intent = new Intent(MediaPlayerActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
    // 跳转至银行数据库管理 界面
    public  void  BankManager(View view){
        Intent intent = new Intent(MediaPlayerActivity.this, BankMainActivity.class);
        startActivity(intent);

    }

}
//    public class media{
//        MediaPlayer  mediaPlayer = new MediaPlayer();
//        mediaPlayer.reset();
//        mediaPlayer.setDataSource("mnt/sdcard/eren.mp3");
//        mediaPlayer.prepare();
//        mediaPlayer.seekTo(10000);  //定位到某个地方
//        mediaPlayer.setLooping(true);  //多媒体循环播放 false 表示不循环
//        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {  //当音乐播放完毕后执行的监听器
//
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                System.out.println("播放完毕，开始下一首");
//
//            }
//        });
//        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {   //如果出现错误，例如加载的是图片呢
//            @Override
//            public boolean onError(MediaPlayer mp, int what, int extra) {
//                return false;
//            }
//        });
//        mediaPlayer.start();
//        mediaPlayer.release();   //表示结束，释放资源
//
////    }
//    }
//
//    }

