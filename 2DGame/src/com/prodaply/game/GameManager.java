package com.prodaply.game;

import java.util.ArrayList;

import com.prodaply.engine.AbstractGameController;
import com.prodaply.engine.GameController;
import com.prodaply.engine.Renderer;
import com.prodaply.game.level.LevelManager;
import com.prodaply.game.level.LevelOne;

public class GameManager extends AbstractGameController {
	private float deltaTime;
	private InputHandler inputHandler;
	private LevelManager levelManager;

	public GameManager() {
		inputHandler = new InputHandler();
		levelManager = new LevelManager();
	}

	@Override
	public void fixedUpdate(GameController gc, float deltaTime) {
		// Handle every unput's interaction from the user here
		inputHandler.update(gc.getInput(), deltaTime);
		levelManager.update(deltaTime);
	}
	@Override
	public void update(GameController gc, Renderer r) {	
		levelManager.render(r);
	}

	public static void main(String[] args) {
		GameController game = new GameController(new GameManager());
		game.start();
	}
}
