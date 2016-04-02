package net.hollowbit.spacegame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.hollowbit.spacegame.screens.MainMenuScreen;

public class SpaceGame extends Game {
	
	public static final int WIDTH = 480;
	public static final int HEIGHT = 720;
	
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
