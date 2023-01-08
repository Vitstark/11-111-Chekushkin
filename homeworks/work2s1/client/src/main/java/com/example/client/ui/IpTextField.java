package com.example.client.ui;

import com.example.client.utils.Fonts;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.text.Font;

/**
 * @author Vitaly Chekushkin
 */
public class IpTextField extends TextField {
	public IpTextField() {
		super();
		setPromptText("IP:PORT");
		setWidth(570);
		setStyle("  -fx-border-color: #2c2c2c;"
				 + "  -fx-border-width: 4 4 4 4;"
				 + "  -fx-background-color: black;"
				 + "  -fx-font-size: 44px;"
				 + "  -fx-text-fill: WHITE;"
				 + "  -fx-prompt-text-fill: GRAY");
	}

	public IpTextField(String s) {
		super(s);
	}
}
