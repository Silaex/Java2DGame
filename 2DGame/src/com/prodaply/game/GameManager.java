package com.prodaply.game;

import java.util.ArrayList;

import com.prodaply.engine.AbstractGameController;
import com.prodaply.engine.GameController;
import com.prodaply.engine.GameObject;
import com.prodaply.engine.Renderer;
import com.prodaply.engine.util.Vector2f;

public class GameManager extends AbstractGameController {
	private InputHandler inputHandler;
	private float deltaTime;
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	public GameManager() {
		inputHandler = new InputHandler();
		
		Vector2f a = new Vector2f(-2, -5);
		Vector2f b = new Vector2f(2, 2);
		
		System.out.println(a.normalized().getMagnitude());
		System.out.println(a.getDirection());
		
		for (GameObject gameObject : gameObjects) {
			gameObject.start();
		}
	}

	@Override
	public void fixedUpdate(GameController gc, float deltaTime) {
		// Handle every unput's interaction from the user here
		inputHandler.update(gc.getInput(), deltaTime);
		this.deltaTime = deltaTime;
	}

	@Override
	public void update(GameController gc, Renderer r) {
		for (GameObject gameObject : gameObjects) {
			gameObject.render(r);
		}
	}
	
	public static void main(String[] args) {
		GameController game = new GameController(new GameManager());
		game.start();
	}
}
