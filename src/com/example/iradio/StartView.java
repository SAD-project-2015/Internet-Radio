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
    HorizontalLayout topBar;
TextField emailField;
PasswordField passwordField;
final String path = "/resources";

    public StartView() {
    	
    }        

    public StartView(final Navigator navigator) {
        mainLayout = new VerticalLayout();
//        layout.setHeight("600px");
//        layout.setWidth("800px");
        mainLayout.setSizeFull();
        mainLayout.setStyleName("backgroundimage");
        
        topBar=new HorizontalLayout();
        topBar.setStyleName("topBar");
		 topBar.addComponent(new Label("This is Top Bar"));
		 mainLayout.addComponent(topBar);
		 
        FormLayout loginForm=new FormLayout();
       this.ui=getAppUI();
       navigator.addView(PlayerView.NAME,new PlayerView(navigator) );
       navigator.addView(RegisterView.NAME, new RegisterView());      
      
       
      emailField = new TextField("Email");
        loginForm.addComponent(emailField);
        emailField.setStyleName("labelblue");
        

       passwordField = new PasswordField("Password");
       passwordField.setStyleName("labelblue");
        loginForm.addComponent(passwordField);

        final Button loginButton = new Button("Login", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
            	 username = emailField.getValue();
                 password = passwordField.getValue();
            	if(username.equals("ram@gmail.com") && password.equals("rams")){
                Notification.show("Welcome " + username);

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
        loginForm.setComponentAlignment(buttonsContainer, Alignment.MIDDLE_CENTER);
        loginForm.setSpacing(true);
        mainLayout.addComponent(loginForm);
        mainLayout.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
        mainLayout.addComponent(getImageComponent());
              setCompositionRoot(mainLayout);

    }
    
    public HorizontalLayout getImageComponent(){
    	HorizontalLayout imgLayout=new HorizontalLayout();
    	FileResource resource = new FileResource(new File(this.getClass().getClassLoader().getResource(path +"/srh.jpg").toString()));

         Image image = new Image("Image from file", resource);
         imgLayout.setSizeUndefined();
         imgLayout.addComponent(image);
         imgLayout.setStyleName("backgroundimage");
    	return imgLayout;
    }

    @Override
    public void enter(ViewChangeEvent event) {
    	emailField.focus();
    }
    
   IradioUI getAppUI() {
		return (IradioUI) UI.getCurrent();
	}

}