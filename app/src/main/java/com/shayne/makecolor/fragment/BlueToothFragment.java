package com.shayne.makecolor.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shayne.makecolor.R;

/**
 * Created by huwei1993 on 2016/5/10.
 */
public class BlueToothFragment  extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return View.inflate(getActivity(), R.layout.blue_tooth_fragment,null);      //  创建蓝牙布局文件

    }
}
