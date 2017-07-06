package com.example.wanglei.treasury.statistics;

import android.content.Context;

import com.example.wanglei.treasury.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;

/**
 * Created by wanglei on 2017/7/6.
 */

public class LineChart {
    private LineChartData lineChartData;

    public LineChartData setLineChartData(Context context, int[] moneyIn, int[] moneyOut, String[] month) {
        List<Line> lines = new ArrayList<Line>();//存储线条
        List<PointValue> pointValues = new ArrayList<PointValue>();

        //收入折线的数据
        for(int i = 0; i < moneyIn.length; i++) {
            pointValues.add(new PointValue(i,moneyIn[i]));
        }
        Line line = new Line(pointValues);
        line.setColor(context.getResources().getColor(R.color.colorWhite));//线条颜色白色
        line.setShape(ValueShape.CIRCLE);//点为圆形
        line.setHasLabels(true);
        lines.add(line);

        //支出折线的数据
        pointValues = new ArrayList<PointValue>();
        for(int i = 0; i < moneyOut.length; i++) {
            pointValues.add(new PointValue(i,moneyOut[i]));
        }
        line = new Line(pointValues);
        line.setColor(context.getResources().getColor(R.color.colorAccent));//线条颜色红色
        line.setShape(ValueShape.SQUARE);//点为圆形
        line.setHasLabels(true);
        lines.add(line);

        lineChartData = new LineChartData(lines);

        //XY轴
        Axis axisX = new Axis();
        Axis axisY = new Axis();
        ArrayList<AxisValue> axisValuesX = new ArrayList<AxisValue>();//定义X轴刻度值的数据集合
        //X轴底部显示的近五月份
        for(int i = 0; i < month.length; i++) {
            axisValuesX.add(new AxisValue(i).setLabel(month[i]));
        }
        axisX.setValues(axisValuesX);
        axisX.setName("近五个月收支");
        axisX.setTextColor(context.getResources().getColor(R.color.colorWhite));
        axisY.setTextColor(context.getResources().getColor(R.color.colorWhite));
        axisX.setLineColor(context.getResources().getColor(R.color.colorWhite));
        axisY.setLineColor(context.getResources().getColor(R.color.colorWhite));
        axisX.setTextSize(16);
        axisY.setHasLines(true);

        lineChartData.setAxisXBottom(axisX);
        lineChartData.setAxisYLeft(axisY);
        lineChartData.setBaseValue(Float.NEGATIVE_INFINITY);

        return lineChartData;
    }
}
