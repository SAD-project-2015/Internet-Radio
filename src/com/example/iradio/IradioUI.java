package com.example.iradio;

import java.io.File;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
@SuppressWarnings("serial")
@Theme("iradio")
public class IradioUI extends UI {
	

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = IradioUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);
		JFXPanel fxPanel = new JFXPanel();
//		layout.addComponent(getPlayer());
		
		Button button = new Button("Click Me");
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {

				String fileName="C:/Users/Ram/Desktop/sound1.mp3";
				String uriString = new File(fileName).toURI().toString();
				MediaPlayer player = new MediaPlayer(new Media(uriString));
				player.play(); // or stop() or pause() etc etc
				
			}
		});
		layout.addComponent(button);
	}
	
//	public HorizontalLayout getPlayer() {
//		HorizontalLayout playerContainer=new HorizontalLayout();
//		SimplePanel panel = new SimplePanel();   // create panel to hold the player
//		 AbstractMediaPlayer player = null;
//		 try {
//			  PlayerInfo pi = PlayerUtil.getPlayerInfo("core", "quicktime");
//			  player = PlayerUtil.getPlayer(pi, "http://bajao.biscoot.com/onlinemusic/share/lalla-lalla-lori-256304.mp3", false, 
//				        "width", "height");
//		      // create the player, specifing URL of media
////		      player = new VLCMediaPlayer("www.example.com/mediafile.wma");
//
//		      panel.setWidget(player); // add player to panel.
//		 } 
//		 catch(PluginVersionException e) {
//		      // required plugin version is not available, alert user possibly providing a link
//		      // to the plugin download page.
//		      panel.setWidget(new HTML(".. some nice message telling the user to download plugin first .."));
//		 } 
//		 catch(PluginNotFoundException e) {
//		      // catch PluginNotFoundException and tell user to download plugin, possibly providing
//		      // a link to the plugin download page.
//		      panel.setWidget(new HTML(".. another nice message telling the user to download plugin.."));
//		 }
//		playerContainer.addComponent((Component) panel);
//		
//		
//		
//		return playerContainer;
//	}
	
//	public HorizontalLayout getPlayer() {	
//	
//	
//	}
	
}