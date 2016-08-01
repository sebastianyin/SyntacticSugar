package com.syntacticsugar.vooga.gameplayer.objects;

import com.syntacticsugar.vooga.gameplayer.attribute.movement.Direction;

import javafx.geometry.Point2D;

public interface IBoundingBox {

	/**
	 * Returns the point of the top-left corner of the bounding box.
	 * @return the point
	 */
	public Point2D getPoint();
	
	/**
	 * Returns the direction of the bounding box.
	 * @return the direction
	 */
	public Direction getDirection();
	
	/**
	 * Returns the rotation of the bounding box.
	 * @return the rotation
	 */
	public double getRotate();
	
	/**
	 * Returns the width of the bounding box.
	 * @return the width
	 */
	public double getWidth();
	
	/**
	 * Returns the height of the bounding box.
	 * @return the height
	 */
	public double getHeight();
	
	/**
	 * Sets the point of the top-left corner of the bounding box.
	 * @param point the point
	 */
	public void setPoint(Point2D point);
	
	/**
	 * Sets the direction of the bounding box.
	 * @param dir the direction
	 */
	public void setDirection(Direction dir);
	
	/**
	 * Sets the rotate of the bounding box.
	 * @param rotation the rotation
	 */
	public void setRotate(double rotation);
	
	/**
	 * Sets the width of the bounding box.
	 * @param width the width
	 */
	public void setWidth(double width);
	
	/**
	 * Sets the height of the bounding box.
	 * @param height the height
	 */
	public void setHeight(double height);

	
}
