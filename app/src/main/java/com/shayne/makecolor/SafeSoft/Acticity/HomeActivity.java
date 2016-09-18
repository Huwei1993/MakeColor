package com.shayne.makecolor.SafeSoft.Acticity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.shayne.makecolor.R;


public class HomeActivity extends AppCompatActivity {

    private GridView gv_jiugongge;
    private String[] names = new String[]{"手机防盗","通讯卫士","软件管家"
            ,"进程管理","流量统计","手机杀毒","缓存清理","高级工具","设置中心"};
    private int[] icons = new int[]{R.mipmap.safe,R.mipmap.callmsgsafe,R.drawable.icon_selector
            ,R.mipmap.taskmanager,R.mipmap.netmanager,R.mipmap.trojan,
            R.mipmap.sysoptimize,R.mipmap.atools,R.mipmap.settings};
    private GVAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

    }

    private void  initData(){
        adapter = new GVAdapter();
        gv_jiugongge.setAdapter(adapter);
    }

    private void initView() {
        setContentView(R.layout.activity_home);
        gv_jiugongge = (GridView) findViewById(R.id.gv_home_jiugongge);
    }

    public class GVAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder  holder = null;
            if (convertView == null){
                convertView = View.inflate(getApplicationContext(),R.layout.item_home_gridview,null);

                holder = new ViewHolder();
                holder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_gridview_item_icon);
                holder.tv_name = (TextView) convertView.findViewById(R.id.tv_gridview_item_name);
                //   绑定组件
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.iv_icon.setBackgroundResource(icons[position]);
            holder.tv_name.setText(names[position]);

            return convertView;
        }
        /*@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View view = View.inflate(getApplicationContext(), R.layout.item_home_gridview, null);

			ImageView gv = (ImageView) view.findViewById(R.id.iv_gridview_item_icon);
			TextView tv = (TextView) view.findViewById(R.id.tv_gridview_item_name);

			gv.setBackgroundResource(icons[position]);
			tv.setText(names[position]);

			return view;
		}*/

    }

    private class ViewHolder{
        ImageView iv_icon;
        TextView tv_name;
    }
}
