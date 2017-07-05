package com.example.wanglei.treasury.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanglei.treasury.R;

/**
 * Created by wanglei on 2017/7/6.
 * 对应发现界面
 */

public class FoundFragment extends Fragment {
    private View foundFragmentLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        foundFragmentLayout = inflater.inflate(R.layout.fragment_found, container, false);
        return foundFragmentLayout;
    }

}
