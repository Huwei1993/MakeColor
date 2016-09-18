package com.shayne.makecolor.SafeSoft.Acticity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.shayne.makecolor.R;
import com.shayne.makecolor.SafeSoft.Acticity.demain.UrlData;
import com.shayne.makecolor.SafeSoft.Acticity.utils.ShowToast;
import com.shayne.makecolor.SafeSoft.Acticity.utils.Stream2String;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SplashActivity extends AppCompatActivity {

    private static final int UPDATAVERSION = 1 ;
    public static final int LOADMAIN =2 ;
    private TextView tv_virsion;
    private PackageManager pm;
    private PackageInfo packageInfo;   // 存放了当前包的所有信息
    RelativeLayout rl_root;
    int versionCode;
    ProgressBar pb_Splash_down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();   // 初始化界面组件
        iniData();      // 初始化界面组件的数据
        initAnim();
        startSubThread();  //    子线程访问版本信息

    }

    /**
     *     封装开启子线程
     */
    private void  startSubThread(){
        new Thread(){

            public void run(){
                checkVersion();
            }
        }.start();
    }

    /**
     * Handler 封装
     */

    private Handler handler = new Handler(){


        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATAVERSION:   //   更新新版本的操作
                    ShowToast.show(SplashActivity.this,"有新版本");
                    //  对话框的形式显示是否更新新版本
                    final UrlData data1 = (UrlData) msg.obj;
                    //  对话框显示新版本信息


                    AlertDialog.Builder  ab = new AlertDialog.Builder(SplashActivity.this);
                    //   ab.setCancelable(false);   //  当点击返回按钮时不起作用
                    ab.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            loadMain();     //  直接加载主界面
                        }
                    });
                    ab.setTitle("新版本")
                            .setMessage(data1.getDesc())
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //   点击取消
                                    loadMain();  //  加载主界面
                                }
                            })
                            .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //   下载更新
                                    System.out.println("data1.getDownUrl ="+data1.getDownUrl());
                                    dialog.dismiss();
                                    downLoadAndInstall(data1.getDownUrl());

                                }


                            });


                    ab.show();

                    break;
                case LOADMAIN:
                    loadMain();  //  加载主界面
                    break;
                default:
                    break;

            }
            super.handleMessage(msg);
        }
    };


    //   下载安装更新
    private void downLoadAndInstall(String downUrl) {
        HttpUtils httpUtils = new HttpUtils();
        pb_Splash_down.setVisibility(View.VISIBLE);//   进度条显示


        httpUtils.download(downUrl, "/sdcard/phoneguard.apk", new RequestCallBack<File>() {
            /**
             *   下载进度
             * @param total
             * @param current
             * @param isUploading
             */
            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                super.onLoading(total, current, isUploading);
                pb_Splash_down.setProgress((int) current);  //   设置总进度
                pb_Splash_down.setMax((int) total);
            }

            /**
             *  下载成功
             */
            @Override
            public void onSuccess(ResponseInfo<File> responseInfo) {
                //   安装apk
                //    参考上层源码
                pb_Splash_down.setVisibility(View.GONE);   //  下载成功后隐藏进度条

                Intent  intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.DEFAULT");
//                intent.setData(Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"/sdcard/phoneguard.apk")));
//                intent.setType("application/vnd.android.package-archive");  //  包的类型的数据
                intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"/sdcard/phoneguard.apk")), "application/vnd.android.package-archive");
                startActivity(intent);

            }

            /**
             * 下载失败
             * @param e
             * @param s
             */
            @Override
            public void onFailure(HttpException e, String s) {
                ShowToast.show(SplashActivity.this,"下载新版本apk失败");
                loadMain();
            }
        });
    }

    /**
     * 检测版本是否是最新版本
     */
    private void checkVersion() {
        //  有三种方法
        Message msg = handler.obtainMessage();   //  第一种msg 已经和handler绑定了
        //Message msg = Message.obtain();

        long startTime = System.currentTimeMillis(); //   开始执行的是时间
        try {
            SystemClock.sleep(3000); //   开启界面显示两秒钟
            //  建立URL链接
            URL url = new URL(getResources().getString(R.string.url));
            HttpURLConnection conn  = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");      //设置GET响应方式
            conn.setConnectTimeout(3000);   //   设置响应时间 5s ,5s后就是超时
            //   代号：  200 成功  404 找不到资源
            int code = conn.getResponseCode();
            if(code==200){    //  链接成功
                //   好去输入流
                InputStream is = conn.getInputStream();
                //  把io流转换成字符串
                String res = Stream2String.process(is);
                //  打印res
                // String aa =URLDecoder.decode(res,"UTF-8");
                System.out.println("res = "+res);


                UrlData data =   parseJson(res);
                //  处理数据判断当前版本号是否一致，如果不一致，则说明有新版本

                // res = new String(res.getBytes("gb2312"),"UTF-8");
                System.out.println(res+">>>>>>");
                ShowToast.show(SplashActivity.this,data+"");

                if(data.getVersionCode()!=versionCode){  //  说明有新版本

                    System.out.println(data.getVersionCode());

                    //  进入会话框  显示是否更新版本
                    msg.what = UPDATAVERSION;
                    msg.obj = data;

                    // msg.sendToTarget();  // 弹出对话框，给自己发handler
                }else {   //没有新版本 进入主要界面
                    //    msg.what = LOADMAIN;
                    //msg.sendToTarget();  // 弹出对话框，给自己发handler
                }
            }else {           //    链接失败
                ShowToast.show(SplashActivity.this, "2000网络连接错误");

            }


        } catch (MalformedURLException e) {
            //  2001 url错误
            String res = "2001 url 错误";
            ShowToast.show(SplashActivity.this,res);

            e.printStackTrace();
        } catch (IOException e) {
            //  io错误，包括网络超时错误  2002
            String res = "2002   io错误，包括网络错误";
            ShowToast.show(SplashActivity.this,res);
            e.printStackTrace();
        } catch (JSONException e) {
            //  2003 Json 数据格式错误
            String res = "2003 Json 数据格式错误";
            ShowToast.show(SplashActivity.this,res);
            e.printStackTrace();
        }finally {
            if (msg.what == UPDATAVERSION) {

            } else {
                msg.what = LOADMAIN;
            }
            long endTime = System.currentTimeMillis();    //  运行结束的时间
            if ((endTime - startTime) < 3000) {
                SystemClock.sleep(3000 - (endTime - startTime));
            } else {
                //  什么都不执行
            }

            msg.sendToTarget();  // 弹出对话框，给自己发handler

        }
    }

    /**
     * j加载主界面
     */
    private void  loadMain(){
        Intent  intent = new Intent(SplashActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }




    /**
     *    解析Json数据
     */

    private UrlData parseJson(String  res)  throws JSONException{
        UrlData  urldata = new UrlData();
        JSONObject json = new JSONObject(res);
        urldata.setDesc(json.getString("desc"));
        urldata.setVersionCode(json.getInt("versionCode"));
        urldata.setDownUrl(json.getString("downloadUrl"));
        ShowToast.show(SplashActivity.this,res);

        return urldata;

    }

    /**
     * 初始化开始界面动画
     */
    private void initAnim() {
        AlphaAnimation aa = new AlphaAnimation(0,1);  //  透明度 从0 到1 透明度
        aa.setDuration(3000);  // 透明度持续时间3 S
        aa.setFillAfter(true);  //  说明渐变后是什么界面就是什么界面
        rl_root.setAnimation(aa);

    }

    /**
     *  初始化界面组件数据
     */
    private void iniData() {
        try {

            String packageName = getPackageName();  //  获取当前程序的包名
            packageInfo =  pm.getPackageInfo(packageName,PackageManager.GET_CONFIGURATIONS);

            String  versionName =  packageInfo.versionName;
            //  显示版本名称
            versionCode = packageInfo.versionCode;
            //     System.out.println(versionName+"   -----"+versionCode);
            tv_virsion.setText(versionName);
        } catch (PackageManager.NameNotFoundException e) {
            //   can't reach     异常不可能发生
        }
    }

    /**
     * 初始化界面组件
     */
    private void initView() {
        setContentView(R.layout.activity_splash);
        tv_virsion = (TextView) findViewById(R.id.tv_splash_versionname);
        pm = getPackageManager();    //   获取当前的包管理器
        rl_root = (RelativeLayout) findViewById(R.id.rl_splash_root);
        //   下载的进度条
        pb_Splash_down = (ProgressBar) findViewById(R.id.pb_Splash_down);
    }


    public void clickText(View view){
        String res = "点击版本号";
        ShowToast.show(SplashActivity.this,res);
        System.out.println(res);
    }
}
