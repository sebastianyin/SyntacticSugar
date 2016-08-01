package com.syntacticsugar.vooga.authoring.objectediting;

import java.io.File;
import java.util.Collections;
import java.util.Observable;

import com.syntacticsugar.vooga.authoring.dragdrop.DragDropManager;
import com.syntacticsugar.vooga.authoring.fluidmotion.mixandmatchmotion.PulsingFadeWizard;
import com.syntacticsugar.vooga.authoring.icon.Icon;
import com.syntacticsugar.vooga.authoring.library.IRefresher;
import com.syntacticsugar.vooga.gameplayer.objects.GameObjectType;
import com.syntacticsugar.vooga.util.ResourceManager;
import com.syntacticsugar.vooga.util.filechooser.FileChooserUtil;
import com.syntacticsugar.vooga.util.gui.factory.AlertBoxFactory;
import com.syntacticsugar.vooga.util.gui.factory.GUIFactory;
import com.syntacticsugar.vooga.xml.XMLHandler;
import com.syntacticsugar.vooga.xml.data.IData;
import com.syntacticsugar.vooga.xml.data.ObjectData;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser.ExtensionFilter;

public class ObjectEditor extends Observable implements IDataClipboard {

	/*This is the main class utilized for object editing. It generates a JavaFX node, which populates the entirety of the right
	side of the Authoring Environment's window. This class includes the functionality necessary for the user to either create a 
	new object from scratch, or edit an existing object's attributes/collisions/image.*/
	
	private static final String DEFAULT_IMAGE_PATH = "scenery_gray.png";
	
	private GridPane myView;
	private IData currentData;
	private AttributeViewer myAttributeViewer;
	private CollisionViewer myCollisionViewer;
	private Icon myIcon;
	private Button mySaveButton;
	private Button myUpdateButton;
	private Button myCreateButton;
	private ComboBox<GameObjectType> myTypeChooser;
	private IRefresher myRefresher;
	private String selectedImagePath;
	
	public ObjectEditor(IRefresher refresher) {
		myView = new GridPane();
		myView.setAlignment(Pos.CENTER);
		currentData = new ObjectData();
		myAttributeViewer = new AttributeViewer();
		myCollisionViewer = new CollisionViewer();
		myRefresher = refresher;
		buildView();
		myIcon.setOnDragDetected(e -> {
			DragDropManager.createClipboard(currentData, myIcon.getImageView(), e);
		});
	}

	private void buildView() {
		GridPane myMainEditorView = buildEditorView();
		myTypeChooser = buildTypeChooser();
		AnchorPane myTopControlPane = GUIFactory.buildAnchorPane(myTypeChooser,
				GUIFactory.buildButton(ResourceManager.getString("new"), e -> createEmptyEditor(), null, null));
		myUpdateButton = GUIFactory.buildButton(ResourceManager.getString("update"), e -> storeEditedObject(), null, null);
		myCreateButton = GUIFactory.buildButton(ResourceManager.getString("create"), e -> createNewObjectInstance(), null, null);
		mySaveButton = GUIFactory.buildButton(ResourceManager.getString("save"), e -> saveObject(), null, null);
		HBox myBottomControlPane = new HBox();
		myBottomControlPane.setSpacing(75);
		myBottomControlPane.getChildren().addAll(myUpdateButton, myCreateButton, mySaveButton);
		myView.add(myTopControlPane, 0, 0, 1, 1);
		myView.add(myMainEditorView, 0, 1, 1, 1);
		myView.add(myBottomControlPane, 0, 2, 1, 1);
		GridPane.setHalignment(myTopControlPane, HPos.CENTER);
		GridPane.setHalignment(myMainEditorView, HPos.CENTER);
		createEmptyEditor();
	}

	private void createEmptyEditor() {
		setTypeChooserViability(true);
		myTypeChooser.setValue(null);
		IData emptyData = new ObjectData();
		emptyData.setImagePath(DEFAULT_IMAGE_PATH);
		myIcon.setImage(new Image(ResourceManager.getResource(this, emptyData.getImagePath())));
		emptyData.setObjectName(null);
		emptyData.setType(null);
		setUpdateButtonVisibility(false);
		setSaveButtonViability(false);
		setCreateButtonViability(false);
		emptyData.setAttributes(FXCollections.observableArrayList());
		emptyData.setCollisionMap(FXCollections.observableHashMap());
		displayData(emptyData);
	}

