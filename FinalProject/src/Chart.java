import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Chart extends Pane {
	private TimeFrame timeFrame;
	private ArrayList<Indicator> indicators = new ArrayList<Indicator>();
	// TODO Update these when the window size changes
	private int width = 800, height = 400;
	
	/**
	 * Creates a blank chart
	 */
	public Chart() {
		// TODO Adjust width and height based on window size
		this.setPrefSize(width, height);
		this.setBackground(new Background(new BackgroundFill(
			Color.WHITE, null, null)));
	}
	
	/**
	 * Creates a chart with a TimeFrame
	 * @param timeFrame
	 */
	public Chart(TimeFrame timeFrame) {
		this();
		setTimeFrame(timeFrame);
	}
	
	public TimeFrame getTimeFrame() {
		return timeFrame;
	}
	
	public void draw() {
		this.getChildren().clear();
	}

	public void setTimeFrame(TimeFrame timeFrame) {
		this.timeFrame = timeFrame;
	}

	public ArrayList<Indicator> getIndicators() {
		return indicators;
	}
	
	public void addIndicator(Indicator indicator) {
		this.indicators.add(indicator);
	}
	
	public boolean removeIndicator(Indicator indicator) {
		return this.indicators.remove(indicator);
	}
}
