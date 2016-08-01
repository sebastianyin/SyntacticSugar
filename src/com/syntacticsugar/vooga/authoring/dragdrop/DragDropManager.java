package com.syntacticsugar.vooga.authoring.dragdrop;

import com.syntacticsugar.vooga.authoring.icon.Icon;

import com.syntacticsugar.vooga.xml.data.IData;
import com.syntacticsugar.vooga.xml.data.TileData;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

/*
 * NOTES:
 * 
 * This class implements the Drag and Drop function that handles copying of string data to the clipboard.
 * 		createDragClipBoards() needs to be implemented for: the Icon that is being dragged.
 * 		dragOverHandler(), dragEnteredHandler(), dragExitedHandler() must be implemented for: the Icon that is receiving the dragged item.
 * 		undoDragOverState(), setDragOverState() implements UI features to show effects of an object being dragged.
 * The dragDropped event method must be implemented individually.
 */

public class DragDropManager {
	private static DataFormat tileDataFormat = new DataFormat("TileData");
	private static DataFormat objectDataFormat = new DataFormat("ObjectData");

	public DragDropManager() {
		}
		
	public static void createClipboard(TileData tileData, ImageView previewTile, MouseEvent event) {
		Dragboard db = previewTile.startDragAndDrop(TransferMode.ANY);
		System.out.println("Tile Dragboard and Clipboard created");
		ClipboardContent content = new ClipboardContent();
		content.put(tileDataFormat, tileData);
		db.setContent(content); 
		event.consume();
	}
	
	public static void createClipboard(IData objectData, ImageView previewTile, MouseEvent event){
		Dragboard db = previewTile.startDragAndDrop(TransferMode.ANY);
		System.out.println("Object Dragboard and Clipboard created");
		ClipboardContent content = new ClipboardContent();
		content.put(objectDataFormat, objectData.getImagePath());
		db.setContent(content); 
		event.consume();
	}
	
	public static void dragOverHandler(DragEvent event) {
		event.getDragboard();
		event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		event.consume();
	}

	public static void dragEnteredHandler(Icon icon, DragEvent event) {
		if (event.getGestureSource() != icon) {
			setDragOverState(icon);
		}
	}

	public static void dragExitedHandler(Icon icon, DragEvent event) {
		if (event.getGestureSource() != icon) {
			undoDragOverState(icon);
		}
	}

	public static void undoDragOverState(Icon icon) {
		icon.getImageView().setOpacity(1);
	}

	public static void setDragOverState(Icon icon) {
		icon.getImageView().setOpacity(0.5);
	}
}
