package simple.obj;

import javafx.geometry.Point2D;

public interface ISimpleBoundingBox {

	public Point2D getPoint();
	
	public double getWidth();
	
	public double getHeight();
	
	public void setPoint(Point2D point);
	
	public void setWidth(double width);
	
	public void setHeight(double height);
	
}
