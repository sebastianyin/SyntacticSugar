package com.syntacticsugar.vooga.authoring.objectediting;

import java.util.Collection;

import com.syntacticsugar.vooga.gameplayer.attribute.IAttribute;
import com.syntacticsugar.vooga.util.ResourceManager;
import com.syntacticsugar.vooga.util.gui.factory.AlertBoxFactory;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class AttributeViewer implements IVisualElement, 
										IListViewDisplay, 
										IDataDisplay<Collection<IAttribute>> {
	
	
	/*Utilized by the objectEditor class to view/store all the attributes added for an object currently being edited.
	This is the standard UI display that we see within the ObjectEditor, which gives us the option of both creating new attributes
	and viewing the object's existing attributes. Once a user creates a new attribute via the AttributeMakerWizard, it is displayed
	in the AttributeViewer's attribute display pane.*/

	private ListView<HBox> myListView;
	private ObservableList<IAttribute> myAttributes;
	private ObservableList<HBox> myAttributeDisplays;


	public AttributeViewer() {
		myAttributeDisplays = FXCollections.observableArrayList();
		myAttributes = FXCollections.observableArrayList();
		myAttributes.addListener(new ListChangeListener<IAttribute>() {
			@Override
			public void onChanged(Change<? extends IAttribute> change) {
				change.next();
				if (change.wasAdded()) {
					for (IAttribute att : change.getAddedSubList()) {
						myAttributeDisplays.add(makeListElement(att));
					}
				}
				if (change.wasRemoved()) {
					for (int i = 0;i < change.getRemovedSize();i++) {
						myAttributeDisplays.remove(change.getFrom());
					}
				}
			}
		});
		myListView = new ListView<HBox>(myAttributeDisplays);
	}

	@Override
	public Node getView() {
		return this.myListView;
	}

	@Override
	public void removeSelectedItem() {
		if (!myAttributes.isEmpty()) {
			int selectedIdx = myListView.getSelectionModel().getSelectedIndex();
			if (selectedIdx == -1) {
				AlertBoxFactory.createObject(ResourceManager.getString("invalid-selection"));
				return;
			}
			System.out.println(selectedIdx);
			myAttributes.remove(selectedIdx);
		}
		else {
			AlertBoxFactory.createObject(ResourceManager.getString("empty-remove"));
		}
	}

	@Override
	public void displayData(Collection<IAttribute> data) {
		myAttributes.clear();
		for (IAttribute att : data) {
			myAttributes.add(att);
		}
	}

	@Override
	public void clearDisplay() {
		myAttributes.clear();
	}
	
	@Override
	public Collection<IAttribute> getData() {
		return this.myAttributes;
	}

	private HBox makeListElement(IAttribute attribute){
		String attributeName = ResourceManager.getString(attribute.getClass().getSimpleName());
		HBox element = new HBox();
		element.getChildren().add(new Text(attributeName));
		return element;
	}

}
