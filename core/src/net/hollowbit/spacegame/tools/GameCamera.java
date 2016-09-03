package net.hollowbit.spacegame.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameCamera {
	
	private OrthographicCamera cam;
	private StretchViewport viewport;
	
	public GameCamera (int width, int height) {
		cam = new OrthographicCamera();
		viewport = new StretchViewport(width, height, cam);
		viewport.apply();
		cam.position.set(width / 2, height / 2, 0);
		cam.update();
	}
	
	public Matrix4 combined() {
		return cam.combined;
	}
	
	public void update (int width, int height) {
		viewport.update(width, height);
	}
	
	public Vector2 getInputInGameWorld () {
		Vector3 inputScreen = new Vector3(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 0);
		Vector3 unprojected = cam.unproject(inputScreen);
		return new Vector2(unprojected.x, unprojected.y);
	}
	
}
