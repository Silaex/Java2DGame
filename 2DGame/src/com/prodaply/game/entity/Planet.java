package com.prodaply.game.entity;

import com.prodaply.engine.AbstractGameObject;
import com.prodaply.engine.components.physics.Rigidbody;

public class Planet extends AbstractGameObject {
	
	public Planet() {
		this.addComponent(new Rigidbody());
		
	}

	@Override
	public void Start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void FixedUpdate(float dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}
}
