package com.vik24rus.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.vik24rus.gdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "gdxGame";
		config.width = 1280;
		config.height =700;
		config.resizable = false;
		config.x = 0;
		config.y = 0;
		new LwjglApplication(new gdxGame(), config);
	}
}
