package simple.attribute.movement;

public interface ISimpleMover {

	/**
	 * Update the X and Y position of this ISimpleMover object based on 
	 * the current X and Y velocities.
	 */
	public void move();
	
	/**
	 * Returns the current speed (scalar) of this ISimpleMover object.
	 * @return
	 */
	public double getSpeed();
	
	/**
	 * Sets the current speed (scalar) of this ISimpleMover object.
	 * @param speed
	 */
	public void setSpeed(double speed);
	
	/**
	 * Sets the X and Y velocities (vectors) of this ISimpleMover object.
	 * @param xVel
	 * @param yVel
	 */
	public void setVelocity(double xVel, double yVel);

	/**
	 * Sets the X velocity of this ISimpleMover object independently.
	 * @param xVel
	 */
	public void setXVelocity(double xVel);
	
	/**
	 * Sets the Y velocity of this ISimpleMover object independently.
	 * @param yVel
	 */
	public void setYVelocity(double yVel);
}
