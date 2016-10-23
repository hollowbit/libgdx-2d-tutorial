package net.hollowbit.turorialland.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

import net.hollowbit.spacegame.SpaceGame;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(SpaceGame.WIDTH, SpaceGame.HEIGHT);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new SpaceGame();
        }
}