	private GridPane buildEditorView() {
		AnchorPane attributeAnchor = buildButtons(e -> buildNewAttribute(), e -> myAttributeViewer.removeSelectedItem(),
				ResourceManager.getString("attributes_added"));
		AnchorPane collisionAnchor = buildButtons(e -> buildNewCollision(), e -> myCollisionViewer.removeSelectedItem(),
				ResourceManager.getString("collision_menu_title"));
		GridPane grid = createMainEditorGrid();
		VBox att = createEditorVBox(attributeAnchor, myAttributeViewer.getView());
		VBox coll = createEditorVBox(collisionAnchor, myCollisionViewer.getView());
		GridPane iconGrid = createIconGrid();
		grid.add(iconGrid, 0, 0, 1, 1);
		grid.add(coll, 0, 1, 2, 1);
		grid.add(att, 1, 0, 1, 1);
		return grid;
	}

	private ComboBox<GameObjectType> buildTypeChooser() {
		ComboBox<GameObjectType> typeChooser = new ComboBox<>();
		typeChooser.setPromptText(ResourceManager.getString("ChooseObjectType"));
		typeChooser.setPrefWidth(150);
		typeChooser.getStyleClass().add("combobox");
		typeChooser.getItems().addAll(GameObjectType.values());
		typeChooser.valueProperty().addListener((o, s1, s2) -> {
			if (s2 == null) {
				setSaveButtonViability(false);
				setCreateButtonViability(false);
				return;
			}
			if (mySaveButton.isDisabled()) {
				setSaveButtonViability(true);
				setCreateButtonViability(true);
			}

			currentData.setType(s2);
		});
		return typeChooser;
	}

	public void displayData(IData data) {
		if (data != null) {
			selectedImagePath = data.getImagePath();
			if (mySaveButton.isDisabled() && data.getType() != null) {
				setSaveButtonViability(true);
				setCreateButtonViability(true);
			}
			currentData = data;
			if (data.getType() != null) {
				myTypeChooser.setValue(currentData.getType());
			}
			myAttributeViewer.displayData(currentData.getAttributes());
			myCollisionViewer.displayData(currentData.getCollisionMap());
			myIcon.setImage(new Image(ResourceManager.getResource(this, currentData.getImagePath())));
		}
	}

	private void storeEditedObject() {
		currentData.setImagePath(new String(selectedImagePath));
		currentData.setType(currentData.getType());
		currentData.setAttributes(myAttributeViewer.getData());
		currentData.setCollisionMap(myCollisionViewer.getData());
		registerChangeAndNotifyObserver();
	}

	private void registerChangeAndNotifyObserver() {
		setChanged();
		notifyObservers();
	}

	private void saveObject() {
		createNewObjectInstance();
		TextInputDialog td = new TextInputDialog(ResourceManager.getString("name_creation"));
		td.showAndWait();
		if (td.getResult() == null || td.getResult().isEmpty()) {
			AlertBoxFactory.createObject(ResourceManager.getString("aborted_save"));
			return;
		}
		currentData.setObjectName(td.getResult());
		launchSaveBox();
		myRefresher.refresh();
	}

