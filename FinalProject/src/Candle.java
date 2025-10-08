import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Candle {
	private FinanceData data;

	public Candle(FinanceData data) {
		this.data = data;
	}

	public FinanceData getData() {
		return data;
	}

	public void setData(FinanceData data) {
		this.data = data;
	}
	
	public void draw(CandleChart chart, double x) {
		// TODO Allow the user to adjust these values
		double width = 16;
		double scale = 64;
		double yHigh = chart.getHeight() - data.getHigh() * scale;
		double yClose = chart.getHeight() - data.getClose() * scale;
		double yOpen = chart.getHeight() - data.getOpen() * scale;
		double yLow = chart.getHeight() - data.getLow() * scale;

		Line wick = new Line(x, yHigh, x, yLow);
		Rectangle body = new Rectangle(x - width / 2, Math.min(yOpen, yClose),
			width, Math.abs(yClose - yOpen));

		Color color = data.getClose() >= data.getOpen() ? Color.GREEN : Color.RED;

		body.setFill(color);
		wick.setStroke(color);
		chart.getChildren().addAll(wick, body);
	}
}
