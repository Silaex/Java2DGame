package com.prodaply.engine;

import java.util.ArrayList;

import com.prodaply.engine.components.Transform;

public class GameObject {
	
	public Transform transform = new Transform();
	private AbstractGameObject ago;
	
	private ArrayList<Component> components;
	
	public GameObject() {
		components = new ArrayList<Component>();
	}
	
	public <T extends Component> T getComponent(Class<T> componentClass) {
		for (Component c : components) {
			if (componentClass.isAssignableFrom(c.getClass())) {
				return componentClass.cast(c);
			}
		}
		
		return null;
	}
	
	public <T extends Component> void removeComponent(Class<T> componentClass) {
		for (int i = 0; i < components.size(); i++) {
			Component c = components.get(i);
			if (componentClass.isAssignableFrom(c.getClass())) {
				components.remove(i);
				return;
			}
		}
	}
	
	public void addComponent(Component c) {
		this.components.add(c);
		c.gameObject = this;
	}
	
	public void update(float dt) {
		for (int i = 0; i < components.size(); i++) {
			Component c = components.get(i);
			c.update(dt);
		}
		ago.FixedUpdate(dt);
	}
	
	public void render(Renderer r) {
		for (int i = 0; i < components.size(); i++) {
			Component c = components.get(i);
			c.render(r);
		}
		ago.Update();
	}
	
	public void start() {
		for (int i = 0; i < components.size(); i++) {
			Component c = components.get(i);
			c.start();
		}
		ago.Start();
	}
}
