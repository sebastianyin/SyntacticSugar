package simple.attribute.movement.algs;

import simple.attribute.movement.ISimpleMover;

public class MoveLeftCardinal implements IMovementSetter {

	@Override
	public void setMovement(ISimpleMover mover) {
		double speed = mover.getSpeed();
		mover.setVelocity(-1.0*speed, 0);
	}

}
