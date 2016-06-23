package br.corp.bonus630.android.gametimecontrol;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class VirtualInput {
	public static boolean pressLeft = false;
	public static boolean pressRight = false;
	public static boolean pressUp = false;
	public static boolean pressDown = false;
	
	
	
	private Bitmap background;
	private Rect leftButton,rightButton,backgroundL,backgroundR; 
	private Paint paint;
	public VirtualInput(GameView gameView,Bitmap bitmap)
	{
		this.background = bitmap;
		paint = new Paint();
		paint.setColor(Color.BLACK);
		backgroundL = new Rect(0,0,100,100);
		backgroundR = new Rect(100,0,200,100);
	}
	
	public void update()
	{
		
	}
	
	public void checkInput(int x, int y)
	{
		resetInput();
		if(leftButton.contains(x, y))
		{
			
			pressLeft = true;
			
		}
		if(rightButton.contains(x, y))
		{
			
			pressRight = true;
		}
		
	}
	public void resetInput()
	{
		pressRight = false;
		pressLeft = false;
	}
	public void draw(Canvas canvas){
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		if(leftButton == null)
			leftButton = new Rect(20,height/2 +100,120,height/2+200);
		if(rightButton==null)
			rightButton = new Rect(width - 120,height/2+100,width-20,height/2+200);
		//canvas.drawBitmap(background,0,0,null);
		canvas.drawBitmap(background, backgroundL, leftButton, paint);
		canvas.drawBitmap(background, backgroundR, rightButton, paint);
		//canvas.drawRect(leftButton, paint);
		//canvas.drawRect(rightButton, paint);
	}

}
