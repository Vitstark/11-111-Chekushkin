package com.example.client;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.input.virtual.VirtualButton;
import com.almasb.fxgl.pathfinding.CellState;
import com.almasb.fxgl.pathfinding.astar.AStarGrid;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
import com.example.client.components.TankViewComponent;
import com.example.client.configuration.AppConfiguration;
import com.example.client.handlers.BulletBrickHandler;
import com.example.client.handlers.BulletEnemyTankHandler;
import com.example.client.scenes.TanksSceneFactory;
import com.example.client.utils.MusicController;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import static com.almasb.fxgl.dsl.FXGL.byType;
import static com.almasb.fxgl.dsl.FXGL.getGameScene;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.getInput;
import static com.almasb.fxgl.dsl.FXGL.getPhysicsWorld;
import static com.almasb.fxgl.dsl.FXGL.loopBGM;
import static com.almasb.fxgl.dsl.FXGL.setLevelFromMap;
import static com.example.client.configuration.GameConfiguration.*;
import static com.example.client.entities.Entities.*;

public class AppConfig extends GameApplication {
	private TankViewComponent tankViewComponent;
	private AStarGrid grid;

	@Override
	protected void onPreInit() {
		MusicController.setMusicVolume(0.3);
		loopBGM("menu_music.mp3");
	}

	@Override
	protected void initInput() {
		Input input = getInput();

		input.addAction(new UserAction("Move Left") {
			@Override
			protected void onAction() {
				tankViewComponent.left();
			}
		}, KeyCode.A, VirtualButton.LEFT);

		input.addAction(new UserAction("Move Right") {
			@Override
			protected void onAction() {
				tankViewComponent.right();
			}
		}, KeyCode.D, VirtualButton.RIGHT);

		input.addAction(new UserAction("Move Up") {
			@Override
			protected void onAction() {
				tankViewComponent.up();
			}
		}, KeyCode.W, VirtualButton.UP);

		input.addAction(new UserAction("Move Down") {
			@Override
			protected void onAction() {
				tankViewComponent.down();
			}
		}, KeyCode.S, VirtualButton.DOWN);

		input.addAction(new UserAction("Shoot") {
			@Override
			protected void onActionBegin() {
				tankViewComponent.shoot();
			}
		}, KeyCode.SPACE);

		input.addAction(new UserAction("Move To") {
			@Override
			protected void onActionBegin() {
				tankViewComponent.getEntity().getComponent(AStarMoveComponent.class)
					.moveToCell((int) (getInput().getMouseXWorld() / 30), (int) (getInput().getMouseYWorld() / 30));
				//tankViewComponent.getEntity().call("moveToCell", 2, 3);
			}
		}, KeyCode.G);
	}

	@Override
	protected void initPhysics() {
		var bulletTankHandler = new BulletEnemyTankHandler();

		getPhysicsWorld().addCollisionHandler(bulletTankHandler);
		getPhysicsWorld().addCollisionHandler(bulletTankHandler.copyFor(BULLET, PLAYER));

		getPhysicsWorld().addCollisionHandler(new BulletBrickHandler());
	}

	@Override
	protected void initSettings(GameSettings gameSettings) {
		gameSettings.setWidth(AppConfiguration.SCENE_WIDTH);
		gameSettings.setHeight(AppConfiguration.SCENE_HEIGHT);
		gameSettings.setTitle(AppConfiguration.TITLE);
		gameSettings.setVersion(AppConfiguration.VERSION);

		gameSettings.setFullScreenAllowed(true);
		gameSettings.setFullScreenFromStart(true);

		gameSettings.setIntroEnabled(false);
		gameSettings.setGameMenuEnabled(true);
		gameSettings.setMainMenuEnabled(true);

		gameSettings.setSceneFactory(new TanksSceneFactory());
		gameSettings.setFontUI("font.ttf");
	}

	@Override
	protected void initGame() {
		getGameScene().setBackgroundColor(Color.LIGHTGRAY);

		getGameWorld().addEntityFactory(new GameFactory());

		setLevelFromMap("level1.tmx");

		// TODO: careful: the world itself is 21x12 but each block we count as 2
		grid = AStarGrid.fromWorld(getGameWorld(), 21 * 2, 12 * 2,
			BLOCK_SIZE / 2, BLOCK_SIZE / 2, (type) -> {
			if (type == BRICK)
				return CellState.NOT_WALKABLE;

			return CellState.WALKABLE;
		});

		tankViewComponent = getGameWorld().getSingleton(PLAYER).getComponent(TankViewComponent.class);
		tankViewComponent.getEntity().addComponent(new AStarMoveComponent(grid));
		//tankViewComponent.getEntity().removeComponent(TankAIComponent.class);

//		byType(ENEMY).forEach(e -> {
//			e.addComponent(new AStarMoveComponent(grid));
//		});
//
//		byType(ALLY).forEach(e -> {
//			e.addComponent(new AStarMoveComponent(grid));
//		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}