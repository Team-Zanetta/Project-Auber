package com.zanetta.auber.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.zanetta.auber.Z_Launcher;
import com.zanetta.auber.ProjectAuberGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
	    config.height = 720;
		new LwjglApplication(new ProjectAuberGame(), config);
	}
}
