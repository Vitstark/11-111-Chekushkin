package com.example.client.ui;

import com.almasb.fxgl.dsl.FXGL;
import javafx.beans.binding.Bindings;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * @author Vitaly Chekushkin
 */
public class MenuButton extends Parent {
	public MenuButton(String text, double fontSize, Runnable action) {
		Text textButton = FXGL.getUIFactoryService().newText(text, Color.WHITE, fontSize);
		textButton.setStrokeWidth(1.5);
		textButton.strokeProperty().bind(textButton.fillProperty());

		textButton.fillProperty().bind(
			Bindings.when(hoverProperty())
				.then(Color.rgb(0,0,204))
				.otherwise(Color.WHITE)
		);

		setOnMouseClicked(e -> action.run());

		textButton.setTextAlignment(TextAlignment.CENTER);
		setPickOnBounds(true);
		getChildren().add(textButton);
	}
}
