package net.hollowbit.spacegame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Asteroid {
	
	public static final int SPEED = 250;
	public static final int WIDTH = 16;
	public static final int HEIGHT = 16;
	private static Texture texture;
	
	float x, y;
	public boolean remove = false;
	
	public Asteroid (float x) {
		this.x = x;
		this.y = Gdx.graphics.getHeight();
		
		if (texture == null)
			texture = new Texture("asteroid.png");
	}
	
	public void update (float deltaTime) {
		y -= SPEED * deltaTime;
		if (y < -HEIGHT)
			remove = true;
	}
	
	public void render (SpriteBatch batch) {
		batch.draw(texture, x, y);
	}
	
}
