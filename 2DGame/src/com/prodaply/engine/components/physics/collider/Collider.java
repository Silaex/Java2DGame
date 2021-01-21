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
	
	protected CollisionEvent listener;
	
	public Collider(Vector2f dimension) {
		this.dimension = dimension;
		colliders.add(this);
	}
	
	public abstract void colliderUpdate(float dt);
	public abstract void colliderRender(Renderer r);
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		this.listener = this.gameObject;
	}
	
	@Override
	public void update(float dt) {
		for (Collider collider : Collider.colliders) {
			if (collider.gameObject != this.gameObject) {
				float distance = Vector2f.Distance(this.gameObject.transform.position, collider.gameObject.transform.position);

				if (distance < (this.dimension.x / 2) + collider.dimension.x / 2) {
				    // OVERLAPPING
					float overlap = 0.5f * (distance - (this.dimension.x / 2) - (collider.dimension.x / 2));
					
					Vector2f thisPosition = this.gameObject.transform.position;
					Vector2f colliderPosition = collider.gameObject.transform.position;
					
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
					Rigidbody thisRB = this.gameObject.getComponent(Rigidbody.class);
					Rigidbody colliderRB = collider.gameObject.getComponent(Rigidbody.class);
					
					float tanDP1 = Vector2f.Dot(thisRB.velocity, tangent);
					float tanDP2 = Vector2f.Dot(colliderRB.velocity, tangent);
					
					// Dot product Normal
					float dpNorm1 = thisRB.velocity.x * nx + thisRB.velocity.y * ny;
					float dpNorm2 = colliderRB.velocity.x * nx + colliderRB.velocity.y * ny;
					
					// Momentum ( With this the collider will interact with the mass of this )
					float m1 = (dpNorm1 * (thisRB.mass - colliderRB.mass) + 2.0f * colliderRB.mass * dpNorm2) / (thisRB.mass + colliderRB.mass);
					float m2 = (dpNorm2 * (colliderRB.mass - thisRB.mass) + 2.0f * thisRB.mass * dpNorm1) / (thisRB.mass + colliderRB.mass);
					
					thisRB.velocity.x 		= tangent.x * tanDP1 + nx * m1;
					thisRB.velocity.y 		= tangent.y * tanDP1 + ny * m1;
					colliderRB.velocity.x 	= tangent.x * tanDP2 + nx * m2;
					colliderRB.velocity.y 	= tangent.y * tanDP2 + ny * m2;
							
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
