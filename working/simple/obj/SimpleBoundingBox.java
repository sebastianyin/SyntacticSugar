package simple.obj;

import java.util.Observable;

import javafx.geometry.Point2D;

public class SimpleBoundingBox extends Observable implements ISimpleBoundingBox {

	private Point2D myPoint;
	private double myWidth;
	private double myHeight;
	
	public SimpleBoundingBox(Point2D point, double width, double height) {
		myPoint = point;
		myWidth = width;
		myHeight = height;
	}
	
	@Override
	public Point2D getPoint() {
		return new Point2D(myPoint.getX(), myPoint.getY());
	}

	@Override
	public double getWidth() {
		return new Double(myWidth);
	}

	@Override
	public double getHeight() {
		return new Double(myHeight);
	}

	@Override
	public void setPoint(Point2D point) {
		myPoint = point;
		triggerViewUpdate();
	}

	@Override
	public void setWidth(double width) {
		myWidth = width;
		triggerViewUpdate();
	}

	@Override
	public void setHeight(double height) {
		myHeight = height;
		triggerViewUpdate();
	}
	
	private void triggerViewUpdate() {
		setChanged();
		notifyObservers();
	}

}
