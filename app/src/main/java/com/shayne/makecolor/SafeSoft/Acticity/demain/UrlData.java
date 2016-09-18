package com.shayne.makecolor.SafeSoft.Acticity.demain;

/**   封装json数据
 *      versionCode
 *      dowUrl
 *      desc
 * Created by huwei1993 on 2016/5/12.
 */
public class UrlData {
    private int versionCode;
    private String downUrl;
    private  String desc;
    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getDesc() {

        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "UrlData{" +
                "versionCode=" + versionCode +
                ", dowUrl='" + downUrl + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
