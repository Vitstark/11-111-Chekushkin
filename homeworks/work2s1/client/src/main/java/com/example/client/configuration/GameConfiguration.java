package com.example.client.configuration;

import javafx.util.Duration;

/**
 * @author Vitaly Chekushkin
 */
public class GameConfiguration {
	public static final int BLOCK_SIZE = 64;
	public static final int TANK_SIZE = BLOCK_SIZE * 3;
	public static final double TANK_SPEED = BLOCK_SIZE * 2;
	public static final double BULLET_SPEED = BLOCK_SIZE * 8;
	public static final Duration FIRE_DELAY = Duration.millis(500);
}
