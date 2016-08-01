package simple.view.implementation;

import javafx.scene.layout.Pane;

public class GameView extends Pane{
	
	private double mySize;
	
	public GameView(double size){
		mySize = size;
		this.setWidth(size);
		this.setHeight(size);
	}
	
	/**
	 * TODO: talk to backend to figure out this scaling situation
	 */
	
	public double getScalingFactor(){
		return (1.0/1000)*mySize;
	}


}
