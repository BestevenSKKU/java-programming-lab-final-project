package com.teameleven.javapracticelab.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.teameleven.javapracticelab.CrossingUsaseng;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "�𿩺��� ������ ��";
		config.useGL30 = true;
		config.width = 1024;
		config.height = 768;
		config.resizable = false;
		new LwjglApplication(new CrossingUsaseng(), config);
	}
}
