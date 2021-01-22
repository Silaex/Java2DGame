package com.prodaply.game.entity;

import java.awt.event.KeyEvent;
import java.util.List;

import com.prodaply.engine.GameObject;
import com.prodaply.engine.Renderer;
import com.prodaply.engine.components.SpriteRenderer;
import com.prodaply.engine.components.physics.Rigidbody;
import com.prodaply.engine.components.physics.collider.BoxCollider;
import com.prodaply.engine.components.physics.collider.Collider;
import com.prodaply.engine.gfx.Animation;
import com.prodaply.engine.util.Vector2f;
import com.prodaply.game.GameManager;

public class Player extends GameObject {
	private Vector2f speed;
	private Rigidbody rb;
	private SpriteRenderer spRenderer;
	private BoxCollider bc;
	private Animation animation;
	
	public Player(Vector2f position, Vector2f speed) {
		super("Player", position);
		this.speed = speed;
		this.rb = new Rigidbody();
		this.spRenderer = new SpriteRenderer(SpriteManager.PLAYER);
		this.bc = new BoxCollider( new Vector2f(spRenderer.sprite.getW(), spRenderer.sprite.getH() / 4), new Vector2f(0, spRenderer.sprite.getH() / 4) );
		this.addComponent(this.rb);
		this.addComponent(this.spRenderer);
		this.addComponent(this.bc);
	}
	
	@Override
	public void Start() {
		
	}

	@Override
	public void FixedUpdate(float dt) {
		Vector2f movement = new Vector2f();
		
		if (GameManager.INPUT.isKey(KeyEvent.VK_LEFT)) {
			movement.x = -this.speed.x * dt;
		}
		if (GameManager.INPUT.isKey(KeyEvent.VK_RIGHT)) {
			movement.x = this.speed.x * dt;
		}
		if (GameManager.INPUT.isKey(KeyEvent.VK_DOWN)) {
			movement.y = this.speed.y * dt;
		}
		if (GameManager.INPUT.isKey(KeyEvent.VK_UP)) {
			movement.y = -this.speed.y * dt;
		}
		
		rb.velocity = movement;
	}

	@Override
	public void Update(Renderer r) {
		
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
