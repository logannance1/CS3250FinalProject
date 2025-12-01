package chart;
import data.FinanceDatum;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Candle {
	private FinanceDatum datum;
	private Line wick;
	private Rectangle body;
	private Chart chart;
	
	public Candle(Chart chart, FinanceDatum datum) {
		this.datum = datum;
		this.chart = chart;
	}

	public FinanceDatum getDatum() {
		return datum;
	}
	
	public Line getWick() {
		return wick;
	}

	public void setDatum(FinanceDatum datum) {
		this.datum = datum;
	}
	
	/**
	 * Draws a candle based on the x position and the candle's
	 * FinancialDatum
	 * @param candle
	 * @param x
	 */
	public void draw(double space, double x,
		double scaleY)
	{
		scaleY = -scaleY;
		double width = space * .75;
		double yHigh = datum.getHigh() * scaleY;
		double yClose = datum.getClose() * scaleY;
		double yOpen = datum.getOpen() * scaleY;
		
		double yLow = datum.getLow() * scaleY;
		wick = new Line(x, yHigh, x, yLow);
		body = new Rectangle(x - width / 2, Math.min(yOpen, yClose),
			width, Math.abs(yClose - yOpen));
		
		body.hoverProperty().addListener((idk, oldVal, newVal) -> {
			if (newVal) {
				Candle.this.chart.updateInfo(datum, x, yHigh);
			}
		});
		
		Color color = datum.getClose() >= datum.getOpen() ? Color.GREEN :
			Color.RED;

		body.setFill(color);
		wick.setStroke(color);
		chart.getChildren().addAll(wick, body);
	}
	
	public void draw(double space, double x,
		double scaleY, Label date)
	{
		scaleY = -scaleY;
		double width = space * .75;
		double yHigh = datum.getHigh() * scaleY;
		double yClose = datum.getClose() * scaleY;
		double yOpen = datum.getOpen() * scaleY;
		double yLow = datum.getLow() * scaleY;
		wick = new Line(x, yHigh, x, yLow);
		body = new Rectangle(x - width / 2, Math.min(yOpen, yClose),
			width, Math.abs(yClose - yOpen));
		
		Color color = datum.getClose() >= datum.getOpen() ? Color.GREEN :
			Color.RED;

		body.setFill(color);
		wick.setStroke(color);
		
		body.hoverProperty().addListener((idk, oldVal, newVal) -> {
			if (newVal) {
				Candle.this.chart.updateInfo(datum, x, yHigh);
			}
		});
		
		Platform.runLater(() -> {
			date.setLayoutX(x - date.getWidth() / 2);
		});
		
		date.setLayoutY(yLow + 8);
		chart.getChildren().addAll(wick, body, date);
	}
}
