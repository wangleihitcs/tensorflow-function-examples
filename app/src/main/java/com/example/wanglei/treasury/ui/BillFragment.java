package com.example.wanglei.treasury.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanglei.treasury.R;

/**
 * Created by wanglei on 2017/7/6.
 * 对应账单界面
 */

public class BillFragment extends Fragment {
    private View billFragmentLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        billFragmentLayout = inflater.inflate(R.layout.fragment_bill, container, false);
        return billFragmentLayout;
    }

}
