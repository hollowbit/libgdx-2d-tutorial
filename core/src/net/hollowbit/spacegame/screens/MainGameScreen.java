package net.hollowbit.spacegame.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import net.hollowbit.spacegame.SpaceGame;
import net.hollowbit.spacegame.entities.Bullet;

public class MainGameScreen implements Screen {
	
	public static final float SPEED = 300;
	
	public static final float SHIP_ANIMATION_SPEED = 0.5f;
	public static final int SHIP_WIDTH_PIXEL = 17;
	public static final int SHIP_HEIGHT_PIXEL = 32;
	public static final int SHIP_WIDTH = SHIP_WIDTH_PIXEL * 3;
	public static final int SHIP_HEIGHT = SHIP_HEIGHT_PIXEL * 3;
	
	public static final float ROLL_TIMER_SWITCH_TIME = 0.25f;
	public static final float SHOOT_WAIT_TIME = 0.3f;
	
	Animation[] rolls;
	
	float x;
	float y;
	int roll;
	float rollTimer;
	float stateTime;
	float shootTimer;
	
	SpaceGame game;
	
	ArrayList<Bullet> bullets;
	
	public MainGameScreen (SpaceGame game) {
		this.game = game;
		y = 15;
		x = SpaceGame.WIDTH / 2 - SHIP_WIDTH / 2;
		bullets = new ArrayList<Bullet>();
		
		shootTimer = 0;
		
		roll = 2;
		rollTimer = 0;
		rolls = new Animation[5];
		
		TextureRegion[][] rollSpriteSheet = TextureRegion.split(new Texture("ship.png"), SHIP_WIDTH_PIXEL, SHIP_HEIGHT_PIXEL);
		
		rolls[0] = new Animation(SHIP_ANIMATION_SPEED, rollSpriteSheet[2]);//All left
		rolls[1] = new Animation(SHIP_ANIMATION_SPEED, rollSpriteSheet[1]);
		rolls[2] = new Animation(SHIP_ANIMATION_SPEED, rollSpriteSheet[0]);//No tilt
		rolls[3] = new Animation(SHIP_ANIMATION_SPEED, rollSpriteSheet[3]);
		rolls[4] = new Animation(SHIP_ANIMATION_SPEED, rollSpriteSheet[4]);//Right
	}
	
	@Override
	public void show () {
		
	}

	@Override
	public void render (float delta) {
		//Shooting code
		shootTimer += delta;
		if (Gdx.input.isKeyPressed(Keys.SPACE) && shootTimer >= SHOOT_WAIT_TIME) {
			shootTimer = 0;
			
			int offset = 4;
			if (roll == 1 || roll == 3)//Slightly tilted
				offset = 8;
			
			if (roll == 0 || roll == 4)//Fully tilted
				offset = 16;
			
			bullets.add(new Bullet(x + offset));
			bullets.add(new Bullet(x + SHIP_WIDTH - offset));
		}
		
		//Update bullets
		ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();
		for (Bullet bullet : bullets) {
			bullet.update(delta);
			if (bullet.remove)
				bulletsToRemove.add(bullet);
		}
		bullets.removeAll(bulletsToRemove);
		
		//Movement code
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {//Left
			x -= SPEED * Gdx.graphics.getDeltaTime();
			
			if (x < 0)
				x = 0;
			
			//Update roll if button just clicked
			if (Gdx.input.isKeyJustPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.RIGHT) && roll > 0) {
				rollTimer = 0;
				roll--;
			}
			
			//Update roll
			rollTimer -= Gdx.graphics.getDeltaTime();
			if (Math.abs(rollTimer) > ROLL_TIMER_SWITCH_TIME && roll > 0) {
				rollTimer -= ROLL_TIMER_SWITCH_TIME;
				roll--;
			}
		} else {
			if (roll < 2) {
				//Update roll to make it go back to center
				rollTimer += Gdx.graphics.getDeltaTime();
				if (Math.abs(rollTimer) > ROLL_TIMER_SWITCH_TIME && roll < 4) {
					rollTimer -= ROLL_TIMER_SWITCH_TIME;
					roll++;
				}
			}
		}
		
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {//Right
			x += SPEED * Gdx.graphics.getDeltaTime();
			
			if (x + SHIP_WIDTH > Gdx.graphics.getWidth())
				x = Gdx.graphics.getWidth() - SHIP_WIDTH;
			
			//Update roll if button just clicked
			if (Gdx.input.isKeyJustPressed(Keys.RIGHT) && !Gdx.input.isKeyPressed(Keys.LEFT) && roll > 0) {
				rollTimer = 0;
				roll--;
			}
			
			//Update roll
			rollTimer += Gdx.graphics.getDeltaTime();
			if (Math.abs(rollTimer) > ROLL_TIMER_SWITCH_TIME && roll < 4) {
				rollTimer -= ROLL_TIMER_SWITCH_TIME;
				roll++;
			}
		} else {
			if (roll > 2) {
				//Update roll
				rollTimer -= Gdx.graphics.getDeltaTime();
				if (Math.abs(rollTimer) > ROLL_TIMER_SWITCH_TIME && roll > 0) {
					rollTimer -= ROLL_TIMER_SWITCH_TIME;
					roll--;
				}
			}
		}
		
		stateTime += delta;

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		
		for (Bullet bullet : bullets) {
			bullet.render(game.batch);
		}
		
		game.batch.draw(rolls[roll].getKeyFrame(stateTime, true), x, y, SHIP_WIDTH, SHIP_HEIGHT);
		
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
