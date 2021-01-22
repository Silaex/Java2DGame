package com.prodaply.engine.components.physics.collider;

import java.util.ArrayList;
import java.util.List;

import com.prodaply.engine.Component;
import com.prodaply.engine.Renderer;
import com.prodaply.engine.components.physics.Rigidbody;
import com.prodaply.engine.util.Vector2f;

public abstract class Collider extends Component {
	public Vector2f dimension;
	public static List<Collider> colliders = new ArrayList<Collider>();
	public Vector2f position, offsetPosition;
	
	protected CollisionEvent listener;
	
	public Collider(Vector2f dimension, Vector2f offsetPosition) {
		this.dimension = dimension;
		colliders.add(this);
		this.offsetPosition = offsetPosition;
	}
	public Collider(Vector2f dimension) {
		this.dimension = dimension;
		colliders.add(this);
		this.offsetPosition = new Vector2f();
	}
	
	public abstract void colliderUpdate(float dt);
	public abstract void colliderRender(Renderer r);
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		this.listener = this.gameObject;
		this.position = new Vector2f(this.gameObject.transform.position).add(this.offsetPosition);
	}
	
	@Override
	public void update(float dt) {
		this.position = new Vector2f(this.gameObject.transform.position).add(this.offsetPosition);
		
		for (Collider collider : Collider.colliders) {
			if (collider.gameObject != this.gameObject) {
				boolean collisionDetected = false;
				float distance = Vector2f.Distance(this.position, collider.position);
				Vector2f thisPosition = this.gameObject.transform.position;
				Vector2f colliderPosition = collider.gameObject.transform.position;
				Rigidbody thisRB = this.gameObject.getComponent(Rigidbody.class);
				Rigidbody colliderRB = collider.gameObject.getComponent(Rigidbody.class);

				if (this.gameObject.getComponent(CircleCollider.class) != null && ((distance <= (this.dimension.x / 2) + collider.dimension.x / 2) || (distance <= (this.dimension.y / 2) + collider.dimension.y / 2))) {
						
					// OVERLAPPING
					float overlap = 0.5f * (distance - (this.dimension.x / 2) - (collider.dimension.x / 2));
					
					thisPosition.x -= overlap * (thisPosition.x - colliderPosition.x) / distance;
					thisPosition.y -= overlap * (thisPosition.y - colliderPosition.y) / distance;
					
					colliderPosition.x += overlap * (thisPosition.x - colliderPosition.x) / distance;
					colliderPosition.y += overlap * (thisPosition.y - colliderPosition.y) / distance;
					
					// COLLISION
					distance = new Vector2f(collider.gameObject.transform.position).sub(this.gameObject.transform.position).getMagnitude();
					
					// Normals
					float nx = (colliderPosition.x - this.gameObject.transform.position.x) / distance;
					float ny = (colliderPosition.y - this.gameObject.transform.position.y) / distance;
					
					// Tangent
					Vector2f tangent = new Vector2f(-ny, nx);
					
					// Dot product
					
					float tanDP1 = Vector2f.Dot(thisRB.velocity, tangent);
					float tanDP2 = Vector2f.Dot(colliderRB.velocity, tangent);
					
					// Dot product Normal
					float dpNorm1 = thisRB.velocity.x * nx + thisRB.velocity.y * ny;
					float dpNorm2 = colliderRB.velocity.x * nx + colliderRB.velocity.y * ny;
					
					// Momentum ( With this the collider will interact with the mass of the current collider )
					float m1 = (dpNorm1 * (thisRB.mass - colliderRB.mass) + 2.0f * colliderRB.mass * dpNorm2) / (thisRB.mass + colliderRB.mass);
					float m2 = (dpNorm2 * (colliderRB.mass - thisRB.mass) + 2.0f * thisRB.mass * dpNorm1) / (thisRB.mass + colliderRB.mass);
					
					thisRB.velocity.x 		= tangent.x * tanDP1 + nx * m1;
					thisRB.velocity.y 		= tangent.y * tanDP1 + ny * m1;
					colliderRB.velocity.x 	= tangent.x * tanDP2 + nx * m2;
					colliderRB.velocity.y 	= tangent.y * tanDP2 + ny * m2;
					
					collisionDetected = true;
							
				} else if (((int)this.position.x - this.dimension.x / 2 < (int)collider.position.x + collider.dimension.x / 2
						&& (int)this.position.x + this.dimension.x / 2 > (int)collider.position.x - collider.dimension.x / 2)
						&& ((int)this.position.y - this.dimension.y / 2 < (int)collider.position.y + collider.dimension.y / 2
						&& (int)this.position.y + this.dimension.y / 2 > (int)collider.position.y - collider.dimension.y / 2))
				{
					this.gameObject.transform.position = new Vector2f(thisPosition).sub(new Vector2f(thisRB.velocity));
					collider.gameObject.transform.position = new Vector2f(colliderPosition).add(thisRB.velocity);
					
					// COLLISION
//					distance = new Vector2f(collider.gameObject.transform.position).sub(this.gameObject.transform.position).getMagnitude();
//					
//					// Normals
//					float nx = (colliderPosition.x - this.gameObject.transform.position.x) / distance;
//					float ny = (colliderPosition.y - this.gameObject.transform.position.y) / distance;
//					
//					// Tangent
//					Vector2f tangent = new Vector2f(-ny, nx);
//					
//					// Dot product
//					
//					float tanDP1 = Vector2f.Dot(thisRB.velocity, tangent);
//					float tanDP2 = Vector2f.Dot(colliderRB.velocity, tangent);
//					
//					// Dot product Normal
//					float dpNorm1 = thisRB.velocity.x * nx + thisRB.velocity.y * ny;
//					float dpNorm2 = colliderRB.velocity.x * nx + colliderRB.velocity.y * ny;
//					
//					// Momentum ( With this the collider will interact with the mass of the current collider )
//					float m1 = (dpNorm1 * (thisRB.mass - colliderRB.mass) + 2.0f * colliderRB.mass * dpNorm2) / (thisRB.mass + colliderRB.mass);
//					float m2 = (dpNorm2 * (colliderRB.mass - thisRB.mass) + 2.0f * thisRB.mass * dpNorm1) / (thisRB.mass + colliderRB.mass);
//					
//					thisRB.velocity.x 		= tangent.x * tanDP1 + nx * m1;
//					thisRB.velocity.y 		= tangent.y * tanDP1 + ny * m1;
//					colliderRB.velocity.x 	= tangent.x * tanDP2 + nx * m2;
//					colliderRB.velocity.y 	= tangent.y * tanDP2 + ny * m2;
//					
//					System.out.println(tangent + ":" + nx + "/" + ny + " => " + m2 + " -> " + (thisRB.mass + colliderRB.mass));
					
					collisionDetected = true;
				}
				
				if (collisionDetected) {
					this.listener.onCollision(collider, this);
					collider.listener.onCollision(this, collider);
				}
			}
		}
	}
	
	@Override
	public void render(Renderer r) {
		for (Collider collider : colliders) {
			collider.colliderRender(r);
		}
	}
}
