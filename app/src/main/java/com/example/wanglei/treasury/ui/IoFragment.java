package com.example.wanglei.treasury.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanglei.treasury.R;

/**
 * Created by wanglei on 2017/7/6.
 * 对应收支界面
 */

public class IoFragment extends Fragment {
    private View ioFragmentLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ioFragmentLayout = inflater.inflate(R.layout.fragment_io, container, false);
        return ioFragmentLayout;
    }

}
