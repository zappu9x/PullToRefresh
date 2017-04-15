package com.vetrio.library.progress;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.ArrayList;

public class LineIndicator extends BallIndicator {
    private ArrayList<RectF> rectFs = new ArrayList<>();

    public LineIndicator() {
        super(12);
        isScale = false;
        isAlpha = true;
        isSmoothAlpha = false;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        float radius=getWidth()/10;
        float scale;
        int alpha;
        for (int i = 0; i < n; i++) {
            canvas.save();
            Point point=circleAt(getWidth(),getHeight(),getWidth()/2.5f-radius,i*(Math.PI*2/n));

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
            canvas.translate(point.x, point.y);
            canvas.scale(scale, scale);
            canvas.rotate(i*360/n);
            paint.setAlpha(alpha);
            if(isCreate) {
                RectF rectF=new RectF(-radius,-radius/4f, radius,radius/4f);
                rectFs.add(rectF);
            }
            canvas.drawRoundRect(rectFs.get(i),radius/4,radius/4, paint);
            canvas.restore();
        }
        isCreate = false;
    }
}