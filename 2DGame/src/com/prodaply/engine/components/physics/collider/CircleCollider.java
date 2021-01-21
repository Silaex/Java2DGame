package com.prodaply.engine.components.physics.collider;

import java.util.ArrayList;
import java.util.List;

import com.prodaply.engine.Renderer;
import com.prodaply.engine.components.physics.Rigidbody;
import com.prodaply.engine.util.Vector2f;

public class CircleCollider extends Collider {
	public int radius;

	public CircleCollider(int radius) {
		super(new Vector2f(radius * 2));
		this.radius = radius;
	}
	
	@Override
	public void colliderUpdate(float dt) {
		
	}

	@Override
	public void colliderRender(Renderer r) {
		// TODO Auto-generated method stub
		
	}

}
