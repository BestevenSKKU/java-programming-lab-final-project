package com.teameleven.javapracticelab.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.teameleven.javapracticelab.CrossingUsang;

public class DesktopLauncher {
	public static void main (String[] arg) {
		System.out.println("Hello, World!");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new CrossingUsang(), config);
	}
}
