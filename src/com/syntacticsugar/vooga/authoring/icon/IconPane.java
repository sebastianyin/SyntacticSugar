package com.syntacticsugar.vooga.authoring.icon;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.syntacticsugar.vooga.authoring.objectediting.IVisualElement;
import com.syntacticsugar.vooga.util.dirview.IConverter;
import com.syntacticsugar.vooga.util.dirview.IDirectoryViewer;
import com.syntacticsugar.vooga.xml.XMLFileFilter;
import com.syntacticsugar.vooga.xml.XMLHandler;
import com.syntacticsugar.vooga.xml.data.IData;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class IconPane implements IVisualElement, IDirectoryViewer<String> {

	private ScrollPane myScrollPane;
	private TilePane myIconPane;
	private Map<ImageView, String> myImagePaths;

	private Icon selectedTile;
	private final ObjectProperty<ImageView> mySelectedIcon = new SimpleObjectProperty<>();

	private final double GLOW_PERCENTAGE = 0.75;
	private final double INSET_VALUE = 3;
	private final int NUM_COLS = 3;

	public IconPane() {
		myImagePaths = new HashMap<>();
		myIconPane = new TilePane();
		mySelectedIcon.addListener((o, s1, s2) -> setSelectedEffect(s1, s2));

		myScrollPane = new ScrollPane();
		myScrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		myScrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		myScrollPane.setFitToWidth(true);
		myScrollPane.setPadding(new Insets(INSET_VALUE));

		clearIconPane();
		initializeGridPane();
	}

	private void initializeGridPane() {
		myIconPane.setPrefColumns(NUM_COLS);
		myIconPane.setAlignment(Pos.CENTER);
		myIconPane.setHgap(INSET_VALUE);
		myIconPane.setVgap(INSET_VALUE);
		myIconPane.maxWidthProperty().set(myScrollPane.viewportBoundsProperty().get().getWidth() - 2 * INSET_VALUE);
		myScrollPane.setContent(myIconPane);
	}

	public Icon getSelectedTile() {
		return selectedTile;
	}

	public void setSelectedTile(Icon selectedTile) {
		this.selectedTile = selectedTile;
	}

	public void addPreviewListener(ChangeListener<ImageView> event) {
		mySelectedIcon.addListener(event);
	}

	public void showDirectoryContents(File directory, IConverter<String> fileConverter) {
		clearIconPane();
		initializeGridPane();
		Collection<String> imagePaths = fileConverter.getContents(directory);

		for (String path : imagePaths) {
			ImageView iv = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(path)));
			iv.fitWidthProperty().bind(myIconPane.maxWidthProperty().divide(NUM_COLS).subtract(INSET_VALUE));
			iv.fitHeightProperty().bind(iv.fitWidthProperty());
			iv.setOnMouseClicked(e -> setSelectedImageView(iv));
			myIconPane.getChildren().add(iv);
			myImagePaths.put(iv, path);
		}
	}

	public Map<ImageView, IData> showDirectoryContentsMap(File directory, IConverter<String> fileConverter) {
		clearIconPane();
		initializeGridPane();
		Map<ImageView, IData> map = new HashMap<>();

		File[] files = directory.listFiles(new XMLFileFilter());
		XMLHandler<IData> xml = new XMLHandler<>();
		for (int i = 0; i < files.length; i++) {
			IData obj = xml.read(files[i]);
			ImageView image = new ImageView(
					new Image(getClass().getClassLoader().getResourceAsStream(obj.getImagePath())));
			image.fitWidthProperty().bind(myIconPane.maxWidthProperty().divide(NUM_COLS).subtract(INSET_VALUE));
			image.fitHeightProperty().bind(image.fitWidthProperty());
			image.setOnMouseClicked(e -> setSelectedImageView(image));
			myIconPane.getChildren().add(image);
			myImagePaths.put(image, obj.getImagePath());
			map.put(image, obj);
		}
		return map;
	}

	public Node getView() {
		return myScrollPane;
	}

	public String getSelectedImagePath() {
		return myImagePaths.get(mySelectedIcon.get());
	}

	private void clearIconPane() {
		myIconPane.getChildren().clear();
		myImagePaths.clear();
		myScrollPane.setContent(null);
	}

	private void setSelectedEffect(ImageView oldIv, ImageView newIv) {
		if (oldIv == null) {
			newIv.setEffect(new Glow(GLOW_PERCENTAGE));
			return;
		}
		if (newIv == null) {
			oldIv.setEffect(null);
			return;
		}
		oldIv.setEffect(null);
		newIv.setEffect(new Glow(GLOW_PERCENTAGE));
	}

	private void setSelectedImageView(ImageView iv) {
		mySelectedIcon.set(iv);
	}

	public ImageView getSelectedIcon() {
		return mySelectedIcon.get();
	}

}
