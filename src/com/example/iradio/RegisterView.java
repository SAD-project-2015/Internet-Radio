package com.example.iradio;


import com.example.services.ServiceProvider;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author PC
 */
public class RegisterView extends CustomComponent implements View, Button.ClickListener {
	
	
	private static final long serialVersionUID = 7291111590350468701L;

	FormLayout registerLayout;

	 public static final String NAME = "register";


    private TextField emailField = new TextField("Email address");
	private PasswordField passwordField = new PasswordField("Password");
	private PasswordField retyped = new PasswordField("Retype");
	private CheckBox acceptTerms = new CheckBox("I accept terms and conditions.");
	
	private Button register = new Button("Register", this);
	
	private Container container;
	
	private FieldGroup fieldGroup = new FieldGroup();
	
	public RegisterView(final Navigator navigator) {
		registerLayout=new FormLayout();
		
		registerLayout.setStyleName("registerpage");
		registerLayout.addComponent(emailField);
		registerLayout.addComponent(passwordField);
		registerLayout.addComponent(retyped);
		registerLayout.addComponent(acceptTerms);
		registerLayout.addComponent(register);
		register.setEnabled(false);
		setCompositionRoot(registerLayout);
		
		for(Field<?> f: new Field<?>[]{this.emailField, this.passwordField, this.retyped, this.acceptTerms}) {
			f.setRequired(true);
			f.setRequiredError("This field is required.");
		}
		
		this.emailField.addValidator(new EmailValidator("Unrecognised format of email address."));
		this.passwordField.addValidator(new Validator() {
			
			@Override
			public void validate(Object value) throws InvalidValueException {
				String pass = value == null ? "" : value.toString();
				if(pass.length() < 6) throw new InvalidValueException("Password must be at least 6 characteres long.");
				if(!pass.matches(".*[0-9].*")) throw new InvalidValueException("Password must contain at least 1 digit.");
				if(!pass.matches(".*[a-zA-Z].*")) throw new InvalidValueException("Password must contain at least 1 letter.");
			}
		});

		this.retyped.addValidator(new Validator() {

			@Override
			public void validate(Object value) throws InvalidValueException {
				String retyped = value == null ? "" : value.toString();
				if(!retyped.equals(passwordField.getValue())) throw new InvalidValueException("Retyped password does not match.");
			}
			
		});
		
		this.acceptTerms.addValueChangeListener(new Property.ValueChangeListener() {
			
			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				register.setEnabled(acceptTerms.getValue());
			}
		});
		
		this.fieldGroup.bind(this.emailField, "email");
		this.fieldGroup.bind(this.passwordField, "password");
		
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		this.container = ServiceProvider.getInstance().getContainerService().getUsersContainer();
		Object newUserId = this.container.addItem();
		if(newUserId == null)
			Notification.show("Could not add user to the database.", Notification.Type.ERROR_MESSAGE);
		else {
			Item newUser = this.container.getItem(newUserId);
			this.fieldGroup.setItemDataSource(newUser);
		}
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if(this.fieldGroup.isValid()) {
			try {
				this.fieldGroup.commit();
				this.register.setEnabled(false);
				Notification.show("Your account has been created.");
				getUI().getNavigator().navigateTo(RegisterView.NAME);
			} catch (CommitException e) {
				e.printStackTrace();
				Notification.show("The data could not be saved.", "Error message: "+e.getMessage(), Notification.Type.ERROR_MESSAGE);
			}			
		}
		else Notification.show("One or more fields contain invalid values.");
	}
	
	IradioUI getAppUI() {
		return (IradioUI) UI.getCurrent();
	}
    
}
