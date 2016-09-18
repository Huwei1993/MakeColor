package com.shayne.makecolor.SafeSoft.Acticity.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**     将数据转换成String类型的
 * Created by huwei1993 on 2016/5/12.
 */
public class Stream2String {
    public  static  String process(InputStream  is){
        String res = "";
        BufferedReader  reader = new BufferedReader(new InputStreamReader(is));
        String line ;
        try {   //    循环读取文件信息
            line = reader.readLine();
            while (line!=null){
                res  += line;
                line=reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }
}
