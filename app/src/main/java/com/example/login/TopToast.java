package com.example.login;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class TopToast extends Toast {

    public static final int NO_TOAST_IMAGE = 0;

    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public TopToast(Context context) {
        super(context);
    }

    public static Toast makeText(Context context, int resId, CharSequence text, int duration){
        Toast toast = new Toast(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.toast_top,null);
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        AppCompatImageView imageView = view.findViewById(R.id.toast_image);
        if (resId != NO_TOAST_IMAGE){
            imageView.setImageResource(resId);
        }

        AppCompatTextView textView = view.findViewById(R.id.toast_text);
        textView.setText(text);

        toast.setView(view);
        toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.TOP, 0, 0);
        toast.setDuration(duration);
        return toast;
    }
}
