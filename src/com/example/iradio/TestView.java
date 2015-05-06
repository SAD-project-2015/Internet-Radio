package com.example.iradio;

import javafx.scene.media.MediaPlayer;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class TestView extends Panel implements View {

	 public static final String NAME = "player";
		private static final long serialVersionUID = 6714096000861957459L;
			Button logoutButton;
			MediaPlayer player;
			 VerticalLayout layout;
			 IradioUI ui;

    public TestView(final Navigator navigator) {
        Layout layout = new VerticalLayout();
//        navigator.addView("", new PlayerView(navigator));	
        final TextField emailField = new TextField("Email");
        layout.addComponent(emailField);

        final PasswordField passwordField = new PasswordField("Password");
//        layout.addComponent(passwordField);

        final Button loginButton = new Button("Login", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
            	
            }
        });
        
       
        setContent(layout);
    }    

    @Override
    public void enter(ViewChangeEvent event) {

    }
}