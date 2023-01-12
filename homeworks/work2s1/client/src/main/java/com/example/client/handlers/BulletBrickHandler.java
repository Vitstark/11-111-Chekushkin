package com.example.client.handlers;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.example.client.entities.Entities;

public class BulletBrickHandler extends CollisionHandler {
    public BulletBrickHandler() {
        super(Entities.BULLET, Entities.BRICK);
    }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity brick) {
        bullet.removeFromWorld();
        brick.call("onHit");
    }
}
