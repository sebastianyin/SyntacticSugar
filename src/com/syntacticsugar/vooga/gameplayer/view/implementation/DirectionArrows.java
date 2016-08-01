package com.syntacticsugar.vooga.gameplayer.view.implementation;

import com.syntacticsugar.vooga.authoring.fluidmotion.mixandmatchmotion.PulsingFadeWizard;
import com.syntacticsugar.vooga.gameplayer.attribute.movement.Direction;
import com.syntacticsugar.vooga.gameplayer.view.gameview.ISimpleGameView;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class DirectionArrows extends BorderPane {

	public DirectionArrows(TileView tile, ISimpleGameView myGameView) {
		double mySize = tile.getViewPane().getWidth();
		ImageView left = createArrowImage(tile, myGameView, Direction.LEFT, mySize);
		ImageView right = createArrowImage(tile, myGameView, Direction.RIGHT, mySize);
		ImageView up = createArrowImage(tile, myGameView, Direction.UP, mySize);
		ImageView down = createArrowImage(tile, myGameView, Direction.DOWN, mySize);
		PulsingFadeWizard.attachPulsingHandlers(left, right,up, down);
		BorderPane.setAlignment(up, Pos.CENTER);
		BorderPane.setAlignment(down, Pos.CENTER);
		Canvas center = new Canvas(mySize, mySize);
		initializeBorderPane(left, right, up, down, center);
	}

	private ImageView createArrowImage(TileView tile, ISimpleGameView myGameView, Direction direction, double mySize) {
		ImageView arrowImage = new ImageView(
				new Image(getClass().getClassLoader().getResourceAsStream("arr" + direction.toString() + ".png")));
		arrowImage.setOnMouseClicked(e -> tile.informTowerControl(direction, this, myGameView));
		arrowImage.setFitHeight(mySize);
		arrowImage.setFitWidth(mySize);
		return arrowImage;
	}

	private void initializeBorderPane(ImageView left, ImageView right, ImageView up, ImageView down, Canvas center) {
		this.setTop(up);
		this.setLeft(left);
		this.setRight(right);
		this.setBottom(down);
		this.setCenter(center);
	}

}
