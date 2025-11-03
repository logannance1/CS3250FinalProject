package chart;
import data.FinanceDatum;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Candle {
	private FinanceDatum datum;
	private Line wick;
	private Rectangle body;
	private double x;

	public Candle(FinanceDatum datum) {
		this.datum = datum;
	}

	public FinanceDatum getDatum() {
		return datum;
	}
	
	public double getX() {
		return x;
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
	public void draw(CandleChart chart, double space, double x, double min, double scaleY) {
		this.x = x;
		
		double width = space * .75 * 64;
		double yHigh = chart.getHeight() - datum.getHigh() * scaleY;
		double yClose = chart.getHeight() - datum.getClose() * scaleY;
		double yOpen = chart.getHeight() - datum.getOpen() * scaleY;
		double yLow = chart.getHeight() - datum.getLow() * scaleY;

		wick = new Line(x, yHigh, x, yLow);
		body = new Rectangle(x - width / 2, Math.min(yOpen, yClose),
			width, Math.abs(yClose - yOpen));
		
		Color color = datum.getClose() >= datum.getOpen() ? Color.GREEN :
			Color.RED;

		body.setFill(color);
		wick.setStroke(color);
		chart.getChildren().addAll(wick, body);
	}
}
