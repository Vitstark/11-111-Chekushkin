package com.example.client.scenes;

import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.scene.SubScene;
import com.example.client.utils.Images;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import static com.almasb.fxgl.dsl.FXGL.centerTextBind;
import static com.almasb.fxgl.dsl.FXGL.getSettings;
import static com.almasb.fxgl.dsl.FXGL.getUIFactoryService;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getAppHeight;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getAppWidth;

/**
 * @author Vitaly Chekushkin
 */
public class ConnectionMenu extends SubScene {
	public ConnectionMenu() {

		ImageView background = createBackground();
		Text titleText = createMainTitle();
		VBox vBox = new VBox();

		getContentRoot().getChildren().addAll(background, titleText, vBox);
	}

	private ImageView createBackground() {
		return new ImageView(Images.MAIN_MENU_BACKGROUND);
	}

	private Text createMainTitle() {
		Text title = getUIFactoryService().newText(getSettings().getTitle(),
			Color.rgb(0,0,204), 130);

		DropShadow shadow = new DropShadow();
		shadow.setColor(Color.rgb(102, 102, 255));
		shadow.setHeight(8);
		shadow.setWidth(8);
		shadow.setOffsetX(8);
		shadow.setOffsetY(10);
		shadow.setSpread(10);

		title.setEffect(shadow);
		centerTextBind(title, getAppWidth() * 0.5, getAppHeight() * 0.35);

		return title;
	}
}
