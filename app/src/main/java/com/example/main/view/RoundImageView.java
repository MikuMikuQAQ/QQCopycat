package com.example.main.view;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;

public class RoundImageView extends AppCompatImageView {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap rawBitmap;
    private BitmapShader shader;
    private Matrix mMatrix = new Matrix();

    public RoundImageView(Context context) {
        super(context);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = getCircleBitmap(getDrawable());
        if (bitmap != null) {
            int viewWidth = getWidth();
            int viewHeight = getHeight();
            int viewMinSize = Math.min(viewWidth, viewHeight);
            float dstWidth = viewMinSize;
            float dstHeight = viewMinSize;
            if (shader == null || !bitmap.equals(rawBitmap)) {
                rawBitmap = bitmap;
                shader = new BitmapShader(rawBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            }
            if (shader != null) {
                mMatrix.setScale(dstWidth / bitmap.getWidth(), dstHeight / bitmap.getHeight());
                shader.setLocalMatrix(mMatrix);
            }
            paint.setShader(shader);
            float radius = viewMinSize / 2.0f;
            canvas.drawCircle(radius, radius, radius, paint);
        } else {
            super.onDraw(canvas);
        }
    }

    private Bitmap getCircleBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof ColorDrawable) {
            Rect rect = drawable.getBounds();
            int width = rect.right - rect.left;
            int height = rect.bottom - rect.top;
            int color = ((ColorDrawable) drawable).getColor();
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            canvas.drawARGB(Color.alpha(color), Color.red(color), Color.green(color), Color.blue(color));
            return bitmap;
        } else {
            return null;
        }
    }
}
