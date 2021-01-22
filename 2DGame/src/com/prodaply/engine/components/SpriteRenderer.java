package com.prodaply.engine.components;

import com.prodaply.engine.Component;
import com.prodaply.engine.GameController;
import com.prodaply.engine.Renderer;
import com.prodaply.engine.components.physics.Rigidbody;
import com.prodaply.engine.gfx.Sprite;
import com.prodaply.engine.util.Sign;
import com.prodaply.engine.util.Vector2f;

public class SpriteRenderer extends Component {
	public Sprite sprite;
	public boolean canBeRender = true;
	
	public SpriteRenderer(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public SpriteRenderer() {}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float dt) {
		canBeRender = true;
		// Out of bounds pixels from window
		if (this.gameObject.transform.position.x < -sprite.getW()) canBeRender = false;
		if (this.gameObject.transform.position.x > GameController.getWidth() + sprite.getW()) canBeRender = false;
		if (this.gameObject.transform.position.y < -sprite.getH()) canBeRender = false;
		if (this.gameObject.transform.position.y > GameController.getHeight() + sprite.getH()) canBeRender = false;
		
		if (this.gameObject.getComponent(Rigidbody.class) != null) {
			this.sprite.setDirection(Sign.of(this.gameObject.getComponent(Rigidbody.class).velocity.getDirection()));
		}
		
		this.sprite.setZIndex((int)this.gameObject.transform.position.y);
	}

	@Override
	public void render(Renderer r) {
		Vector2f goPosition = this.gameObject.transform.position;
		Vector2f spriteCenter = new Vector2f((sprite.getW() / 2), (sprite.getH() / 2));
		if (canBeRender) {
			r.drawSprite(sprite, new Vector2f(goPosition).sub(spriteCenter));
		}
	}

}
