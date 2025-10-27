package chart;
import java.util.ArrayList;

import data.TimeFrame;
import javafx.scene.layout.Pane;
import misc.Indicator;

public class Chart extends Pane {
	private TimeFrame timeFrame;
	private ArrayList<Indicator> indicators = new ArrayList<Indicator>();
	protected XAxis xAxis;
	protected YAxis yAxis;
	
	/**
	 * Creates a blank chart
	 */
	public Chart(XAxis xAxis, YAxis yAxis) {
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.setStyle("-fx-background-color: #fff");
	}
	
	public TimeFrame getTimeFrame() {
		return timeFrame;
	}
	
	public void draw() {
		this.getChildren().clear();
	}

	public void setTimeFrame(TimeFrame timeFrame) {
		this.timeFrame = timeFrame;
		xAxis.getChildren().clear();
		yAxis.getChildren().clear();
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
