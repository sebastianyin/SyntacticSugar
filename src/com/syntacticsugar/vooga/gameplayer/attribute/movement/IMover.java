package com.syntacticsugar.vooga.gameplayer.attribute.movement;

import com.syntacticsugar.vooga.gameplayer.universe.IGameUniverse;

public interface IMover {

	/**
	 * Update the X and Y position of this ISimpleMover object based on 
	 * the current X and Y velocities.
	 */
	public void move(IGameUniverse universe);
	
	/**
	 * Returns the current speed (scalar) of this ISimpleMover object.
	 * @return the speed
	 */
	public double getSpeed();
	
	/**
	 * Sets the current speed (scalar) of this ISimpleMover object.
	 * @param speed the speed to be set
	 */
	public void setSpeed(double speed);
	
	/**
	 * Sets the velocity of this ISimpleMover object equal to the current
	 * speed of the object in the specified direction.
	 * @param Dir the direction of the velocity
	 */
	public void setVelocity(Direction Dir);
	
	/**
	 * Set the direction of this object.
	 * @param dir the direction
	 */
	public void setDirection(Direction dir);
	
	/**
	 * Get the direction of this object.
	 * 
	 * @return the direction
	 */
	public Direction getDirection();
	
	/**
	 * Sets the X and Y velocity of this ISimpleMover object equal to zero.
	 */
	void resetVelocity();
	
	/**
	 * Updates this object's position.
	 * @param universe the universe
	 */
	public void updateSelf(IGameUniverse universe);
}
