package com.example.iradio;

import java.io.File;

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

public class PlayerView implements View {
	
	private static final long serialVersionUID = 6714096000861957459L;
	public static final String NAME = "player";
		Button logoutButton;
		MediaPlayer player;
		
	@SuppressWarnings("restriction")
	public	PlayerView(final Navigator navigator){
		JFXPanel fxPanel = new JFXPanel();
		 VerticalLayout layout = new VerticalLayout();
	 logoutButton = new Button("Logout", new Button.ClickListener() {
         @Override
         public void buttonClick(ClickEvent event) {

             ((IradioUI) UI.getCurrent()).setLoggedInUser(null);
             logoutButton.setCaption("Login");
             navigator.navigateTo(LoginView.NAME);

         }
     });
     layout.addComponent(logoutButton);
     layout.setComponentAlignment(logoutButton, Alignment.TOP_RIGHT);
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
		playButton.addClickListener(new Button.ClickListener() {
			@SuppressWarnings("restriction")
			public void buttonClick(ClickEvent event) {

				String fileName="C:/Users/Ram/Desktop/SAD/sound1.mp3";
				String uriString = new File(fileName).toURI().toString();
				player = new MediaPlayer(new Media(uriString));
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
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	

}
