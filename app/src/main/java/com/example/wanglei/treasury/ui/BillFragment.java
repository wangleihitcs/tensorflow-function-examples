package com.example.wanglei.treasury.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.wanglei.treasury.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by wanglei on 2017/7/6.
 * 对应账单界面
 */

public class BillFragment extends Fragment {
    private View billFragmentLayout;

    private ListView listView;//用于显示账单信息
    private SimpleAdapter simpleAdapter;//适配器
    private List<Map<String, Object>> listViewData = new ArrayList<Map<String, Object>>();;//list数据
    private String[] key = new String[]{"time", "type" ,"money", "explain"};
    private int[] listViewId = new int[]{R.id.listView_bill_name, R.id.listView_bill_type, R.id.listView_bill_money, R.id.listView_bill_explain};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        billFragmentLayout = inflater.inflate(R.layout.fragment_bill, container, false);

        initViews();
        getData();

        //listView设置适配器
        simpleAdapter = new SimpleAdapter(billFragmentLayout.getContext(), listViewData, R.layout.listview_bill_item, key, listViewId);
        listView.setAdapter(simpleAdapter);

        return billFragmentLayout;
    }

    /**
     * 初始化各个控件
     */
    public void initViews() {
        listView = (ListView) billFragmentLayout.findViewById(R.id.listView_bill);
    }
    /**
     * 获得账单listview数据
     */
    public void getData() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("time", "时间");
        map.put("type", "类型");
        map.put("money", "金额");
        map.put("explain", "说明");
        listViewData.add(map);

        map = new HashMap<String, Object>();
        map.put("time", "2017-05-31");
        map.put("type", "收入");
        map.put("money", "10000");
        map.put("explain", "工资到账");
        listViewData.add(map);

        map = new HashMap<String, Object>();
        map.put("time", "2017-06-11");
        map.put("type", "支出");
        map.put("money", "2300");
        map.put("explain", "买手机");
        listViewData.add(map);
    }


}
