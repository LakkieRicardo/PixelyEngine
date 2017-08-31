package net.lakkie.pixely.entity.defaults.physics;

import net.lakkie.pixely.context.PixelyContext;
import net.lakkie.pixely.entity.EntityAttachment;
import net.lakkie.pixely.math.Mathif;
import net.lakkie.pixely.math.Vector2f;
import net.lakkie.pixely.math.Vector2i;
import net.lakkie.pixely.utils.PhysicsProperties;

/**
 * 
 * A physics body that uses precise velocity(floating point).
 * 
 * @author Diego
 *
 */
public class AttachmentStaticRigidBody extends EntityAttachment {

	public Vector2f velocity = new Vector2f();
	public Vector2f approximatePosition = new Vector2f();

	public void start(PixelyContext ctx) {
		this.approximatePosition = Mathif.vecConvertToFloat(this.getEntity().pos);
	}

	public String getName() {
		return "static-rigidbody";
	}

	public void update(PixelyContext ctx) {
		velocity = velocity.add(PhysicsProperties.gravity);
	}

	public void addForce(Vector2f force) {
		this.velocity = this.velocity.add(force);
	}

	public void postUpdate(PixelyContext ctx) {
		removeVelocityMovement();
		updateVelocityMovement();
	}
	
	public Vector2i roundVelocity() {
		return new Vector2i(Mathif.halfRoundInt(this.velocity.x), Mathif.halfRoundInt(this.velocity.y));
	}
	
	public Vector2i roundPosition() {
		return new Vector2i(Mathif.halfRoundInt(this.approximatePosition.x), Mathif.halfRoundInt(this.approximatePosition.y));
	}

	/**
	 * Removes the default entity velocity
	 */
	private void removeVelocityMovement() {
		getEntity().pos = getEntity().pos.subtract(getEntity().velocity);
	}

	/**
	 * 
	 */
	private void updateVelocityMovement() {
		this.getEntity().pos = roundPosition();
	}

}
