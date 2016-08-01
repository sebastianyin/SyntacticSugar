package com.syntacticsugar.vooga.authoring.objectediting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.syntacticsugar.vooga.authoring.parameters.ParameterInputFactory;
import com.syntacticsugar.vooga.gameplayer.event.ICollisionEvent;
import com.syntacticsugar.vooga.gameplayer.objects.GameObjectType;
import com.syntacticsugar.vooga.util.ResourceManager;
import com.syntacticsugar.vooga.util.gui.factory.AlertBoxFactory;
import com.syntacticsugar.vooga.util.reflection.Reflection;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CollisionMakerWizard {
	
	/*Similar to the AttributeMakerWizard, this is the class which creates a new Stage whenever a user selects the option
	to add a new collision pair. Similar to AttributeMakerWizard, this class utilize reflection via a ParameterInputFactory.
	At first, a new stage is generated and populated with Hbox/VBoxes, so that the user can view all possible types of objects
	that this specific object can collide with in a listView (on the left) and then see all the possible event type outcomes 
	 of this collision as a listView (on the right). Once both an object type and eventType have been selected and 'OK' is 
	clicked, a call to the ParameterInputFactory is made, which generates a new stage populated with all necessary outcomes
	of an event type (i.e. HealthChangeEvent generates textField to set healthChangeAmount). Once, all relevant textFields
	have been set and confirmed, collision pair is added to the CollisionViewer's display pane.*/
	
	private Scene myScene;
	private Stage myStage;
	private Map<GameObjectType, Collection<ICollisionEvent>> myCollisions;
	private ICollisionEvent collisionEventToAdd;
	private GameObjectType selectedCollideObjType;
	private String selectedCollisionEvent;
	private final double SCENE_DIMENSION = 400;

	public CollisionMakerWizard(GameObjectType type, Map<GameObjectType, Collection<ICollisionEvent>> collisions) {
		myCollisions = collisions;
		myStage = new Stage();
		setType(type);
	}

	public void setType(GameObjectType type) {
		myScene = new Scene(buildCollisions(type), SCENE_DIMENSION, SCENE_DIMENSION);
		myStage.setScene(myScene);
		myStage.initModality(Modality.APPLICATION_MODAL);
		myStage.showAndWait();
	}

	private VBox buildCollisions(GameObjectType type) {
		VBox ret = new VBox();
		HBox listViewsBox = new HBox();
		listViewsBox.getChildren().addAll(createCollideObjTypeListView(), createCollideEventListView());
		ret.getChildren().addAll(listViewsBox, createAddCollisionBtn());
		ret.setSpacing(5);
		ret.setPrefSize(400, 150);
		return ret;
	}

	private ListView<GameObjectType> createCollideObjTypeListView() {
		ListView<GameObjectType> types = new ListView<GameObjectType>();
		types.getItems().addAll(GameObjectType.values());
		types.setOnMouseClicked(e -> {
			selectedCollideObjType = types.getSelectionModel().getSelectedItem();
		});
		return types;
	}

	private ListView<String> createCollideEventListView() {
		ListView<String> events = new ListView<String>();
		events.getItems().addAll(ResourceManager.getString("game_events").split(","));
		events.setOnMouseClicked(e -> {
			selectedCollisionEvent = events.getSelectionModel().getSelectedItem();
		});
		return events;
	}

	private Button createAddCollisionBtn() {
		Button addCollision = new Button(ResourceManager.getString("add_collision"));
		addCollision.setOnMouseClicked(e -> {
			createCollision();
		});
		return addCollision;
	}

	private void createCollision() {
		if (selectedCollideObjType == null) {
			AlertBoxFactory.createObject(ResourceManager.getString("select_gameobjecttype"));
			return;
		}
		if (selectedCollisionEvent == null) {
			AlertBoxFactory.createObject(ResourceManager.getString("select_gameeventtype"));
			return;
		}

		if (myCollisions.containsKey(selectedCollideObjType)) {
			for (ICollisionEvent i : myCollisions.get(selectedCollideObjType)) {
				if (i.getClass().getSimpleName().equals(selectedCollisionEvent)) {
					AlertBoxFactory.createObject(String.format("Cannot add more than one %s to collide type %s",
							selectedCollisionEvent, selectedCollideObjType));
					return;
				}
			}
			addCollideEventToExistingKey();
		} else {
			addCollideEventToNonExistingKey();
		}

	}

	private void addCollideEventToExistingKey() {
		String className = ResourceManager.getString(String.format("%s_%s", selectedCollisionEvent, "name"));
		collisionEventToAdd = (ICollisionEvent) Reflection.createInstance(className);
		ParameterInputFactory.createInputFields(collisionEventToAdd);
		myCollisions.get(selectedCollideObjType).add(collisionEventToAdd);
	}

	private void addCollideEventToNonExistingKey() {
		String className = ResourceManager.getString(String.format("%s_%s", selectedCollisionEvent, "name"));
		Collection<ICollisionEvent> collideEvents = new ArrayList<ICollisionEvent>();
		ICollisionEvent eventToAdd = (ICollisionEvent) Reflection.createInstance(className);
		ParameterInputFactory.createInputFields(eventToAdd);
		collideEvents.add(eventToAdd);
		myCollisions.put(selectedCollideObjType, collideEvents);
	}
}
