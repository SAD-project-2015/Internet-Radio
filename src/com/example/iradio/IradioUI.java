package com.example.iradio;

import java.io.File;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
@SuppressWarnings("serial")
@Theme("iradio")
public class IradioUI extends UI {
	
	MediaPlayer player;
	VerticalLayout layout;
   TextField user;
    PasswordField    password ;
	 

      Button loginButton;
      Navigator navigator;
      protected static final String MAINVIEW = "main";
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = IradioUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		
		layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);
		navigator = new Navigator(this,this);
		navigator.addView("", new StartView(navigator,"hi"));
		JFXPanel fxPanel = new JFXPanel();
			layout.addComponent(getPlayer());	
//			layout.addComponent(getLoginPanel());
//		layout.setComponentAlignment(getLoginPanel(), Alignment.TOP_IGHT);
//			navigator.navigateTo(SimpleLoginMainView.NAME);
			}
	
	private Component getLoginPanel() {
		Panel loginPanel=new Panel();
		loginPanel.setWidth("300px");  
		   
        user = new TextField("User:");
        user.setWidth("300px");
        user.setRequired(true);
        user.setInputPrompt("Your username (eg. joe@email.com)");
        user.setInvalidAllowed(false);

        password = new PasswordField("Password:");
        password.setWidth("300px");      
        password.setRequired(true);
        password.setValue("");
        password.setNullRepresentation("");

        // Create login button
        loginButton = new Button("Login");

        // Add both to a panel
        VerticalLayout fields = new VerticalLayout(user, password, loginButton);
        fields.setCaption("Please login to access the application. (test@test.com/passw0rd)");
        fields.setSpacing(true);
        fields.setSizeUndefined();          
        loginPanel.setContent(fields);
		return loginPanel;
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
			public void buttonClick(ClickEvent event) {

				String fileName="C:/Users/Ram/Desktop/SAD/sound1.mp3";
				String uriString = new File(fileName).toURI().toString();
				player = new MediaPlayer(new Media(uriString));
				player.play(); 
				
				}
		});
		pauseButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
//				player.pause();
				navigator.navigateTo(StartView.NAME);
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
	playerContainer.addComponent(getLoginPanel());
//	playerContainer.setComponentAlignment(getLoginPanel(), Alignment.TOP_RIGHT);
	return playerContainer;
	}
	 public void enter(ViewChangeEvent event) {
         // focus the username field when user arrives to the login view
         user.focus();
     }

     
     

     public void buttonClick(ClickEvent event) {

             if (!user.isValid() || !password.isValid()) {
             return;
         }

         String username = user.getValue();
         String password = this.password.getValue();

         //
         // Validate username and password with database here. For examples sake
         // I use a dummy username and password.
         //
         boolean isValid = username.equals("test@test.com")
                 && password.equals("passw0rd");

         if (isValid) {

             // Store the current user in the service session
             getSession().setAttribute("user", username);

             // Navigate to main view
//             getUI().getNavigator().navigateTo(SimpleLoginMainView.NAME);//

         } else {

             // Wrong password clear the password field and refocuses it
             this.password.setValue(null);
             this.password.focus();

         }
	
}
}