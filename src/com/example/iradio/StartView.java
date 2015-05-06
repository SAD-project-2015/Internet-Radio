package com.example.iradio;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class StartView extends Panel implements View {
	
	private Player player;

    public static final String NAME = "login";

    String username;
    String password;
IradioUI ui;

    public StartView() {
    	
    }        

    public StartView(final Navigator navigator) {
        VerticalLayout layout = new VerticalLayout();
        FormLayout loginForm=new FormLayout();
       this.ui=getAppUI();
//       player=new Player(navigator);
       navigator.addView(Player.NAME,new Player(navigator) );
       
        final TextField emailField = new TextField("Email");
        loginForm.addComponent(emailField);

        final PasswordField passwordField = new PasswordField("Password");
        loginForm.addComponent(passwordField);

        final Button loginButton = new Button("Login", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
            	 username = emailField.getValue();
                 password = passwordField.getValue();
            	if(username.equals("ram@gmail.com") && password.equals("rams")){
                Notification.show("Welcome " + username);

                ((IradioUI)UI.getCurrent()).setLoggedInUser(username);
//navigator.addView(Player.NAME,new Player(navigator) );
//                getAppUI().displayPlayer();
                getUI().getNavigator().navigateTo(Player.NAME);
            	}
            }
        });
        
        final Button registerButton = new Button("Register", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
//            	 navigator.navigateTo(RegisterView.NAME);
            	
            }
        });   
        
        final Button cancelButton = new Button("Cancel", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
            	
            }
        });    
        
        HorizontalLayout buttonsContainer=new HorizontalLayout();
        buttonsContainer.addComponent(loginButton);
        buttonsContainer.addComponent(cancelButton);
        buttonsContainer.addComponent(registerButton);
        buttonsContainer.setComponentAlignment(registerButton, Alignment.BOTTOM_CENTER);
        buttonsContainer.setSpacing(true);
        loginForm.addComponent(buttonsContainer);
        loginForm.setSpacing(true);
        layout.addComponent(loginForm);
        layout.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
        setContent(layout);

    }

    @Override
    public void enter(ViewChangeEvent event) {

    }
    
   IradioUI getAppUI() {
		return (IradioUI) UI.getCurrent();
	}

}