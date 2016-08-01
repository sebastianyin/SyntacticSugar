package com.syntacticsugar.vooga.menu;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public interface IVoogaApp {

	public void assignCloseHandler(EventHandler<WindowEvent> onclose);

}
