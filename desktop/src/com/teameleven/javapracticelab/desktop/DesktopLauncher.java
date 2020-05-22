package com.teameleven.javapracticelab.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.teameleven.javapracticelab.UsasengCrossing;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "모여봐요 동물의 숲";
		config.width = 1024;
		config.height = 768;
		config.resizable = true;
		new LwjglApplication(new UsasengCrossing(), config);
	}
}
