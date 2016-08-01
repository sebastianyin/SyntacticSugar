package com.syntacticsugar.vooga.authoring.objectediting;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.syntacticsugar.vooga.gameplayer.event.ICollisionEvent;
import com.syntacticsugar.vooga.gameplayer.objects.GameObjectType;
import com.syntacticsugar.vooga.util.ResourceManager;
import com.syntacticsugar.vooga.util.gui.factory.AlertBoxFactory;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CollisionViewer
		implements IVisualElement, IListViewDisplay, IDataDisplay<Map<GameObjectType, Collection<ICollisionEvent>>> {
	
	/*Utilized by the objectEditor class to view/store all the collision pairs added for an object currently being edited.
	This is the standard UI display that we see within the ObjectEditor, which gives us the option of both creating new collision pairs
	and viewing the object's existing collision pairs. Once a user creates a new attribute via the CollisionMakerWizard,
	it is displayed in the CollisionViewer's collision display pane.*/
	
	private VBox myView;
	private ListView<HBox> myListView;
	private ObservableList<HBox> myCollisionDisplays;
	private ObservableMap<GameObjectType, Collection<ICollisionEvent>> myCollisions;
	private Map<GameObjectType, HBox> myDisplayMap;


	public CollisionViewer() {
		myView = new VBox();
		myDisplayMap = new HashMap<>();
		myCollisionDisplays = FXCollections.observableArrayList();
		myCollisions = FXCollections.observableHashMap();
		myCollisions.addListener(new MapChangeListener<GameObjectType, Collection<ICollisionEvent>>() {
			@Override
			public void onChanged(Change<? extends GameObjectType, ? extends Collection<ICollisionEvent>> change) {
				if (change.wasAdded()) {
					HBox newListElement = makeHBoxDisplay(change.getKey(), change.getValueAdded());
					myDisplayMap.put(change.getKey(), newListElement);
					myCollisionDisplays.add(newListElement);
				}
				if (change.wasRemoved()) {
					myCollisionDisplays.remove(myDisplayMap.remove(change.getKey()));
				}
			}
		});
		myListView = new ListView<HBox>(myCollisionDisplays);
		myView.getChildren().addAll(myListView);
	}

	private HBox makeHBoxDisplay(GameObjectType type, Collection<ICollisionEvent> events) {
		HBox element = new HBox();
		element.setAlignment(Pos.BASELINE_CENTER);
		Text text = new Text(type.toString());
		ComboBox<String> dropdown = makeEventDropdown(events);
		dropdown.getStyleClass().add("combobox");
		element.setSpacing(100);
		element.getChildren().add(text);
		element.getChildren().add(dropdown);
		return element;
	}

	private ComboBox<String> makeEventDropdown(Collection<ICollisionEvent> eventList) {
		ComboBox<String> eventDropdown = new ComboBox<String>();
		eventDropdown.getStyleClass().add("combobox");
		for (ICollisionEvent event : eventList) {
			eventDropdown.getItems().add(ResourceManager.getString(event.getClass().getSimpleName()));
		}
		return eventDropdown;
	}

	public Node getView() {
		return this.myView;
	}

	@Override
	public void displayData(Map<GameObjectType, Collection<ICollisionEvent>> data) {
		myCollisions.clear();
		myCollisions.putAll(data);
		// for (GameObjectType type: data.keySet()){
		// Collection<ICollisionEvent> collisions = new
		// ArrayList<ICollisionEvent>();
		// for (ICollisionEvent collision: data.get(type)){
		// collisions.add(collision);
		// }
		// myCollisions.put(type, collisions);
		// }
	}

	@Override
	public void clearDisplay() {
		myCollisions.clear();
		myListView.getItems().clear();
	}

	@Override
	public Map<GameObjectType, Collection<ICollisionEvent>> getData() {
		return myCollisions;
	}

	@Override
	public void removeSelectedItem() {
		if (!myCollisionDisplays.isEmpty()) {
			int selectedIdx = myListView.getSelectionModel().getSelectedIndex();
			if (selectedIdx == -1) {
				AlertBoxFactory.createObject(ResourceManager.getString("invalid-selection"));
				return;
			}
			String toDeleteStr = ((Text) myListView.getSelectionModel().getSelectedItem().getChildren().get(0))
					.getText().toUpperCase();
			myCollisions.remove(GameObjectType.valueOf(toDeleteStr));
		} else {
			AlertBoxFactory.createObject(ResourceManager.getString("empty-remove"));
		}
	}

}