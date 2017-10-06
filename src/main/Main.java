package main;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	public Stage stage;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {
		
		this.stage=stage;

//		new DigitalClock(stage);
		new AnalogClock(stage);

	}

}
