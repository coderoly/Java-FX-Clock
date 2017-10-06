package main;

import java.util.Collection;import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.GroupBuilder;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.RadialGradientBuilder;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.PathBuilder;
import javafx.scene.shape.PathElement;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.RotateBuilder;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AnalogClock {
	double unit = 100.0;

	@SuppressWarnings("deprecation")
	public AnalogClock(Stage stage) {

		Parent root = GroupBuilder.create()
				.children(outerRim(), hourHand(), minuteHand(), secondHand(), tickMarks(), centerPoint()).build();

		// setUpMouseForScaleAndMove(stage, root);
		Scene scene = makeATransparentScene(root);
		makeATransparentStage(stage, scene);

	}

	private Node outerRim() {

		Stop stops[] = {

				new Stop(0.8, Color.WHITE), new Stop(0.9, Color.BLACK), new Stop(0.95, Color.WHITE),
				new Stop(1.0, Color.BLACK)

		};
		double focusAngle = 0;
		double focusDistance = 0;
		double centerX = 0.5;
		double centerY = 0.5;
		double radius = 0.5;
		boolean proportional = true;
		CycleMethod cycleMethod = CycleMethod.NO_CYCLE;

		RadialGradient gradient = new RadialGradient(focusAngle, focusDistance, centerX, centerY, radius, proportional,
				cycleMethod, stops);

		Circle circle = new Circle(unit, gradient);
		circle.setCenterX(unit);
		circle.setCenterY(unit);

		return circle;
	}

	@SuppressWarnings("deprecation")
	private Node hand(double strechRelativeToRim, Color color, int startAngle) {

		return PathBuilder.create().fill(color).stroke(color.TRANSPARENT)
				.elements(new MoveTo(unit, unit), new LineTo(unit * 0.9, unit * 0.9),
						new LineTo(unit, strechRelativeToRim), new LineTo(unit * 1.1, unit * 0.9),
						new LineTo(unit, unit))
				.transforms(RotateBuilder.create().pivotX(unit).pivotY(unit).angle(startAngle).build()).build();
	}

	private Node hourHand() {
		return hand(unit * 0.4, Color.BLACK, 180);
	}

	private Node minuteHand() {
		return hand(unit * 0.2, Color.BLACK, 30);
	}

	private Node secondHand() {
	//	rotateHands().angleProperty().bind(AnalogClockFunctionality.second.multiply(360 /60 ));
		return hand(unit * 0.2, Color.BLACK, 30);
	}
	Rotate rotateHands() {
		return RotateBuilder.create()
				.pivotX(unit)
				.pivotY(unit)
				.build();
	}

	private Node tickMarks() {

		Group tickMarkGroup = new Group();
		for (int n = 0; n < 12; n++) {
			tickMarkGroup.getChildren().add(tickMark(n));
		}
		return tickMarkGroup;
	}

	private Node tickMark(int n) {
		return LineBuilder.create().startX(unit).endX(unit).startY(unit * 0.12).endY(unit * (n % 3 == 0 ? 0.3 : 0.2))
				.transforms(RotateBuilder.create().pivotX(unit).pivotY(unit).angle(360 / 12 * n).build()).strokeWidth(2)
				.build();
	}
	
	Node centerPoint() {
		return CircleBuilder.create()
				.fill(Color.BLACK)
				.radius(0.05 * unit)
				.centerX(unit)
				.centerY(unit)
				.build();
	}
	
//void setUpMouseForScaleAndMove(Stage stage, Parent root){
//	root.onMouseDraggedProperty().set(new EventHandler<Event>() {
//
//		@Override
//		public void handle(Event event) {
//			// TODO Auto-generated method stub
//			
//		}
//	});
//	root.onScrollProperty().set(new EventHandler<Event>() {
//
//		@Override
//		public void handle(Event event) {
//			// TODO Auto-generated method stub
//			
//		}
//	});
//}

private Scene makeATransparentScene(Parent root) {
	return SceneBuilder.create()
			.root(root)
			.fill(Color.TRANSPARENT)
			.build();
}

private void makeATransparentStage(Stage stage, Scene scene) {
	stage.setScene(scene);
	stage.initStyle(StageStyle.TRANSPARENT);
	stage.show();
}
}
