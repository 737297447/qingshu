package com.lingdian.saylove.second.layout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.lingdian.saylove.R;
import com.lingdian.saylove.second.lib.Discrollvable;

/**
 *
 */
public class DiscrollvablePurpleLayout extends LinearLayout implements Discrollvable {

    private static final String TAG = "DiscrollvablePurpleLayout";

    private View mPurpleView1;
    private View mPurpleView2;

    private float mPurpleView1TranslationX;
    private float mPurpleView2TranslationX;

    public DiscrollvablePurpleLayout(Context context) {
        super(context);
    }

    public DiscrollvablePurpleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressLint("NewApi") public DiscrollvablePurpleLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @SuppressLint("NewApi") @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mPurpleView1 = findViewById(R.id.purpleView1);
        mPurpleView1TranslationX = mPurpleView1.getTranslationX();
        mPurpleView2 = findViewById(R.id.purpleView2);
        mPurpleView2TranslationX = mPurpleView2.getTranslationX();
    }

    @SuppressLint("NewApi") @Override
    public void onResetDiscrollve() {
        mPurpleView1.setAlpha(0);
        mPurpleView2.setAlpha(0);
        mPurpleView1.setTranslationX(mPurpleView1TranslationX);
        mPurpleView2.setTranslationX(mPurpleView2TranslationX);
    }

    @SuppressLint("NewApi") @Override
    public void onDiscrollve(float ratio) {
        if(ratio <= 0.5f) {
            mPurpleView2.setTranslationX(0);
            mPurpleView2.setAlpha(0.0f);
            float rratio = ratio / 0.5f;
            mPurpleView1.setTranslationX(mPurpleView1TranslationX * (1 - rratio));
            mPurpleView1.setAlpha(rratio);
        } else {
            mPurpleView1.setTranslationX(0);
            mPurpleView1.setAlpha(1.0f);
            float rratio = (ratio - 0.5f) / 0.5f;
            mPurpleView2.setTranslationX(mPurpleView2TranslationX * (1 - rratio));
            mPurpleView2.setAlpha(rratio);
        }
    }
}
