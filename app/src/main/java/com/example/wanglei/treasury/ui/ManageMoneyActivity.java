package com.example.wanglei.treasury.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanglei.treasury.R;

/**
 * Created by wanglei on 2017/7/11.
 */

public class ManageMoneyActivity extends AppCompatActivity {
    private TextView textViewTitle, textViewMoney, textViewRate, textViewMonth, textViewLixi;
    private Context context;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managemoney);
        context = this;
        initViews();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textViewMoney.getText().toString().equals("") | textViewRate.getText().toString().equals("") |
                        textViewMonth.getText().toString().equals("")) {
                    Toast.makeText(context, "不能输入为空", Toast.LENGTH_LONG).show();
                } else {
                    double money = Double.parseDouble(textViewMoney.getText().toString());
                    double rate = Double.parseDouble(textViewRate.getText().toString());
                    double month = Double.parseDouble(textViewMonth.getText().toString());

                    textViewLixi.setText((money*rate*month)/1200+"");
//                    Toast.makeText(context, "利息："+(money*rate*month)/12, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initViews() {
        textViewTitle = (TextView) findViewById(R.id.textView_title);
        textViewTitle.setText("理财利息");

        textViewMoney = (TextView) findViewById(R.id.manage_money);
        textViewMoney.setText("10000");

        textViewRate = (TextView) findViewById(R.id.manage_rate);
        textViewRate.setText("4.5");

        textViewMonth = (TextView) findViewById(R.id.manage_month);
        textViewMonth.setText("13");

        button = (Button) findViewById(R.id.manage_button);

        textViewLixi = (TextView) findViewById(R.id.manage_lixi);
    }
}
