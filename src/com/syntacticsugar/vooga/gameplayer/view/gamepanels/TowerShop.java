package com.syntacticsugar.vooga.gameplayer.view.gamepanels;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import com.syntacticsugar.vooga.authoring.icon.Icon;
import com.syntacticsugar.vooga.gameplayer.event.implementations.MoneyChangeEvent;
import com.syntacticsugar.vooga.gameplayer.event.implementations.ObjectSpawnEvent;
import com.syntacticsugar.vooga.gameplayer.objects.towers.Tower;
import com.syntacticsugar.vooga.gameplayer.universe.IEventPoster;
import com.syntacticsugar.vooga.gameplayer.universe.IUniverseView;
import com.syntacticsugar.vooga.gameplayer.universe.money.IMoney;
import com.syntacticsugar.vooga.gameplayer.view.implementation.MoneyInfo;
import com.syntacticsugar.vooga.gameplayer.view.implementation.TowerPlaceInfo;
import com.syntacticsugar.vooga.util.properties.PropertiesManager;
import com.syntacticsugar.vooga.xml.data.TowerData;

import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TowerShop extends Observable implements Observer {

	private VBox myContent;
	private ScrollPane towerList;
	private TowerData currentSelection;
	private Collection<Icon> myTowerIcons;
	private MoneyInfo myMoneyInfo;
	private int availableCash;
	private IEventPoster myPoster;
	private PropertiesManager myPropertiesManager;

	public TowerShop() {
		myPropertiesManager = new PropertiesManager("com/syntacticsugar/vooga/resources/View");
		myContent = new VBox(20);
		myContent.getStyleClass().add("vbox");
		myContent.setAlignment(Pos.TOP_CENTER);
		
		Label title = new Label("Tower Shop");
		title.setId("tower-shop");
		title.setAlignment(Pos.CENTER);
		
		myMoneyInfo = new MoneyInfo(0, "money");
		myMoneyInfo.setAlignment(Pos.CENTER);
		myContent.getChildren().addAll(title, myMoneyInfo);
		towerList = new ScrollPane(myContent);
	}

	public void initialize(Collection<TowerData> availableTowers, IUniverseView universe, IEventPoster poster) {
		initializeTowerIcons(availableTowers);
		//myUniverse = universe;
		myPoster = poster;
		availableCash = universe.getMoney().getMoney();
		myMoneyInfo.changeMoney(availableCash);
	}

	private void initializeTowerIcons(Collection<TowerData> availableTowers) {
		myTowerIcons = new ArrayList<>();
		for (TowerData towerObject : availableTowers) {
			Icon tower = new Icon(towerObject.getImagePath());
			towerObject.setCost(-100);
			double towerSize = myPropertiesManager.getDoubleProperty("TowerSize");
			tower.setPrefSize(towerSize, towerSize);
			tower.setOnMouseClicked(event -> selectedTower(tower, towerObject));
			tower.setOnMouseEntered(event -> checkIfCanBuy(tower, towerObject.getCost()));
			tower.setOnMouseExited(event -> deselect(tower));
			addTowerInfo(tower, towerObject);
			myTowerIcons.add(tower);
		}
	}

	private void deselect(Icon tower) {
		if(currentSelection == null){
			tower.setStyle("-fx-opacity: 1");
		}
	}

	private void checkIfCanBuy(Icon tower, int price) {
		if(currentSelection == null){
			if( (-1)*price <= availableCash){
				tower.setStyle("-fx-opacity: 0.5");
			}
		}
	}

	private void addTowerInfo(Icon towerIcon, TowerData towerData) {
		HBox towerInfo = new HBox(10);
		VBox towerValues = new VBox(5);
		towerValues.getChildren().addAll(new Label(towerData.getName()), 
				new MoneyInfo((-1)*towerData.getCost(), "cost"));
		towerInfo.getChildren().addAll(towerIcon, towerValues);
		myContent.getChildren().add(towerInfo);
	}

	private void selectedTower(Icon tower, TowerData towerObject) {
		if((-1)*towerObject.getCost() <= availableCash){
			if (towerObject.equals(currentSelection)) {
				tower.setStyle("-fx-background-color: transparent");
				setChanged();
				notifyObservers(false);
				currentSelection = null;
			} else {
				for (Icon towerIcon : myTowerIcons) {
					towerIcon.setStyle("-fx-background-color: transparent");
				}
				tower.setStyle("-fx-background-color: red");
				currentSelection = towerObject;
				setChanged();
				notifyObservers(true);
			}
		}else{
			//currentSelection = null;
			setChanged();
			notifyObservers(false);
		}
	}

	public ScrollPane getContent() {
		return towerList;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof IMoney){
			int money = ((IMoney) arg1).getMoney();
			availableCash = money;
			myMoneyInfo.changeMoney(availableCash);
		} else {
			TowerPlaceInfo data = (TowerPlaceInfo) arg1;
			Point2D coordinates = data.getCoordinates();
			currentSelection.setSpawnPoint(coordinates.getX(), coordinates.getY());
			currentSelection.setDirection(data.getDirection());
			currentSelection.setHeight(data.getHeight());
			currentSelection.setWidth(data.getWidth());
			
			ObjectSpawnEvent event = new ObjectSpawnEvent(new Tower(currentSelection));
			myPoster.postEvent(event);

			MoneyChangeEvent ev2 = new MoneyChangeEvent(currentSelection.getCost());
			myPoster.postEvent(ev2);
		}
	}
}
