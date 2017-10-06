package main;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DigitalClock extends Label {
	
	public DigitalClock(Stage stage) {

		Label label = new Label();

		KeyFrame frame1 = new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				label.setText(new GregorianCalendar().get(Calendar.HOUR) + " : "
						+ new GregorianCalendar().get(Calendar.MINUTE) + " : "
						+ new GregorianCalendar().get(Calendar.SECOND));
			}
		});

		KeyFrame frame2 = new KeyFrame(Duration.seconds(1));

		Timeline timeline = new Timeline(frame1, frame2);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();

		Scene scene = new Scene(label, 200, 250);
		stage.setTitle("Time");
		stage.setScene(scene);
		stage.show();

	}
}

