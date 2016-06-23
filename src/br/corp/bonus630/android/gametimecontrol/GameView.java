package br.corp.bonus630.android.gametimecontrol;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


public class GameView extends View implements Runnable{

	public  GameTimeControl gameTimeControl;
	public int width;
	public int heigth;
	
	private boolean running;
	private Thread thread;
	//private float top = 0;
	//private String teste = "";
	
	
	public Player player;
	private Stage stage;
	public VirtualInput virtualInput;
	
	
	public Bitmap bitmapPlayer;
	public Bitmap bitmapStage;
	public Bitmap bitmapVirtualPad;
	private Rect src,dest;
	public int moveX,moveY;
	
	
	public GameView(Context context) {
		super(context);
		gameTimeControl = new GameTimeControl();
		running = true;
		bitmapPlayer = BitmapFactory.decodeResource(getResources(), R.drawable.mario_sprite2);
		bitmapStage = BitmapFactory.decodeResource(getResources(), R.drawable.plataforma);
		bitmapVirtualPad = BitmapFactory.decodeResource(getResources(), R.drawable.setas);
		thread = new Thread(this);
		thread.setPriority(Thread.MIN_PRIORITY);
		thread.start();
		
		
	}

	@Override
	public void run() {
		virtualInput = new VirtualInput(this,bitmapVirtualPad);
		player = new Player(this,bitmapPlayer);
		stage = new Stage(this,bitmapStage);
		
		//Este laço tem que se repetir 30 fps por segundos por padrão de jogos mobile
		while(running)
		{
			
			try {
				gameTimeControl.Start();
				update();
				if(!gameTimeControl.skipFrame())
				{
					postInvalidate();
					
				}
				gameTimeControl.End();
				Thread.sleep(gameTimeControl.sleepTime());
				
			} catch (Exception e) {
				Log.e("erro",e.getMessage());
			}
		}
		
	}
	private void update()
	{
		//Aqui vem toda atualização
		player.update();
		stage.update();
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		canvas.drawColor(Color.rgb(0, 94, 181));
		stage.draw(canvas);
		player.draw(canvas);
		virtualInput.draw(canvas);
		//canvas.drawBitmap(bitmapVirtualPad, 200, 200,null);
		//Paint paint = new Paint();
		//paint.setColor(Color.BLACK);
		//canvas.drawText(String.valueOf(VirtualInput.pressLeft), 200, 200, paint);
	}
	public void onPause()
	{
		this.running = false;
	}
	public void onResume()
	{
		this.running = true;
	}
	/*@Override
	public boolean onTouchEvent(MotionEvent event) {
		//virtualInput.reset();
		if(event.getAction()==MotionEvent.ACTION_UP)
		{
			Log.e("UP",String.valueOf(event.getX()));
			virtualInput.resetInput();
		}
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			//player.setMove((int)event.getX());
			virtualInput.checkInput((int)event.getX(),(int)event.getY());
			Log.e("HOVER", String.valueOf(event.getX()));
		}
		if(event.getAction() == event.ACTION_UP){
		Log.e("Action", String.valueOf("up"));
		}
		return super.onTouchEvent(event);
	
	}*/
	public void checkInput(int x, int y)
	{
		virtualInput.checkInput(x, y);
		
	}
	public void resetInput()
	{
		virtualInput.resetInput();
	}
	private void setMove(int x)
	{
		this.moveX = x;
	
	}
}
