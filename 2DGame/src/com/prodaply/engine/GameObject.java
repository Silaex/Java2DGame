package com.prodaply.engine;

import java.util.ArrayList;
import java.util.List;

import com.prodaply.engine.components.Transform;
import com.prodaply.engine.components.physics.collider.Collider;
import com.prodaply.engine.components.physics.collider.CollisionEvent;
import com.prodaply.engine.util.Vector2f;

public abstract class GameObject implements CollisionEvent {
	
	public String name;
	public Transform transform = new Transform();
	
	private ArrayList<Component> components;
	
	public GameObject(String name) {
		this.name = name;
		
		components = new ArrayList<Component>();
		this.addComponent(transform);
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
		c.start();
		c.gameObject = this;
	}
	
	public abstract void FixedUpdate(float dt);
	public void update(float dt) {
		this.FixedUpdate(dt);
		for (int i = 0; i < components.size(); i++) {
			Component c = components.get(i);
			c.update(dt);
		}
	}
	
	public abstract void Update(Renderer r);
	public void render(Renderer r) {
		this.Update(r);
		for (int i = 0; i < components.size(); i++) {
			Component c = components.get(i);
			c.render(r);
		}
	}
	
	public abstract void Start();
	public void start() {
		for (int i = 0; i < components.size(); i++) {
			Component c = components.get(i);
			c.start();
		}
		this.Start();
	}
	
	/* ******* COLLISION INTERFACE METHODS ********  */
	
	public abstract void OnCollision(Collider collider, Collider self);
	@Override
	public void onCollision(Collider collider, Collider self) {
		this.OnCollision(collider, self);
	}
	
	public abstract void OnCollision(List<Collider> colliders, Collider self);
	@Override
	public void onCollision(List<Collider> colliders, Collider self) {
		this.OnCollision(colliders, self);
	}
}
