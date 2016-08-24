package net.hollowbit.spacegame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.hollowbit.spacegame.screens.MainMenuScreen;
import net.hollowbit.spacegame.tools.ScrollingBackground;

public class SpaceGame extends Game {
	
	public static final int WIDTH = 480;
	public static final int HEIGHT = 720;
	
	public SpriteBatch batch;
	public ScrollingBackground scrollingBackground;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.scrollingBackground = new ScrollingBackground();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void resize(int width, int height) {
		this.scrollingBackground.resize(width, height);
		super.resize(width, height);
	}
	
}
