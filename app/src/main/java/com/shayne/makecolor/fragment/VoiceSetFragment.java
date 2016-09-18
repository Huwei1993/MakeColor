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
public class VoiceSetFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return View.inflate(getActivity(), R.layout.voice_set_fragment,null);      //  创建声音布局文件

    }
}
