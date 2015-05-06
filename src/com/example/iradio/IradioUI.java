package com.example.iradio;

import java.io.File;
import java.net.URL;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.MediaPlayer;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
@SuppressWarnings("serial")
@Theme("iradio")
public class IradioUI extends UI {

	private Player player;
	
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
		navigator.addView("", new StartView(navigator));		
		JFXPanel fxPanel = new JFXPanel();
			}
		
	 public void enter(ViewChangeEvent event) {
         user.focus();
     }


	public void setLoggedInUser(String username) {
		// TODO Auto-generated method stub
			}     
	
Navigator getNavigatorManager(){
	return navigator;
}

public void displayPlayer() {
player.showPlayer();	
}

public Object getLoggedInUser() {
	// TODO Auto-generated method stub
	return null;
}
    }