package com.prodaply.engine.components.physics;

import com.prodaply.engine.Component;
import com.prodaply.engine.Renderer;
import com.prodaply.engine.util.Random;
import com.prodaply.engine.util.Vector2f;

public class Rigidbody extends Component {
	public Vector2f position;
	public Vector2f rotation;
	public Vector2f velocity;
	public Vector2f centerOfMass;
	public boolean useGravity;
	public float mass;

	@Override
	public void start() {
		position = new Vector2f();
		rotation = new Vector2f();
		velocity = new Vector2f();
		useGravity = true;
		mass = 1;
	}

	@Override
	public void update(float dt) {
		Vector2f tp = this.gameObject.transform.position;
		if (this.velocity.getMagnitude() < 0.001f) {
			this.velocity.x = 0;
			this.velocity.y = 0;
		}
		tp = new Vector2f(tp.add(velocity));
	}

	@Override
	public void render(Renderer r) {
		// TODO Auto-generated method stub
		
	}
	
}
