package com.example.client.handlers;

import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.example.client.entities.Entities;

public class BulletEnemyTankHandler extends CollisionHandler {

    public BulletEnemyTankHandler() {
        super(Entities.BULLET, Entities.ENEMY);
    }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity tank) {
        bullet.removeFromWorld();

        var hp = tank.getComponent(HealthIntComponent.class);

        hp.damage(1);

        if (hp.isZero()) {
            tank.removeFromWorld();
        }
    }
}
