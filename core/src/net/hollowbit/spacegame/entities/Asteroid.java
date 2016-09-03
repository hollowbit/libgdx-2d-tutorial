package net.hollowbit.spacegame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.hollowbit.spacegame.SpaceGame;
import net.hollowbit.spacegame.tools.CollisionRect;

public class Asteroid {
	
	public static final int SPEED = 250;
	public static final int WIDTH = 16;
	public static final int HEIGHT = 16;
	private static Texture texture;
	
	float x, y;
	CollisionRect rect;
	public boolean remove = false;
	
	public Asteroid (float x) {
		this.x = x;
		this.y = SpaceGame.HEIGHT;
		this.rect = new CollisionRect(x, y, WIDTH, HEIGHT);
		
		if (texture == null)
			texture = new Texture("asteroid.png");
	}
	
	public void update (float deltaTime) {
		y -= SPEED * deltaTime;
		if (y < -HEIGHT)
			remove = true;

		rect.move(x, y);
	}
	
	public void render (SpriteBatch batch) {
		batch.draw(texture, x, y);
	}
	
	public CollisionRect getCollisionRect () {
		return rect;
	}
	
	public float getX () {
		return x;
	}
	
	public float getY () {
		return y;
	}
	
}
