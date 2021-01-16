package com.prodaply.game;

import com.prodaply.engine.AbstractGameController;
import com.prodaply.engine.GameController;
import com.prodaply.engine.Renderer;
import com.prodaply.engine.util.Vector2f;
import com.prodaply.game.entity.Box;
import com.prodaply.game.entity.Player;

public class GameManager extends AbstractGameController {
	public static final Player PLAYER = new Player(new Vector2f());
	private Box box = new Box(new Vector2f(PLAYER.getPosition()));
	private InputHandler inputHandler;
	
	public GameManager() {
		inputHandler = new InputHandler();
	}

	@Override
	public void update(GameController gc, float deltaTime) {
		// Handle every unput's interaction from the user here
		inputHandler.update(gc.getInput(), deltaTime);
	}

	@Override
	public void render(GameController gc, Renderer r) {
		r.drawSprite(box.getSprite(), 64, 64);
		r.drawSprite(PLAYER.getSprite(), (int)PLAYER.getPosition().getX(), (int)PLAYER.getPosition().getY());
	}
	
	public static void main(String[] args) {
		GameController game = new GameController(new GameManager());
		game.start();
	}
}
