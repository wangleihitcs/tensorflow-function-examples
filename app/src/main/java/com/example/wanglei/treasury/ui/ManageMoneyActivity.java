package com.example.wanglei.treasury.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.wanglei.treasury.R;

/**
 * Created by wanglei on 2017/7/11.
 */

public class ManageMoneyActivity extends AppCompatActivity {
    private TextView textViewTitle, textViewMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managemoney);

        initViews();
    }

    private void initViews() {
        textViewTitle = (TextView) findViewById(R.id.textView_title);
        textViewTitle.setText("理财利息");

        textViewMoney = (TextView) findViewById(R.id.manage_money);
        textViewMoney.setText("10000");
    }
}
