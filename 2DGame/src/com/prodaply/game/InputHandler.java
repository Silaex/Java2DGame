package com.prodaply.game;

import java.awt.event.KeyEvent;

import com.prodaply.engine.Input;
import com.prodaply.engine.util.Vector2f;
import com.prodaply.game.entity.Player;

public class InputHandler {

	public InputHandler() {

	}

	public void update(Input input, float deltaTime) {
		Player p = GameManager.PLAYER;
		Vector2f movementDirection = new Vector2f();
		if (input.isKey(KeyEvent.VK_LEFT)) {
			movementDirection.setX(-deltaTime);
		}
		else if (input.isKey(KeyEvent.VK_RIGHT)) {
			movementDirection.setX(deltaTime);
		}
		else if (input.isKey(KeyEvent.VK_UP)) {
			movementDirection.setY(-deltaTime);
		}
		else if (input.isKey(KeyEvent.VK_DOWN)) {
			movementDirection.setY(deltaTime);
		}
		p.move(movementDirection);
	}
}
