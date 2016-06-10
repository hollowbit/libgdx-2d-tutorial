package net.hollowbit.spacegame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet {
	
	public static final int SPEED = 500;
	public static final int DEFAULT_Y = 40;
	private static Texture texture;
	
	float x, y;
	public boolean remove = false;
	
	public Bullet (float x) {
		this.x = x;
		this.y = DEFAULT_Y;
		
		if (texture == null)
			texture = new Texture("bullet.png");
	}
	
	public void update (float deltaTime) {
		y += SPEED * deltaTime;
		if (y > Gdx.graphics.getHeight())
			remove = true;
	}
	
	public void render (SpriteBatch batch) {
		batch.draw(texture, x, y);
	}
	
}
