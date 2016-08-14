package net.hollowbit.spacegame.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScrollingBackground {
	
	public static final int DEFAULT_SPEED = 80;
	public static final int ACCELERATION = 50;
	public static final int GOAL_REACH_ACCELERATION = 200;
	
	Texture image;
	float y1, y2;
	float imageScale;
	int speed;//In pixels / second
	int goalSpeed;
	
	boolean speedFixed;
	
	public ScrollingBackground () {
		image = new Texture("stars_background.png");
		
		y1 = 0;
		y2 = image.getHeight();
		imageScale = 0;
		speed = DEFAULT_SPEED;
		goalSpeed = DEFAULT_SPEED;
		speedFixed = true;
	}
	
	public void updateAndRender (float deltaTime, SpriteBatch batch) {
		//Update speed
		if (speed < goalSpeed) {
			speed += GOAL_REACH_ACCELERATION * deltaTime;
			if (speed > goalSpeed)
				speed = goalSpeed;
		} else if (speed > goalSpeed) {
			speed -= GOAL_REACH_ACCELERATION * deltaTime;
			if (speed < goalSpeed)
				speed = goalSpeed;
		}
		
		if (!speedFixed)
			speed += ACCELERATION * deltaTime;
		
		y1 -= deltaTime * speed;
		y2 -= deltaTime * speed;
		
		//If image reaches bottom of screen and is no longer visible, move it to the top
		if (y1 + image.getHeight() * imageScale <= 0)
			y1 = y2 + image.getHeight() * imageScale;
		
		if (y2 + image.getHeight() * imageScale <= 0)
			y2 = y1 + image.getHeight() * imageScale;
		
		//Render
		batch.draw(image, 0, y1, Gdx.graphics.getWidth(), image.getHeight() * imageScale);
		batch.draw(image, 0, y2, Gdx.graphics.getWidth(), image.getHeight() * imageScale);
	}
	
	public void resize (int width, int height) {
		imageScale = Gdx.graphics.getWidth() / image.getWidth();
	}
	
	public void setSpeed (int speedInPixelsPerSecond) {
		this.goalSpeed = speedInPixelsPerSecond;
	}
	
	public void setSpeedFixed (boolean speedFixed) {
		this.speedFixed = speedFixed;
	}
	
}
