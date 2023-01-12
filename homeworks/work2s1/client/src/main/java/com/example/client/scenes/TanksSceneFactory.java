package com.example.client.scenes;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;

/**
 * @author Vitaly Chekushkin
 */
public class TanksSceneFactory extends SceneFactory {
	@Override
	public FXGLMenu newMainMenu() {
		return new MainMenu();
	}
}
