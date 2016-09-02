package com.lingdian.saylove.second.layout;

import android.animation.ArgbEvaluator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lingdian.saylove.R;
import com.lingdian.saylove.second.lib.Discrollvable;

/**
 *
 */
public class DiscrollvableGreenLayout extends LinearLayout implements Discrollvable {

    private static final String TAG = "DiscrollvableGreenLayout";

    private TextView mGreenView1;
    private float mGreenView1TranslationY;
    private int mGreenColor;
    private int mBlackColor = Color.BLACK;
    @SuppressLint("NewApi") private ArgbEvaluator mArgbEvaluator = new ArgbEvaluator();

    public DiscrollvableGreenLayout(Context context) {
        super(context);
    }

    public DiscrollvableGreenLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressLint("NewApi") public DiscrollvableGreenLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @SuppressLint("NewApi") @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mGreenView1 = (TextView) findViewById(R.id.greenView1);
        mGreenView1TranslationY = mGreenView1.getTranslationY();
        mGreenColor = getResources().getColor(android.R.color.holo_green_light);
    }

    @SuppressLint("NewApi") @Override
    public void onResetDiscrollve() {
        mGreenView1.setTranslationY(mGreenView1TranslationY);
        mGreenView1.setTextColor(mGreenColor);
        setBackgroundColor(mBlackColor);
    }

    @SuppressLint("NewApi") @Override
    public void onDiscrollve(float ratio) {
        mGreenView1.setTranslationY(mGreenView1TranslationY * (1 - ratio));
        if(ratio >= 0.5f) {
            ratio = (ratio - 0.5f) / 0.5f;
            mGreenView1.setTextColor((Integer) mArgbEvaluator.evaluate(ratio, mBlackColor, mGreenColor));
            setBackgroundColor((Integer) mArgbEvaluator.evaluate(ratio, mGreenColor, mBlackColor));
        }
    }
}
