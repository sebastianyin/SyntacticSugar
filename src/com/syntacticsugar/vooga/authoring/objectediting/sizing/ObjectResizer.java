package com.syntacticsugar.vooga.authoring.objectediting.sizing;

import com.syntacticsugar.vooga.xml.data.ObjectData;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ObjectResizer {

	private Stage myStage;
	private Scene myScene;
	private ImageView myImageView;
	private GridPane myZoomedTileView;
	private ObjectData myObjectData;
	
	public ObjectResizer(ObjectData data, String[][] imagePaths) {
		myStage = new Stage();
		setMyObjectData(data);
		myImageView = buildResizableImageView(data.getImagePath());
		System.out.println(data.getImagePath());
		myZoomedTileView = buildTileView(imagePaths);
		myZoomedTileView.add(myImageView, 1, 1);
		StackPane root = new StackPane();
		root.getChildren().add(myZoomedTileView);
		//root.getChildren().addAll(myZoomedTileView, myImageView);
		myScene = new Scene(root, 400, 400);
		myStage.setScene(myScene);
		myStage.show();
	}
	
	private ImageView buildResizableImageView(String imagePath) {
		ObjectProperty<Point2D> clickPoint = new SimpleObjectProperty<>();
		ImageView resize = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imagePath)));
		Pane container = new Pane(resize);
		resize.fitWidthProperty().bind(container.widthProperty());
		resize.setOnMousePressed(e -> clickPoint.set(new Point2D(e.getSceneX(), e.getSceneY())));
		resize.setOnMouseDragged(e -> {
			System.out.println(e.getX());
			if (e.getX() < container.getMaxWidth()/2) {
				System.out.println("left");
			}
			else {
				System.out.println("right");
			}
			double deltaW = e.getSceneX() - clickPoint.get().getX();
			container.widthProperty().add(container.getWidth() + deltaW);
		});
		return resize;
	}
	
	private GridPane buildTileView(String[][] imagePaths) {
		GridPane grid = new GridPane();
		addConstraints(grid);
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				Pane tile = buildResizableImagePane(imagePaths[j][i]);
				grid.add(tile, i, j, 1, 1);
			}
		}
		grid.setGridLinesVisible(true);
		return grid;
	}
	
	private void addConstraints(GridPane grid) {
		grid.getColumnConstraints().addAll(
				colWithPercent(33.3),	
				colWithPercent(33.3),
				colWithPercent(33.3));
		grid.getRowConstraints().addAll(
				rowWithPercent(33.3),
				rowWithPercent(33.3),
				rowWithPercent(33.3));
	}
	
	private ColumnConstraints colWithPercent(double percent) {
		ColumnConstraints c = new ColumnConstraints();
		c.setPercentWidth(percent);
		return c;
	}

	private RowConstraints rowWithPercent(double percent) {
		RowConstraints r = new RowConstraints();
		r.setPercentHeight(percent);
		return r;
	}
	
	private Pane buildResizableImagePane(String imagePath) {
		Pane pane = new Pane();
		ImageView imageView = new ImageView(
				new Image(getClass().getClassLoader().getResourceAsStream(imagePath)));
		imageView.fitWidthProperty().bind(pane.widthProperty());
		imageView.fitHeightProperty().bind(pane.heightProperty());
		pane.getChildren().add(imageView);
		return pane;
	}

	public ObjectData getMyObjectData() {
		return myObjectData;
	}

	public void setMyObjectData(ObjectData myObjectData) {
		this.myObjectData = myObjectData;
	}
}
