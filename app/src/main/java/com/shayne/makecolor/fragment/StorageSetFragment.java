package com.shayne.makecolor.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shayne.makecolor.R;

/**
 * Created by huwei1993 on 2016/5/10.
 */
public class StorageSetFragment extends Fragment {
    private  View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.storage_set_fragment,null);
        return   view;   //  创建存储布局文件

    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        view.findViewById(R.id.stroage);    //   因为这不是Activity ，所以需要换一种方式获取id控件
//    }
}
