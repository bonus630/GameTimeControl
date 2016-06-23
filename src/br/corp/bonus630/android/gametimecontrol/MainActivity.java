package br.corp.bonus630.android.gametimecontrol;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends Activity {

	GameView gameView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		gameView = new GameView(this);
		gameView.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction()==MotionEvent.ACTION_UP)
				{
					Log.e("UP",String.valueOf(event.getX()));
					gameView.resetInput();
				}
				if(event.getAction()==MotionEvent.ACTION_DOWN){
					//player.setMove((int)event.getX());
					gameView.checkInput((int)event.getX(),(int)event.getY());
					Log.e("HOVER", String.valueOf(event.getX()));
				}
				return true;
			}
		});
		setContentView(gameView);
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	/*@Override
	public boolean onTouchEvent(MotionEvent event) {
		//virtualInput.reset();
		if(event.getAction()==MotionEvent.ACTION_UP)
		{
			Log.e("UP",String.valueOf(event.getX()));
			gameView.resetInput();
		}
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			//player.setMove((int)event.getX());
			gameView.checkInput((int)event.getX(),(int)event.getY());
			Log.e("HOVER", String.valueOf(event.getX()));
		}
		return true;
		//return super.onTouchEvent(event);
	
	}*/
}
