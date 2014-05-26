package com.my.app;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.jdesktop.application.Application;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.my.app.components.SearchFrame;

@Configurable(preConstruction=true)
public class Main extends Application {

	public static void main(String[] args) {
		try {
			new ClassPathXmlApplicationContext("classpath:/META-INF/spring/applicationContext*.xml"
					);
		} catch (BeansException be) {
			be.printStackTrace();
			System.exit(1);
		}

		Application.launch(Main.class, args);

		final JFrame frame = new SearchFrame();
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				frame.dispose();
				System.exit(0);
			}
		});
		frame.setVisible(true);
	}

	@Override
	protected void startup() {
		// TODO Auto-generated method stub
		
	}
}
