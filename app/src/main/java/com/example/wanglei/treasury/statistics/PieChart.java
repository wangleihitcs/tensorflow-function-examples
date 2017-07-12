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

    public PieChartData setPieChart(Context context, double in, double out) {

        List<SliceValue> data = new ArrayList<SliceValue>();
        SliceValue sliceValueIn = new SliceValue((float) in);
        sliceValueIn.setLabel("收入");
        sliceValueIn.setColor(context.getResources().getColor(R.color.colorPrimary));
        data.add(sliceValueIn);

        SliceValue sliceValueOut = new SliceValue((float) out);
        sliceValueOut.setLabel("支出");
        sliceValueOut.setColor(context.getResources().getColor(R.color.colorAccent));
        data.add(sliceValueOut);

        pieChartData = new PieChartData(data);
        pieChartData.setHasLabels(true);
        pieChartData.setHasLabelsOutside(true);

        return pieChartData;
    }
}
