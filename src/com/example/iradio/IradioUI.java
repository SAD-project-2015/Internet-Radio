package com.example.iradio;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
@SuppressWarnings("serial")
@Theme("mytheme")
public class IradioUI extends UI {

	private PlayerView player;
	
    String user; 
    Navigator navigator;
    
      protected static final String MAINVIEW = "main";
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = IradioUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
				navigator = new Navigator(this,this);
		navigator.addView("", new StartView(navigator));
			}
		
	public void setLoggedInUser(String username) {
this.user=username;		
	}     
	
Navigator getNavigatorManager(){
	return navigator;
}

public void displayPlayer() {
player.showPlayer();	
}

public Object getLoggedInUser() {
	return user;
}
    }