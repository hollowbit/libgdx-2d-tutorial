package net.hollowbit.spacegame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.hollowbit.spacegame.screens.MainGameScreen;

public class SpaceGame extends Game {
	
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainGameScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
