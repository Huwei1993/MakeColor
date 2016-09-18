package com.shayne.makecolor.Message2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.shayne.makecolor.R;

/**
 * Created by huwei1993 on 2016/5/7.
 */
public class SmessListActivity2 extends AppCompatActivity {
    private ListView lv;
    String[] objects = {
            "1、青春是用意志的血滴和拼搏的汗水酿成的琼浆——历久弥香；",
            "2、青春是用不凋的希望和不灭的向往编织的彩虹——绚丽辉煌；青春是用永恒的执著和顽强的韧劲筑起的一道铜墙铁壁——固若金汤。" ,
            "3、信念是巍巍大厦的栋梁，没有它，就只是一堆散乱的砖瓦；信念是滔滔大江的河床，没有它，就只有一片泛滥的波浪；",
            "4信念是熊熊烈火的引星，没有它，就只有一把冰冷的柴把；信念是远洋巨轮的主机，没有它，就只剩下瘫痪的巨架。",
            "5、站在历史的海岸漫溯那一道道历史沟渠：楚大夫沉吟泽畔，九死不悔；魏武帝扬鞭东指，壮心不已；陶渊明悠然南山，饮酒采菊……他们选择了永恒，纵然谄媚诬蔑视听，也不随其流扬其波",
            "6、这是执著的选择；纵然马革裹尸，魂归狼烟，只是豪壮的选择；纵然一身清苦，终日难饱，也愿怡然自乐，躬耕陇亩，这是高雅的选择。在一番选择中，帝王将相成其盖世伟业，贤士迁客成其千古文章。",
            "7、只有启程，才会到达理想和目的地，只有拼搏，才会获得辉煌的成功，只有播种，才会有收获。只有追求，才会品味堂堂正正的人。"

    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messagelist2);

        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new ArrayAdapter<String>(this,R.layout.item2,objects));

        //给ListView 设置条目点击事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            /**
             * @parent 是当前整个页面的ListView
             * @View    是点击条目的当前行
             * @position  当前页面的第几条 条目，从零开始
             * @id       id与position是一样的效果
             */
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            System.out.println("parent---"+parent+"view-"+view+"--position"+position+"id ---"+id);
                String content  = objects[position];  //获取短信的内容
//                Intent intent = new Intent(SmessListActivity2.this,SmessMainActivity2.class);
//                intent.putExtra("content",content);
//                startActivity(intent);
                System.out.println("content:"+content);
                Intent data = new Intent();     //  不开启任何界面，纯粹只是表示数据
                data.putExtra("content",content);
                setResult(0,data);

                finish();      //关闭界面

            }
        });
    }
}
