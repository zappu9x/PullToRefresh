package com.vetrio.library.progress;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.ArrayList;

public class LineIndicator extends BallIndicator {
    private ArrayList<RectF> rectFs = new ArrayList<>();

    public LineIndicator() {
        super();
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        float radius=getWidth()/10;
        float scale;
        int alpha;
        for (int i = 0; i < 8; i++) {
            canvas.save();
            Point point=circleAt(getWidth(),getHeight(),getWidth()/2.5f-radius,i*(Math.PI/4));

            if(!isRefreshing) {
                if(i < percent * 8) {
                    scale = 1f;
                    alpha = 255;
                } else {
                    scale = 0.5f;
                    alpha = 126;
                }
            } else {
                scale = scaleFloats[i];
                alpha = alphas[i];
            }
            canvas.translate(point.x, point.y);
            canvas.scale(scale, scale);
            canvas.rotate(i*45);
            paint.setAlpha(alpha);
            if(isCreate) {
                RectF rectF=new RectF(-radius,-radius/3f,2f*radius,radius/3f);
                rectFs.add(rectF);
            }
            canvas.drawRoundRect(rectFs.get(i),5,5,paint);
            canvas.restore();
        }
        isCreate = false;
    }
}