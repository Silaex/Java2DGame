package com.prodaply.game.level;

import java.util.ArrayList;

import com.prodaply.engine.GameController;
import com.prodaply.engine.Renderer;
import com.prodaply.engine.Scene;
import com.prodaply.engine.util.Vector2f;
import com.prodaply.game.GameManager;

public class LevelManager {
	
	private GameController gameController;
	private ArrayList<Scene> scenes = new ArrayList<Scene>();
	private Scene currentScene;
	private LevelOne levelOne = new LevelOne();

	public LevelManager() {
		this.addScene(levelOne);
		this.setScene(levelOne.name);
	}
	
	public void addScene(Scene sc) {
		scenes.add(sc);
	}
	
	public void removeScene(Scene sc) {
		scenes.remove(sc);
	}
	
	public Scene getScene(String name) {
		for (Scene scene : scenes) {
			if (scene.name == name) {
				return scene;
			}
		}
		
		return null;
	}
	
	public void setScene(String name) {
		Scene sceneToInit = getScene(name);
		if (sceneToInit != null) {
			currentScene = sceneToInit;
			currentScene.run();
		}
	}

	public void update(float deltaTime) {
		currentScene.update(deltaTime);
	}
	
	public void render(Renderer r) {
		currentScene.render(r);
	}
}
