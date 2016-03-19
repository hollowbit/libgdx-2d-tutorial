package net.hollowbit.spacegame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import net.hollowbit.spacegame.SpaceGame;

public class MainGameScreen implements Screen {
	
	public static final float SPEED = 120;

	Texture img;
	float x;
	float y;
	
	SpaceGame game;
	
	public MainGameScreen (SpaceGame game) {
		this.game = game;
	}
	
	@Override
	public void show () {
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render (float delta) {
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			y += SPEED * Gdx.graphics.getDeltaTime();
		}
		
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			y -= SPEED * Gdx.graphics.getDeltaTime();
		}
		
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			x -= SPEED * Gdx.graphics.getDeltaTime();
		}
		
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			x += SPEED * Gdx.graphics.getDeltaTime();
		}

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		
		game.batch.draw(img, x, y);
		
		game.batch.end();
	}

	@Override
	public void resize (int width, int height) {
		
	}

	@Override
	public void pause () {
		
	}

	@Override
	public void resume () {
		
	}

	@Override
	public void hide () {
		
	}

	@Override
	public void dispose () {
		
	}

}
