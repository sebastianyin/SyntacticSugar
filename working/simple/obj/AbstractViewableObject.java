package simple.obj;

import javafx.geometry.Point2D;

public abstract class AbstractViewableObject implements IViewableObject{
	
	private SimpleBoundingBox myBoundingBox;
	private String myPath;
	
	public AbstractViewableObject(Point2D point, double width, double height, String path){
		myBoundingBox = new SimpleBoundingBox(point, width, height);
		myPath = path;
	}
	
	@Override
	public SimpleBoundingBox getBoundingBox(){
		return myBoundingBox;
	}
	
	@Override
	public String getPath(){
		return myPath;
	}

	
}
