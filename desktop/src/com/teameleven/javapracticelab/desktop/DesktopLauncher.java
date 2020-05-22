package com.teameleven.javapracticelab.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.teameleven.javapracticelab.UsasengCrossing;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "모여봐요 유생의 숲";
		config.width = 1024;
		config.height = 768;
		config.useGL30 = true;
		config.resizable = false;
		// icon size : 32px, 32px
		config.addIcon("icon32_2.png", Files.FileType.Internal);
		new LwjglApplication(new UsasengCrossing(), config);
	}
}
