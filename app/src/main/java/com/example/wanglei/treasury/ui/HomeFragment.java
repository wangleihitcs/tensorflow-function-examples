package com.example.wanglei.treasury.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wanglei.treasury.R;
import com.example.wanglei.treasury.service.GetLineData;
import com.example.wanglei.treasury.statistics.LineChart;

import java.sql.SQLException;
import java.util.Date;

import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by wanglei on 2017/7/6.
 * 对应主页界面
 */

public class HomeFragment extends Fragment {
    private View homeFragmentLayout;

    private LineChartView lineChartView;
    private LineChartData lineChartData;

<<<<<<< HEAD
    private GetLineData getLineData = new GetLineData();
=======
    private String name = "刘龙航", username = "liullhitcs";
    private TextView textViewName, textViewUsername;//姓名和用户名
>>>>>>> 7657b2db31ff7b51bde437aa5322c80a8d28f19a

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homeFragmentLayout = inflater.inflate(R.layout.fragment_home, container, false);

        initViews();
        setLinerChartData();

        lineChartView.setLineChartData(lineChartData);

        return homeFragmentLayout;
    }

    /**
     * 初始化各个views
     */
    public void initViews() {
        lineChartView = (LineChartView) homeFragmentLayout.findViewById(R.id.lineChart);
        textViewName = (TextView) homeFragmentLayout.findViewById(R.id.textView_name);
        textViewUsername = (TextView) homeFragmentLayout.findViewById(R.id.textView_username);

        textViewName.setText(name);
        textViewUsername.setText(username);
    }

    public void setNameAndUsername(String name, String username) {
        this.name = name;
        this.username = username;
    }
    /**
     * 最近五个月的折线图数据
     *
     */
    public void setLinerChartData() throws SQLException, ClassNotFoundException {
        int[] moneyIn = new int[] {1000, 3000, 2000, 1500, 2000};
        int[] moneyOut = new int[] {1200, 2000, 2000, 2500, 500};

        lineChartData = new LineChart().setLineChartData(homeFragmentLayout.getContext(), moneyIn, moneyOut, month);
    }
}
