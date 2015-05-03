package com.example.iradio;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class LoginView extends Panel implements View {

  
	private static final long serialVersionUID = 9000896655313937631L;

	public static final String NAME = "login";
    
    TextField user;
    PasswordField    password ;
    Button loginButton,registerButton;
    Navigator navigator;

    public LoginView(final Navigator navigator,
            final String fragmentAndParameters) {
        Layout layout = new VerticalLayout();

        final TextField email = new TextField("Email");
        layout.addComponent(email);

        final PasswordField password = new PasswordField("Password");
        layout.addComponent(password);

        final Button login = new Button("Login", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {

                ((IradioUI) UI.getCurrent()).setLoggedInUser(email
                        .getValue());

                // navigate back to the intended place
//                navigator.navigateTo(fragmentAndParameters);
            }
        });
        layout.addComponent(login);
        setContent(layout);

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
        loginButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
		
				navigator.navigateTo(PlayerView.NAME);		
			}
		});

        // Add both to a panel
        VerticalLayout fields = new VerticalLayout(user, password, loginButton);
        fields.setCaption("Please login to access the application. (test@test.com/passw0rd)");
        fields.setSpacing(true);
        fields.setSizeUndefined();          
        loginPanel.setContent(fields);
		return loginPanel;
	}

    @Override
    public void enter(ViewChangeEvent event) {
        // TODO Auto-generated method stub

    }
}
