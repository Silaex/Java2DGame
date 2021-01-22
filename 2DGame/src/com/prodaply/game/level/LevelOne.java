package com.prodaply.game.level;

import java.util.ArrayList;
import java.util.List;

import com.prodaply.engine.GameController;
import com.prodaply.engine.GameObject;
import com.prodaply.engine.Renderer;
import com.prodaply.engine.Scene;
import com.prodaply.engine.util.Vector2f;
import com.prodaply.game.entity.Box;
import com.prodaply.game.entity.Player;

public class LevelOne extends Scene {

	private Vector2f halfScreen = new Vector2f(GameController.getWidth() / 2, GameController.getHeight() / 2);

	private List<GameObject> go = new ArrayList<>() {
		{
			add(new Player(new Vector2f(halfScreen), new Vector2f(75)));
			add(new Box(new Vector2f(halfScreen.x + 32, halfScreen.y)));
//			add(new Planet("Moon", SpriteManager.MOON, new Vector2f(halfScreen.x * 1.5f, 0), new Vector2f(-75, 125)));
		}
	};

	public LevelOne() {
		super("level_one", new Vector2f(GameController.getWidth(), GameController.getHeight()));
	}

	@Override
	public void sceneStart() {
		for (GameObject gameObject : go) {
			this.addGameObject(gameObject);
		}
	}

	@Override
	public void sceneUpdate(float deltaTime) {
		
	}

	@Override
	public void sceneRender(Renderer r) {
		// TODO Auto-generated method stub
		r.drawText(""+ go.size(), 0, 0, 0xffffffff);
	}
}
