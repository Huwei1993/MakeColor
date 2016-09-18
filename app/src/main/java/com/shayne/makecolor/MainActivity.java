package com.shayne.makecolor;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.shayne.makecolor.Message.SmessMainActivity;
import com.shayne.makecolor.Message2.SmessMainActivity2;
import com.shayne.makecolor.SafeSoft.Acticity.SplashActivity;
import com.shayne.makecolor.mediaplay.Camera.Makevideos;
import com.shayne.makecolor.mediaplay.MediaPlayerActivity;
import com.shayne.makecolor.mediaplay.PlayVideoActivity;
import com.shayne.makecolor.phoneWiretap.MainWiretapActivity;


public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private SeekBar sb_red;
    private SeekBar sb_green;
    private SeekBar sb_blue;
    private ImageView iv;
    private Bitmap alterBitmap;   //原图的一个拷贝，画笔
    private Canvas canvas;      //画布
    private Paint paint;
    private Bitmap srcBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sb_red = (SeekBar) findViewById(R.id.sb_red);
        sb_green = (SeekBar) findViewById(R.id.sb_green);
        sb_blue = (SeekBar) findViewById(R.id.sb_blue);
        iv = (ImageView) findViewById(R.id.iv_result);

        srcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.iamge1);
        alterBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), srcBitmap.getConfig());
        canvas = new Canvas(alterBitmap);
        paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        //矩阵，从上往下 分别表示红 绿 蓝 界面透明度 数字都表示倍数关系
        cm.set(new float[]{
                2, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0
        });

        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        canvas.drawBitmap(srcBitmap, new Matrix(), paint);
        iv.setImageBitmap(alterBitmap);
        sb_red.setOnSeekBarChangeListener(this);
        sb_green.setOnSeekBarChangeListener(this);
        sb_blue.setOnSeekBarChangeListener(this);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        //当鼠标按住按钮时变化，图片的颜色也会跟着变化，比较消耗内存

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //当鼠标按住按钮时变化，图片的颜色也会跟着变化，比较消耗内存

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        float red = 1, green = 1, blue = 1;
        //当鼠标按住按钮时不发生变化，当鼠标移动到某位置停下时，图片颜色发生变化，节约内存
        switch (seekBar.getId()) {

            case R.id.sb_red:
                red = seekBar.getProgress() / 128.0f;
                break;
            case R.id.sb_green:
                green = seekBar.getProgress() / 128.0f;
                break;
            case R.id.sb_blue:
                blue = seekBar.getProgress() / 128.0f;
                break;
        }
        ColorMatrix cm = new ColorMatrix();
        //矩阵，从上往下 分别表示红 绿 蓝 界面透明度 数字都表示倍数关系
        cm.set(new float[]{
                red, 0, 0, 0, 0,
                0, green, 0, 0, 0,
                0, 0, blue, 0, 0,
                0, 0, 0, 1, 0
        });

        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        canvas.drawBitmap(srcBitmap, new Matrix(), paint);
        iv.setImageBitmap(alterBitmap);
    }

    /**
     * 跳转至音乐播放器页面
     */
    public void click(View view) {
        Intent intent = new Intent(MainActivity.this, MediaPlayerActivity.class);
        startActivity(intent);

    }

    /**
     * 跳转至视频播放器页面
     */
    public void playVideo(View view) {
        Intent intent = new Intent(MainActivity.this, PlayVideoActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 点开视频录制界面0
     */

    public void Camera(View view) {

        Intent intent = new Intent(MainActivity.this, Makevideos.class);
        startActivity(intent);

    }

    /**
     * 发送短信界面
     */
    public void message(View view) {
        Intent intent = new Intent(MainActivity.this, SmessMainActivity.class);
        startActivity(intent);

    }

    /**
     * 发送短信2界面   升级版
     */

    public void Message2(View view) {
        Intent intent = new Intent(MainActivity.this, SmessMainActivity2.class);
        startActivity(intent);

    }
            //   进入窃听界面
    public void wiretap(View view) {
            Intent intent = new Intent(MainActivity.this, MainWiretapActivity.class);
            startActivity(intent);
            }
    public  void BankManager(View view){

    }
    //  进入手机安全卫士界面
    public void toSafe(View view){
        Intent   intent = new Intent(MainActivity.this, SplashActivity.class);
        startActivity(intent);

    }

}

