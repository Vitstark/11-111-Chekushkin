package com.example.client.utils;

import com.example.client.configuration.AppConfiguration;
import javafx.scene.text.Font;

/**
 * @author Vitaly Chekushkin
 */
public class Fonts {
	public static final Font MAIN_FONT = Font.loadFont(Fonts.class.getResourceAsStream(
		"/assets/ui/fonts/font.ttf"), AppConfiguration.BUTTONS_FONT);
}
