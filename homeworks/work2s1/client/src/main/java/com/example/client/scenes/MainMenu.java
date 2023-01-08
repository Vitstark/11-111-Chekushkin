package com.example.client.scenes;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.example.client.client.SocketClient;
import com.example.client.ui.IpTextField;
import com.example.client.ui.MenuButton;
import com.example.client.utils.Images;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.example.client.configuration.AppConfiguration.BUTTONS_FONT;
import static com.example.client.configuration.AppConfiguration.TITLE_FONT;

/**
 * @author Vitaly Chekushkin
 */
public class MainMenu extends FXGLMenu {
	private ImageView background;
	private Text title;
	private VBox buttons;

	public MainMenu() {
		super(MenuType.MAIN_MENU);

		this.background = createBackground();
		this.title = createMainTitle();
		this.buttons = getButtonsBox();
		getContentRoot().getChildren().addAll(background, title, buttons);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		fillMainButtons();
	}


	private ImageView createBackground() {
		return new ImageView(Images.MAIN_MENU_BACKGROUND);
	}

	private Text createMainTitle() {
		Text title = getUIFactoryService().newText(getSettings().getTitle(),
			Color.rgb(0,0,204), TITLE_FONT);

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
		VBox buttons = new VBox();

		buttons.setAlignment(Pos.CENTER);
		buttons.setTranslateX(getAppWidth() * 0.5 - (BUTTONS_FONT * 10) * 0.55);
		buttons.setTranslateY(getAppHeight() * 0.45);
		buttons.setSpacing(20);

		return buttons;
	}

	private void fillMainButtons() {
		cleanUpButtons();
		this.buttons
			.getChildren()
			.addAll(new MenuButton("SINGLEPLAYER", BUTTONS_FONT, () -> fireNewGame()),
				new MenuButton("MULTIPLAYER", BUTTONS_FONT, () -> fillMultiplayerButtons()),
				new MenuButton("EXIT", BUTTONS_FONT, () -> fireExit()));
	}

	private void fillMultiplayerButtons() {
		cleanUpButtons();
		TextField textField = new IpTextField();
		this.buttons
			.getChildren()
			.addAll(textField,
				new MenuButton("CONNECT", BUTTONS_FONT, () -> joinLobby(textField.getText())),
				new MenuButton("BACK", BUTTONS_FONT, () -> fillMainButtons()));
		buttons.setAlignment(Pos.CENTER);
	}

	private void joinLobby(String hostPort) {
		try {
			tryToConnect(hostPort);
		} catch (RuntimeException e) {
			TextField textField = (TextField) buttons.getChildren().get(0);
			textField.clear();
			textField.setPromptText("Cant connect to this host");
			return;
		}


	}

	private void tryToConnect(String hostPort) {
		String[] subStrings = hostPort.split(":");
		String host = subStrings[0];
		int port = Integer.parseInt(subStrings[1]);
		SocketClient.connect(host, port);
	}

	private void cleanUpButtons() {
		this.buttons.getChildren().clear();
		buttons.setAlignment(Pos.CENTER);
	}
}
