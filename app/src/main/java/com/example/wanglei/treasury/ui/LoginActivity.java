package com.example.wanglei.treasury.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanglei.treasury.R;
import com.example.wanglei.treasury.entity.UserEntity;
import com.example.wanglei.treasury.service.UserService;
import com.example.wanglei.treasury.utils.JellyInterpolator;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by wanglei on 2017/7/9.
 */

public class LoginActivity extends Activity implements View.OnClickListener {
    private TextView mBtnLogin;
    private View progress;
    private View mInputLayout;
    private float mWidth, mHeight;
    private LinearLayout mName, mPsw;

    private Context context;

    private ImageView imageViewBack;//返回图标
    private TextView textViewRegist;
    private EditText editText_username, editText_password; //用户名、密码
    private UserEntity userEntity = new UserEntity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        context = this;
        initViews();
    }

    private void initViews() {
        mBtnLogin = (TextView) findViewById(R.id.main_btn_login);
        progress = findViewById(R.id.layout_progress);
        mInputLayout = findViewById(R.id.input_layout);
        mName = (LinearLayout) findViewById(R.id.input_layout_name);
        mPsw = (LinearLayout) findViewById(R.id.input_layout_psw);

        textViewRegist = (TextView) findViewById(R.id.main_btn_regist);
        imageViewBack = (ImageView) findViewById(R.id.imageView_back);
        editText_username = (EditText) findViewById(R.id.editText_username);
        editText_password = (EditText) findViewById(R.id.editText_password);

        textViewRegist.setOnClickListener(this);
        imageViewBack.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // 计算出控件的高与宽
        mWidth = mBtnLogin.getMeasuredWidth();
        mHeight = mBtnLogin.getMeasuredHeight();
        // 隐藏输入框
        mName.setVisibility(View.INVISIBLE);
        mPsw.setVisibility(View.INVISIBLE);

        switch (v.getId()) {
            //登录
            case R.id.main_btn_login:
                //调用进度条的动画,转到响应界面
                String username = editText_username.getText().toString();
                String password = editText_password.getText().toString();
                if(username.equals("") | password.equals("")) {
                    mName.setVisibility(View.VISIBLE);
                    mPsw.setVisibility(View.VISIBLE);
                    Toast.makeText(context, "请输入内容", Toast.LENGTH_LONG).show();
                } else {
//                    try {
//                        if(isLogin(username, password)) {
//                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
//                            i.putExtra("name", userEntity.getName());
//                            i.putExtra("username", userEntity.getUsername());
//                            inputAnimator(mInputLayout, mWidth, mHeight, i);
//                        }
//                        else {
//                            Intent i = null;
//                            inputAnimator(mInputLayout, mWidth, mHeight, i);
//                        }
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    } catch (ClassNotFoundException e) {
//                        e.printStackTrace();
//                    }

                    if(isLogin2(username, password)) {
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        i.putExtra("name", userEntity.getName());
                        i.putExtra("username", userEntity.getUsername());
                        inputAnimator(mInputLayout, mWidth, mHeight, i);
                    }
                    else {
                        Intent i = null;
                        inputAnimator(mInputLayout, mWidth, mHeight, i);
                    }
                }
                break;
            //返回
            case R.id.imageView_back:
                finish();
                break;
            //注册
            case R.id.main_btn_regist:
                Intent i = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(i);
                break;
        }
    }

    public boolean isLogin(String user, String psw) throws SQLException, ClassNotFoundException {
        UserService userService = new UserService();
        UserEntity selectUser = userService.checkLogin(user, psw);
        if(selectUser != null) {
            userEntity.setUsername(user);
            userEntity.setUserpassword(psw);
            userEntity.setName(selectUser.getName());
            return true;
        }
        return false;
    }

    public boolean isLogin2(String user, String psw) {
        if(user.equals("zheng123") && psw.equals("123456")) {
            userEntity.setUsername("zheng123");
            userEntity.setUserpassword("123456");
            userEntity.setName("应秉正");
            return true;
        }
        return false;
    }




    /**
     * 输入框的动画效果
     *
     * @param view
     *      控件
     * @param w
     *      宽
     * @param h
     *      高
     */
    private void inputAnimator(final View view, float w, float h, Intent i) {

        AnimatorSet set = new AnimatorSet();

        ValueAnimator animator = ValueAnimator.ofFloat(0, w);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view
                        .getLayoutParams();
                params.leftMargin = (int) value;
                params.rightMargin = (int) value;
                view.setLayoutParams(params);
            }
        });

        //框动画
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mInputLayout,
                "scaleX", 1f, 0.5f);
        set.setDuration(500);
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.playTogether(animator, animator2);
        set.start();
        set.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                /**
                 * 动画结束后，先显示加载的动画，然后再隐藏输入框
                 */
                progress.setVisibility(View.VISIBLE);
                progressAnimator(progress, i);
                mInputLayout.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });

    }

    /**
     * 出现进度动画
     *
     * @param view
     */
    private void progressAnimator(final View view, Intent i) {
        PropertyValuesHolder animator = PropertyValuesHolder.ofFloat("scaleX",
                0.5f, 1f);
        PropertyValuesHolder animator2 = PropertyValuesHolder.ofFloat("scaleY",
                0.5f, 1f);
        ObjectAnimator animator3 = ObjectAnimator.ofPropertyValuesHolder(view,
                animator, animator2);
        animator3.setDuration(2000);
        animator3.setInterpolator(new JellyInterpolator());
        animator3.start();
        animator3.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(i != null) {
                    startActivity(i);
                } else {
                    Toast.makeText(context, "用户名或密码错误", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

}
