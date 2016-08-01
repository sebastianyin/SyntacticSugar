package com.syntacticsugar.vooga.gameplayer.objects;

import javafx.geometry.Point2D;

public interface IViewableObject {
	
	/**
	 * Returns the BoundingBox associated with this ViewableObject.
	 * @return the box
	 */
	public BoundingBox getBoundingBox();

	/**
	 * Returns the path for the image used to display this ViewableObject.
	 * @return the image path
	 */
	public String getPath();
	
	/**
	 * Return the raw coordinate of this ViewableObject.
	 * @return the point
	 */
	public Point2D getPoint();

	/**
	 * Explicitly move this ViewableObject to the desired point.
	 * @param point the point
	 */
	void setPoint(Point2D point);

	

}
