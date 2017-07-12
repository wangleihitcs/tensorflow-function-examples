package com.example.wanglei.treasury.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.wanglei.treasury.R;
import com.example.wanglei.treasury.entity.UserEntity;

/**
 * Created by wanglei on 2017/7/10.
 */

public class UserInformationFragment extends Fragment implements View.OnClickListener{
    private View userFragmentLayout;
    private LinearLayout linearLayout;

    private EditText editTextUsername, editTextPassword, editTextName;
    private Button buttonCommit;

    private UserEntity userEntity = new UserEntity();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        userFragmentLayout = inflater.inflate(R.layout.activity_regist, container, false);
        initViews();
        return userFragmentLayout;
    }

    private void initViews() {
        linearLayout = (LinearLayout) userFragmentLayout.findViewById(R.id.include_main_title);
        linearLayout.setVisibility(View.GONE);
        editTextUsername = (EditText) userFragmentLayout.findViewById(R.id.regist_username);
        editTextPassword = (EditText) userFragmentLayout.findViewById(R.id.regist_password);
        editTextName = (EditText) userFragmentLayout.findViewById(R.id.regist_name);
        buttonCommit = (Button) userFragmentLayout.findViewById(R.id.regist_commit);

        editTextUsername.setText("zheng123");
        editTextPassword.setText("123456");
        editTextName.setText("应秉正");

        buttonCommit.setText("修改提交");

        buttonCommit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(editTextUsername.getText().toString().equals("") | editTextPassword.getText().toString().equals("") | editTextName.getText().toString().equals("")) {
            Toast.makeText(userFragmentLayout.getContext(), "不能为空", Toast.LENGTH_LONG).show();
        } else {
            userEntity.setUsername(editTextUsername.getText().toString());
            userEntity.setUserpassword(editTextPassword.getText().toString());
            userEntity.setName(editTextName.getText().toString());

            updateUserEntity(userEntity);

            Toast.makeText(userFragmentLayout.getContext(), "修改成功", Toast.LENGTH_LONG).show();

            Intent i = new Intent(userFragmentLayout.getContext(), MainActivity.class);
            i.putExtra("name", userEntity.getName());
            i.putExtra("username", userEntity.getUsername());
            startActivity(i);
        }
    }

    public void updateUserEntity(UserEntity userEntity) {

    }
}
