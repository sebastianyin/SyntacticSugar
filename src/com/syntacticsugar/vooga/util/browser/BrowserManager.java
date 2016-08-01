/**  
 *  From http://stackoverflow.com/questions/10967451/open-a-link-in-browser-with-java-button 
 */

package com.syntacticsugar.vooga.util.browser;

import java.awt.Desktop;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class BrowserManager {
	public static void open(String url) {
		try {
			openWebpage(new URL(url));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	private static void openWebpage(URI uri) {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(uri);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

	private static void openWebpage(URL url) {
	    try {
	        openWebpage(url.toURI());
	    } catch (URISyntaxException e) {
	        e.printStackTrace();
	    }
	}
}
