import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class CandlestickChart extends Chart {
	private ArrayList<Candlestick> candlesticks;

	public CandlestickChart() {
		super();
	}
	
	/**
	 * Draws candles based on the timeFrame, eventually will have markings to
	 * indicate the specific price, and allow for scrolling
	 * @param timeFrame
	 */
	public CandlestickChart(TimeFrame timeFrame) {
		super(timeFrame);
		candlesticks = new ArrayList<Candlestick>(timeFrame.getData().size());
		
		for (int i = 0; i < timeFrame.getData().size(); ++i) {
			candlesticks.add(new Candlestick(timeFrame.getData().get(i)));
		}
		
		drawCandles();
	}
	
	/**
	 * Updates the timeFrame and redraws candles
	 */
	@Override
	public void setTimeFrame(TimeFrame timeFrame) {
		super.setTimeFrame(timeFrame);
		candlesticks = new ArrayList<Candlestick>(timeFrame.getData().size());
		
		for (int i = 0; i < timeFrame.getData().size(); ++i) {
			candlesticks.add(new Candlestick(timeFrame.getData().get(i)));
		}
		
		drawCandles();
	}
	
	/**
	 * Simple loop to draw each candle
	 */
	private void drawCandles() {
		this.getChildren().clear();
		for (int i = 0; i < candlesticks.size(); ++i) {
			drawCandle(candlesticks.get(i), (i + 1) * 20);
		}
	}
	
	/**
	 * Draws a candle based on the x position and the candle's
	 * FinancialData
	 * @param candle
	 * @param x
	 */
	private void drawCandle(Candlestick candle, double x) {
		FinanceData fd = candle.getData();
		// TODO Allow the user to adjust these values
		double width = 16;
		double scale = 64;
		
		double yHigh = height - fd.getHigh() * scale;
		double yClose = height - fd.getClose() * scale;
		double yOpen = height - fd.getOpen() * scale;
		double yLow = height - fd.getLow() * scale;
		
		Line wick = new Line(x, yHigh, x, yLow);
		Rectangle body = new Rectangle(x - width / 2, Math.min(yOpen, yClose),
			width, Math.abs(yClose - yOpen));
		
		Color color = fd.getClose() >= fd.getOpen() ? Color.GREEN : Color.RED;
		body.setFill(color);
		wick.setStroke(color);
		this.getChildren().addAll(wick, body);
	}
}
