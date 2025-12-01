package chart;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import data.FinanceDatum;
import data.TimeFrame;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import misc.Indicator;

public class Chart extends Group {
	private TimeFrame timeFrame;
	private ArrayList<Indicator> indicators = new ArrayList<Indicator>();
	private VBox info;
	protected double scale = 64;
	/**
	 * Creates a blank chart
	 */
	public Chart() {
		this.setStyle("-fx-background-color: #f00");
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
	
	public VBox getInfo() {
		return info;
	}
	
	public void updateInfo(FinanceDatum datum, double x, double y) {
		this.getChildren().remove(info);
		info = new VBox();
		info.setPadding(new Insets(4));
		info.setLayoutX(x);
		info.setLayoutY(y - 144);
		info.setStyle("-fx-background-color: #00f8;" +
			"-fx-background-radius: 8");
		
		info.setMouseTransparent(true);
		SimpleDateFormat sdf = new SimpleDateFormat("EEE-MMM-yyyy");
		Label date = new Label(sdf.format(datum.getDate()));
		date.setStyle("-fx-text-fill: #eee;" +
			"-fx-font-weight: bold");
		
		Label high = new Label("High: $" + datum.getHigh());
		high.setStyle("-fx-text-fill: #eee");
		
		Label low = new Label("Low: $" + datum.getLow());
		low.setStyle("-fx-text-fill: #eee");
		
		Label open = new Label("Open: $" + datum.getOpen());
		open.setStyle("-fx-text-fill: #eee");
		
		Label close = new Label("Close: $" + datum.getClose());
		close.setStyle("-fx-text-fill: #eee");
		
		info.getChildren().addAll(new StackPane(date), open, close, high, low);
		this.getChildren().add(info);
	}
	
	public boolean removeIndicator(Indicator indicator) {
		return this.indicators.remove(indicator);
	}
	
	public void incrementScale(double delta) {
		if (delta == 0) {
			return;
		}
		
		var newScale = scale + delta;
		
		if (newScale > 0) {
			scale = newScale;
			draw();
		}
	}
}
