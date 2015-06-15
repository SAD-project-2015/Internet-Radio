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
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class PlayerView extends Panel implements View  {
	 public static final String NAME = "player";
	private static final long serialVersionUID = 6714096000861957459L;
		Button logoutButton;
	 MediaPlayer player;
	 Media media;
	 HorizontalLayout topBar,menuBar;
	 VerticalLayout contentBar;
	 TabSheet menuSheet;
	 boolean currentlyPlaying=false;
	 int k=0;
	 
		 VerticalLayout layout;
		 IradioUI ui;
		 String fileList[];
		 Table playList;
		 final String path = "/audio";
	@SuppressWarnings({ "unused" })
	public	PlayerView(Navigator navigator){
	final	JFXPanel fxPanel = new JFXPanel();
		fileList=new String[100];
		layout = new VerticalLayout();
		layout.setStyleName("mainlayout");
		 
		  topBar=new HorizontalLayout();
		  menuBar=new HorizontalLayout();	
		  contentBar=new VerticalLayout();		  
			 layout.addComponent(getTopBar());			       
			 layout.addComponent(getMenuBar());	    
     setContent(layout);
}
	private Component getTopBar(){
		
		 Label title=new Label("Campus-Radio");
		 title.setStyleName("title");
		 topBar.addComponent(title);
		 topBar.addStyleName("topBar");
		 logoutButton = new Button("Logout", new Button.ClickListener() {
		        
				private static final long serialVersionUID = 2718672708618255597L;

				@Override
		         public void buttonClick(ClickEvent event) {

		             ((IradioUI) UI.getCurrent()).setLoggedInUser(null);
		             logoutButton.setCaption("Logout");
		         }
		     });
		 logoutButton.setStyleName("logoutbutton");
		     topBar.addComponent(logoutButton);
		    topBar.setComponentAlignment(logoutButton, Alignment.BOTTOM_RIGHT);		    
		    return topBar;
	}
	
	private Component getMenuBar(){
		menuSheet=new TabSheet();
		menuSheet.setSizeFull();
		menuSheet.addStyleName("menusheet");
		
		menuSheet.addTab(getContentBar(),"Radio");
		
		menuSheet.addTab(getUserProfile(),
		          "Profile");
		menuSheet.addTab(getRecordingsPage(),
		          "Recordings");		
		menuSheet.addTab(getDownloadsPage(),
		          "Downloads");
		menuSheet.addTab(getContactPage(),
		          "Contact");
		menuSheet.addTab(getEventsPage(),
		          "Events");
		menuSheet.addTab(getPhotosPage(),
		          "Photos");
		menuSheet.addTab(getVideosPage(),
		          "Video-Seminars");
		menuSheet.addTab(getEventsPage(),
		          "PodCasts");
		return menuSheet;
	}
	
	private Component getContentBar(){
		 contentBar.setStyleName("contentbar");
		 contentBar.setSizeFull();
		contentBar.addComponent(getPlayer());
	     contentBar.addComponent(getPlayList());
		return contentBar;
		
	}
	
	private Component getEventsPage() {
		HorizontalLayout eventsLayout=new HorizontalLayout();
	eventsLayout.setHeight("600px");
	eventsLayout.setWidth("1400px");
		eventsLayout.setStyleName("contentbar");
		return eventsLayout;
	}
	private Component getContactPage() {
		HorizontalLayout contactLayout=new HorizontalLayout();
		contactLayout.setHeight("600px");
		contactLayout.setWidth("1400px");
		contactLayout.setStyleName("contentbar");	
		return contactLayout;
	}
	
	private Component getRecordingsPage() {
		HorizontalLayout recordingsLayout=new HorizontalLayout();
		recordingsLayout.setHeight("600px");
		recordingsLayout.setWidth("1400px");
		recordingsLayout.addComponent(getPlayList());
		recordingsLayout.setStyleName("contentbar");	
		return recordingsLayout;
	}
	private Component getDownloadsPage() {
		HorizontalLayout downloadsLayout=new HorizontalLayout();
		downloadsLayout.setHeight("600px");
		downloadsLayout.setWidth("1400px");
		downloadsLayout.addComponent(getPlayList());
		downloadsLayout.setStyleName("contentbar");	
		return downloadsLayout;
	}
	private Component getPhotosPage() {
		HorizontalLayout photosLayout=new HorizontalLayout();
		photosLayout.setHeight("600px");
		photosLayout.setWidth("1400px");
		photosLayout.setStyleName("contentbar");	
		return photosLayout;
	}
	
	private Component getVideosPage() {
		HorizontalLayout videosLayout=new HorizontalLayout();
		videosLayout.setHeight("600px");
		videosLayout.setWidth("1400px");
		videosLayout.setStyleName("contentbar");	
		return videosLayout;
	}
	
	private Component getUserProfile(){
		HorizontalLayout profileLayout=new HorizontalLayout();
		profileLayout.setStyleName("contentbar");
		Table profileTable=new Table();
		profileTable.setHeight("400px");
		profileTable.setWidth("250px");
		profileTable.setSizeUndefined();
		profileTable.addContainerProperty("Detail", String.class, null);
		profileTable.addContainerProperty("Value", String.class, null);
profileTable.addItem(new Object[]{"Name :",""},1);
profileTable.addItem(new Object[]{"User Name :",""},2);
profileTable.addItem(new Object[]{"Password :","******"},3);
profileTable.addItem(new Object[]{"Email Id:",""},4);
profileTable.addItem(new Object[]{"Date Of Birth :",""},5);
profileTable.addItem(new Object[]{"Nationality :",""},6);
profileTable.addItem(new Object[]{"Matriculation Number :"},7);
profileLayout.addComponent(profileTable);
		return profileLayout;
	}
	private Component getPlayList() {
		  getFileListFromFolder();
		playList=new Table("PlayList");
		playList.setHeight("400px");
		playList.setWidth("300px");
		playList.setSelectable(true);
		playList.setSortEnabled(false);
		playList.setStyleName("tracklist");
		playList.setPageLength(playList.size());
		playList.addContainerProperty("Song", String.class, null);
		playList.addContainerProperty("No.", String.class, null);
		int m=0;
		for(String song:fileList){
			playList.addItem(new Object[]{song,String.valueOf(m+1)},m);
			m++;
		}
		return playList;
	}
	public HorizontalLayout getPlayer() {	
				HorizontalLayout playerContainer=new HorizontalLayout();
				playerContainer.setStyleName("playercontainer");
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
					
					setCurrentTrack(fileList[k]);
				    player.play();
					for(String songName: fileList){			
				   
				    	player.setOnEndOfMedia(new Runnable() {
				            @Override public void run() {
				               playNext();
				              }
				            });
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
	 IradioUI getAppUI() {
			return (IradioUI) UI.getCurrent();
		}

}
