package com.prodaply.engine.components.physics.collider;

import java.util.List;

public interface CollisionEvent {
	void onCollision(Collider colliderr, Collider self);

	void onCollision(List<Collider> colliders, Collider self);
}
