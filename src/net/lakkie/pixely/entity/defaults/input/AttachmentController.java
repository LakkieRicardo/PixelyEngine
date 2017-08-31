package net.lakkie.pixely.entity.defaults.input;

import net.lakkie.pixely.context.PixelyContext;
import net.lakkie.pixely.entity.EntityAttachment;
import net.lakkie.pixely.input.InputManager;
import net.lakkie.pixely.math.Vector2i;
import net.lakkie.pixely.utils.MovementInputLayout;

public class AttachmentController extends EntityAttachment {

	private int speed;
	private MovementInputLayout layout;
	
	public AttachmentController(int speed, MovementInputLayout layout) {
		this.speed = speed;
		this.layout = layout;
	}
	
	public String getName() {
		return "controller";
	}
	
	public void update(PixelyContext ctx) {
		if (InputManager.isKeyPressed(layout.getUpKey())) {
			this.getEntity().translate(new Vector2i(0, -speed));
		}

		if (InputManager.isKeyPressed(layout.getLeftKey())) {
			this.getEntity().translate(new Vector2i(-speed, 0));
		}

		if (InputManager.isKeyPressed(layout.getDownKey())) {
			this.getEntity().translate(new Vector2i(0, speed));
		}

		if (InputManager.isKeyPressed(layout.getRightKey())) {
			this.getEntity().translate(new Vector2i(speed, 0));
		}
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public MovementInputLayout getLayout() {
		return layout;
	}

}
