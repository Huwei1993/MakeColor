package com.shayne.makecolor.mediaplay.Camera;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;


import com.shayne.makecolor.MainActivity;
import com.shayne.makecolor.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by huwei1993 on 2016/5/6.
 */
public class Makevideos  extends AppCompatActivity {

    private Camera mCamera;
    private CameraPreview mPreview;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.makevideo);

        // Create an instance of Camera
        mCamera = getCameraInstance();

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);

        // Add a listener to the Capture button
        Button captureButton = (Button) findViewById(R.id.button_capture);
        captureButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // get an image from the camera
                        mCamera.takePicture(null, null, new Camera.PictureCallback() {
                            @Override
                            public void onPictureTaken(byte[] data, Camera camera) {
                                try {
                                    File file = new  File(Environment.getExternalStorageDirectory(),"sbsb.jpg");     //创建一个sbsb.jpg的文件
                                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                                    fileOutputStream.write(data);
                                    fileOutputStream.close();
                                    Toast.makeText(getApplicationContext(),"拍照完毕",Toast.LENGTH_SHORT).show();
                                    mCamera.startPreview();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


                            }
                        });   //快门播放（可以是MP3）
                    }
                }
        );




    }
    /**
     * 检查手机是否有相机功能
      */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    /** 使用安全的方法使用照相机
     *
      */
    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // 打开一个照相机   默认地是后置摄像头 如果（1）置为1，则表示前置摄像头
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    //   返回主界面
    public  void  return2(View view){
        Intent intent = new Intent(Makevideos.this, MainActivity.class);
        startActivity(intent);
        finish();  //    关闭页面
    }
}
