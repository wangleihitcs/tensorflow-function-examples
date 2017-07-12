package com.example.wanglei.treasury.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.wanglei.treasury.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wanglei on 2017/7/6.
 * 对应发现界面
 */

public class MoreFragment extends Fragment {
    private View moreFragmentLayout;
    private GridView gridView;

    private List<Map<String, Object>> gridViewData = new ArrayList<Map<String, Object>>();;//list数据
    private String[] key = new String[]{"image","text"};
    private int[] viewIds = new int[]{R.id.gridView_image, R.id.gridView_text};

    private FragmentManager fragmentManager;//Fragment管理器
    private UserInformationFragment userFragment;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        moreFragmentLayout = inflater.inflate(R.layout.fragment_more, container, false);
        initViews();

        fragmentManager = getFragmentManager();

        //设置数据
        getData();
        SimpleAdapter simpleAdapter = new SimpleAdapter(moreFragmentLayout.getContext(), gridViewData, R.layout.gridview_more_item, key, viewIds);
        gridView.setAdapter(simpleAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    //个人信息修改
                    case 0:
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.hide(MoreFragment.this);

                        if(userFragment == null) {
                            userFragment = new UserInformationFragment();
                            fragmentTransaction.add(R.id.content, userFragment);
                        } else {
                            fragmentTransaction.show(userFragment);
                        }
                        fragmentTransaction.commit();
                        break;
                    case 1:
                        Intent i = new Intent(moreFragmentLayout.getContext(), ManageMoneyActivity.class);
                        startActivity(i);
                        break;
                }
            }
        });

        return moreFragmentLayout;
    }

    private void initViews() {
        gridView = (GridView) moreFragmentLayout.findViewById(R.id.more_gridview);
    }

    /**
     * 获取网格view
     */
    public void getData() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("image", R.drawable.ic_userin);
        map.put("text", "用户信息");
        gridViewData.add(map);

        map = new HashMap<String, Object>();
        map.put("image", R.drawable.ic_cny);
        map.put("text", "理财利息");
        gridViewData.add(map);
    }
}
