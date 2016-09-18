package com.shayne.makecolor.phoneWiretap;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class PhoneService extends Service {
    public  PhoneService(){

    }

    private TelephonyManager tm;   //电话管理器，系统的服务，没有界面的
    private MyListener myListener;
    private   MediaRecorder recorder;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        //监听手机通话状态
        tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        myListener = new MyListener();
        tm.listen(myListener,PhoneStateListener.LISTEN_CALL_STATE);  //监听通话的状态
        super.onCreate();
    }

    public class MyListener extends PhoneStateListener {
        //  当呼叫的状态发生变化时调用的方法
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            //  状态： 响铃   通话状态  空闲
            switch (state){
                case TelephonyManager.CALL_STATE_IDLE:   //  空闲状态

                    if (recorder!=null) {
                        recorder.stop();
                        recorder.release();  //释放资源
                        recorder = null;
                        System.out.println("上传至服务器");
                        Toast.makeText(PhoneService.this, "通话音频已上传至服务器，请注意", Toast.LENGTH_SHORT).show();
                        break;
                    }
                case TelephonyManager.CALL_STATE_RINGING:  //响铃状态
                    Toast.makeText(PhoneService.this,"手机铃声响了，准备监听，并录制",Toast.LENGTH_SHORT).show();
                    System.out.println("手机铃声响了，准备监听，并录制");
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:   //  电话接通
                    System.out.println("开始录音");
                    Toast.makeText(PhoneService.this,"开始录音,上传至服武器",Toast.LENGTH_SHORT).show();
                    satrtRecorder(incomingNumber);
                    break;
            }
            super.onCallStateChanged(state, incomingNumber);
        }


    }


    @Override
    public void onDestroy() {
        //停止监听服务
        tm.listen(myListener,PhoneStateListener.LISTEN_NONE);   //  LISTEN_NONE 表示什么都不监听
        super.onDestroy();
    }
    private void satrtRecorder(String incomingNumber) {
        try {
        // 1.创建一个录音机实例
             recorder = new MediaRecorder();
        // 2. 设置录制的数据源
        //  VOICE_CALL   可以监听双方的语音(一般国产手机支持，国外（Iphone 三星）手机失效)   MIC  是麦克风只能是手机一端的语音，模拟器只能支持 MIC
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        //  3.设置输入文件的格式
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);    //
        // 4. 指定保存的文件名
        recorder.setOutputFile("/sdcard/"+incomingNumber+"1.3gp");  //来电号码作为名称
        // 5. 设置编码方式    // 比如MP3 格式的有流畅、普通、高质等
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);   //  AMR 是 手机音频的压缩格式   NB 非平衡的裁量方式
        // 6.准备开始录音
        recorder.prepare();
        recorder.start();
        }catch (IllegalStateException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
