import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class CandleChart extends Chart {
	private ArrayList<Candle> candles;

	public CandleChart() {
		super();
	}
	
	/**
	 * Draws candles based on the timeFrame, eventually will have markings to
	 * indicate the specific price, and allow for scrolling
	 * @param timeFrame
	 */
	public CandleChart(TimeFrame timeFrame) {
		super(timeFrame);
		candles = new ArrayList<Candle>(timeFrame.getData().size());
		
		for (int i = 0; i < timeFrame.getData().size(); ++i) {
			candles.add(new Candle(timeFrame.getData().get(i)));
		}
		
		draw();
	}
	
	/**
	 * Updates the timeFrame and redraws candles
	 */
	@Override
	public void setTimeFrame(TimeFrame timeFrame) {
		super.setTimeFrame(timeFrame);
		candles = new ArrayList<Candle>(timeFrame.getData().size());
		
		for (int i = 0; i < timeFrame.getData().size(); ++i) {
			candles.add(new Candle(timeFrame.getData().get(i)));
		}
		
		draw();
	}
	
	/**
	 * Simple loop to draw each candle
	 */
	@Override
	public void draw() {
		super.draw();
		
		for (int i = 0; i < candles.size(); ++i) {
			Candle candle = candles.get(i);
			candle.draw(this, (i + 1) * 20);
		}
	}
	
	/**
	 * Draws a candle based on the x position and the candle's
	 * FinancialData
	 * @param candle
	 * @param x
	 */
}