	private void createNewObjectInstance() {
		GameObjectType tempObjType = currentData.getType();
		Class<?> c;
		try {
			c = Class.forName(ResourceManager.getString(tempObjType.toString() + "_DATA"));
			currentData = (IData) c.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		currentData.setType(tempObjType);
		currentData.setImagePath(new String(selectedImagePath));
		currentData.setAttributes(Collections.unmodifiableCollection(myAttributeViewer.getData()));
		currentData.setCollisionMap(Collections.unmodifiableMap(myCollisionViewer.getData()));
	}

	private void launchSaveBox() {
		FileChooserUtil.saveFile("Save Resource File", String.format("%s.%s", currentData.getObjectName(), "xml"),
				new File(ResourceManager
						.getString(String.format("%s_%s", currentData.getType().toString().toLowerCase(), "data"))),
				selectedFile -> {
					XMLHandler<IData> xml = new XMLHandler<>();
					xml.write(currentData, selectedFile);
				});
	}

	private void selectImage() {
		if (currentData.getType() == null) {
			AlertBoxFactory.createObject(ResourceManager.getString("select_object_type_error"));
			return;
		}

		FileChooserUtil.loadFile("Select Image File", new ExtensionFilter("Image Files", "*.jpeg", "*.gif", "*.png"),
				new File(ResourceManager
						.getString(String.format("%s_%s", currentData.getType().toString().toLowerCase(), "images"))),
				selectedFile -> {
					selectedImagePath = selectedFile.getName();
					myIcon.setImage(new Image(getClass().getClassLoader().getResourceAsStream(selectedFile.getName())));
				});
	}

	private void buildNewAttribute() {
		new AttributeMakerWizard(currentData.getType(), myAttributeViewer.getData());
		if (currentData.getType() == null) {
			AlertBoxFactory.createObject(ResourceManager.getString("select_object_type_error"));
			return;
		}
	}

	private void buildNewCollision() {
		new CollisionMakerWizard(currentData.getType(), myCollisionViewer.getData());
	}

	public Node getView() {
		return myView;
	}

	private GridPane createIconGrid() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		myIcon = new Icon(DEFAULT_IMAGE_PATH);
		PulsingFadeWizard.attachPulsingHandlers(myIcon);
		Button button = GUIFactory.buildButton(ResourceManager.getString("select_image"), 
				e -> selectImage(), null, null);
		grid.getChildren().addAll(button, myIcon);
		GridPane.setConstraints(button, 0, 0, 1, 1);
		GridPane.setConstraints(myIcon, 0, 1, 1, 1);
		GridPane.setHalignment(myIcon, HPos.CENTER);
		GridPane.setHalignment(button, HPos.LEFT);
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		return grid;
	}

	private VBox createEditorVBox(AnchorPane anchor, Node viewer) {
		VBox att = new VBox();
		att.getChildren().addAll(anchor, viewer);
		att.setSpacing(10);
		return att;
	}

	private GridPane createMainEditorGrid() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.getColumnConstraints().addAll(columnWithPercentage(50), columnWithPercentage(50));
		grid.getRowConstraints().addAll(rowWithPercentage(45), rowWithPercentage(45), rowWithPercentage(10));
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10));
		return grid;
	}

	private ColumnConstraints columnWithPercentage(double percent) {
		ColumnConstraints c = new ColumnConstraints();
		c.setPercentWidth(percent);
		return c;
	}

	private RowConstraints rowWithPercentage(double percent) {
		RowConstraints r = new RowConstraints();
		r.setPercentHeight(percent);
		return r;
	}

	private AnchorPane buildButtons(EventHandler<ActionEvent> add, EventHandler<ActionEvent> remove, String label) {
		Button a = new Button();
		a.setText("+");
		a.setOnAction(add);
		a.setPrefWidth(25);
		Button r = new Button();
		r.setText("-");
		r.setOnAction(remove);
		r.setPrefWidth(25);
		GridPane buttonGrid = new GridPane();
		buttonGrid.add(a, 0, 0, 1, 1);
		buttonGrid.add(r, 1, 0, 1, 1);
		buttonGrid.setHgap(5);
		a.setAlignment(Pos.CENTER);
		r.setAlignment(Pos.CENTER);
		GridPane.setHgrow(a, Priority.ALWAYS);
		GridPane.setHgrow(r, Priority.ALWAYS);
		AnchorPane anchor = GUIFactory.buildAnchorPane(new Label(label), buttonGrid);
		return anchor;
	}

	public void setTypeChooserViability(boolean flag) {
		myTypeChooser.setDisable(!flag);
	}

	public void setUpdateButtonVisibility(boolean flag) {
		myUpdateButton.setDisable(!flag);
	}

	public void setSaveButtonViability(boolean flag) {
		mySaveButton.setDisable(!flag);
	}
	
	private void setCreateButtonViability(boolean flag) {
		myCreateButton.setDisable(!flag);
	}

	@Override
	public IData obtainSelectedIData() {
		return currentData;
	}
	
}
