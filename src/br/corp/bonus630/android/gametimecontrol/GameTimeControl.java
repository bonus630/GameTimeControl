package br.corp.bonus630.android.gametimecontrol;



public class GameTimeControl {

	private int fps = 30;
	private int sleepTime;
	private boolean skipFrame = false;
	private long startTime,endTime;
	private int lastSleepTime = 0;
	
	public GameTimeControl()
	{
		setSleepTime();
	}
	public GameTimeControl(int fps)
	{
		this.fps = fps;
		setSleepTime();
	}
	
	private void setSleepTime()
	{
		this.sleepTime = 1000 / fps;
	}
	
	public void Start() {
		//this.startTime = System.nanoTime();
		this.startTime = System.currentTimeMillis();
		
	}

	public void End() {
		//this.endTime = System.nanoTime();
		
		this.endTime = System.currentTimeMillis();
		this.lastSleepTime = (int)(this.endTime - this.startTime);
		if(this.lastSleepTime<this.sleepTime)
		{
			this.lastSleepTime = this.sleepTime - this.lastSleepTime;
		}
				
		if(this.skipFrame)
			this.lastSleepTime = 0;
	}

	public boolean skipFrame()
	{
		if(this.lastSleepTime>this.sleepTime)
		{
			if(this.skipFrame==false)
				this.skipFrame = true;
			else
				this.skipFrame = false;	
		}
		else
			this.skipFrame = false;
		return this.skipFrame;
	}
	public int sleepTime() {
		
		return this.lastSleepTime;
	}
	public int spriteSleepTime(int spriteFPS)
	{
		if(this.lastSleepTime!=0){
		return 1000 / (this.lastSleepTime * spriteFPS);
		}
		else
			return this.lastSleepTime;
	}
}
