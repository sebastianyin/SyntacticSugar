package com.syntacticsugar.vooga.authoring.level.spawner;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

import com.syntacticsugar.vooga.authoring.fluidmotion.FadeTransitionWizard;
import com.syntacticsugar.vooga.authoring.fluidmotion.FluidGlassBall;
import com.syntacticsugar.vooga.authoring.level.IDataSelector;
import com.syntacticsugar.vooga.authoring.level.QueueBox;
import com.syntacticsugar.vooga.authoring.level.map.MapManager;
import com.syntacticsugar.vooga.authoring.level.map.MapView;
import com.syntacticsugar.vooga.authoring.library.IRefresher;
import com.syntacticsugar.vooga.authoring.objectediting.IVisualElement;
import com.syntacticsugar.vooga.authoring.tooltips.ObjectTooltip;
import com.syntacticsugar.vooga.xml.data.ObjectData;

import javafx.animation.Animation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;

public class SpawnerView implements IDataSelector<ObjectData>, IVisualElement, IRefresher, Observer {

	private ListView<Node> myQueuePane;
	private Queue<ObjectData> myQueue;
	private ObservableList<Node> myWave;
	private Node selectedItem;
	private HashMap<Node, ObjectData> myObjects;
	private MapManager myMapManager;

	public SpawnerView(MapManager mapManager) {
		myMapManager = mapManager;
		myObjects = new HashMap<Node, ObjectData>();
		myQueuePane = new ListView<Node>();
		myQueue = new LinkedList<ObjectData>();
		myWave = FXCollections.observableArrayList();
		myQueuePane.setItems(myWave);
		myQueuePane.setOrientation(Orientation.HORIZONTAL);
	}

	@Override
	public void addData(ObjectData obj) { 
		myQueue.add(obj);
		addToSpawnerView(obj);
	}

	private void addToSpawnerView(ObjectData obj) {
		Node temp = createQueueBoxFromObjData(obj);
		myWave.add(temp);
		temp.setOnMousePressed(e -> highlightSpawnTile(obj));
		temp.setOnMouseReleased(e -> deHighlightSpawnTile(obj));
		temp.setOnMouseClicked(e -> setSelectItem(temp));
		myObjects.put(temp, obj);
		Tooltip.install(temp, new ObjectTooltip(myObjects.get(temp)));
	}

	private void highlightSpawnTile(ObjectData obj) {
		double x = obj.getSpawnPoint().getX();
		double y = obj.getSpawnPoint().getY();
		System.out.println("( " + x + ", " + y + ")");
		int colIndex = (int) Math.round(myMapManager.getMapSize() * x / 1000);
		int rowIndex = (int) Math.round(myMapManager.getMapSize() * y / 1000);
		System.out.println("( " + rowIndex + ", " + colIndex + ")");
		myMapManager.getTileIconMap().get(myMapManager.getMapData().getTileData(colIndex, rowIndex))
				.setEffect(MapView.TILE_EFFECT);
	}

	private void deHighlightSpawnTile(ObjectData obj) {
		double x = obj.getSpawnPoint().getX();
		double y = obj.getSpawnPoint().getY();
		int colIndex = (int) Math.round(myMapManager.getMapSize() * x / 1000);
		int rowIndex = (int) Math.round(myMapManager.getMapSize() * y / 1000);
		myMapManager.getTileIconMap().get(myMapManager.getMapData().getTileData(colIndex, rowIndex)).setEffect(null);
	}

	private void setSelectItem(Node temp) {
		selectedItem = temp;

	}

	@Override
	public void removeSelectedData() {
		if (selectedItem != null) {
			Animation fade = FadeTransitionWizard.fadeOut(selectedItem, FluidGlassBall.getFadeDuration(),
					FluidGlassBall.getFadeOutOpacityStart(), FluidGlassBall.getFadeOutOpacityEnd(),
					FluidGlassBall.getFadeCycleCount());
			fade.setOnFinished(toExecuteOnFinished -> removeSelectedData_BAREBONE());
			fade.play();
		}
	}

	private void removeSelectedData_BAREBONE() {
		myQueue.remove(myObjects.get(selectedItem));
		myObjects.remove(selectedItem);
		myWave.remove(selectedItem);
	}

	@Override
	public ObjectData getSelectedData() {
		if (selectedItem != null) {
			return myObjects.get(selectedItem);
		}
		return null;

	}

	private Node createQueueBoxFromObjData(ObjectData obj) {
		QueueBox queueBox = new QueueBox(obj);
		return queueBox.getView();
	}

	@Override
	public Node getView() {
		return myQueuePane;
	}

	@Override
	public void clearData() {

	}

	@Override
	public Collection<ObjectData> getData() {
		return myObjects.values();
	}

	public Collection<ObjectData> getObjectQueue() {
		return myQueue;
	}

	@Override
	public void refresh() {
		myWave.clear();
		myQueue.forEach(i -> addToSpawnerView(i));
		myQueuePane.refresh();
	}

	@Override
	public void update(Observable o, Object arg) {
		refresh();

	}

}
