package com.example.iradio;

import java.io.File;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MediaControl;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class PlayerView extends Panel implements View  {
	 public static final String NAME = "player";
	private static final long serialVersionUID = 6714096000861957459L;
		Button logoutButton;
	 MediaPlayer player;
	 Media media;
MediaControl mc=new MediaControl() {
	
	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
};
	 boolean currentlyPlaying=false;
	 int k=0;
	 
		 VerticalLayout layout;
		 IradioUI ui;
		 String fileList[];
		 Table playList;
		 final String path = "/resources";
	@SuppressWarnings({ "unused" })
	public	PlayerView(Navigator navigator){
		JFXPanel fxPanel = new JFXPanel();
		fileList=new String[100];
		layout = new VerticalLayout();
		
	 logoutButton = new Button("Logout", new Button.ClickListener() {
        
		private static final long serialVersionUID = 2718672708618255597L;

		@Override
         public void buttonClick(ClickEvent event) {

             ((IradioUI) UI.getCurrent()).setLoggedInUser(null);
             logoutButton.setCaption("Logout");

         }
     });
     layout.addComponent(logoutButton);
     layout.setComponentAlignment(logoutButton, Alignment.TOP_RIGHT);
     layout.addComponent(getPlayer());
     getFileListFromFolder();
     layout.addComponent(getPlayList());
     layout.setStyleName("backgroundblack");
     setContent(layout);
}
	private Component getPlayList() {
		playList=new Table("PlayList");
		playList.setWidth("300px");
		playList.setHeight("600px");
		playList.setPageLength(playList.size());
		playList.addContainerProperty("Song", String.class, null);
		int m=0;
		for(String song:fileList){
			playList.addItem(new Object[]{song},m);
			m++;
		}
		return playList;
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
			private static final long serialVersionUID = -1422054963939732398L;

			public void buttonClick(ClickEvent event) {
				
				if(!currentlyPlaying){
					currentlyPlaying=true;
					
					for(String songName: fileList){
					setCurrentTrack(fileList[k+2]);
				    player.play();
				   
				    	player.setOnEndOfMedia(new Runnable() {
				            @Override public void run() {
				               playNext();
				              }
				            });
//					playNext();
					
					
				}
				}
				}
				
				
		});
		pauseButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {		
				if(currentlyPlaying){
				player.pause(); 
				currentlyPlaying=false;
				}
			}
		});
		
		stopButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				if(currentlyPlaying){
				player.stop(); 		
				currentlyPlaying=false;
				}
			}
		});
		
	playerContainer.addComponent(playButton);
	playerContainer.addComponent(pauseButton);
	playerContainer.addComponent(stopButton);
	return playerContainer;
	}
	
	private void playNext() {
        setCurrentTrack(fileList[k+1]);	
        player.play();
		}
	
	public void getCurrentlyPlayingTrack(){
		
	}
	
	public void setCurrentTrack(String st){
		media=new Media(this.getClass().getClassLoader().getResource(path +"/"+st).toString());
				player = new MediaPlayer(media);
	}
	
	public String[] getFileListFromFolder() {		
		int i=0;
		File folder=new File(this.getClass().getResource(path).getFile());
		
	if(folder!=null && folder.isDirectory()){
	    for (String fileName : folder.list()) {
	          	  fileList[i]= fileName;
	          	  i++;
	        }
	    }
	return fileList;
	}
	
	
	public VerticalLayout showPlayer() {
		return layout;
	}
	@Override
	public void enter(ViewChangeEvent event) {
		
	}	

}
