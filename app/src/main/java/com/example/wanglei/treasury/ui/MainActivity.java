package com.example.wanglei.treasury.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.wanglei.treasury.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private FragmentManager fragmentManager;//Fragment管理器

    //四个fragment
    private HomeFragment homeFragment;
    private IoFragment ioFragment;
    private BillFragment billFragment;
    private MoreFragment moreFragment;

    //对应底部四个菜单栏区域，图标+TextView,用于菜单的选中变色，没太大用处
    private View homeLayout, ioLayout, billLayout, foundLayout;
    private TextView textView_menu_home, textView_menu_io, textView_menu_bill, textView_menu_found;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        fragmentManager = getFragmentManager();
        setTabSelection(0);
    }

    /**
     * 初始化各个控件，找到资源ID
     */
    public void initViews() {
        homeLayout = (View) findViewById(R.id.homeLayout);
        ioLayout = (View) findViewById(R.id.ioLayout);
        billLayout = (View) findViewById(R.id.billLayout);
        foundLayout = (View) findViewById(R.id.foundLayout);

        textView_menu_home = (TextView) findViewById(R.id.textView_MenuHome);
        textView_menu_io = (TextView) findViewById(R.id.textView_MenuIo);
        textView_menu_bill = (TextView) findViewById(R.id.textView_MenuBill);
        textView_menu_found = (TextView) findViewById(R.id.textView_MenuFound);

        homeLayout.setOnClickListener(this);
        ioLayout.setOnClickListener(this);
        billLayout.setOnClickListener(this);
        foundLayout.setOnClickListener(this);
    }


    /**
     * 底部菜单点击响应处理
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeLayout:
                setTabSelection(0);
                break;
            case R.id.ioLayout:
                setTabSelection(1);
                break;
            case R.id.billLayout:
                setTabSelection(2);
                break;
            default:
                setTabSelection(3);
                break;
        }
    }

    /**
     * 根据传入的参数，显示不同的底部菜单选项
     */
    public void setTabSelection(int index) {
        //第一步，初始化各个TextView颜色
        textView_menu_home.setTextColor(getResources().getColor(R.color.colorTextMenu));
        textView_menu_io.setTextColor(getResources().getColor(R.color.colorTextMenu));
        textView_menu_bill.setTextColor(getResources().getColor(R.color.colorTextMenu));
        textView_menu_found.setTextColor(getResources().getColor(R.color.colorTextMenu));

        //第二步，开启一个Fragment事物，隐藏所有Fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }
        if(ioFragment != null) {
            fragmentTransaction.hide(ioFragment);
        }
        if(billFragment != null) {
            fragmentTransaction.hide(billFragment);
        }
        if(moreFragment != null) {
            fragmentTransaction.hide(moreFragment);
        }

        //第三步，设置选中的底部菜单
        switch (index) {
            case 0://点击了"主页"
                String name = getIntent().getStringExtra("name");
                String username = getIntent().getStringExtra("username");
                textView_menu_home.setTextColor(getResources().getColor(R.color.colorTabSelect));
                if(homeFragment == null) {
                    homeFragment = new HomeFragment();
                    homeFragment.setNameAndUsername(name, username);
                    fragmentTransaction.add(R.id.content, homeFragment);
                } else {
                    fragmentTransaction.show(homeFragment);
                }
                break;
            case 1://点击了"收支"
                textView_menu_io.setTextColor(getResources().getColor(R.color.colorTabSelect));
                if(ioFragment == null) {
                    ioFragment = new IoFragment();
                    fragmentTransaction.add(R.id.content, ioFragment);
                } else {
                    fragmentTransaction.show(ioFragment);
                }
                break;
            case 2://点击了"账单"
                textView_menu_bill.setTextColor(getResources().getColor(R.color.colorTabSelect));
                if(billFragment == null) {
                    billFragment = new BillFragment();
                    fragmentTransaction.add(R.id.content, billFragment);
                } else {
                    fragmentTransaction.show(billFragment);
                }
                break;
            default://点击了"发现"
                textView_menu_found.setTextColor(getResources().getColor(R.color.colorTabSelect));
                if(moreFragment == null) {
                    moreFragment = new MoreFragment();
                    fragmentTransaction.add(R.id.content, moreFragment);
                } else {
                    fragmentTransaction.show(moreFragment);
                }
                break;
        }

        //第四步，提交Fragment事物
        fragmentTransaction.commit();
    }

}
