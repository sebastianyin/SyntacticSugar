package simple.view.implementation;

import javafx.scene.image.ImageView;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.image.Image;
import simple.obj.SimpleBoundingBox;

public class ObjectView implements Observer{
	

	private ImageView myImageView;
	private double scalingFactor;
	
	public ObjectView(String path, SimpleBoundingBox box , GameView myGameView) {
		this.myImageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(path)));
		scalingFactor = myGameView.getScalingFactor();
		applyTransform(box);
		myImageView.setFitHeight(scalingFactor*box.getHeight());
		myImageView.setFitWidth(scalingFactor*box.getWidth());
		myGameView.getChildren().add(myImageView);
		box.addObserver(this);
	}

	@Override
	public void update(Observable obs, Object arg1) {
		SimpleBoundingBox box = (SimpleBoundingBox) obs;
		// TODO Use the myTransform to scale the properties of 
		// the bounding box passed as box
		applyTransform(box);
	}
	
	private void applyTransform(SimpleBoundingBox box) {
		double xCoordinate = box.getPoint().getX()*scalingFactor;
		double yCoordinate = box.getPoint().getY()*scalingFactor;
		myImageView.relocate(xCoordinate, yCoordinate);
	}
	
	public ImageView getImageView(){
		return myImageView;
	}

}
