package com.example.client.scenes;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.example.client.ui.MenuButton;
import com.example.client.utils.Images;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.centerTextBind;
import static com.almasb.fxgl.dsl.FXGL.getGameTimer;
import static com.almasb.fxgl.dsl.FXGL.getSceneService;
import static com.almasb.fxgl.dsl.FXGL.getSettings;
import static com.almasb.fxgl.dsl.FXGL.getUIFactoryService;

/**
 * @author Vitaly Chekushkin
 */
public class MainMenu extends FXGLMenu {
	public MainMenu() {
		super(MenuType.MAIN_MENU);

		ImageView background = createBackground();
		Text titleText = createMainTitle();
		VBox vBox = getButtonsBox();

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

	private VBox getButtonsBox() {
		int fontSize = 48;

		VBox buttons = new VBox(
			new MenuButton("SINGLEPLAYER", fontSize, () -> newGame()),
			new MenuButton("MULTIPLAYER", fontSize, () -> multiPlayerMenu()),
			new MenuButton("EXIT", fontSize, () -> fireExit())
		);

		buttons.setAlignment(Pos.BASELINE_CENTER);
		buttons.setTranslateX(getAppWidth() * 0.5 - (fontSize * 10) * 0.55);
		buttons.setTranslateY(getAppHeight() * 0.45);
		buttons.setSpacing(20);

		return buttons;
	}

	public void newGame() {
		fireNewGame();
	}

	public void multiPlayerMenu() {
		getSceneService().pushSubScene(new ConnectionMenu());
	}

	private void none() {}
}
