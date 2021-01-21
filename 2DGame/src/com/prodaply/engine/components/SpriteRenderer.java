package com.prodaply.engine.components;

import com.prodaply.engine.Component;
import com.prodaply.engine.Renderer;
import com.prodaply.engine.gfx.Sprite;
import com.prodaply.engine.util.Vector2f;

public class SpriteRenderer extends Component {
	public Sprite sprite;

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float dt) {
		
	}

	@Override
	public void render(Renderer r) {
		Vector2f goPosition = this.gameObject.transform.position;
		Vector2f spriteCenter = new Vector2f((sprite.getW() / 2), (sprite.getH() / 2));
		r.drawSprite(sprite, new Vector2f(goPosition).sub(spriteCenter));
	}

}
