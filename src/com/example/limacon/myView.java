package com.example.limacon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.MotionEvent.PointerCoords;
import android.view.View;
import android.widget.TextView;

public class myView extends View {

	private static final String TAG = "myView";
	private int idrawType = 0;
	private Point cirCenter = null;
	private int radius = 100;
	private int h_radius = 5;
	private int offset = 200;
	
//	private TextView pointShow;
	
	public myView(Context context, AttributeSet attrs) {
		super(context, attrs);
		cirCenter = new Point(300,300);
		setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:
					Log.d(TAG, "onTouch down");
					cirCenter.x = (int) event.getX();
					cirCenter.y = (int) event.getY();
					break;
				case MotionEvent.ACTION_MOVE:
					Log.d(TAG, "onTouch move");			
					cirCenter.x = (int) event.getX();
					cirCenter.y = (int) event.getY();
					
					break;
				}
				Log.d(TAG, "" + cirCenter.x + "\n" + cirCenter.y);
				invalidate();
				return false;
			}
			
			
		});
		// TODO Auto-generated constructor stub
	}
	
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas){
        Paint paint = new Paint();
        // 去锯齿
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
//		drawComCircle(canvas);
        cirCenter.y = canvas.getHeight()/2;
        cirCenter.x = canvas.getWidth()/2;
        drawLimacon(canvas,paint);
	}
	
	private void drawComCircle(Canvas canvas){
		
		canvas.drawColor(Color.WHITE);
        Paint paint = new Paint();
        // 去锯齿
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        // 绘制圆形
        canvas.drawCircle(cirCenter.x, cirCenter.y, radius, paint);
	}
	
	private void drawLimacon(Canvas canvas,Paint paint){
//		Math.cos(d);

		canvas.drawLine(0, cirCenter.y, canvas.getWidth(), cirCenter.y, paint);
		canvas.drawLine(cirCenter.x, 0, cirCenter.x, canvas.getHeight(), paint);
		
		double sin_x = 0;
		double cos_x = 1;
		int x_pre = (int)(50 * h_radius*(1 - cos_x)*sin_x);
		int y_pre = (int)(50 * h_radius*(1 - cos_x)*cos_x) + offset;
		Log.d(TAG, "x : " + x_pre + "\n" + "y : " + y_pre);
		int i = 0;
		for (i = 0; i <= 180; i++) {
			double angle = i*Math.PI/180;
			sin_x = Math.sin(angle);
			cos_x = Math.cos(angle);
			int x_cur = (int)(50 * h_radius*(1 - cos_x)*sin_x);
			int y_cur = (int)(50 * h_radius*(1 - cos_x)*cos_x) + offset;
			
			canvas.drawLine(cirCenter.x + x_pre, cirCenter.y - y_pre, cirCenter.x + x_cur, cirCenter.y - y_cur,paint);
			canvas.drawLine(cirCenter.x - x_pre, cirCenter.y - y_pre, cirCenter.x - x_cur, cirCenter.y - y_cur,paint);
			
			x_pre = x_cur;
			y_pre = y_cur;
		}
	}
	
}
