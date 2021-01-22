package com.prodaply.game;

import com.prodaply.engine.AbstractGameController;
import com.prodaply.engine.GameController;
import com.prodaply.engine.Input;
import com.prodaply.engine.Renderer;
import com.prodaply.game.level.LevelManager;

public class GameManager extends AbstractGameController {
	private float deltaTime;
	private LevelManager levelManager;
	public static Input INPUT = null;

	public GameManager() {
		levelManager = new LevelManager();
	}

	@Override
	public void fixedUpdate(GameController gc, float deltaTime) {
		// Handle every unput's interaction from the user here
		levelManager.update(deltaTime);
	}
	@Override
	public void update(GameController gc, Renderer r) {	
		INPUT = gc.getInput();
		levelManager.render(r);
	}

	public static void main(String[] args) {
		GameController game = new GameController(new GameManager());
		game.start();
	}
}
