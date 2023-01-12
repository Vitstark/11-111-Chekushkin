package com.example.client;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.dsl.EntityBuilder;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.action.ActionComponent;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.state.StateComponent;
import com.almasb.fxgl.pathfinding.CellMoveComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.example.client.components.*;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGL.texture;
import static com.example.client.configuration.GameConfiguration.*;
import static com.example.client.entities.Entities.*;

/**
 * @author Vitaly Chekushkin
 */
public class GameFactory implements EntityFactory {
	@Spawns("player,playerSpawnPoint")
	public Entity newPlayer(SpawnData data) {
		return entityBuilder(data)
			.type(PLAYER)
			//.scale(0.8, 0.8)
			//.rotationOrigin(32, 32)
			.anchorFromCenter()
			.collidable().
			bbox(new HitBox(new Point2D(10, 10), BoundingShape.box(48, 48)))
			//.view(texture("tank64.png").multiplyColor(Color.GREEN))
			.viewWithBBox("tank64.png")
			.with(new PlayerArrowViewComponent())
			.with(new HealthIntComponent(10))
			.with(new MoveComponent())
			.with(new TankViewComponent())
			.with(new CellMoveComponent(TANK_SIZE, TANK_SIZE, TANK_SPEED).allowRotation(true))
			.with(new ActionComponent())
			.with(new StateComponent())
			.with(new TankAIComponent())
			.build();
	}

	@Spawns("brick")
	public Entity newBrick(SpawnData data) {
		return entityBuilder(data)
			.type(BRICK)
			.view(texture("bricks.png"))
			.bbox(new HitBox(new Point2D(-10, -10), BoundingShape.box(BLOCK_SIZE, BLOCK_SIZE)))
			.collidable()
			.with(new BrickComponent())
			.build();
	}

	@Spawns("Bullet")
	public Entity newBullet(SpawnData data) {
		Entity owner = data.get("owner");
		Point2D centerOfTexture = data.get("center");

		var collidable = new CollidableComponent(true);
		collidable.addIgnoredType(owner.getType());

		return entityBuilder(data)
			.at(centerOfTexture)
			//.bbox(new HitBox(new Point2D(24, 24), BoundingShape.box(8, 8)))
			.type(BULLET)
			//.view("bullet.png")
			.viewWithBBox("bullet.png")
			.scale(0.8, 0.8)
			.with(collidable)
			.with(new OffscreenCleanComponent())
			.with(new ProjectileComponent(data.get("direction"), BULLET_SPEED))
			.build();
	}
}
