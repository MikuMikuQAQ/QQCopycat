package com.example.qq;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Animation.AnimationListener {

    private AppCompatButton loginYes;

    private AppCompatButton regYes;

    private AppCompatButton loginButton;

    private AppCompatEditText username;

    private AppCompatEditText password;

    private LinearLayout bottomLayout;

    private LinearLayout loginLayout;

    private LinearLayout logoLayout;

    private LinearLayout logoLayout1;

    private Animation fadeIn;

    private Animation fadeOut;

    private Animation logoMove;

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

        fadeIn = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(this,R.anim.fade_out);
        logoMove = AnimationUtils.loadAnimation(this,R.anim.logo_move);

        loginYes.setOnClickListener(this);
        regYes.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        logoMove.setAnimationListener(this);
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
            default:
                break;
        }
    }

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
}
