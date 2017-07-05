package com.example.wanglei.treasury.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanglei.treasury.R;

/**
 * Created by wanglei on 2017/7/6.
 * 对应主页界面
 */

public class HomeFragment extends Fragment {
    private View homeFragmentLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homeFragmentLayout = inflater.inflate(R.layout.fragment_home, container, false);
        return homeFragmentLayout;
    }

}
