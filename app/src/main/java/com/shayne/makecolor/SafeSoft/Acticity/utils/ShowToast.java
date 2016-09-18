package com.shayne.makecolor.SafeSoft.Acticity.utils;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by huwei1993 on 2016/5/12.
 */
public class ShowToast {
    public  static void show(final Activity context, final String str){
        if (Thread.currentThread().getName().equals("main")){
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        } else {//子线程
            context.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
