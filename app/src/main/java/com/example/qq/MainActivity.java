package com.example.qq;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    private AppCompatButton loginYes;

    private AppCompatButton regYes;

    private AppCompatButton loginButton;

    private AppCompatEditText username;

    private AppCompatEditText password;

    private AppCompatImageView clearPwd;

    private AppCompatImageView eyesOpen;

    private AppCompatImageView eyesClose;

    private AppCompatImageView clearUsername;

    private LinearLayout bottomLayout;

    private LinearLayout loginLayout;

    private LinearLayout logoLayout;

    private LinearLayout logoLayout1;

    private Animation fadeIn;

    private Animation fadeOut;

    private Animation logoMove;

    private boolean isPwdClear = true;

    private boolean isUserClear = true;

    private TextWatcher pwdWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //Log.e("TAG", "beforeTextChanged: " + s + "\t" + start + "\t" + count + "\t" + after);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //Log.e("TAG", "onTextChanged: "  + s + "\t" + start + "\t" + count + "\t" + before);
            if (count >= 1) {
                isPwdClear = false;
                pwdEditViewStatus(isPwdClear);
            } else if (start == 0 && count == 0){
                isPwdClear = true;
                pwdEditViewStatus(isPwdClear);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            //Log.e("TAG", "afterTextChanged: " + s);
        }
    };

    private TextWatcher userWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (count >= 1) {
                isUserClear = false;
                userEditViewStatus(isUserClear);
            } else if (start == 0 && count == 0){
                isUserClear = true;
                userEditViewStatus(isUserClear);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private Animation.AnimationListener logoMoveListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            logoLayout1.setVisibility(View.VISIBLE);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        }

        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){
        loginYes = findViewById(R.id.login_yes);
        regYes = findViewById(R.id.registered_yes);
        loginButton = findViewById(R.id.btn_login);
        username = findViewById(R.id.edit_username);
        password = findViewById(R.id.edit_password);
        bottomLayout = findViewById(R.id.bottom_layout);
        loginLayout = findViewById(R.id.login_layout);
        logoLayout = findViewById(R.id.logo_layout);
        logoLayout1 = findViewById(R.id.logo_layout_1);
        clearPwd = findViewById(R.id.password_clear);
        eyesOpen = findViewById(R.id.password_eyes_open);
        eyesClose = findViewById(R.id.password_eyes_close);
        clearUsername = findViewById(R.id.username_clear);

        fadeIn = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(this,R.anim.fade_out);
        logoMove = AnimationUtils.loadAnimation(this,R.anim.logo_move);

        loginYes.setOnClickListener(this);
        regYes.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        clearPwd.setOnClickListener(this);
        eyesOpen.setOnClickListener(this);
        eyesClose.setOnClickListener(this);
        clearUsername.setOnClickListener(this);

        logoMove.setAnimationListener(logoMoveListener);

        password.setOnFocusChangeListener(this);
        username.setOnFocusChangeListener(this);

        password.addTextChangedListener(pwdWatcher);
        username.addTextChangedListener(userWatcher);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_yes:
                bottomLayout.setVisibility(View.GONE);
                bottomLayout.startAnimation(fadeOut);
                loginLayout.setVisibility(View.VISIBLE);
                loginLayout.startAnimation(fadeIn);
                logoLayout.setVisibility(View.GONE);
                logoLayout.startAnimation(logoMove);
                break;
            case R.id.registered_yes:
                Toast.makeText(this, "功能开发中敬请期待...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_login:
                Toast.makeText(this, "功能开发中敬请期待...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.password_clear:
                password.setText("");
                isPwdClear = true;
                pwdEditViewStatus(isPwdClear);
                break;
            case R.id.password_eyes_open:
                password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                eyesOpen.setVisibility(View.GONE);
                eyesClose.setVisibility(View.VISIBLE);
                break;
            case R.id.password_eyes_close:
                password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                eyesClose.setVisibility(View.GONE);
                eyesOpen.setVisibility(View.VISIBLE);
                break;
            case R.id.username_clear:
                username.setText("");
                isUserClear = true;
                userEditViewStatus(isUserClear);
                break;
            default:
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.edit_password:
                if (hasFocus) {
                    eyesClose.setVisibility(View.VISIBLE);
                    pwdEditViewStatus(isPwdClear);
                } else {
                    eyesClose.setVisibility(View.GONE);
                    pwdEditViewStatus(true);
                }
                break;
            case R.id.edit_username:
                if (hasFocus) {
                    userEditViewStatus(isUserClear);
                } else {
                    userEditViewStatus(true);
                }
                break;
            default:
                break;
        }
    }

    private void pwdEditViewStatus(boolean b){
        if (b) {
            clearPwd.setVisibility(View.GONE);
        } else {
            clearPwd.setVisibility(View.VISIBLE);
        }
    }

    private void userEditViewStatus(boolean b){
        if (b) {
            clearUsername.setVisibility(View.GONE);
        } else {
            clearUsername.setVisibility(View.VISIBLE);
        }
    }
}
