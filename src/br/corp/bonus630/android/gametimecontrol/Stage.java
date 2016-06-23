package br.corp.bonus630.android.gametimecontrol;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

public class Stage {

	private Bitmap bitmapStage;
	private int stageSpeed;
	private int stagePosition;
	// private int playerState;
	private GameView gameView;
	private int stageTime;
	private Paint paintWhite;
	private int stageEnd;
	private int stageGravity = 10;
	int width, height;
	Rect dst;
	private Player player;
	private boolean playerDowning;
	Rect src;
	public Rect solid;

	public Stage(GameView gameView, Bitmap bitmapStage) {

		this.gameView = gameView;
		// stagePosition = this.bitmapStage.getWidth()/2;
		stagePosition = 0;
		stageTime = 500;
		paintWhite = new Paint();
		paintWhite.setColor(Color.WHITE);

		player = gameView.player;
	}

	public void update() {
		stageTime--;
		if (player.playerRect != null) {
			if (player.playerRect.bottom > solid.top
					&& player.playerPositionX < solid.right)
				playerDowning = false;
			else
				playerDowning = true;
		}
		if (playerDowning) {
			player.playerPositionY += stageGravity;
			if (player.playerPositionY > height) {
				player.playerLives--;
				player.reset();
				if (player.playerLives == 0) {

				}
			}
		}

		if (gameView.player.playerState == PlayerState.WALKING) {

			if (gameView.player.playerFacing == PlayerState.MOVELEFT)
				stageSpeed = -5;

			if (gameView.player.playerFacing == PlayerState.MOVERIGHT)
				stageSpeed = 5;
		} else
			stageSpeed = 0;

		stagePosition += stageSpeed;

		if (stagePosition < 0) {
			gameView.player.stageScrolling = false;
			stagePosition = 0;
		}
		if (stagePosition > (this.stageEnd - width)) {
			gameView.player.stageScrolling = false;
			stagePosition = 0;
		}

	}

	public void draw(Canvas canvas) {

		if (bitmapStage == null) {
			width = canvas.getWidth();
			height = canvas.getHeight();
			bitmapStage = gameView.bitmapStage;
			solid = new Rect(0, 200, bitmapStage.getWidth() / 2,
					(bitmapStage.getHeight() / 2) + 200);
			// this.bitmapStage =
			// Bitmap.createScaledBitmap(gameView.bitmapStage,
			// gameView.bitmapStage.getWidth(), height/2,false);
			stageEnd = bitmapStage.getWidth();
			// dst = new Rect(0,0,width,bitmapStage.getHeight());
			dst = new Rect(0, 0, width, height / 2);
			src = new Rect(0, 0, bitmapStage.getWidth(),
					bitmapStage.getHeight());
		}
		// Paint teste = new Paint();
		// teste.setColor(Color.YELLOW);
		// canvas.drawRect(src, teste);
		canvas.drawBitmap(bitmapStage, src, solid, null);
		// canvas.drawBitmap(bitmapStage,150,150,null);
		/*
		 * Rect src = new Rect(stagePosition, 0, stagePosition + width,
		 * bitmapStage.getHeight());
		 * 
		 * canvas.drawBitmap(bitmapStage, src, dst, null);
		 * canvas.drawText(String.valueOf(stageTime), 20, 20, paintWhite); if
		 * (gameView.player.playerRect != null) { if
		 * (solid.contains(gameView.player.playerRect.right,
		 * gameView.player.playerRect.top))
		 * canvas.drawText(String.valueOf(bitmapStage.getHeight()) + " - " +
		 * String.valueOf(canvas.getHeight()), 150, 150, paintWhite); }
		 */
		Paint teste = new Paint();
		teste.setColor(Color.YELLOW);
		canvas.drawText("Vidas X "+String.valueOf(player.playerLives), 20, 20, teste);
	}

}
