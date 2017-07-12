package com.example.wanglei.treasury.statistics;

import android.content.Context;

import com.example.wanglei.treasury.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;

/**
 * Created by wanglei on 2017/7/12.
 */

public class PieChart {
    private PieChartData pieChartData;

    public PieChartData setPieChart(Context context, int in, int out) {

        List<SliceValue> data = new ArrayList<SliceValue>();
        SliceValue sliceValueIn = new SliceValue();
        sliceValueIn.setLabel("收入");
        sliceValueIn.setColor(context.getResources().getColor(R.color.colorPrimary));
        data.add(sliceValueIn);

        SliceValue sliceValueOut = new SliceValue();
        sliceValueIn.setLabel("支出");
        sliceValueIn.setColor(context.getResources().getColor(R.color.colorAccent));
        data.add(sliceValueIn);

        pieChartData = new PieChartData(data);
        pieChartData.setHasLabelsOutside(true);

        return pieChartData;
    }
}
