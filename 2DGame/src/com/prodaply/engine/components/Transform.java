package com.prodaply.engine.components;

import com.prodaply.engine.Component;
import com.prodaply.engine.Renderer;
import com.prodaply.engine.util.Vector2f;

public class Transform extends Component {
	public Vector2f position;
	public Vector2f rotation;
	public Transform parent;

	@Override
	public void start() {
		position = new Vector2f();
		rotation = new Vector2f();
		parent = null;
	}
	
	@Override
	public void update(float dt) {
		
	}

	@Override
	public void render(Renderer r) {}

}
