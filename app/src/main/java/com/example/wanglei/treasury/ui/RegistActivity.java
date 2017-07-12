package com.example.wanglei.treasury.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanglei.treasury.R;
import com.example.wanglei.treasury.entity.UserEntity;
import com.example.wanglei.treasury.service.UserService;

import java.sql.SQLException;

/**
 * Created by wanglei on 2017/7/10.
 */

public class RegistActivity extends AppCompatActivity implements View.OnClickListener{
    private Context context;
    private TextView textViewTitle;
    private EditText editTextUsername, editTextPassword, editTextName;
    private Button buttonCommit;

    private UserEntity userEntity = new UserEntity();//注册用户
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        context = this;
        initViews();
    }

    private void initViews() {
        textViewTitle = (TextView) findViewById(R.id.textView_title);
        textViewTitle.setText("用户注册");

        editTextUsername = (EditText) findViewById(R.id.regist_username);
        editTextPassword = (EditText) findViewById(R.id.regist_password);
        editTextName = (EditText) findViewById(R.id.regist_name);
        buttonCommit = (Button) findViewById(R.id.regist_commit);

        buttonCommit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //提交注册
            case R.id.regist_commit:
                if(editTextUsername.getText().toString().equals("") | editTextPassword.getText().toString().equals("") | editTextName.getText().toString().equals("")) {
                    Toast.makeText(context, "不能为空", Toast.LENGTH_LONG).show();
                } else {
                    userEntity.setUsername(editTextUsername.getText().toString());
                    userEntity.setUserpassword(editTextPassword.getText().toString());
                    userEntity.setName(editTextName.getText().toString());

                    try {
                        addUserEntity(userEntity);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(context, "注册成功", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(RegistActivity.this, LoginActivity.class);
                    startActivity(i);
                }

                break;
        }
    }

    //注册新用户
    public void addUserEntity(UserEntity userEntity) throws SQLException, ClassNotFoundException {
        UserService userService = new UserService();
        userService.addUser(userEntity);
    }
}
