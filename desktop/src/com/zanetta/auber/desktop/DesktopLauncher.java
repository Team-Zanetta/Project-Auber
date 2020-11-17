package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MainGame;
import com.mygdx.game.MyGdxGame;

/*
public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MyGdxGame(), config);
	}
}
*/

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		float scale = 0.6F;

		config.width = (int) (480 * scale);
		config.height = (int) (800 * scale);


		config.resizable = false;
		new LwjglApplication(new MainGame(), config);
	}
}

