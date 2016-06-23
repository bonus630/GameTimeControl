package br.corp.bonus630.android.gametimecontrol;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Player {

	public int playerPositionX, playerPositionY, moveX, moveY, width, heigth;
	private int currentFrame;
	// private Canvas c;
	private Bitmap bitmap;
	public int playerState, playerFacing;
	private GameView gv;
	private int spriteF = 8;
	private int spriteR = 2;
	private int playerSpeed = 5;
	private int spriteRow;
	public boolean stageScrolling;
	public Rect playerRect;
	public int playerLives = 5;

	Player(GameView gv, Bitmap bitmap) {
		reset();
		this.bitmap = bitmap;

		this.gv = gv;
		width = bitmap.getWidth();
		heigth = bitmap.getHeight();
	}

	public void reset() {
		playerPositionX = 0;
		// playerPositionY = c.getHeight() / 2 - bitmap.getHeight()/2;
		playerPositionY = 140;
		playerState = PlayerState.STANDBY;
	}

	public void update2() {

		if (playerState == PlayerState.STANDBY) {
			currentFrame = 0;
			spriteRow = 1;
		} else {

			try {
				int st = gv.gameTimeControl.spriteSleepTime(spriteF);
				if (st == 0)
					currentFrame++;
				else
					Thread.sleep(st);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentFrame++;
			if (currentFrame == spriteF) {
				currentFrame = 0;
			}
			if (playerPositionX > moveX) {
				spriteRow = 1;
				playerPositionX -= playerSpeed;
			} else {
				spriteRow = 0;
				playerPositionX += playerSpeed;
			}
			if (playerPositionX == moveX) {
				playerState = PlayerState.STANDBY;
			}
		}
	}

	public void update() {

		if (stageScrolling
				&& (playerPositionX == playerPositionX - playerPositionX % 5)) {
			playerSpeed = 0;
		} else {
			playerSpeed = 5;
		}

		if (VirtualInput.pressLeft) {

			playerFacing = PlayerState.MOVELEFT;
			spriteRow = 1;
			playerPositionX -= playerSpeed;
		}
		if (VirtualInput.pressRight) {
			playerFacing = PlayerState.MOVERIGHT;
			spriteRow = 0;
			playerPositionX += playerSpeed;
		}
		playerState = PlayerState.WALKING;

		if (!VirtualInput.pressLeft && !VirtualInput.pressRight) {
			playerState = PlayerState.STANDBY;
			switch (playerFacing) {
			case PlayerState.MOVELEFT:
				currentFrame = 0;
				spriteRow = 1;
				break;
			case PlayerState.MOVERIGHT:
				currentFrame = spriteF - 1;
				spriteRow = 0;
				break;
			default:
				currentFrame = spriteF - 1;
				spriteRow = 0;
				break;
			}

		} else {

			try {
				int st = gv.gameTimeControl.spriteSleepTime(spriteF);
				if (st == 0)
					currentFrame++;
				else
					Thread.sleep(st);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentFrame++;
			if (currentFrame == spriteF) {
				currentFrame = 0;
			}
			// playerState = PlayerState.STANDBY;

		}
	}

	public void draw(Canvas c) {
		int sw = width / spriteF;
		int sh = heigth / spriteR;
		// Rect dest = new
		// Rect(playerPositionX,playerPositionY,bitmap.getWidth(),bitmap.getHeight());
		Rect source = new Rect(sw * currentFrame, sh * spriteRow, sw
				* (currentFrame + 1), sh * (spriteRow + 1));
		playerRect = new Rect(playerPositionX, playerPositionY, playerPositionX
				+ sw, playerPositionY + sh);
		c.drawBitmap(bitmap, source, playerRect, null);
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		// c.drawText("LEFT - "+String.valueOf(VirtualInput.pressLeft), 200,
		// 160, paint);
		// c.drawText("RIGHT - "+String.valueOf(VirtualInput.pressRight), 200,
		// 200, paint);
	}

	public void setMove(int moveX) {

		this.moveX = moveX - (moveX % playerSpeed);
		if (playerPositionX > moveX) {
			playerState = PlayerState.MOVELEFT;
		} else {
			playerState = PlayerState.MOVERIGHT;
		}
	}

}
