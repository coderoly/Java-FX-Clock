package main;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class AnalogClockFunctionality {

	static SimpleIntegerProperty hour, minute;
	static SimpleIntegerProperty second;

	public void startTicking() {
		TimelineBuilder.create().cycleCount(Timeline.INDEFINITE)
				.keyFrames(new KeyFrame(Duration.seconds(1), updateTime())).build().play();

	}

	private EventHandler updateTime() {

		return new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				GregorianCalendar c = new GregorianCalendar();
				hour.set(c.get(Calendar.HOUR));
				minute.set(c.get(Calendar.MINUTE));
				second.set(c.get(Calendar.SECOND));
			}
		};
	}
	
	

}