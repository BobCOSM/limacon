package com.example.limacon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.MotionEvent.PointerCoords;
import android.view.View;

public class myView extends View {

	
	private int idrawType = 0;
	private Point cirCenter = null;
	private int radius = 100;
	public myView(Context context, AttributeSet attrs) {
		super(context, attrs);
		cirCenter = new Point(300,300);
		setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_MOVE:
					cirCenter.x = (int) event.getX();
					cirCenter.y = (int) event.getY();
					invalidate();
				}
				return false;
			}
		});
		// TODO Auto-generated constructor stub
	}
	
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas){
		drawComCircle(canvas);
	}
	
	private void drawComCircle(Canvas canvas){

		canvas.drawColor(Color.WHITE);
        Paint paint = new Paint();
        // »•æ‚≥›
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        // ªÊ÷∆‘≤–Œ
        canvas.drawCircle(cirCenter.x, cirCenter.y, radius, paint);
	}
}
