package com.example.iradio;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
@SuppressWarnings("serial")
//@Theme("iradio")
@PreserveOnRefresh
public class IradioUI extends UI {
	protected static final String MAINVIEW = "main";
	
	
	    Navigator navigator;

	    String loggedInUser;
	    
	    public IradioUI(){
	    	
	    }

	    @Override
	    public void init(VaadinRequest request) {
	    	navigator = new Navigator(this, this);

	        // Add some Views
	        // no fragment for main view
	        navigator.addView("",
	                new LoginView(navigator,"hi"));
	        // Create Navigator, make it control the ViewDisplay
	    	HorizontalLayout hl=new HorizontalLayout();
	    	hl.setSizeFull();
	    	Button b=new Button("Go to login Page");
	    	hl.addComponent(b);
	    	b.addClickListener(new Button.ClickListener() {
				public void buttonClick(ClickEvent event) {
			
					navigator.navigateTo(LoginView.NAME);		
				}
			});
	        

	        // #settings
//	        navigator.addView(RegisterView.NAME, new RegisterView());

	        // #count will be a new instance each time we navigate to it, counts:
	        /*
	         * Commented away from other example // no fragment for main view
	         * navigator.addView(MainView.NAME, new MainView(navigator));
	         * 
	         * navigator.addView(CountView.NAME, CountView.class);
	         * 
	         * // #message adds a label with whatever it receives as a parameter
	         * navigator.addView(MessageView.NAME, new MessageView());
	         * 
	         * // #secret works as #message, but you need to be logged in
	         * navigator.addView(SecretView.NAME, new SecretView());
	         * 
	         * // #login will navigate to the main view if invoked via this
	         * mechanism navigator.addView(LoginView.NAME, new LoginView(navigator,
	         * MainView.NAME));
	         * 
	         * // we'll handle permissions with a listener here, you could also do
	         * // that in the View itself. navigator.addViewChangeListener(new
	         * ViewChangeListener() {
	         * 
	         * @Override public boolean beforeViewChange(ViewChangeEvent event) { if
	         * (((NavigationtestUI)UI.getCurrent()).getLoggedInUser() == null) { //
	         * Show to LoginView instead, pass intended view String
	         * fragmentAndParameters = event.getViewName(); if
	         * (event.getParameters() != null) { fragmentAndParameters += "/";
	         * fragmentAndParameters += event.getParameters(); }
	         * navigator.getDisplay().showView(new LoginView(navigator,
	         * fragmentAndParameters)); return false;
	         * 
	         * } else { return true; } }
	         * 
	         * @Override public void afterViewChange(ViewChangeEvent event) {
	         * 
	         * } });
	         */
	        // react to initial fragment, received before we created the Navigator

	        // This was removed in beta10
	        // navigator.navigate();
	    }

	    public String getLoggedInUser() {
	        return loggedInUser;
	    }

	    public void setLoggedInUser(String user) {
	        loggedInUser = user;
	    }
	}