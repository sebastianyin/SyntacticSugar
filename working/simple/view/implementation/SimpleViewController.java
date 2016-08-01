package simple.view.implementation;

import java.util.HashMap;
import java.util.Map;

import simple.obj.IViewableObject;

public class SimpleViewController {

	private Map<IViewableObject, ObjectView> myViewMap;
	private GameView myGameView;

	public SimpleViewController(double size) {
		myViewMap = new HashMap<IViewableObject, ObjectView>();
		myGameView = new GameView(size);
	}

	public void addViewObject(IViewableObject obj) {
		ObjectView newView = new ObjectView(obj.getPath(), obj.getBoundingBox(), myGameView);
		myViewMap.put(obj, newView);
	}

	public GameView getGameView() {
		return myGameView;
	}
	
	public void removeViewObject(IViewableObject obj){
		ObjectView object = myViewMap.get(obj);
		myGameView.getChildren().remove(object.getImageView());
		myViewMap.remove(obj);
	}

}
