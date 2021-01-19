package com.prodaply.game;

import java.awt.event.KeyEvent;

import com.prodaply.engine.Input;
import com.prodaply.engine.util.Vector2f;
import com.prodaply.game.entity.Player;

public class InputHandler {

	public InputHandler() {

	}

	public void update(Input input, float deltaTime) {
		Vector2f movementDirection = new Vector2f();
		if (input.isKey(KeyEvent.VK_LEFT)) {
			movementDirection.x = -1;
		}
		 if (input.isKey(KeyEvent.VK_RIGHT)) {
			movementDirection.x = 1;
		}
		 if (input.isKey(KeyEvent.VK_UP)) {
			movementDirection.y = -1;
		}
		 if (input.isKey(KeyEvent.VK_DOWN)) {
			movementDirection.y = 1;
		}
	}
}
