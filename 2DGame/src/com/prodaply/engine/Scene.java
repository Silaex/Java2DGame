package com.prodaply.engine;

import java.util.ArrayList;
import java.util.List;

import com.prodaply.engine.util.Vector2f;

public abstract class Scene {
	public String name;
	public Vector2f size;

	private List<GameObject> gameObjects = new ArrayList<GameObject>();
	private boolean isRunning = false;
	private boolean isFirstLaunch = true;

	public Scene(String name, Vector2f size) {
		this.size = size;
	}

	public void addGameObject(GameObject go) {
		gameObjects.add(go);
		go.start();
	}

	public <T extends GameObject> T getGameObject(Class<T> componentClass) {
		for (GameObject go : gameObjects) {
			if (componentClass.isAssignableFrom(go.getClass())) {
				return componentClass.cast(go);
			}
		}

		return null;
	}

	public <T extends GameObject> void removeGameObject(Class<T> componentClass) {
		for (int i = 0; i < gameObjects.size(); i++) {
			GameObject c = gameObjects.get(i);
			if (componentClass.isAssignableFrom(c.getClass())) {
				gameObjects.remove(i);
				return;
			}
		}
	}

	public abstract void sceneStart();

	public abstract void sceneUpdate(float deltaTime);

	public abstract void sceneRender(Renderer r);

	public void run() {
		if (isFirstLaunch) {
			for (GameObject gameObject : gameObjects) {
				gameObject.start();
			}
			this.sceneStart();
			isFirstLaunch = false;
		}
		this.isRunning = true;
	}

	public void stop() {
		this.isRunning = false;
	}

	public void update(float deltaTime) {
		if (isRunning) {
			this.sceneUpdate(deltaTime);
			for (GameObject gameObject : gameObjects) {
				gameObject.update(deltaTime);
			}
		}
	}

	public void render(Renderer renderer) {
		if (isRunning) {
			this.sceneRender(renderer);
			for (GameObject gameObject : gameObjects) {
				gameObject.render(renderer);
			}
		}
	}

}
