package com.prodaply.game.entity;

import java.util.List;

import com.prodaply.engine.GameObject;
import com.prodaply.engine.Renderer;
import com.prodaply.engine.Time;
import com.prodaply.engine.components.SpriteRenderer;
import com.prodaply.engine.components.physics.Rigidbody;
import com.prodaply.engine.components.physics.collider.CircleCollider;
import com.prodaply.engine.components.physics.collider.Collider;
import com.prodaply.engine.components.physics.collider.CollisionEvent;
import com.prodaply.engine.gfx.Sprite;
import com.prodaply.engine.util.Sign;
import com.prodaply.engine.util.Vector2f;

public class Planet extends GameObject {
	private Vector2f position;
	public Vector2f speed;
	private Rigidbody rb;
	private SpriteRenderer spRenderer;
	private CircleCollider cc;

	public Planet(String name, Sprite sp, Vector2f position, Vector2f speed) {
		super(name);
		this.position = position;
		this.speed = speed;
		this.addComponent(new SpriteRenderer());
		this.addComponent(new Rigidbody());
		this.addComponent(new CircleCollider(sp.getH() / 2));
		spRenderer = this.getComponent(SpriteRenderer.class);
		rb = this.getComponent(Rigidbody.class);
		cc = this.getComponent(CircleCollider.class);
		
		spRenderer.sprite = sp;
	}

	@Override
	public void Start() {
		// TODO Auto-generated method stub
		this.transform.position = this.position;
		rb.velocity = new Vector2f(this.speed).mul(Time.deltaTime);
		rb.mass = spRenderer.sprite.getW();
	}

	@Override
	public void FixedUpdate(float dt) {
		
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
