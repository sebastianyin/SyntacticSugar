package com.syntacticsugar.vooga.authoring.objectediting;

import java.util.Collection;
import com.syntacticsugar.vooga.authoring.parameters.ParameterInputFactory;
import com.syntacticsugar.vooga.gameplayer.attribute.IAttribute;
import com.syntacticsugar.vooga.gameplayer.objects.GameObjectType;
import com.syntacticsugar.vooga.util.ResourceManager;
import com.syntacticsugar.vooga.util.gui.factory.AlertBoxFactory;
import com.syntacticsugar.vooga.util.reflection.Reflection;
import com.syntacticsugar.vooga.util.reflection.ReflectionException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AttributeMakerWizard {

	
	/*This class is used to generate a new stage and window, which is opened when the user selects the option to add an attribute
	via the '+' button in the attributeViewer subsection of the objectEditor display.This works in tandem with the 
	ParameterInputFactory in order to generate the appropriate labels, TextFields, and buttons for each specific attribute,
	and then populates the newly generated stage/scene with these components. Whenever new attribute created, it is added to the
	list of displayed attributes within the AttributeViewer.*/
	
	private Stage myStage;
	private Scene myScene;
	private Collection<IAttribute> myAttributes;
	private IAttribute attributeToAdd;
	private String selectedAttribute;
	private final double SCENE_DIMENSION = 300;	


	public AttributeMakerWizard(GameObjectType type, Collection<IAttribute> attributes) {
		myAttributes = attributes;
		myStage = new Stage();
		setType(type);
	}

	public void setType(GameObjectType type) {
		if (buildAttributes(type).getChildren().size() == 0) 
			return;
		myScene = new Scene(buildAttributes(type), SCENE_DIMENSION, SCENE_DIMENSION);
		myStage.setScene(myScene);
		myStage.initModality(Modality.APPLICATION_MODAL);
		myStage.showAndWait();
	}

	private VBox buildAttributes(GameObjectType type) {
		VBox ret = new VBox();
		ListView<String> attributeView = createAttributeListView(type);
		
		if (attributeView.getItems().size() == 0) 
			return new VBox(); 
		
		Button addAttribute = createAddAttributeBtn();
		ret.getChildren().addAll(attributeView, addAttribute);
		ret.setAlignment(Pos.CENTER_LEFT);
		ret.setSpacing(10);
		ret.setPrefSize(400, 100);
		return ret;
	}

	private ListView<String> createAttributeListView(GameObjectType type) {
		ListView<String> attributeView = new ListView<String>();
		if (type == null) return new ListView<String>();
		String[] attributeNames = ResourceManager
				.getString(String.format("%s%s", type.name().toLowerCase(), "_attributes")).split(",");
		attributeView.getItems().addAll(attributeNames);
		attributeView.setOnMouseClicked(e -> {
			selectedAttribute = attributeView.getSelectionModel().getSelectedItem();
		});
		return attributeView;
	}

	private Button createAddAttributeBtn() {
		Button addAttribute = new Button(ResourceManager.getString("add_attribute"));
		addAttribute.setOnMouseClicked(e -> {
			createAttribute();
		});
		return addAttribute;
	}

	private void createAttribute() {
		if (selectedAttribute == null) {
			AlertBoxFactory.createObject(ResourceManager.getString("select_attribute"));
			return;
		}
		for (IAttribute i : myAttributes) {
			if (selectedAttribute.equals(i.getClass().getSimpleName())) {
				AlertBoxFactory.createObject(String.format("Cannot add more than one %s", selectedAttribute));
				return;
			}
		}
		addAttributeToList();
	}

	private void addAttributeToList() {
			String className = ResourceManager.getString(String.format("%s_%s", selectedAttribute, "name"));
			try {
				attributeToAdd = (IAttribute) Reflection.createInstance(className);
				ParameterInputFactory.createInputFields(attributeToAdd);
				myAttributes.add(attributeToAdd);
			}
			catch (ReflectionException ex) {
				System.out.println(className);
			}
	}
	
}
