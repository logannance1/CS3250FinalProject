import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Candle {
	private FinanceDatum datum;
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

	public void setDatum(FinanceDatum datum) {
		this.datum = datum;
	}
	
	/**
	 * Draws a candle based on the x position and the candle's
	 * FinancialDatum
	 * @param candle
	 * @param x
	 */
	public void draw(CandleChart chart, double space, double x) {
		// TODO Allow the user to adjust these values
		this.x = x;
		double width = space * 0.75;
		double scale = 64;
		double yHigh = chart.getHeight() - datum.getHigh() * scale;
		double yClose = chart.getHeight() - datum.getClose() * scale;
		double yOpen = chart.getHeight() - datum.getOpen() * scale;
		double yLow = chart.getHeight() - datum.getLow() * scale;

		Line wick = new Line(x, yHigh, x, yLow);
		Rectangle body = new Rectangle(x - width / 2, Math.min(yOpen, yClose),
			width, Math.abs(yClose - yOpen));
		Color color = datum.getClose() >= datum.getOpen() ? Color.GREEN :
			Color.RED;

		body.setFill(color);
		wick.setStroke(color);
		chart.getChildren().addAll(wick, body);
	}
}
