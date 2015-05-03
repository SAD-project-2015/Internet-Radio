package com.example.iradio;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
@SuppressWarnings("serial")
@Theme("iradio")
public class IradioUI extends UI {
	 Navigator navigator;
	 String loggedInUser;
		HorizontalLayout layout;
    
	 

      Button loginButton,registerButton;
	
//	@WebServlet(value = "/*", asyncSupported = true)
//	@VaadinServletConfiguration(productionMode = false, ui = IradioUI.class)
//	public static class Servlet extends VaadinServlet {
//	}

	@Override
	protected void init(VaadinRequest request) {
		navigator = new Navigator(this, this);
		navigator.addView(LoginView.NAME, new LoginView(navigator,""));
		layout = new HorizontalLayout();
		layout.addComponent(getLoginButton());
		layout.addComponent(getRegisterButton());
		layout.setMargin(true);
		setContent(layout);
		
			}
    
	private Component getRegisterButton() {
		registerButton=new Button("Register");
		registerButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
		
//				navigator.navigateTo(RegisterView.NAME);		
			}
		});
		return registerButton;
	}

	private Component getLoginButton() {
		loginButton=new Button("Login");
			loginButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().addView("login", LoginView.class);
				getUI().getNavigator().navigateTo("login");
//				navigator.navigateTo(LoginView.NAME);	
			}
		});
		return loginButton;
	}

	
	 public void enter(ViewChangeEvent event) {
		 navigator.navigateTo(LoginView.NAME);	
	    }


	public void setLoggedInUser(String value) {
		
	}
	public String getLoggedInUser() {
        return loggedInUser;
    }
}