package com.syntacticsugar.vooga.authoring.level.map;

import java.util.HashMap;
import java.util.Map;

import com.syntacticsugar.vooga.authoring.dragdrop.DragDropManager;
import com.syntacticsugar.vooga.authoring.icon.Icon;
import com.syntacticsugar.vooga.authoring.level.IAddToSpawner;
import com.syntacticsugar.vooga.authoring.objectediting.IDataClipboard;
import com.syntacticsugar.vooga.authoring.objectediting.IVisualElement;
import com.syntacticsugar.vooga.authoring.objectediting.sizing.ObjectResizer;
import com.syntacticsugar.vooga.authoring.tooltips.TileInfoTooltip;
import com.syntacticsugar.vooga.gameplayer.objects.GameObjectType;
import com.syntacticsugar.vooga.gameplayer.universe.map.tiles.effects.ITileEffect;
import com.syntacticsugar.vooga.util.ResourceManager;
import com.syntacticsugar.vooga.util.gui.factory.AlertBoxFactory;
import com.syntacticsugar.vooga.util.gui.factory.GUIFactory;
import com.syntacticsugar.vooga.util.gui.factory.MsgInputBoxFactory;
import com.syntacticsugar.vooga.util.gui.factory.SliderDialogFactory;
import com.syntacticsugar.vooga.xml.data.MapData;
import com.syntacticsugar.vooga.xml.data.ObjectData;
import com.syntacticsugar.vooga.xml.data.TileData;
import com.syntacticsugar.vooga.xml.data.TileImplementation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Effect;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class MapView implements IMapDisplay, IVisualElement {

	public static final Effect TILE_EFFECT = createEffect();
	private static final String DEFAULT_TILE_IMAGE = "path_rock_2.png";

	private static Effect createEffect() {
		InnerShadow shadow = new InnerShadow();
		shadow.setColor(Color.RED);
		return shadow;
	}

	private ObservableSet<TileData> myTileSelection;
	private Map<TileData, Icon> myTileIconMap;
	private MapData myMapData;
	private int myMapSize;
	private GridPane myMapGrid;
	private TitledPane myViewPane;
	private IDataClipboard iObject;

	private IAddToSpawner iSpawn;

	public MapView(IDataClipboard clip, IAddToSpawner isp) throws Exception {
		iObject = clip;
		iSpawn = isp;
		myMapSize = inputMapSize();
		myTileSelection = buildSelectionSet();
		myMapData = new MapData(myMapSize, DEFAULT_TILE_IMAGE);
		myMapGrid = new GridPane();
		myTileIconMap = new HashMap<>();
		initializeMapView(myMapData);
		myViewPane = GUIFactory.buildTitledPane(ResourceManager.getString("map_display"), myMapGrid);
	}

	@Override
	public void displayData(TileData newData) {
		if (newData == null)
			return;
		boolean showAlert = false;
		for (TileData toChange : myTileSelection) {
			setImplementation(toChange, newData.getImplementation());
			setImagePath(toChange, newData.getImagePath());
			if (newData.getEffect() != null)
				setEffect(toChange, newData.getEffect());
			System.out.println("here");
			showAlert = setAsDestination(toChange, newData.isDestination());
		}
		if (showAlert)
			AlertBoxFactory.createObject(ResourceManager.getString("tile_setting_error"));
	}

	@Override
	public void clearDisplay() {
		myTileSelection.clear();
	}

	/**
	 * Should not be used
	 */
	@Deprecated
	@Override
	public TileData getData() {
		return null;
	}

	@Override
	public void selectAllTiles() {
		myTileSelection.clear();
		myTileSelection.addAll(myMapData.getTiles());
	}

	@Override
	public Node getView() {
		return myViewPane;
	}

	public void loadMapData(MapData loadedMap) {
		myTileSelection = buildSelectionSet();
		myTileIconMap = new HashMap<>();
		myMapData = loadedMap;
		myMapSize = loadedMap.getMapSize();
		initializeMapView(myMapData);
	}

	public MapData getMapData() {
		return myMapData;
	}

	private ObservableSet<TileData> buildSelectionSet() {
		ObservableSet<TileData> set = FXCollections.observableSet();
		set.addListener(new SetChangeListener<TileData>() {
			@Override
			public void onChanged(javafx.collections.SetChangeListener.Change<? extends TileData> change) {
				if (change.wasAdded())
					tileEffectOn(myTileIconMap.get(change.getElementAdded()));
				else if (change.wasRemoved()) {
					tileEffectOff(myTileIconMap.get(change.getElementRemoved()));
				}
			}
		});
		return set;
	}

	private int inputMapSize() {
		double size = SliderDialogFactory.createSliderDialog(
				ResourceManager.getString("input_mapsize"), 10, 40, 1);
		if (size == Double.MIN_VALUE) {
			return 10;
		}
		return (int) size;
	}

	private void initializeMapView(MapData mapData) {
		myMapGrid.getChildren().clear();
		for (int i = 0; i < myMapSize; i++) {
			for (int j = 0; j < myMapSize; j++) {
				TileData tile = mapData.getTileData(i, j);
				Icon icon = new Icon(tile.getImagePath());
				myTileIconMap.put(tile, icon);
				Tooltip.install(icon, new TileInfoTooltip(tile));
				icon.setOnMouseEntered(e -> mouseOverHandler(tile, e.isControlDown(), e.isShiftDown()));
				icon.setOnMouseClicked(e -> mouseClickHandler(tile));
				icon.setOnDragOver((DragEvent event) -> DragDropManager.dragOverHandler(event));
				icon.setOnDragEntered((DragEvent event) -> DragDropManager.dragEnteredHandler(icon, event));
				icon.setOnDragExited((DragEvent event) -> DragDropManager.dragExitedHandler(icon, event));
				myMapGrid.add(icon, i, j, 1, 1);
			}
			myMapGrid.setOnDragDropped(e -> {
				dragOverHandler(e);
			});
		}
	}

	private void dragOverHandler(DragEvent event) {
		double x = event.getX();
		double y = event.getY();
		System.out.println("( " + x + ", " + y + ")");
		double colIndex = (int) (myMapSize * x / myMapGrid.getWidth());
		double rowIndex = (int) (myMapSize * y / myMapGrid.getHeight());
		System.out.println("( " + rowIndex + ", " + colIndex + ")");
		Dragboard db = event.getDragboard();
		if (db.hasContent(DataFormat.lookupMimeType("TileData"))) {
			TileData tdFromClipBoard = (TileData) db.getContent(DataFormat.lookupMimeType("TileData"));
			TileData toedit = myMapData.getTileData((int)colIndex, (int)rowIndex);
			editTileDataFromClipboard(tdFromClipBoard, toedit);
			myMapData.setTileData(toedit, (int)colIndex, (int)rowIndex);
		} else if (db.hasContent(DataFormat.lookupMimeType("ObjectData"))) {
			//System.out.println(iObject);
			//System.out.println(iObject.obtainSelectedIData());
			ObjectData receivedData = (ObjectData) iObject.obtainSelectedIData();
			
			MsgInputBoxFactory spawnNumMsgBox = new MsgInputBoxFactory("Input spawn number");
			int numSpawn = (int) spawnNumMsgBox.getInputValue();
			
			ObjectData toCopy = new ObjectData();
			for (int i = 0; i < numSpawn; i++) {
				toCopy = new ObjectData(receivedData);     
				System.out.println("( " + colIndex/myMapSize * 1000 + ", " + rowIndex/myMapSize * 1000 + ")");
				toCopy.setSpawnPoint(colIndex/myMapSize * 1000, rowIndex/myMapSize * 1000);
				// Add to Spawner
				iSpawn.addToSpawner(toCopy);
			}

			String[][] imagePathArray = populateImagePathArray((int)colIndex, (int)rowIndex);
			if(!toCopy.getType().equals(GameObjectType.TOWER)){
				new ObjectResizer(toCopy, imagePathArray);
			}
		}
	}

	private String[][] populateImagePathArray(int colIndex, int rowIndex) {
		String[][] imagePathArray = new String[3][3];// [row][col]
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2; j++) {
				try {
					imagePathArray[i][j] = myMapData.getTileData(j + colIndex - 1, i + rowIndex - 1).getImagePath();
				} catch (IndexOutOfBoundsException e) {
					imagePathArray[i][j] = "scenery_white.png";
				}
			}
		}
		return imagePathArray;
	}

	private void editTileDataFromClipboard(TileData tdFromClipBoard, TileData toedit) {
		setImplementation(toedit, tdFromClipBoard.getImplementation());
		setImagePath(toedit, tdFromClipBoard.getImagePath());
		setAsDestination(toedit, tdFromClipBoard.isDestination());
	}

	private void mouseOverHandler(TileData tile, boolean isControlDown, boolean isShiftDown) {
		multiSelectTile(tile, isControlDown, isShiftDown);
	}

	private void mouseClickHandler(TileData tile) {
		toggleTileSelection(tile);
	}

	private void multiSelectTile(TileData tile, boolean isControlDown, boolean isShiftDown) {
		if (isControlDown) {
			myTileSelection.add(tile);
		} else if (isShiftDown) {
			myTileSelection.remove(tile);
		}
	}

	private void toggleTileSelection(TileData tile) {
		if (myTileSelection.contains(tile))
			myTileSelection.remove(tile);
		else
			myTileSelection.add(tile);
	}

	private void tileEffectOff(Icon icon) {
		icon.setEffect(null);
	}

	private void tileEffectOn(Icon icon) {
		icon.setEffect(TILE_EFFECT);
	}

	/**
	 * Check the TileData in question to see if it's Scenery or Path. If Path,
	 * then we are able to set the destination marker and return false (no need
	 * to show an alert). If the TileData is of type Scenery, then we return
	 * true without changing the TileData to allow the calling method to show
	 * the appropriate error alert.
	 * 
	 * @param toChange
	 * @param isDestination
	 * @return
	 */
	private boolean setAsDestination(TileData toChange, boolean isDestination) {
		if (toChange.getImplementation().equals(TileImplementation.Scenery) && isDestination) {
			return true;
		} else {
			toChange.setDestination(isDestination);
			return false;
		}
	}

	private void setImplementation(TileData toChange, TileImplementation impl) {
		toChange.setImplementation(impl);
	}

	private void setImagePath(TileData toChange, String imagePath) {
		toChange.setImagePath(imagePath);
		myTileIconMap.get(toChange).setImage(new Image(ResourceManager.getResource(this, imagePath)));
	}

	private void setEffect(TileData toChange, ITileEffect effect) {
		toChange.setEffect(effect);
	}

	@Override
	public Point2D getDropPoint(DragEvent e) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getMapSize() {
		return myMapSize;
	}
	
	public double getMapGridWidth() {
		return myMapGrid.getWidth();
	}
	
	public double getMapGridHeight() {
		return myMapGrid.getHeight();
	}
	
	public Map<TileData, Icon> getTileIconMap() {
		return myTileIconMap;
	}

}
