package com.example.qq;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class LoginEditView extends AppCompatEditText {

    private int width;

    private int height;

    private Paint lineDown;

    public LoginEditView(Context context) {
        super(context);
    }

    public LoginEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoginEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        setBackground(null);
        setMaxLines(1);
        setLines(1);

        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager manager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.showSoftInput(LoginEditView.this,0);
                return true;
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        lineDown = new Paint();
        lineDown.setColor(getColor(R.color.colorFont));
        lineDown.setAntiAlias(true);
        lineDown.setStrokeWidth(10);
        canvas.drawLine(0, height , width, height , lineDown);
    }

    public int getColor(int id) {
        return ContextCompat.getColor(getContext(), id);
    }

    public int spTopx(float spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
