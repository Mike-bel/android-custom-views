package com.yifeng.view.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.yifeng.view.R;

/**
 * Created by lxf on 2017/7/29.
 * arc
 */

public class CircleLoadingView extends View {

    private Paint mPaint;
    private RectF mRectF;

    private int mRatio = 60;    // a ratio used to control rotational speed

    private float mArcSpacing;  // the spacing between two section arc
    private float mArcWidth;    // the arc paint stroke width
    private float mArcRadian;   // the radian of a single section arc
    private int mStartColor;
    private int mEndColor;

    private int mWidth;
    private int mHeight;

    private float maxAngle = 0;

    public CircleLoadingView(Context context) {
        this(context, null);
    }

    public CircleLoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleLoadingView);
        mStartColor = typedArray.getColor(R.styleable.CircleLoadingView_circleStartColor,
                ContextCompat.getColor(context, R.color.colorPrimary));
        mEndColor = typedArray.getColor(R.styleable.CircleLoadingView_circleEndColor,
                ContextCompat.getColor(context, R.color.colorPrimaryDark));
        mArcSpacing = typedArray.getInteger(R.styleable.CircleLoadingView_circleArcSpacing, 10);
        mArcWidth = typedArray.getInteger(R.styleable.CircleLoadingView_circleArcWidth, 5);
        mArcRadian = typedArray.getInteger(R.styleable.CircleLoadingView_circleArcWidth, 5);
        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mArcWidth);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = getWidth();
        mHeight = getHeight();
        mRectF = new RectF(mArcWidth, mArcWidth, mWidth - mArcWidth, mHeight - mArcWidth);

        Shader shader = new LinearGradient(0f, 0f, mWidth, mHeight,
                mStartColor, mEndColor, Shader.TileMode.CLAMP);
        mPaint.setShader(shader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (maxAngle <= 360) {
            float angle = 0;
            canvas.rotate(mRatio * maxAngle / 360, mWidth / 2, mHeight / 2);
            canvas.drawArc(mRectF, maxAngle, 360 - maxAngle, false, mPaint);
            while (angle <= maxAngle) {
                float length = mArcRadian * angle / maxAngle;
                canvas.drawArc(mRectF, 0, length, false, mPaint);
                canvas.rotate(mArcSpacing, mWidth / 2, mHeight / 2);
                angle += mArcSpacing;
            }
        } else {
            float angle = 0;
            canvas.rotate(mRatio + mRatio * (maxAngle - 360) / 360, mWidth / 2, mHeight / 2);
            canvas.drawArc(mRectF, 0, maxAngle - 360, false, mPaint);
            canvas.rotate(maxAngle - 360, mWidth / 2, mHeight / 2);
            while (angle <= 720 - maxAngle) {
                float length = mArcRadian * angle / (720 - maxAngle);
                canvas.drawArc(mRectF, 0, length, false, mPaint);
                canvas.rotate(mArcSpacing, mWidth / 2, mHeight / 2);
                angle += mArcSpacing;
            }
        }

        if (maxAngle <= 720) {
            maxAngle += mArcSpacing;
            postInvalidateDelayed(30);
        }
    }

}
