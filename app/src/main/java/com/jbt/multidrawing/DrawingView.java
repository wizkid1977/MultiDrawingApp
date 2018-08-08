package com.jbt.multidrawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class DrawingView extends View {

    private Paint paint;
    private ArrayList<PointF> points = new ArrayList<>();

    public DrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        paint.setColor(Color.CYAN);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (PointF point: points) {
            canvas.drawCircle(point.x, point.y, 50, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int index = event.getActionIndex();

        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN: // first touch on screen
                points.add(new PointF(event.getX(index), event.getY(index)));
                break;

            case MotionEvent.ACTION_POINTER_DOWN: // second or above touch on screen
                points.add(new PointF(event.getX(index) + 100, event.getY(index) + 100));
                break;
        }

        invalidate();
        return true;
    }
}
