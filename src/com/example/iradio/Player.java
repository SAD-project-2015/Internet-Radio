package com.example.iradio;

import java.io.File;
import java.net.URL;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("restriction")
public class Player extends Panel implements View  {
	 public static final String NAME = "player";
	private static final long serialVersionUID = 6714096000861957459L;
		Button logoutButton;
		MediaPlayer player;
		 VerticalLayout layout;
		 IradioUI ui;
		
	@SuppressWarnings({ "restriction", "unused" })
	public	Player(Navigator navigator){
		this.ui=ui;
		JFXPanel fxPanel = new JFXPanel();
		layout = new VerticalLayout();
		
	 logoutButton = new Button("Logout", new Button.ClickListener() {
        
		private static final long serialVersionUID = 2718672708618255597L;

		@Override
         public void buttonClick(ClickEvent event) {

             ((IradioUI) UI.getCurrent()).setLoggedInUser(null);
             logoutButton.setCaption("Logout");
//             navigator.navigateTo(LoginView.NAME);

         }
     });
     layout.addComponent(logoutButton);
     layout.setComponentAlignment(logoutButton, Alignment.TOP_RIGHT);
     layout.addComponent(getPlayer());
     setContent(layout);
}
	public HorizontalLayout getPlayer() {	
		Panel playerPanel=new Panel();		
				HorizontalLayout playerContainer=new HorizontalLayout();
		playerPanel.setContent(playerContainer);
		playerPanel.setHeight("60px");
		playerPanel.setWidth("350px");
		playerContainer.setSpacing(true);
		playerContainer.setMargin(true);
		Button playButton = new Button("Play");
		Button pauseButton = new Button("Pause");
		Button stopButton = new Button("Stop");
		
		URL is = this.getClass().getClassLoader().getResource("resources/sound1.mp3");
		player = new MediaPlayer(new Media(is.toString()));
		
		playButton.addClickListener(new Button.ClickListener() {
			@SuppressWarnings("restriction")
			public void buttonClick(ClickEvent event) {

//				String fileName="C:/Users/Ram/Desktop/SAD/sound1.mp3";
//				String uriString = new File(fileName).toURI().toString();			
				player.play(); 
				
				}
		});
		pauseButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
		
				player.pause(); 		
			}
		});
		
		stopButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				player.stop(); 			
			}
		});
		
	playerContainer.addComponent(playButton);
	playerContainer.addComponent(pauseButton);
	playerContainer.addComponent(stopButton);
	return playerContainer;
	}
	
	public VerticalLayout showPlayer() {
		return layout;
	}
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}	

}
