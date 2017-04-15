package com.vetrio.library.progress;

import android.graphics.Canvas;
import android.graphics.Paint;

import android.animation.ValueAnimator;

import java.util.ArrayList;

public class BallIndicator extends Indicator {
    public int n = 10;

    private static final float SCALE=1.0f;
    private static final int ALPHA=255;

    public boolean isSmoothScale = true, isSmoothAlpha = true;
    public boolean isScale = true, isAlpha = true;

    public float[] scales;
    public int[] alphas;
    public int[] delays;

    public BallIndicator() {
        super();
        setUp();
    }

    public void setUp() {
        scales = new float[n];
        alphas = new int[n];
        delays = new int[n];
        for(int i = 0; i < n; i++) {
            scales[i] = SCALE;
            alphas[i] = ALPHA;
            delays[i] = i*80;
        }
    }

    public BallIndicator(int n) {
        super();
        this.n = n;
        setUp();
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        float radius=getWidth()/10;
        float scale;
        int alpha;
        for (int i = 0; i < n; i++) {
            canvas.save();
            Point point=circleAt(getWidth(),getHeight(),getWidth()/2-radius,i*(Math.PI*2/n));

            if(!isRefreshing) {
                if(i < percent * n) {
                    scale = 1f;
                    alpha = 255;
                } else {
                    scale = 0.5f;
                    alpha = 126;
                }
            } else {
                scale = scales[i];
                alpha = alphas[i];
            }


            canvas.translate(point.x,point.y);
            canvas.scale(scale, scale);
            paint.setAlpha(alpha);
            canvas.drawCircle(0,0,radius,paint);
            canvas.restore();
        }
    }

    @Override
    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> animators=new ArrayList<>();
        for (int i = 0; i < n; i++) {
            final int index=i;
            ValueAnimator scaleAnim = isScale ?
                    isSmoothScale? ValueAnimator.ofFloat(1, 0.4f, 1): ValueAnimator.ofFloat(1, 0.4f):
                    ValueAnimator.ofFloat(1, 1f);

            scaleAnim.setDuration(delays[n -1]);
            scaleAnim.setRepeatCount(-1);
            scaleAnim.setStartDelay(delays[i]);
            addUpdateListener(scaleAnim,new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    scales[index] = (float) animation.getAnimatedValue();
                    postInvalidate();
                }
            });

            ValueAnimator alphaAnim = isAlpha?
                    isSmoothAlpha? ValueAnimator.ofInt(255, 77, 255): ValueAnimator.ofInt(255, 0):
                    ValueAnimator.ofInt(255, 255);

            alphaAnim.setDuration(delays[n-1]);
            alphaAnim.setRepeatCount(-1);
            alphaAnim.setStartDelay(delays[i]);
            addUpdateListener(alphaAnim, new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    alphas[index] = (int) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            animators.add(scaleAnim);
            animators.add(alphaAnim);
        }
        return animators;
    }

    Point circleAt(int width,int height,float radius,double angle){
        float x= (float) (width/2+radius*(Math.cos(angle)));
        float y= (float) (height/2+radius*(Math.sin(angle)));
        return new Point(x,y);
    }

    final class Point{
        public float x;
        public float y;

        public Point(float x, float y){
            this.x=x;
            this.y=y;
        }
    }


}