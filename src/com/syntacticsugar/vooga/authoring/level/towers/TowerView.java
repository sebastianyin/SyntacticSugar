package com.syntacticsugar.vooga.authoring.level.towers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.syntacticsugar.vooga.authoring.fluidmotion.FadeTransitionWizard;
import com.syntacticsugar.vooga.authoring.fluidmotion.FluidGlassBall;
import com.syntacticsugar.vooga.authoring.fluidmotion.ParallelTransitionWizard;
import com.syntacticsugar.vooga.authoring.level.IDataSelector;
import com.syntacticsugar.vooga.authoring.level.QueueBox;
import com.syntacticsugar.vooga.authoring.library.IRefresher;
import com.syntacticsugar.vooga.authoring.objectediting.IVisualElement;
import com.syntacticsugar.vooga.authoring.tooltips.ObjectTooltip;
import com.syntacticsugar.vooga.util.ResourceManager;
import com.syntacticsugar.vooga.util.gui.factory.GUIFactory;
import com.syntacticsugar.vooga.xml.data.TowerData;

import javafx.animation.Animation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;

public class TowerView implements IVisualElement, IDataSelector<TowerData>, IRefresher, Observer {

	private ListView<Node> myTowerView;
	private List<TowerData> myTowers;
	private ObservableList<Node> myObservable;
	private HashMap<Node, TowerData> myMap;
	private Object selectedItem;
	private TitledPane myViewPane;

	public TowerView() {
		myTowerView = new ListView<Node>();
		myTowers = new ArrayList<>();
		myObservable = FXCollections.observableArrayList();
		myTowerView.setItems(myObservable);
		myTowerView.setOrientation(Orientation.VERTICAL);
		myTowerView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		myMap = new HashMap<Node, TowerData>();

		myViewPane = GUIFactory.buildTitledPane(ResourceManager.getString("available_towers"), myTowerView);
	}

	@Override
	public void addData(TowerData data) {

		myTowers.add(data);
		Node newTower = createQueueBoxFromObjData(data);
		newTower.setOnMouseClicked(e -> selectedItem = newTower);
		myMap.put(newTower, data);
		Tooltip.install(newTower, new ObjectTooltip(myMap.get(newTower)));
		myObservable.add(newTower);
	}

	// Used when we initiate a save game in the authoring environment
	@Override
	public Collection<TowerData> getData() {
		ArrayList<TowerData> list = new ArrayList<TowerData>();
		for (Node n : myObservable) {
			list.add(myMap.get(n));
		}
		return list;
	}

	@Override
	public TowerData getSelectedData() {
		return myMap.get(myTowerView.getSelectionModel().getSelectedItem());
	}

	@Override
	public void removeSelectedData() {
		if (selectedItem != null) {
			Animation fade = FadeTransitionWizard.fadeOut((Node) selectedItem, FluidGlassBall.getFadeDuration(),
					FluidGlassBall.getFadeOutOpacityStart(), FluidGlassBall.getFadeOutOpacityEnd(),
					FluidGlassBall.getFadeCycleCount());
			fade.setOnFinished(toExecuteOnFinished -> removeObjectFromList_BAREBONE());
			fade.play();
		}
	}

	@Override
	public void clearData() {
		Animation towerClear = ParallelTransitionWizard.parallelize(convertNodeListToAnimList());
		towerClear.setOnFinished(toExecuteOnFinished -> clearAll_BAREBONE());
		towerClear.play();
	}

	@Override
	public Node getView() {
		return myViewPane;
	}

	// *********************************************//

	public Node createQueueBoxFromObjData(TowerData obj) {
		QueueBox queueBox = new QueueBox(obj);
		return queueBox.getView();
	}

	private void removeObjectFromList_BAREBONE() {
		myTowers.remove(myMap.get(selectedItem));
		myObservable.remove(selectedItem);
		myMap.remove(selectedItem);
	}

	private List<Animation> convertNodeListToAnimList() {
		List<Animation> animationList = new ArrayList<Animation>();
		for (Node node : myObservable) {
			Animation nodeAnim = FadeTransitionWizard.fadeOut(node, FluidGlassBall.getFadeDuration(),
					FluidGlassBall.getFadeOutOpacityStart(), FluidGlassBall.getFadeOutOpacityEnd(),
					FluidGlassBall.getFadeCycleCount());
			animationList.add(nodeAnim);
		}
		return animationList;
	}

	private void clearAll_BAREBONE() {
		myObservable.clear();
		myMap.clear();
	}

	@Override
	public void refresh() {
		myObservable.clear();
		myTowers.forEach(e -> {
			Node newTower = createQueueBoxFromObjData(e);
			newTower.setOnMouseClicked(a -> selectedItem = newTower);
			myMap.put(newTower, e);
			myObservable.add(newTower);
		});
		myTowerView.refresh();
	}

	@Override
	public void update(Observable o, Object arg) {
		refresh();
	}

}
