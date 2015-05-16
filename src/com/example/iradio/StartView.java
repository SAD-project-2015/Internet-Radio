package com.example.iradio;

import java.io.File;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class StartView extends CustomComponent implements View {
	
	private PlayerView player;

    public static final String NAME = "login";

    String username;
    String password;
    IradioUI ui;
    VerticalLayout mainLayout;
TextField emailField;
PasswordField passwordField;
final String path = "/resources";

   
    public StartView(final Navigator navigator) {
        mainLayout = new VerticalLayout();
        mainLayout.setStyleName("backgroundimage");
        
        FormLayout loginForm=new FormLayout();
       this.ui=getAppUI();
       navigator.addView(PlayerView.NAME,new PlayerView(navigator) );
       navigator.addView(RegisterView.NAME, new RegisterView(navigator));
      
       
      emailField = new TextField("Email");
        loginForm.addComponent(emailField);
        emailField.setStyleName("labelred");
        

       passwordField = new PasswordField("Password");
       passwordField.setStyleName("labelred");
        loginForm.addComponent(passwordField);

        final Button loginButton = new Button("Login", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
            	 username = emailField.getValue();
                 password = passwordField.getValue();
            	if(username.equals("test@test.com") && password.equals("test12345")){
                Notification.show("Welcome " + username);
                getAppUI().setLoggedInUser(username);
                ((IradioUI)UI.getCurrent()).setLoggedInUser(username);
                getUI().getNavigator().navigateTo(PlayerView.NAME);
                
            	}
            }
        });
        
        final Button registerButton = new Button("Register", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
            	 navigator.navigateTo(RegisterView.NAME);
            	
            }
        }); 
        
        final Button guestButton = new Button("Continue As Guest", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
            	 navigator.navigateTo(PlayerView.NAME);
            	
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
        Label note=new Label("Login with Username:test@test.com and Password:test12345 to use the application");
        note.setStyleName("labelwhite");
        buttonsContainer.addComponent(note);
//        buttonsContainer.addComponent(registerButton);
//        buttonsContainer.addComponent(guestButton);
//        buttonsContainer.setComponentAlignment(registerButton, Alignment.BOTTOM_CENTER);
        buttonsContainer.setSpacing(true);
        loginForm.addComponent(buttonsContainer);
        loginForm.setComponentAlignment(buttonsContainer, Alignment.MIDDLE_CENTER);
        loginForm.setSpacing(true);
        mainLayout.addComponent(loginForm);
        mainLayout.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
        buttonsContainer.addComponent(registerButton);
        buttonsContainer.addComponent(new Label("OR"));
        buttonsContainer.addComponent(guestButton);
        buttonsContainer.setComponentAlignment(registerButton, Alignment.BOTTOM_RIGHT);
              setCompositionRoot(mainLayout);

    }
    
    @Override
    public void enter(ViewChangeEvent event) {
    	emailField.focus();
    }
    
   IradioUI getAppUI() {
		return (IradioUI) UI.getCurrent();
	}

}