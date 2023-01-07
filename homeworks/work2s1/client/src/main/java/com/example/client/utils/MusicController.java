package com.example.client.utils;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getSettings;

/**
 * @author Vitaly Chekushkin
 */
public class MusicController {
	public static void setMusicVolume(double musicVolume) {
		getSettings().setGlobalMusicVolume(musicVolume);
	}
}
