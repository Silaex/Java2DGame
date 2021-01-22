package com.prodaply.game.entity;

import java.util.List;

import com.prodaply.engine.GameObject;
import com.prodaply.engine.Renderer;
import com.prodaply.engine.components.SpriteRenderer;
import com.prodaply.engine.components.physics.Rigidbody;
import com.prodaply.engine.components.physics.collider.BoxCollider;
import com.prodaply.engine.components.physics.collider.Collider;
import com.prodaply.engine.util.Vector2f;

public class Box extends GameObject {
	
	private Rigidbody rb;
	private BoxCollider bc;
	private SpriteRenderer spRenderer;
	
	public Box(Vector2f position) {
		super("Box", position);
		this.rb = new Rigidbody();
		this.spRenderer = new SpriteRenderer(SpriteManager.BOX);
		this.bc = new BoxCollider( new Vector2f(spRenderer.sprite.getW(), 12), new Vector2f(0, spRenderer.sprite.getH() / 4) );
		this.addComponent(rb);
		this.addComponent(spRenderer);
		this.addComponent(bc);
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
	public void Update(Renderer r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnCollision(Collider collider, Collider self) {
		// TODO Auto-generated method stub
	}

	@Override
	public void OnCollision(List<Collider> colliders, Collider self) {
		// TODO Auto-generated method stub
		
	}
}
