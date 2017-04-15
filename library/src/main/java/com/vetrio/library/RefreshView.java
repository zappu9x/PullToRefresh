package com.vetrio.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vetrio.library.progress.ArcIndicator;
import com.vetrio.library.progress.BallIndicator;
import com.vetrio.library.progress.Indicator;
import com.vetrio.library.progress.LineIndicator;
import com.vetrio.library.progress.ProgressView;

public class RefreshView extends LinearLayout {
    ProgressView mLoadingView;
    TextView mTitleView;
    LinearLayout mRefreshLayout;

    private Indicator mIndicator;

    public RefreshView(Context context) {
        super(context);
        init(context);
    }

    public RefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PullToRefreshView, 0, 0);
        try {
            String titleText = typedArray.getString(R.styleable.PullToRefreshView_titleText);
            int titleColor = typedArray.getColor(R.styleable.PullToRefreshView_titleColor, Color.WHITE);
            boolean isShowTitle = typedArray.getBoolean(R.styleable.PullToRefreshView_showTitle, true);
            int progressName = typedArray.getInt(R.styleable.PullToRefreshView_progressName, 0);
            int progressColor = typedArray.getColor(R.styleable.PullToRefreshView_progressColor, Color.WHITE);

            mTitleView.setText(titleText);
            mTitleView.setTextColor(titleColor);
            mTitleView.setVisibility(isShowTitle ? VISIBLE : GONE);
            mLoadingView.setIndicatorColor(progressColor);
            setIndicator(progressName);
            mLoadingView.setIndicator(mIndicator);
            setPercent(0, false);
        } finally {
            typedArray.recycle();
        }
    }

    private void setIndicator(int code) {
        switch (code){
            case 0:
                mIndicator =  new LineIndicator();
                break;
            case 1:
                mIndicator =  new BallIndicator();
                break;
            case 2:
                mIndicator =  new ArcIndicator();
                break;
            default:
                mIndicator =  new LineIndicator();
        }
    }

    public RefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RefreshView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.custom_refresh_view, this);
        mLoadingView = (ProgressView) findViewById(R.id.loading);
        mTitleView = (TextView) findViewById(R.id.title);
        mRefreshLayout = (LinearLayout) findViewById(R.id.refresh_layout);
        mIndicator = new LineIndicator();
        mIndicator.stop();
        mLoadingView.setIndicator(mIndicator);
        setPercent(0, false);
    }

    public void setPercent(float percent, boolean invalidate) {
        if(percent > 1f) percent = 1f;
        mIndicator.setPercent(percent);
        mLoadingView.setAlpha(percent);
        mTitleView.setScaleX(percent);
        mTitleView.setScaleY(percent);
        mTitleView.setAlpha(percent);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void start() {
        mIndicator.setRefreshing(true);
    }

    public void stop() {
        mIndicator.setRefreshing(false);
    }
}
