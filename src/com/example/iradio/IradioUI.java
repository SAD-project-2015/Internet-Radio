package com.example.iradio;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.StreamResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Audio;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("iradio")
public class IradioUI extends UI {
	

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = IradioUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);
		layout.addComponent(getPlayer());

//		Button button = new Button("Click Me");
//		button.addClickListener(new Button.ClickListener() {
//			public void buttonClick(ClickEvent event) {
//				layout.addComponent(new Label("Thank you for clicking"));
//			}
//		});
//		layout.addComponent(button);
	}
	
	public HorizontalLayout getPlayer(){
		HorizontalLayout playerContainer=new HorizontalLayout();
		Audio audio = new Audio("MyAudio");
		audio.setSource(new ThemeResource("/IRadio/sound.mp3"));
		playerContainer.addComponent(audio);
		
		StreamResource resource = new StreamResource(
			    new StreamResource.StreamSource() {

			        public InputStream getStream() {
			            Object soundId;
						byte[] data = getBinaryData(
			                    soundId, BinaryDataSource.TYPE_SOUND, 0, 0);
			            if (data == null) {
			                return null;
			            }
			            return new ByteArrayInputStream(data);
			        }
			    }, "") {
			        @Override
			        public String getMIMEType() {
			            return "audio/mp4";
			        }
			    };
		
		return playerContainer;
	}

}