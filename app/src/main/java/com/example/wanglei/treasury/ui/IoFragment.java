package com.example.wanglei.treasury.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanglei.treasury.R;
import com.example.wanglei.treasury.entity.BillEntity;
import com.example.wanglei.treasury.service.SavaBill;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * Created by wanglei on 2017/7/6.
 * 对应收支界面
 */

public class IoFragment extends Fragment {
    private View ioFragmentLayout;
    private DatePicker datePicker;//日期
    private TextView textView;//显示日期的textview
    private RadioGroup radioGroup;//类型
    private RadioButton radioButton;//类型,收入、支出
    private EditText editTextMoney;//金额
    private EditText editTextExplain;//说明
    private Button button_add;//增加

    private SavaBill savaBill;

    //获取账单信息
    private BillEntity billEntity = new BillEntity();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ioFragmentLayout = inflater.inflate(R.layout.fragment_io, container, false);

        initViews();

        //点击日期控件出现
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.setVisibility(View.VISIBLE);
                datePicker.init(2017, 7, 7, new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        textView.setText(year+"-"+monthOfYear+"-"+dayOfMonth);
                        datePicker.setVisibility(View.GONE);
                    }
                });
            }
        });

        //增加账单
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 第一步,得到账单信息
                 */
                //获取时间
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    billEntity.setDate(simpleDateFormat.parse(textView.getText().toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //获取类型
                radioButton = (RadioButton) ioFragmentLayout.findViewById(radioGroup.getCheckedRadioButtonId());
                if("收入".equals(radioButton.getText().toString())) {
                    billEntity.setType(1);
                } else {
                    billEntity.setType(0);
                }
                billEntity.setBillId(System.currentTimeMillis()+"");
                //获取金额
                billEntity.setMoney(Integer.parseInt(editTextMoney.getText().toString()));
                //获取说明
                billEntity.setExpalin(editTextExplain.getText().toString());
                //获取用户名
                billEntity.setUsername("zheng123");

                Toast.makeText(ioFragmentLayout.getContext(),billEntity.toString(), Toast.LENGTH_LONG).show();

                /**
                 * 第二步,将账单增加到数据库
                 */
                try {
                    addBillEntityToBase();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        return ioFragmentLayout;
    }

    /**
     * 初始化各个控件
     */
    public void initViews() {
        datePicker = (DatePicker) ioFragmentLayout.findViewById(R.id.datePicker);
        textView = (TextView) ioFragmentLayout.findViewById(R.id.textView_date);
        radioGroup = (RadioGroup) ioFragmentLayout.findViewById(R.id.radioGroup_type1);
        editTextMoney = (EditText) ioFragmentLayout.findViewById(R.id.editText_money);
        editTextExplain = (EditText) ioFragmentLayout.findViewById(R.id.editText_explain);
        button_add = (Button) ioFragmentLayout.findViewById(R.id.button_add);
    }

    /**
     * 增加账单信息billEntity处理
     */
    public void addBillEntityToBase() throws SQLException, ClassNotFoundException {
        savaBill = new SavaBill();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    savaBill.saveBill(billEntity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
