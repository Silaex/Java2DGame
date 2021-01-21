package com.prodaply.game.level;

import java.util.ArrayList;
import java.util.List;

import com.prodaply.engine.GameController;
import com.prodaply.engine.GameObject;
import com.prodaply.engine.Renderer;
import com.prodaply.engine.Scene;
import com.prodaply.engine.components.physics.Rigidbody;
import com.prodaply.engine.util.Random;
import com.prodaply.engine.util.Sign;
import com.prodaply.engine.util.Vector2f;
import com.prodaply.game.entity.Planet;
import com.prodaply.game.entity.SpriteManager;

public class LevelOne extends Scene {
	
	private Vector2f halfScreen = new Vector2f(GameController.getWidth()/2, GameController.getHeight()/2);
	
	private List<GameObject> go = new ArrayList<>(){{
			add(new Planet("Earth", SpriteManager.EARTH, new Vector2f(halfScreen), new Vector2f(0)));
			add(new Planet("Moon", SpriteManager.MOON, new Vector2f(halfScreen.x * .25f, halfScreen.y * 2), new Vector2f(75, -125)));
//			add(new Planet("Moon", SpriteManager.MOON, new Vector2f(halfScreen.x * 1.5f, 0), new Vector2f(-75, 125)));
	}};
	
	public LevelOne() {
		super("level_one", new Vector2f(GameController.getWidth(), GameController.getHeight()));
	}

	@Override
	public void sceneStart() {
		for (int i = go.size(); i < Random.Int(20, 20); i++) {
			Vector2f speed = new Vector2f(Random.Int(10, 30), Random.Int(10, 30));
			Vector2f position = new Vector2f(Random.Int(64, (int)(halfScreen.x*2)), Random.Int(64, (int)(halfScreen.y*2)));
			Vector2f direction = new Vector2f(Sign.of(Random.Int(-1, 1)), Sign.of(Random.Int(-1, 1)));
			speed.mul(direction);			
			go.add(new Planet("Moon", SpriteManager.EARTH, new Vector2f(position), new Vector2f(speed)));
		}
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
		r.drawText(go.get(0).getComponent(Rigidbody.class).velocity.toString(), 0, 0, 0xffffffff);
	}
}
